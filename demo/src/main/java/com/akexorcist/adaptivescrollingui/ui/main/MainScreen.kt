package com.akexorcist.adaptivescrollingui.ui.main

import android.content.Intent
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.akexorcist.adaptivescrolling.compose.AdaptiveVerticalScrollContainer
import com.akexorcist.adaptivescrollingui.ui.compose.ComposeSimpleActivity
import com.akexorcist.adaptivescrollingui.ui.compose.ComposeStickyFooterActivity
import com.akexorcist.adaptivescrollingui.ui.compose.ComposeVerticalWeightActivity
import com.akexorcist.adaptivescrollingui.ui.theme.AdaptiveScrollingUiTheme
import com.akexorcist.adaptivescrollingui.ui.view.ViewSimpleActivity
import com.akexorcist.adaptivescrollingui.ui.view.ViewStickyFooterActivity
import com.akexorcist.adaptivescrollingui.ui.view.ViewVerticalWeightActivity

private val buttonWidth = 200.dp

@Composable
fun MainRoute() {
    val activity = LocalActivity.current
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            MainScreen(
                navigateToComposeSimple = {
                    activity?.startActivity(Intent(activity, ComposeSimpleActivity::class.java))
                },
                navigateToComposeVerticalWeight = {
                    activity?.startActivity(Intent(activity, ComposeVerticalWeightActivity::class.java))
                },
                navigateToComposeStickyFooter = {
                    activity?.startActivity(Intent(activity, ComposeStickyFooterActivity::class.java))
                },
                navigateToViewSimple = {
                    activity?.startActivity(Intent(activity, ViewSimpleActivity::class.java))
                },
                navigateToViewVerticalWeight = {
                    activity?.startActivity(Intent(activity, ViewVerticalWeightActivity::class.java))
                },
                navigateToViewStickyFooter = {
                    activity?.startActivity(Intent(activity, ViewStickyFooterActivity::class.java))
                },
            )
        }
    }
}

@Composable
fun MainScreen(
    navigateToComposeSimple: () -> Unit,
    navigateToComposeVerticalWeight: () -> Unit,
    navigateToComposeStickyFooter: () -> Unit,
    navigateToViewSimple: () -> Unit,
    navigateToViewVerticalWeight: () -> Unit,
    navigateToViewStickyFooter: () -> Unit,
) {
    AdaptiveVerticalScrollContainer(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 32.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Compose",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.width(buttonWidth),
                onClick = navigateToComposeSimple,
            ) {
                Text("Simple")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.width(buttonWidth),
                onClick = navigateToComposeVerticalWeight,
            ) {
                Text("With Vertical Weight")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.width(buttonWidth),
                onClick = navigateToComposeStickyFooter,
            ) {
                Text("With Sticky Footer")
            }

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "View",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.width(buttonWidth),
                onClick = navigateToViewSimple,
            ) {
                Text("Simple")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.width(buttonWidth),
                onClick = navigateToViewVerticalWeight,
            ) {
                Text("With Vertical Weight")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.width(buttonWidth),
                onClick = navigateToViewStickyFooter,
            ) {
                Text("With Sticky Footer")
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
    AdaptiveScrollingUiTheme {
        MainScreen(
            navigateToComposeSimple = {},
            navigateToComposeVerticalWeight = {},
            navigateToComposeStickyFooter = {},
            navigateToViewSimple = {},
            navigateToViewVerticalWeight = {},
            navigateToViewStickyFooter = {},
        )
    }
}
