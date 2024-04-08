package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BusinessCard(
                        name = stringResource(R.string.name),
                        designation = stringResource(R.string.designation),
                        contact = stringResource(R.string.contact),
                        email = stringResource(R.string.email),
                        social = stringResource(R.string.social)
                    )
                }
            }
        }
    }
}

@Composable
fun BusinessCard(
    name: String,
    designation: String,
    contact: String,
    email: String,
    social: String,
    modifier: Modifier = Modifier
    ){
    val avatar = painterResource(R.drawable.android_logo)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .fillMaxHeight()
                .padding(bottom = 70.dp)
                .weight(1f)
        ) {
            Image(
                painter = avatar,
                contentDescription = null,
                modifier = Modifier.width(96.dp)
            )
            Text(
                text = name,
                fontSize = 40.sp
            )
            Text(
                text = designation.uppercase(),
                fontSize = 18.sp
            )
        }
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = modifier.padding(bottom = 20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(bottom = 12.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_call_24),
                    contentDescription = null,
                    tint = Color(0xFF3ddc84)
                )
                Text(
                    text = contact,
                    modifier = Modifier.padding(start = 24.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(bottom = 12.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_email_24),
                    contentDescription = null,
                    tint = Color(0xFF3ddc84)
                )
                Text(
                    text = email,
                    modifier = Modifier.padding(start = 24.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.padding(bottom = 12.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.twitter),
                    contentDescription = null,
                    tint = Color(0xFF3ddc84)
                )
                Text(
                    text = social,
                    modifier = Modifier.padding(start = 24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
        BusinessCard(
            name = stringResource(R.string.name),
            designation = stringResource(R.string.designation),
            contact = stringResource(R.string.contact),
            email = stringResource(R.string.email),
            social = stringResource(R.string.social)
        )
    }
}