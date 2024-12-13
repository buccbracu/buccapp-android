package com.buccbracu.bucc.components.member

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ContactsDialog(
    contact: String,
    emergencyContact: String,
    onDissmissRequest: () -> Unit
){
    val context = LocalContext.current
    Dialog(
        onDismissRequest = onDissmissRequest
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Call Member",
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))
                if(contact.length >= 11){
                    ContactCard("Personal: ", contact)
                }
                if(emergencyContact.length >= 11){
                    ContactCard("Emergency: ", emergencyContact)
                }
            }
        }
    }

}

@Composable
fun ContactCard(label: String, text: String){
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    ElevatedCard (
        modifier = Modifier
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        onClick = {
            openDialer(context, text)
        }
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row{
                Text(
                    text = label,
                    fontWeight = FontWeight.W700
                )
                Text(
                    text = text,
                )
            }
            IconButton(
                onClick = {

                    clipboardManager.setText(AnnotatedString(text))
//                Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.ContentCopy,
                    contentDescription = "Copy",
                    modifier = Modifier
                        .size(20.dp)

                )
            }

        }
    }

}

fun openDialer(context: Context, phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    context.startActivity(intent)
}