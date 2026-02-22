package com.akexorcist.adaptivescrolling.compose

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * A container that scrolls its content vertically when the content's height exceeds the container's height.
 * The layout will not be scrollable if the content's height is less than or equal to the container's height.
 *
 * This is useful for creating layouts that adapt to different content sizes, avoiding unnecessary scrolling
 * when the content fits within the available space.
 *
 * @param modifier The modifier to be applied to the layout.
 * @param scrollState The state of the scroll.
 * @param scrollEnabled Controls whether scrolling is enabled.
 * @param flingBehavior The fling behavior to be used for scrolling.
 * @param reverseScrolling `true` to reverse the direction of scrolling and layout, when `false` items will be composed from top to bottom and `scrollState` will increase when scrolling down.
 * @param verticalArrangement The vertical arrangement of the layout's children.
 * @param horizontalAlignment The horizontal alignment of the layout's children.
 * @param content The content of the layout. The `isScrollable` parameter is a boolean that indicates whether the content is scrollable or not.
 */
@Composable
fun AdaptiveVerticalScrollContainer(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    scrollEnabled: Boolean = true,
    flingBehavior: FlingBehavior? = null,
    reverseScrolling: Boolean = false,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.(isScrollable: Boolean) -> Unit,
) {
    val isScrollable by remember {
        derivedStateOf { scrollState.maxValue > 0 }
    }
    Column(
        modifier = modifier
            .verticalScroll(
                state = scrollState,
                enabled = true,
                flingBehavior = flingBehavior,
                reverseScrolling = reverseScrolling,
            ),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
    ) {
        content(isScrollable)
    }
}
