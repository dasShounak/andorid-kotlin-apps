package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var contentNumber by remember { mutableIntStateOf(1) }
    var squeezes by remember { mutableIntStateOf(0) }
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Topbar()
        when (contentNumber) {
            1 -> LemonImageAndText(
                imageResourceId = R.drawable.lemon_tree,
                contentDescId = R.string.content_desc_1,
                textResourceId = R.string.text_1,
                onImageClick = {
                    contentNumber = 2
                    squeezes = (2..4).random()
                })

            2 -> LemonImageAndText(
                imageResourceId = R.drawable.lemon_squeeze,
                contentDescId = R.string.content_desc_2,
                textResourceId = R.string.text_2,
                onImageClick = {
                    squeezes--
                    if (squeezes == 0) {
                        contentNumber = 3
                    }
                })

            3 -> LemonImageAndText(
                imageResourceId = R.drawable.lemon_drink,
                contentDescId = R.string.content_desc_3,
                textResourceId = R.string.text_3,
                onImageClick = {
                    contentNumber = 4
                })

            else -> LemonImageAndText(
                imageResourceId = R.drawable.lemon_restart,
                contentDescId = R.string.content_desc_4,
                textResourceId = R.string.text_4,
                onImageClick = {
                    contentNumber = 1
                })

        }
    }
}

@Composable
fun LemonImageAndText(
    imageResourceId: Int,
    contentDescId: Int,
    textResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            onClick = onImageClick,
            modifier = Modifier
                .wrapContentWidth()
        ) {
            Image(
                painter = painterResource(imageResourceId),
                contentDescription = stringResource(contentDescId)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(textResourceId),
            fontSize = 18.sp
        )
    }
}

@Composable
fun Topbar(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.app_name),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = modifier
            .background(Color(0xFFf9e44c))
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    LemonadeAppTheme {
        LemonadeApp()
    }
}