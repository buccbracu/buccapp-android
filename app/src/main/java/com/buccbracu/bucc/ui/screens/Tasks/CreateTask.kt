package com.buccbracu.bucc.ui.screens.Tasks

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.buccbracu.bucc.allDepartments
import com.buccbracu.bucc.allDesignations
import com.buccbracu.bucc.backend.remote.models.NewTask
import com.buccbracu.bucc.backend.viewmodels.TaskVM
import com.buccbracu.bucc.components.ButtonLoading
import com.buccbracu.bucc.components.DatePickerModal
import com.buccbracu.bucc.components.MovableFloatingActionButton
import com.buccbracu.bucc.components.OutlinedDropDownMenu
import com.buccbracu.bucc.ebgb
import kotlinx.coroutines.launch
import java.time.format.TextStyle
import java.util.Locale

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CreateTask(
    navController: NavHostController
) {
    val taskvm: TaskVM = hiltViewModel()
    val scope = rememberCoroutineScope()
    val dataTemp = taskvm.profile.value
    val profile by taskvm.profile.collectAsState(initial = dataTemp)
    var departments by remember{
        mutableStateOf(allDepartments)
    }
    var designations by remember{
        mutableStateOf(allDesignations)
    }
    val (taskTitle, setTaskTitle) = rememberSaveable { mutableStateOf("") }
    val (taskDescription, setTaskDescription) = rememberSaveable { mutableStateOf("") }
    val (toDept, setToDept) = rememberSaveable { mutableStateOf(departments[0]) }
    val (toDesignation, setToDesignation) = rememberSaveable { mutableStateOf(designations[0]) }
    val (deadline, setDeadline) = rememberSaveable { mutableStateOf("") }
    LaunchedEffect(profile) {
        profile?.let {
            val des = profile!!.designation
            if(!ebgb.contains(des)){
                departments = allDepartments.slice(1..<allDepartments.size)
                designations = listOf("Senior Executive")
            }
            setToDept(departments[0])
            setToDesignation(designations[0])
        }
    }

    LaunchedEffect(toDept) {
        designations =
            if(toDept.lowercase() ==  allDepartments[0].lowercase()) {
                allDesignations.slice(0..3)
            }
            else if(profile != null && profile!!.designation == "Senior Executive"){
                listOf("Senior Executive")
            }
            else{
                allDesignations.slice(4..6)
            }
        setToDesignation(designations[0])
    }


    var showDatePicker by remember{
        mutableStateOf(false)
    }
    var isLoading by remember{
        mutableStateOf(false)
    }

    val horizontalPadding = 50
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = horizontalPadding.dp)
            .padding(top = 70.dp)
    ) {

        Text(
            text = "Create New Task",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(),
            fontWeight = FontWeight.W900,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = taskTitle,
            onValueChange = setTaskTitle,
            label = { Text("Task Title") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = taskDescription,
            onValueChange = setTaskDescription,
            label = { Text("Task Description") },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            maxLines = 5,
            minLines = 3,
        )

        OutlinedDropDownMenu(
            dropdownItems = departments,
            onItemClick = {
                setToDept(it)
            },
            parentHorizontalPadding = horizontalPadding,
            label = "Department",
            selectedText = toDept
        )
        OutlinedDropDownMenu(
            dropdownItems = designations,
            onItemClick = {
                setToDesignation(it)
            },
            label = "Designation",
            parentHorizontalPadding = horizontalPadding,
            selectedText = toDesignation
        )


        DateField(
            value = deadline,
            label = "Deadline"
        ) {
            showDatePicker = true
        }

        if((taskTitle != "" && taskDescription != "" && toDept != "" && toDesignation != "" && deadline != "")){
            Button(
                onClick = {

                    scope.launch {
                        isLoading = true
                        val newTask = NewTask(
                            taskTitle = taskTitle,
                            taskDescription = taskDescription,
                            toDept = toDept,
                            toDesignation = toDesignation,
                            deadline = deadline
                        )

                        taskvm.createTask(
                            task = newTask,
                            onSuccess = {
                                isLoading = false
                                navController.navigate("Task Dashboard")
                            }
                        )
                    }

                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                enabled = !isLoading
            ) {
                if (isLoading) {
                    ButtonLoading()
                } else {
                    Text("Create Task")
                }
            }
        }
        else{
            Button(
                onClick = {
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                enabled = false
            ) {
                Text("Create Task")
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
    MovableFloatingActionButton(
        onClick = {
            navController.navigate("Task Dashboard")
        },
        icon = Icons.Filled.ArrowBackIosNew
    )
}



