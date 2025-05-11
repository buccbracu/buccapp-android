package com.buccbracu.bucc.components.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buccbracu.bucc.components.member.openDialer

@Composable
fun FieldCard(label: String, text: String){
    Card (
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),

        ){
            Text(
                text = label,
                fontWeight = FontWeight.W700,
                fontSize = 12.sp
            )
            Text(
                text = text,
                fontSize = 15.sp
            )

        }
    }

}