package com.buccbracu.bucc.ui.screens.Tasks

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.buccbracu.bucc.backend.remote.models.TaskData
import com.buccbracu.bucc.backend.viewmodels.TaskVM
import com.buccbracu.bucc.components.ExpandableCard
import com.buccbracu.bucc.components.MovableFloatingActionButton
import kotlinx.coroutines.launch

@Composable
fun TaskDashboard(navController:  NavHostController){
    val taskvm: TaskVM = hiltViewModel()
    val scope = rememberCoroutineScope()
    var allTasks by remember{
        mutableStateOf<List<TaskData>>(emptyList())
    }

    LaunchedEffect(Unit) {
        scope.launch {
            taskvm.getAllTasks(
                setTasks = {
                    allTasks = it
                }
            )
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
//            .padding(horizontal = 10.dp)
            .padding(top = 70.dp)
    ){


            LazyColumn {
                items(allTasks) { task ->
                    TaskCard(task)
                }
            }
        MovableFloatingActionButton {
            navController.navigate("Create Task")
        }

    }
}

@Composable
fun TaskCard(task: TaskData){
    var showDatePicker by remember{
        mutableStateOf(false)
    }
    var saveAssigned by remember{
        mutableStateOf(false)
    }
    var assignedDate by remember{
        mutableStateOf("Date")
    }
    var deadline by remember{
        mutableStateOf("Date")
    }

    ExpandableCard(
       title = task.taskTitle
    ) {
        Row(
        ){
            Column(
                modifier = Modifier
                    .padding(end = 10.dp)
            ) {
                OutlinedTextField(
                    value = "${task.toDept} - ${task.toDesignation}",
                    onValueChange = {
                    },
                    readOnly = true,
                    label = {
                        Text("Assigned To")
                    },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
//                DateField(
//                    label = "Assigned Date",
//                    value = assignedDate,
//                    onClick = {
//                        showDatePicker = true
//                        saveAssigned = true
//                    }
//                )
//                DateField(
//                    label = "Deadline",
//                    value = deadline,
//                    onClick = {
//                        showDatePicker = true
//                        saveAssigned = false
//                    }
//                )
            }
//            Column {
//                OutlinedTextField(
//                    value = "Accepted By",
//                    onValueChange = {
//                        assignedDate = it
//                    },
//                    readOnly = true,
//                    label = {
//                        Text("Assigned By")
//                    },
//                    shape = RoundedCornerShape(10.dp)
//                )
//                DateField(
//                    label = "Accepted Date",
//                    value = assignedDate,
//                    onClick = {
//                        showDatePicker = true
//                        saveAssigned = true
//                    }
//                )
//
//            }
        }
    }

//    if(showDatePicker){
//        DatePickerModal(
//            onDateSelected = { date ->
//                if (saveAssigned){
//                    assignedDate = date
//                }
//                else{
//                    deadline = date
//                }
//
//            },
//            onDismiss = {
//                showDatePicker = false
//            }
//        )
//    }
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