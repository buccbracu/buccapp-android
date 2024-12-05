package com.buccbracu.bucc.ui.screens.Tasks

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.buccbracu.bucc.backend.remote.models.NewTask
import com.buccbracu.bucc.backend.viewmodels.TaskVM
import com.buccbracu.bucc.components.DatePickerModal
import com.buccbracu.bucc.components.OutlinedDropDownMenu
import kotlinx.coroutines.launch

@Composable
fun CreateTask(
    navController: NavHostController
) {
    val taskvm: TaskVM = hiltViewModel()
    val scope = rememberCoroutineScope()
    // Using rememberSaveable for state persistence
    var (taskTitle, setTaskTitle) = rememberSaveable { mutableStateOf("") }
    var (taskDescription, setTaskDescription) = rememberSaveable { mutableStateOf("") }
    var (toDept, setToDept) = rememberSaveable { mutableStateOf("") }
    var (toDesignation, setToDesignation) = rememberSaveable { mutableStateOf("") }
    var (deadline, setDeadline) = rememberSaveable { mutableStateOf("") }

    var showDatePicker by remember{
        mutableStateOf(false)
    }
    val departments = listOf(
        "Governing Body",
        "Communication and Marketing",
        "Creative",
        "Event Management",
        "Finance",
        "Human Resources",
        "Press Release and Publications",
        "Research and Development"
    )
    val designations = listOf(
        "President",
        "Vice President",
        "General Secretary",
        "Treasurer",
        "Director",
        "Assistant Director",
        "Senior Executive",
        "Executive",
        "General Member",
    )
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
            label = "Department"
        )
        OutlinedDropDownMenu(
            dropdownItems = designations,
            onItemClick = {
                setToDesignation(it)
            },
            label = "Designation",
            parentHorizontalPadding = horizontalPadding
        )


        DateField(
            value = deadline,
            label = "Deadline"
        ) {
            showDatePicker = true
        }

        Button(
            onClick = {

                scope.launch{
                    val newTask = NewTask(
                        taskTitle = taskTitle,
                        taskDescription = taskDescription,
                        toDept = toDept,
                        toDesignation = toDesignation,
                        deadline = "2024"
                    )

                    taskvm.createTask(newTask)
                }

            },
            modifier = Modifier.fillMaxWidth(),
            enabled = taskTitle != "" && taskDescription != "" && toDept != "" && toDesignation != "" && deadline != ""
        ) {
            Text("Create Task")
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



