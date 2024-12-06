package com.buccbracu.bucc.ui.screens.Tasks

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.buccbracu.bucc.backend.remote.models.TaskData
import com.buccbracu.bucc.backend.viewmodels.TaskVM
import com.buccbracu.bucc.components.DatePickerModal
import com.buccbracu.bucc.components.ExpandableCard
import com.buccbracu.bucc.components.MovableFloatingActionButton
import com.buccbracu.bucc.shortForm
import com.buccbracu.bucc.ui.theme.paletteGreen
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
    val (taskTitle, setTaskTitle) = rememberSaveable { mutableStateOf(task.taskTitle) }
    val (taskDescription, setTaskDescription) = rememberSaveable { mutableStateOf(task.taskDescription ?: "") }
    val (fromDept, setFromDept) = rememberSaveable { mutableStateOf(shortForm(task.fromDept ?: "")) }
    val (fromDesignation, setFromDesignation) = rememberSaveable { mutableStateOf(shortForm(task.fromDesignation ?: "")) }
    val (toDept, setToDept) = rememberSaveable { mutableStateOf(shortForm(task.toDept ?: "")) }
    val (toDesignation, setToDesignation) = rememberSaveable { mutableStateOf(shortForm(task.toDesignation ?: "")) }
    val assignDate = task.assignDate!!.slice(0..9)
    val (deadline, setDeadline) = rememberSaveable { mutableStateOf(task.deadline ?: "") }
    val (acceptedBy, setAcceptedBy) = rememberSaveable { mutableStateOf(task.acceptedBy.joinToString(", ")) }
    val (dateCompleted, setDateCompleted) = rememberSaveable { mutableStateOf(task.dateCompleted ?: "") }
    val (comment, setComment) = rememberSaveable { mutableStateOf(task.comment) }
    val (status, setStatus) = rememberSaveable { mutableStateOf(task.status) }


    ExpandableCard(
       task = task
    ) {
        Column{
            OutlinedTextField(
                value = taskDescription,
                onValueChange = {
                },
                readOnly = true,
                label = {
                    Text("Description")
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )
            OutlinedTextField(
                value = task.comment,
                onValueChange = {
                },
                label = {
                    Text("Comment")
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Filled.DoneAll,
                        contentDescription = "Done",
                        tint = paletteGreen
                    )
                }
            }

        }
    }

    if(showDatePicker){
        DatePickerModal(
            onDateSelected = { date ->
                setDeadline(date)

            },
            onDismiss = {
                showDatePicker = false
            }
        )
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