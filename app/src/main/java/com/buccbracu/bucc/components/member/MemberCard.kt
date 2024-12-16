package com.buccbracu.bucc.components.member

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.buccbracu.bucc.R
import com.buccbracu.bucc.backend.remote.models.Member
import com.buccbracu.bucc.components.ShowSocials

@Composable
fun MemberCard(
    member: Member,
    onCardClick: () -> Unit
){
    var showContactDialog by remember{
        mutableStateOf(false)
    }
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onSecondary),
        onClick = onCardClick
    ) {
        Card(
            modifier = Modifier
                .padding(10.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model =
                    if(member.profileImage == "") R.drawable.empty_person
                    else member.profileImage,
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
                    verticalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    Text(
                        text = member.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W900
                    )
                    Text(
                        text = member.designation,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W600
                    )
                    Text(
                        text = member.buccDepartment,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400
                    )

                }
                IconButton(
                    onClick = {
                        showContactDialog = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Phone,
                        contentDescription = "Call"
                    )
                }
            }
        }
        ShowSocials(
            github = member.memberSocials.Github,
            linedkin = member.memberSocials.Linkedin,
            facebook = member.memberSocials.Facebook,
            gsuit = member.email
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
    if(showContactDialog){
        ContactsDialog(
            contact = member.contactNumber,
            emergencyContact = member.emergencyContact,
            onDissmissRequest = {
                showContactDialog = false
            }
        )
    }
}