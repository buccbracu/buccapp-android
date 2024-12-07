package com.buccbracu.bucc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.buccbracu.bucc.R
import com.buccbracu.bucc.backend.remote.models.Member
import com.buccbracu.bucc.backend.viewmodels.DeptMemVM
import com.buccbracu.bucc.backend.viewmodels.UserVM
import com.buccbracu.bucc.components.NoButtonCircularLoadingDialog
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DeptMemScreen() {
    val deptMemVM: DeptMemVM = hiltViewModel()
    var memberList by remember {
        mutableStateOf(listOf<Member>())
    }
    val scope = rememberCoroutineScope()
    var isLoading by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(memberList) {
        if(memberList.isEmpty()){
            scope.launch {
                isLoading = true
                deptMemVM.getMembers(
                    setMembers = { list ->
                        memberList = list
                    },
                    setLoading = { loading ->
                        isLoading = loading

                    }
                )

            }
        }
    }
    if(isLoading){
        NoButtonCircularLoadingDialog(
            title = "Loading Department Members",
            message = "Please wait..."
        )
    }
    else{
        if(memberList.isNotEmpty()){
            LazyColumn(
                modifier = Modifier
                    .padding(top = 70.dp)
            ) {
                items(memberList){ member ->
                    MemberCard(member)
                }
            }
        }
    }

}

@Composable
fun MemberCard(member: Member){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainerHigh)
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
        }
    }
}
