package com.buccbracu.bucc.components.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.buccbracu.bucc.R
import com.buccbracu.bucc.backend.local.models.User.Profile

@Composable
fun ProfileCard(profile: Profile, navController: NavHostController){


    ElevatedCard(
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainerHigh)
    ) {
        Box(){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {
                        navController.navigate("Edit Profile")
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Edit,
                        contentDescription = "Edit profile",
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp, top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model =
                    if (profile.profileImage == "") R.drawable.empty_person
                    else profile.profileImage,
                    contentDescription = "Profile image",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(10.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.FillWidth
                )
                Text(
                    text = profile.name,
                    modifier = Modifier
                        .padding(top = 10.dp),
                    fontWeight = FontWeight.W900,
                )
                Text(
                    text = profile.designation,
                    modifier = Modifier
                        .padding(top = 5.dp),
                    fontWeight = FontWeight.W600,
                    fontSize = 12.sp
                )
                Text(
                    text = profile.buccDepartment,
                    modifier = Modifier
                        .padding(top = 0.dp),
                    fontWeight = FontWeight.W400,
                    fontSize = 10.sp
                )
//                FieldCard(
//                    label = "Student ID",
//                    text = profile.studentId
//                )
//                FieldCard(
//                    label = "G-Suit",
//                    text = profile.email
//                )
//                FieldCard(
//                    label = "Personal Email",
//                    text = profile.personalEmail
//                )
            }
        }

    }

}