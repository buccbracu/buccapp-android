package com.buccbracu.bucc.components.dashboard.taskoverview

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.buccbracu.bucc.backend.remote.models.TaskOverview
import com.buccbracu.bucc.backend.viewmodels.TaskVM
import com.buccbracu.bucc.components.CircularLoadingBasic
import com.buccbracu.bucc.components.EmptyScreenText
import com.buccbracu.bucc.components.ExpandableCard
import com.buccbracu.bucc.components.checkServer
import kotlinx.coroutines.launch

@SuppressLint("MutableCollectionMutableState")
@Composable
fun TaskOverview(

){

    val taskvm: TaskVM = hiltViewModel()
    var overView by remember{
        mutableStateOf(TaskOverview())
    }
    val scope = rememberCoroutineScope()
    var isLoading by remember {
        mutableStateOf(true)
    }
    var hasInternet by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit){
        scope.launch {
            hasInternet = checkServer()
            if(hasInternet){
                taskvm.getTaskOverview(
                    setTasks = {
                        overView = it
                    },
                    onSuccess = {
                        isLoading = false
                    }
                )
            }
            else{
                isLoading = false
            }

        }
    }

    ExpandableCard(
        title = "Tasks Overview",
        description = "Departmental tasks status at a glance"
    ) {
        if(isLoading){
            CircularLoadingBasic("Loading Task Overview")
        }
        else if(!hasInternet){
            EmptyScreenText("Connect to the Internet and Try Again.")
        }
        else{
            Column {
//                FieldCard(
//                    label = "Accepted",
//                    text = overView.accepted.toString()
//                )
//                FieldCard(
//                    label = "Pending",
//                    text = overView.pending.toString()
//                )
//                FieldCard(
//                    label = "Completed",
//                    text = overView.completed.toString()
//                )
//                FieldCard(
//                    label = "Due Tomorrow",
//                    text = overView.dueTomorrow.toString()
//                )
                Card {
                    TaskGraph(
                        taskCounts = overView
                    )
                }
            }
        }
    }

}