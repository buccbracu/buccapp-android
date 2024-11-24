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
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.buccbracu.bucc.backend.viewmodels.LoginVM
import com.buccbracu.bucc.components.ButtonLoading
import com.buccbracu.bucc.components.createNotification
import com.dotlottie.dlplayer.Event
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    loginVM: LoginVM,
    navController: NavHostController,
    fromLanding: Boolean = false
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
    var loginLater by remember{
        mutableStateOf(false)
    }
    var isLoading by remember{
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
        ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome Back",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(
            modifier = Modifier
                .height(4.dp)
        )
        Text(text = "Login")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text(text = "Email address")
            }
        )
        Spacer(
            modifier = Modifier.
            height(20.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = { Text(text = "Password") },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )

        Button(
            onClick = {
                scope.launch {
                    isLoading = true
                    loginVM.login(
                        email = email,
                        password = password,
                        loginStatus = { status, user ->
                            if(status){
//                                navController.navigate("About BUCC")
                                createNotification(
                                    context,
                                    title = "BRAC University Computer Club",
                                    bodyText = "Welcome ${user.name} - ${user.buccDepartment} - ${user.designation}"
                                )
                            }
                        },
                        setLoading = { loading ->
                            isLoading = loading
                        }
                    )
                }
            },
            shape = RoundedCornerShape(5.dp),
            enabled = !isLoading,
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
        Spacer(modifier = Modifier.height(15.dp))


        TextButton(
            onClick = {
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

}

