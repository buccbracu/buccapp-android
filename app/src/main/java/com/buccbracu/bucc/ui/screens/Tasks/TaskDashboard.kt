package com.buccbracu.bucc.ui.screens.Tasks

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.buccbracu.bucc.backend.remote.models.TaskData
import com.buccbracu.bucc.backend.viewmodels.TaskVM
import com.buccbracu.bucc.components.MovableFloatingActionButton
import com.buccbracu.bucc.components.NoButtonCircularLoadingDialog
import com.buccbracu.bucc.components.task.TaskCard
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun TaskDashboard(navController:  NavHostController){
    val taskvm: TaskVM = hiltViewModel()
    val dataTemp = taskvm.profile.value
    val profile by taskvm.profile.collectAsState(initial = dataTemp)
    val scope = rememberCoroutineScope()
    var allTasks by remember{
        mutableStateOf<List<TaskData>>(emptyList())
    }
    var filteredTasks by remember{
        mutableStateOf<List<TaskData>>(emptyList())
    }
    var taskUpdateStatus by remember{
        mutableStateOf(false)
    }
    var taskCompleteStatus by remember{
        mutableStateOf(false)
    }
    var taskDeleteStatus by remember{
        mutableStateOf(false)
    }
    var isLoading by remember {
        mutableStateOf(true)
    }

    fun fetchTasks(){
        scope.launch {
            isLoading = true
            taskvm.getAllTasks(
                setTasks = {
                    allTasks = it
                },
                onSuccess = {
                    isLoading = false
                }
            )
        }
    }

    LaunchedEffect(taskDeleteStatus) {
        if(!taskDeleteStatus){
            fetchTasks()
        }
    }
    LaunchedEffect(taskCompleteStatus) {
        if(!taskCompleteStatus){
            fetchTasks()
        }
    }
    if(isLoading){
        NoButtonCircularLoadingDialog(
            title = "Loading Tasks",
            message = "Please wait..."
        )
    }
    else{
        Box(
            modifier = Modifier
                .fillMaxSize()
//            .padding(horizontal = 10.dp)
                .padding(top = 70.dp),
        ) {


            if(allTasks.isEmpty()){
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        "No tasks has been assigned to your department"
                    )
                }
            }
            else{
                LazyColumn(
                    modifier = Modifier
                        .padding(bottom = 30.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    items(allTasks) { task ->
                        profile?.let {
                            TaskCard(
                                task = task,
                                userName = profile!!.name,
                                userDept = profile!!.buccDepartment,
                                userDesignation = profile!!.designation,
                                onUpdate = { task ->
                                    scope.launch {
                                        taskUpdateStatus = true
                                        taskvm.updateTask(task) {
                                            taskUpdateStatus = false
                                        }
                                    }

                                },
                                onDone = { task ->
                                    scope.launch {
                                        taskCompleteStatus = true
                                        taskvm.updateTask(task){
                                            taskCompleteStatus = false
                                        }
                                    }
                                },
                                onDelete = {
                                    scope.launch {
                                        taskDeleteStatus = true
                                        taskvm.deleteTask(task._id!!){
                                            taskDeleteStatus = false
                                        }
                                    }
                                },
                                updateLoading = taskUpdateStatus,
                                completeLoading = taskCompleteStatus,
                                deleteLoading = taskDeleteStatus
                            )
                        }
                    }
                }
            }
            MovableFloatingActionButton(
                onClick = {
                    navController.navigate("Create Task")
                }
            )

        }
    }
}


@Composable
fun DateField(
    value: String,
    label: String,
    onClick: () -> Unit
){
    OutlinedTextField(
        value = value,
        onValueChange = {
            // do nothing
        },
        label = {
            Text(label)
        },
        modifier = Modifier
            .fillMaxWidth(),
        readOnly = true,
        shape = RoundedCornerShape(10.dp),
        trailingIcon = {
            IconButton(
                onClick = onClick
            ){
                Icon(
                    imageVector = Icons.Filled.EditCalendar,
                    contentDescription = "Calender",
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        }
    )
}