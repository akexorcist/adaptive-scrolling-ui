package com.akexorcist.adaptivescrollingui.ui.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akexorcist.adaptivescrolling.compose.AdaptiveScrollContainer
import com.akexorcist.adaptivescrollingui.R
import com.akexorcist.adaptivescrollingui.ui.theme.AdaptiveScrollingUiTheme

class ComposeVerticalWeightActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdaptiveScrollingUiTheme {
                VerticalWeightRoute()
            }
        }
    }
}

@Composable
private fun VerticalWeightRoute() {
    val dispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    Scaffold { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            VerticalWeightScreen(
                onBackPressed = {
                    dispatcher?.onBackPressed()
                },
            )
        }
    }
}

@Composable
private fun VerticalWeightScreen(
    onBackPressed: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        AdaptiveScrollContainer(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) { isScrollable ->
            Spacer(
                modifier = if (isScrollable) Modifier.height(48.dp)
                else Modifier.weight(1f)
            )
            Text(
                text = stringResource(R.string.vertical_weight_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row {
                PinDot(isActive = false)
                Spacer(modifier = Modifier.width(16.dp))
                PinDot(isActive = false)
                Spacer(modifier = Modifier.width(16.dp))
                PinDot(isActive = false)
                Spacer(modifier = Modifier.width(16.dp))
                PinDot(isActive = false)
            }
            Spacer(
                modifier = if (isScrollable) Modifier.height(36.dp)
                else Modifier.weight(1f)
            )
            Keypad()
            Spacer(
                modifier = if (isScrollable) Modifier.height(36.dp)
                else Modifier.weight(1f)
            )
        }

        Box(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        ) {
            FilledIconButton(
                modifier = Modifier.size(40.dp),
                onClick = onBackPressed,
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.ic_back),
                    contentDescription = stringResource(R.string.content_description_back_button),
                )
            }
        }
    }
}

@Suppress("SameParameterValue")
@Composable
private fun PinDot(isActive: Boolean) {
    val color = MaterialTheme.colorScheme.onSurface
    Box(
        modifier = Modifier
            .size(16.dp)
            .then(
                if (isActive) {
                    Modifier.background(
                        color = color,
                        shape = CircleShape,
                    )
                } else {
                    Modifier.border(
                        width = 2.dp,
                        color = color,
                        shape = CircleShape,
                    )
                }
            )
    )
}

@Composable
private fun Keypad() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            KeypadButton(text = "1")
            Spacer(modifier = Modifier.width(16.dp))
            KeypadButton(text = "2")
            Spacer(modifier = Modifier.width(16.dp))
            KeypadButton(text = "3")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            KeypadButton(text = "4")
            Spacer(modifier = Modifier.width(16.dp))
            KeypadButton(text = "5")
            Spacer(modifier = Modifier.width(16.dp))
            KeypadButton(text = "6")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            KeypadButton(text = "7")
            Spacer(modifier = Modifier.width(16.dp))
            KeypadButton(text = "8")
            Spacer(modifier = Modifier.width(16.dp))
            KeypadButton(text = "9")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.Center) {
            Spacer(modifier = Modifier.size(72.dp))
            Spacer(modifier = Modifier.width(16.dp))
            KeypadButton(text = "0")
            Spacer(modifier = Modifier.width(16.dp))
            KeypadButton(
                text = stringResource(R.string.vertical_weight_delete),
                textSize = 20,
            )
        }
    }
}

@Composable
private fun KeypadButton(
    text: String,
    textSize: Int = 32,
) {
    OutlinedButton(
        modifier = Modifier.size(72.dp),
        contentPadding = PaddingValues(0.dp),
        onClick = { }
    ) {
        Text(
            text = text,
            fontSize = textSize.sp,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Preview
@Composable
private fun VerticalWeightContentPreview() {
    AdaptiveScrollingUiTheme {
        VerticalWeightScreen(onBackPressed = { })
    }
}
