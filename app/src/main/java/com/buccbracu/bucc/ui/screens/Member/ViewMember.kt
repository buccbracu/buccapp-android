package com.buccbracu.bucc.ui.screens.Member

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.buccbracu.bucc.backend.remote.models.Member
import com.buccbracu.bucc.components.MovableFloatingActionButton
import com.buccbracu.bucc.components.ProfileView
import com.buccbracu.bucc.components.ShowSocials
import com.buccbracu.bucc.components.dashboard.ProfileCard
import com.buccbracu.bucc.memberToProfile

@Composable
fun ViewMember(
    allFields: Boolean,
    member: Member,
    navController: NavHostController
){
    Column(
        modifier = Modifier
            .padding(top = 70.dp)
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MemberDetails(allFields, member)
    }
    MovableFloatingActionButton(
        onClick = {
            navController.navigate("memberPage")
        },
        icon = Icons.Filled.ArrowBackIosNew
    )

}