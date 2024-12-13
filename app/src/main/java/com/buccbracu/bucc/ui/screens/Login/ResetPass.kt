package com.buccbracu.bucc.ui.screens.Login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.DoneOutline
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.buccbracu.bucc.backend.viewmodels.LoginVM
import com.buccbracu.bucc.components.ButtonLoading
import com.buccbracu.bucc.ui.theme.palette2DarkRed
import com.buccbracu.bucc.ui.theme.palette2Plum
import com.buccbracu.bucc.ui.theme.paletteGreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun EmailPrompt(
    loginvm: LoginVM,
    onDissmiss: () -> Unit,
){
    val (email, setEmail) = remember{ mutableStateOf("") }
    val (message, setMessage) = remember{ mutableStateOf("none") }
    var isLoading by remember{
        mutableStateOf(false)
    }
    val scope = rememberCoroutineScope()
    LaunchedEffect(message) {
        if(message.contains("registered")){
            scope.launch {
                setEmail("")
                delay(3000)
                onDissmiss()
            }
        }
    }

    Dialog(
        onDismissRequest = {
            onDissmiss()
        },
    ) {
        ElevatedCard(
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ){
                if(message != "none"){
                    Text(
                        text = message,
                        textAlign = TextAlign.Justify,
                        color =
                        if(message.contains("registered")) paletteGreen
                        else MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight(300),
                        fontSize = 12.sp
                    )
                }
                Text(
                    "Reset Password",
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    "Enter your G-Suit Email",
                    modifier = Modifier
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    fontSize = 15.sp
                )
                OutlinedTextField(
                    value = email,
                    onValueChange = setEmail,
                    placeholder = {
                        Text("full.name@g.bracu.ac.bd")
                    },
                    label = {
                        Text("G-Suit Email")
                    },
                    shape = RoundedCornerShape(10.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    IconButton(
                        onClick = {
                            onDissmiss()
                        },
                        modifier = Modifier
                            .padding(top = 10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Cancel,
                            contentDescription = "Cancel",
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                    IconButton(
                        onClick = {
                            scope.launch {
                                isLoading = true
                                loginvm.resetPassword(
                                    email = email,
                                    setMessage = {
                                        setMessage(it)
                                        isLoading = false
                                    }
                                )


                            }
                        },
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .padding(start = 10.dp),
                        enabled = email != ""
                    ) {
                        if(isLoading){
                            ButtonLoading()
                        }
                        else{
                            Icon(
                                imageVector = Icons.Filled.DoneOutline,
                                contentDescription = "Cancel",
                                tint = if(email != "") paletteGreen else Color.DarkGray,
                            )
                        }
                    }
                }
            }
        }
    }
}