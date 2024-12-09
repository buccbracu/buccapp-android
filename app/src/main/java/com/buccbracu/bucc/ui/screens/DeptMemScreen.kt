package com.buccbracu.bucc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.outlined.Person
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
import com.buccbracu.bucc.allMemberPermission
import com.buccbracu.bucc.backend.remote.models.Member
import com.buccbracu.bucc.backend.viewmodels.DeptMemVM
import com.buccbracu.bucc.backend.viewmodels.UserVM
import com.buccbracu.bucc.components.NoButtonCircularLoadingDialog
import com.buccbracu.bucc.components.SearchBar
import com.buccbracu.bucc.components.filters.memberFilter
import com.buccbracu.bucc.ebgb
import com.buccbracu.bucc.gb
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun DeptMemScreen(
    designation: String,
    department: String
) {
    val deptMemVM: DeptMemVM = hiltViewModel()
    var memberList by remember {
        mutableStateOf(listOf<Member>())
    }
    var filteredList by remember {
        mutableStateOf(listOf<Member>())
    }

    val (query, setQuery) = remember{ mutableStateOf("") }
    val scope = rememberCoroutineScope()
    var isLoading by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        if(memberList.isEmpty()){
            scope.launch {
                isLoading = true

                if(allMemberPermission(dept = department, des = designation)){
                    // for gb & HR
                    deptMemVM.getAllMembers(
                        setMembers = { list ->
                            memberList = list
                            filteredList = list
                        },
                        setLoading = { loading ->
                            isLoading = loading

                        }
                    )
                }
                else{
                    deptMemVM.getMembers(
                        setMembers = { list ->
                            memberList = list
                            filteredList = list
                        },
                        setLoading = { loading ->
                            isLoading = loading

                        }
                    )
                }


            }
        }
    }
    LaunchedEffect(query) {
        if(query != ""){
            scope.launch {
                filteredList = memberFilter(query, memberList)
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


        Column(
            modifier = Modifier
                .padding(top = 70.dp, bottom = 50.dp)
        ){
            SearchBar(
                query = query,
                onChange = setQuery,
                label = "Search Member",
                placeholder = "Name || Designation",
                onClear = {
                    setQuery("")
                    filteredList = memberList
                },
                leadingIcon = Icons.Outlined.Person
            )
            if(filteredList.isEmpty()){
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text("No Blogs containing $query is found.")
                }
            }
            else{
                LazyColumn(

                ) {
                    items(filteredList) { member ->
                        MemberCard(member)
                    }
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
