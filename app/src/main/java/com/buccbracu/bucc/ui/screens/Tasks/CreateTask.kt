package com.buccbracu.bucc.ui.screens.Tasks

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDownCircle
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.buccbracu.bucc.backend.remote.models.NewTask
import com.buccbracu.bucc.components.DatePickerModal
import com.buccbracu.bucc.components.DropDownCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTask(
    navController: NavHostController
) {
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
        "Creative", "HR", "R&D", "EM"
    )
    val designations = listOf(
        "SE", "EB"
    )
    Column(
        modifier = Modifier.
        fillMaxWidth()
            .padding(horizontal = 50.dp)
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

        DropDownCard(
            dropdownItems = departments,
        ) { setToDept(it) }



        DropDownCard(
            dropdownItems = designations
        ) {
            setToDesignation(it)
        }

        DateField(
            value = deadline,
            label = "Deadline"
        ) {
            showDatePicker = true
        }

        Button(
            onClick = {
                // When the form is submitted, create a NewTask object
                val newTask = NewTask(
                    taskTitle = taskTitle,
                    taskDescription = taskDescription,
                    toDept = toDept,
                    toDesignation = toDesignation,
                    deadline = deadline
                )
                // Navigate or handle the task creation logic here
            },
            modifier = Modifier.fillMaxWidth()
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



