package com.buccbracu.bucc.ui.screens.Login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.buccbracu.bucc.backend.viewmodels.LoginVM
import com.buccbracu.bucc.components.ButtonLoading
import com.buccbracu.bucc.components.checkServer
import com.buccbracu.bucc.components.createNotification
import com.dotlottie.dlplayer.Event
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.log

@Composable
fun LoginScreen(
    loginVM: LoginVM,
    navController: NavHostController,
) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current.applicationContext
    val scope = rememberCoroutineScope()
    var loginStatus by remember{
        mutableStateOf(false)
    }
    var loginMessage by remember{
        mutableStateOf("")
    }
    var passwordVisible by remember {
        mutableStateOf(false)
    }
    var loginLater by remember{
        mutableStateOf(false)
    }
    var isLoading by remember{
        mutableStateOf(false)
    }
    var showResetPrompt by remember{
        mutableStateOf(false)
    }
    var hasInternet by remember{
        mutableStateOf(false)
    }
    val noInternet = "Connect to the Internet and Try Again."
    LaunchedEffect(Unit){
        scope.launch {
            hasInternet = checkServer()
        }
        if(!hasInternet){
            loginMessage = noInternet
        }
    }
    LaunchedEffect(loginMessage) {
        scope.launch {
            if(loginMessage != noInternet){
                delay(3000)
                loginMessage = ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
        ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(
            modifier = Modifier.
            height(10.dp)
        )
        if(loginMessage != "Success" && loginMessage != ""){
            Text(
                text = loginMessage,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )
            Spacer(
                modifier = Modifier.
                height(10.dp)
            )
        }

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = "Email address")
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
            shape = MaterialTheme.shapes.medium
        )
        Spacer(
            modifier = Modifier.
            height(10.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = { Text(text = "Password") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.VisibilityOff
                else Icons.Filled.Visibility

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, contentDescription = null)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            shape = MaterialTheme.shapes.medium

        )
        Spacer(
            modifier = Modifier
                .height(10.dp)
        )

        Button(
            onClick = {
                scope.launch {
                    isLoading = true
                    loginVM.login(
                        email = email,
                        password = password,
                        loginStatus = { status, message ->
                            if(status){
//                                navController.navigate("About BUCC")
                                createNotification(
                                    context,
                                    title = "BRAC University Computer Club",
                                    bodyText = "Welcome"
                                )
                            }
                            loginMessage = message
                        },
                        setLoading = { loading ->
                            isLoading = loading
                        }
                    )
                }
            },
            shape = RoundedCornerShape(5.dp),
            enabled = !isLoading && hasInternet,
            modifier = Modifier
                .width(100.dp)
        ) {
            if(isLoading){
                ButtonLoading()
            }
            else{
                Text(text = "Login")

            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        TextButton(
            onClick = {
                showResetPrompt = true
            },
            enabled = !isLoading
        ) {
            Text(text = "Forgot Password?")
        }

        TextButton(
            onClick = {
                loginLater = true
            },
            enabled = !isLoading
        ) {
            Text("Login Later")
        }

    }
    if (loginStatus){
        navController.navigate("Profile")
    }
    if(loginLater){
        navController.navigate("About BUCC")
    }
    if(showResetPrompt){
        EmailPrompt(
            loginvm = loginVM,
            onDissmiss = {
                showResetPrompt = false
            }
        )
    }

}

