package dev.akexorcist.adaptivescrollingui.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dev.akexorcist.adaptivescrollingui.ui.theme.AdaptiveScrollingUiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdaptiveScrollingUiTheme {
                MainRoute()
            }
        }
    }
}
