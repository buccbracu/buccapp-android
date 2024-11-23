package com.buccbracu.bucc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.buccbracu.bucc.backend.local.models.Session
import com.buccbracu.bucc.backend.local.models.emptyMember
import com.buccbracu.bucc.backend.viewmodels.UserVM
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Profile(session: Session) {
    val context = LocalContext.current
    val uservm: UserVM = hiltViewModel()
    val scope = rememberCoroutineScope()

    var userData by remember{
        mutableStateOf(emptyMember)
    }
    var profileData by remember {
        mutableStateOf(uservm.profileData.value)
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        profileData?.let {
            Text(
                text = it.name
            )
        }
//        createNotification(
//            context,
//            title = "BRAC University Computer Club",
//            bodyText = "Welcome ${session.name} - ${session.buccDepartment} - ${session.designation}"
//        )
    }
}