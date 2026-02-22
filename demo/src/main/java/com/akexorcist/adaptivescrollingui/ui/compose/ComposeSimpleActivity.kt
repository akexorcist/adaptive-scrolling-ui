package com.akexorcist.adaptivescrollingui.ui.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akexorcist.adaptivescrolling.compose.AdaptiveVerticalScrollContainer
import com.akexorcist.adaptivescrollingui.R
import com.akexorcist.adaptivescrollingui.ui.theme.AdaptiveScrollingUiTheme

class ComposeSimpleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdaptiveScrollingUiTheme {
                SimpleRoute()
            }
        }
    }
}

@Composable
private fun SimpleRoute() {
    val dispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    Scaffold { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            SimpleScreen(
                onBackPressed = {
                    dispatcher?.onBackPressed()
                },
            )
        }
    }
}

@Composable
private fun SimpleScreen(
    onBackPressed: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        AdaptiveVerticalScrollContainer(
            modifier = Modifier.fillMaxSize(),
        ) { _ ->
            Spacer(modifier = Modifier.height(32.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(48.dp))
                Text(
                    text = stringResource(R.string.simple_title),
                    fontSize = 24.sp,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(24.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = {},
                    label = { Text(stringResource(R.string.simple_email_hint)) },
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = "",
                    onValueChange = {},
                    label = { Text(stringResource(R.string.simple_password_hint)) },
                    visualTransformation = PasswordVisualTransformation(),
                )
                Spacer(modifier = Modifier.height(2.dp))
                TextButton(
                    modifier = Modifier.align(Alignment.End),
                    onClick = { },
                ) {
                    Text(text = stringResource(R.string.simple_forget_password))
                }
            }
            Spacer(modifier = Modifier.height(32.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(50),
                    onClick = { },
                ) {
                    Text(text = stringResource(R.string.simple_sign_up))
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { },
                ) {
                    Text(text = stringResource(R.string.simple_login))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
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

@Preview
@Composable
private fun ComposeSimpleContentPreview() {
    AdaptiveScrollingUiTheme {
        SimpleScreen(onBackPressed = {})
    }
}
