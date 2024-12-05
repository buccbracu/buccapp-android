package com.buccbracu.bucc.ui.screens.Tasks

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.buccbracu.bucc.allDepartments
import com.buccbracu.bucc.allDesignations
import com.buccbracu.bucc.backend.remote.models.NewTask
import com.buccbracu.bucc.backend.viewmodels.TaskVM
import com.buccbracu.bucc.components.ButtonLoading
import com.buccbracu.bucc.components.DatePickerModal
import com.buccbracu.bucc.components.OutlinedDropDownMenu
import kotlinx.coroutines.launch
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
    var (taskTitle, setTaskTitle) = rememberSaveable { mutableStateOf("") }
    var (taskDescription, setTaskDescription) = rememberSaveable { mutableStateOf("") }
    var (toDept, setToDept) = rememberSaveable { mutableStateOf(departments[0]) }
    var (toDesignation, setToDesignation) = rememberSaveable { mutableStateOf(designations[0]) }
    var (deadline, setDeadline) = rememberSaveable { mutableStateOf("") }
    LaunchedEffect(toDept, toDesignation, profile) {
            profile?.let {
                val des = profile!!.designation
                val dep = profile!!.buccDepartment
                departments =
                    if(dep.lowercase() != allDepartments[0].lowercase()) {
                        allDepartments.slice(1..<allDepartments.size)
                    }
                    else {
                        allDepartments
                    }.toList()
                designations =
                    if(toDept.lowercase() ==  allDepartments[0].lowercase()) {
                        allDesignations.slice(0..3)
                    }
                    else if(des.lowercase() == "senior executive"){
                        allDesignations.slice(6..<allDesignations.size)
                    }
                    else{
                        allDesignations.slice(4..<allDesignations.size)
                    }.toList()
                println("Departments: $departments")
                println("Designations: $designations")
                setToDept(departments[0])
                setToDesignation(designations[0])
            }

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
                .heightIn(min = 100.dp), // Adjust the height to make the text field bigger
            maxLines = 5,  // Allows up to 5 lines of text
            minLines = 3,  // Ensures the text field has at least 3 lines visible
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
}



