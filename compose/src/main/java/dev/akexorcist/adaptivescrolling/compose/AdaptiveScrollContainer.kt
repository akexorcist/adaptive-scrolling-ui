package dev.akexorcist.adaptivescrolling.compose

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.Constraints

@Composable
fun AdaptiveScrollContainer(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    scrollEnabled: Boolean = true,
    flingBehavior: FlingBehavior? = null,
    reverseScrolling: Boolean = false,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.(isScrollable: Boolean) -> Unit,
) {
    SubcomposeLayout(modifier = modifier) { constraints ->
        val contentConstraints = constraints.copy(maxHeight = Constraints.Infinity)
        val nonScrollableContent = subcompose("content") { Column { content(false) } }[0].measure(contentConstraints)
        val contentHeight = nonScrollableContent.height
        val isScrollable = contentHeight > constraints.maxHeight
        val finalContentPlaceable = subcompose("finalContent") {
            val scrollModifier = if (isScrollable && scrollEnabled) {
                Modifier.verticalScroll(
                    state = scrollState,
                    enabled = true,
                    flingBehavior = flingBehavior,
                    reverseScrolling = reverseScrolling,
                )
            } else {
                Modifier
            }
            Column(
                modifier = scrollModifier,
                verticalArrangement = verticalArrangement,
                horizontalAlignment = horizontalAlignment,
            ) {
                content(isScrollable)
            }
        }[0].measure(constraints)
        layout(finalContentPlaceable.width, finalContentPlaceable.height) {
            finalContentPlaceable.place(0, 0)
        }
    }
}
