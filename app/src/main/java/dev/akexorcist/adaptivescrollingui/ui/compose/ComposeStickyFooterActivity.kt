package dev.akexorcist.adaptivescrollingui.ui.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.akexorcist.adaptivescrolling.compose.AdaptiveScrollContainer
import dev.akexorcist.adaptivescrollingui.R
import dev.akexorcist.adaptivescrollingui.ui.theme.AdaptiveScrollingUiTheme

class ComposeStickyFooterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdaptiveScrollingUiTheme {
                StickyFooterRoute()
            }
        }
    }
}

@Composable
private fun StickyFooterRoute() {
    val dispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    Scaffold { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            StickyFooterScreen(
                onBackPressed = {
                    dispatcher?.onBackPressed()
                },
            )
        }
    }
}

@Composable
private fun StickyFooterScreen(
    onBackPressed: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        AdaptiveScrollContainer(
            modifier = Modifier.fillMaxSize()
        ) { _ ->
            Spacer(modifier = Modifier.height(32.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(48.dp))
                Image(
                    modifier = Modifier.size(120.dp),
                    painter = painterResource(R.drawable.ic_profile),
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.sticky_footer_username),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.sticky_footer_email),
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = stringResource(R.string.sticky_footer_short_description),
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .align(Alignment.CenterHorizontally),
            ) {
                Info(
                    icon = R.drawable.ic_location,
                    text = stringResource(R.string.sticky_footer_location),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Info(
                    icon = R.drawable.ic_website,
                    text = stringResource(R.string.sticky_footer_website),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Info(
                    icon = R.drawable.ic_joined_date,
                    text = stringResource(R.string.sticky_footer_joined_date),
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Follower(
                    modifier = Modifier.weight(1f),
                    count = stringResource(R.string.sticky_footer_followers_count),
                    label = stringResource(R.string.sticky_footer_followers_label)
                )
                Follower(
                    modifier = Modifier.weight(1f),
                    count = stringResource(R.string.sticky_footer_following_count),
                    label = stringResource(R.string.sticky_footer_following_label)
                )
            }
            Column(
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(R.string.sticky_footer_repositories_label),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Repository(
                    name = stringResource(R.string.sticky_footer_repositories_project_name_1),
                    description = stringResource(R.string.sticky_footer_repositories_project_description_1),
                    star = stringResource(R.string.sticky_footer_repositories_project_star_1),
                    language = stringResource(R.string.sticky_footer_repositories_project_language_1)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Repository(
                    name = stringResource(R.string.sticky_footer_repositories_project_name_2),
                    description = stringResource(R.string.sticky_footer_repositories_project_description_2),
                    star = stringResource(R.string.sticky_footer_repositories_project_star_2),
                    language = stringResource(R.string.sticky_footer_repositories_project_language_2)
                )
                Spacer(modifier = Modifier.height(64.dp))
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f))
        ) {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(bottom = 16.dp),
                onClick = { },
            ) {
                Text(text = stringResource(R.string.sticky_footer_logout))
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
}

@Composable
private fun Info(
    @DrawableRes icon: Int,
    text: String,
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.primary,
            painter = painterResource(icon),
            contentDescription = text,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text)
    }
}

@Composable
private fun Follower(
    modifier: Modifier = Modifier,
    count: String,
    label: String,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = count,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = label)
    }
}

@Composable
private fun Repository(
    name: String,
    description: String,
    star: String,
    language: String,
) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(R.drawable.ic_repository),
                contentDescription = name
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = description)
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(R.drawable.ic_star),
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = star)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = language)
        }
    }
}


@Preview
@Composable
private fun StickyFooterScreenPreview() {
    AdaptiveScrollingUiTheme {
        StickyFooterScreen(onBackPressed = {})
    }
}
