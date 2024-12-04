package com.buccbracu.bucc.ui.screens.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.buccbracu.bucc.backend.viewmodels.LoginVM
import com.buccbracu.bucc.components.NoButtonCircularLoadingDialog
import com.buccbracu.bucc.components.NoButtonDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Logout(loginVM: LoginVM, navController: NavHostController) {
    val scope = rememberCoroutineScope()

    var isLoading by remember {
        mutableStateOf(false)
    }
    var logoutComplete by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        scope.launch {
            isLoading = true
            loginVM.logout()
//            delay(500)

            isLoading = false
            logoutComplete = true
        }
    }
    if(logoutComplete){
        navController.navigate("Login Landing")
    }
    if(isLoading){
        Box(
            modifier = Modifier
                .background(Color.Gray)
                .alpha(0.3f)
        ){
            NoButtonCircularLoadingDialog(title = "Logout", message = "Logging Out...")
        }
    }
}