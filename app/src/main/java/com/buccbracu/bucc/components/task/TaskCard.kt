package com.buccbracu.bucc.components.task

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.GroupAdd
import androidx.compose.material.icons.filled.GroupRemove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buccbracu.bucc.backend.remote.models.TaskData
import com.buccbracu.bucc.backend.remote.models.UpdateTask
import com.buccbracu.bucc.components.DatePickerModal
import com.buccbracu.bucc.ebgb
import com.buccbracu.bucc.ui.screens.Tasks.DateField
import com.buccbracu.bucc.ui.theme.palette2DarkRed
import com.buccbracu.bucc.ui.theme.paletteGreen

@SuppressLint("MutableCollectionMutableState")
@Composable
fun TaskCard(
    task: TaskData,
    userName: String,
    userDept: String,
    userDesignation: String,
    onUpdate: (UpdateTask) -> Unit,
    onDone: (UpdateTask) -> Unit,
    onDelete: () -> Unit,
    updateLoading: Boolean,
    completeLoading: Boolean,
    deleteLoading: Boolean,
){

    val editPermission = ebgb.contains(userDesignation)
    var isEditable by remember{
        mutableStateOf(false)
    }
    var showDatePicker by remember{
        mutableStateOf(false)
    }
    var accept by remember{
        mutableStateOf(task.acceptedBy.toMutableStateList())
    }
    var updateStatus by remember{
        mutableStateOf(task.status)
    }
    val (deadline, setDeadline) = remember{ mutableStateOf(task.deadline!!.slice(0..9)) }
    val (comment, setComment) = remember{ mutableStateOf(task.comment) }
    val (description, setDescription) = remember { mutableStateOf(task.taskDescription!!) }

    if(updateStatus!= "completed"){
        updateStatus =
            if (accept.isEmpty()) {
                "pending"
            } else {
                "accepted"
            }
    }
    ExpandableTaskCard(
        task = task,
        status = updateStatus,
        deadline = deadline
    ) {
        Column{
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.secondaryContainer)
            ){
                if (isEditable) {
                    OutlinedTextField(
                        value = description,
                        onValueChange = {
                            setDescription(it)
                            println("Setting description: $description")
                        },
                        label = { Text("Description") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = 100.dp)
                            .padding(10.dp), // Adjust the height to make the text field bigger
                        maxLines = 5,  // Allows up to 5 lines of text
                        minLines = 3,  // Ensures the text field has at least 3 lines visible
                    )
                } else {
                    Text(
                        "Description",
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .padding(top = 5.dp),
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                    Text(
                        text = description,
                        style = TextStyle(
                            textAlign = TextAlign.Justify,
                            fontSize = 15.sp,
                            lineHeight = 24.sp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
            if(isEditable){
                DateField(
                    label = "Deadline",
                    value = deadline,
                    onClick = {
                        showDatePicker = true
                    }
                )
            }
            OutlinedTextField(
                value = accept.joinToString(", "),
                onValueChange = {},
                readOnly = true,
                label = {
                    Text("Accepted By")
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                trailingIcon = {
                    if(updateStatus != "completed"){
                        if (accept.contains(userName)) {
                            IconButton(
                                onClick = {
                                    accept.remove(userName)
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.GroupRemove,
                                    contentDescription = "Leave",
                                    tint = palette2DarkRed
                                )
                            }
                        } else {
                            IconButton(
                                onClick = {
                                    accept = accept.apply {
                                        add(userName)
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.GroupAdd,
                                    contentDescription = "Join",
                                    tint = paletteGreen
                                )
                            }

                        }
                    }
                }
            )
            OutlinedTextField(
                value = comment,
                onValueChange = {
                    setComment(it)
                },
                label = {
                    Text("Comment")
                },
                readOnly = updateStatus == "completed",
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                if(editPermission && task.status != "completed"){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            onClick = {
                                isEditable = !isEditable
                            }
                        ) {
                            Icon(
                                imageVector =
                                if(isEditable) Icons.Filled.Done
                                else Icons.Filled.EditNote,
                                contentDescription = "Edit",
                                tint = paletteGreen,
                                modifier = Modifier
                                    .padding(vertical = 5.dp)
                                    .size(20.dp)

                            )
                        }
                        Text(
                            "Edit",
                            fontSize = 12.sp
                        )
                    }
                }
                if(ebgb.contains(userDesignation)){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            onClick = {
                                onDelete()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Delete",
                                tint = palette2DarkRed,
                                modifier = Modifier
                                    .padding(vertical = 5.dp)
                                    .size(20.dp)

                            )
                        }
                        Text(
                            "Delete",
                            fontSize = 12.sp
                        )
                    }
                }


                if(updateStatus != "completed"){
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(
                            onClick = {
                                val update = UpdateTask(
                                    _id = task._id!!,
                                    taskDescription = description,
                                    deadline = deadline,
                                    acceptedBy = accept,
                                    comment = comment,
                                    status = updateStatus
                                )
                                onUpdate(update)

                            }
                        ) {
                            if (updateLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .size(20.dp),
                                    color = paletteGreen
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Filled.CloudUpload,
                                    contentDescription = "Update",
                                    tint = paletteGreen,
                                    modifier = Modifier
                                        .padding(vertical = 5.dp)
                                        .size(20.dp)
                                )
                            }
                        }
                        Text(
                            "Update",
                            fontSize = 12.sp
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        IconButton(
                            onClick = {
                                val update = UpdateTask(
                                    _id = task._id!!,
                                    taskDescription = description,
                                    deadline = deadline,
                                    acceptedBy = accept,
                                    comment = comment,
                                    status = "completed"
                                )
                                onDone(update)
//                                updateStatus = "completed"
                            },
                            enabled = accept.isNotEmpty()
                        ) {
                            if (completeLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .size(20.dp),
                                    color = paletteGreen
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Filled.DoneAll,
                                    contentDescription = "Done",
                                    tint =
                                    if (accept.isEmpty()) Color.Gray
                                    else paletteGreen,
                                    modifier = Modifier
                                        .padding(vertical = 5.dp)
                                        .size(20.dp),

                                    )
                            }
                        }
                        Text(
                            "Completed",
                            fontSize = 12.sp
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

}