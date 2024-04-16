package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
    ) {
        var artId by remember { mutableIntStateOf(1) }
        if (artId < 1) artId = 5
        if (artId > 5) artId = 1
        AppHeader()
        DisplayArt(artId)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        ) {
            ControlButton(R.string.button_prev, onClickAction = { artId-- })
            ControlButton(R.string.button_next, onClickAction = { artId++ })
        }
    }
}

// Display Art
@Composable
fun DisplayArt(artId: Int, modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .padding(top = 32.dp, start = 32.dp, end = 32.dp)
    ) {
        when (artId) {
            1 -> {
                Art(
                    art = R.string.art_1,
                    artist = R.string.artist_1,
                    date = R.string.year_1,
                    image = R.drawable.image1,
                )
            }

            2 -> {
                Art(
                    art = R.string.art_2,
                    artist = R.string.artist_2,
                    date = R.string.year_2,
                    image = R.drawable.image2,
                )
            }

            3 -> {
                Art(
                    art = R.string.art_3,
                    artist = R.string.artist_3,
                    date = R.string.year_3,
                    image = R.drawable.image3,
                )
            }

            4 -> {
                Art(
                    art = R.string.art_4,
                    artist = R.string.artist_4,
                    date = R.string.year_4,
                    image = R.drawable.image4,
                )
            }

            else -> {
                Art(
                    art = R.string.art_5,
                    artist = R.string.artist_5,
                    date = R.string.year_5,
                    image = R.drawable.image5,
                )
            }

        }
    }
}

// Single Art View
@Composable
fun Art(
    @StringRes art: Int,
    @StringRes artist: Int,
    @StringRes date: Int,
    @DrawableRes image: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(width = 900.dp, height = 500.dp)
                .padding(top = 20.dp, bottom = 28.dp)
                .shadow(12.dp)
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
        ) {
            // Art Name
            Text(
                text = stringResource(art),
                fontSize = 32.sp,
                lineHeight = 38.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            // Artist and Date
            Text(
                buildAnnotatedString {
                    // Artist
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)) {
                        append(stringResource(artist))
                    }
                    // Date
                    withStyle(style = SpanStyle(fontSize = 18.sp)) {
                        append(" (${stringResource(date)})")
                    }
                }
            )
        }
    }
}

// Control Buttons
@Composable
fun ControlButton(
    @StringRes content: Int,
    onClickAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClickAction,
        content = {
            Text(
                text = stringResource(content).uppercase(),
                fontSize = 16.sp,
                letterSpacing = 2.sp,
                fontWeight = FontWeight.Bold
            )
        },
        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray, contentColor = Color.White),
        modifier = modifier.size(width = 140.dp, height = 40.dp)
    )

}

// TopBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppHeader(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        modifier = modifier
            .fillMaxWidth(),
        title = {
            Text(
                text = stringResource(R.string.app_name),
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.DarkGray,
            titleContentColor = Color.White
        )
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}