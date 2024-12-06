package com.buccbracu.bucc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.buccbracu.bucc.backend.viewmodels.UserVM

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DeptMemScreen() {
    val uservm: UserVM = hiltViewModel()

    val profileData by uservm.profileData.collectAsState()

    var name by remember { mutableStateOf("") }
    var buccDepartment by remember { mutableStateOf("") }
    var designation by remember { mutableStateOf("") }
    var profileImage by remember { mutableStateOf("") }

    LaunchedEffect(profileData) {
        profileData?.let {
            name = it.name
            buccDepartment = it.buccDepartment
            designation = it.designation
            profileImage = it.profileImage
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 60.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    shape = RoundedCornerShape(10.dp),
                    elevation = androidx.compose.material3.CardDefaults.cardElevation(2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = profileImage,
                            contentDescription = "Profile Image",
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape),
                            alignment = Alignment.Center
                        )

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = name,
                                fontSize = 16.sp,

                            )
                            Text(
                                text = designation,
                                fontSize = 14.sp,

                            )
                            Text(
                                text = buccDepartment,
                                fontSize = 12.sp,

                            )
                        }


                    }
                }
            }
        }
    }
}
