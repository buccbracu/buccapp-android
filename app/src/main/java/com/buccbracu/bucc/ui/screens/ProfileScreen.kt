package com.buccbracu.bucc.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.buccbracu.bucc.backend.local.models.Session
import com.buccbracu.bucc.backend.viewmodels.UserVM

@Composable
fun Profile(session: Session) {
    val context = LocalContext.current
    val profilevm: UserVM = hiltViewModel()
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = session.name
        )
//        createNotification(
//            context,
//            title = "BRAC University Computer Club",
//            bodyText = "Welcome ${session.name} - ${session.buccDepartment} - ${session.designation}"
//        )
    }
}