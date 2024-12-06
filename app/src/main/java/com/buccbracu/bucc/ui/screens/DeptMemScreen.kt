package com.buccbracu.bucc.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.buccbracu.bucc.backend.remote.models.Member
import com.buccbracu.bucc.backend.viewmodels.DeptMemVM
import com.buccbracu.bucc.components.NoButtonCircularLoadingDialog
import kotlinx.coroutines.launch

@Composable
fun DeptMemScreen(){
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
            LazyColumn {
                items(memberList){ member ->
                    Text(member.name)
                }
            }
        }
    }

}

