package com.buccbracu.bucc.components

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.material.icons.filled.SensorOccupied
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.buccbracu.bucc.R
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.platform.LocalContext

@Composable
fun ShowSocials(
    github: String = "",
    linedkin: String = "",
    facebook: String = "",
    gsuit: String = ""
){

    val context = LocalContext.current
    val iconSize = 40.dp
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),

        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                openLink(context, github)
            },
            enabled = github != ""
        ) {
            Image(
                painter = painterResource(R.drawable.github),
                contentDescription = "Github",
                modifier = Modifier
                    .size(iconSize)
            )
        }
        IconButton(
            onClick = {
                openLink(context, linedkin)
            },
            enabled = linedkin != ""
        ) {
            Image(
                painter = painterResource(R.drawable.linkedin),
                contentDescription = "Linkedin",
                modifier = Modifier
                    .size(iconSize)
            )
        }
        IconButton(
            onClick = {
                openLink(context, facebook)
            },
            enabled = facebook != ""
        ) {
            Image(
                painter = painterResource(R.drawable.facebook),
                contentDescription = "Facebook",
                modifier = Modifier
                    .size(35.dp),
            )
        }
        IconButton(
            onClick = {
                sendEmail(context, gsuit)
            },
            enabled = gsuit != ""
        ) {
            Image(
                painter = painterResource(R.drawable.gmail_red),
                contentDescription = "Gmail",
                modifier = Modifier
                    .size(35.dp),
            )
        }



    }

}


fun openLink(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}


fun sendEmail(context: Context, email: String) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:$email")
    }
    context.startActivity(intent)
}