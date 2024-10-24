package com.buccbracu.bucc.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buccbracu.bucc.components.DatePickerModal
import com.buccbracu.bucc.components.ExpandableCard
import com.buccbracu.bucc.ui.theme.palette3violet1

@Composable
fun SEDashboard(){
    Box(
        modifier = Modifier
//            .padding(horizontal = 10.dp)
            .padding(top = 70.dp, bottom = 110.dp)
    ){
        LazyColumn {
            item {
                TaskCard()
            }
            item {
                TaskCard()
            }
            item {
                TaskCard()
            }
            item {
                TaskCard()
            }
            item {
                TaskCard()
            }
        }
    }
}

@Composable
fun TaskCard(){
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
//    ElevatedCard(
//        modifier = Modifier
//            .padding(15.dp)
//            .fillMaxWidth()
//            .animateContentSize(
//                animationSpec = TweenSpec(
//                    durationMillis = 300,
//                    easing = LinearOutSlowInEasing
//                )
//            ),
//        elevation = CardDefaults.cardElevation(10.dp),
//        shape = RoundedCornerShape(10.dp)
//    ) {
//
//        Column(
//            modifier = Modifier
//                .padding(15.dp)
//                .fillMaxSize()
//
//        ) {
//            Text(
//                text ="Task Title",
//                fontSize = 20.sp,
//                fontWeight = FontWeight.Bold,
//                color = palette3violet1,
//                modifier = Modifier
//                    .fillMaxWidth(),
//                textAlign = TextAlign.Center
//            )
//
//
//
//
//        }
//
//    }

    ExpandableCard(
       title = "Task"
    ) {
        Row(
        ){
            Column(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .fillMaxWidth(0.5f)
            ) {
                OutlinedTextField(
                    value = "Assigned By",
                    onValueChange = {
                        assignedDate = it
                    },
                    readOnly = true,
                    label = {
                        Text("Assigned By")
                    },
                    shape = RoundedCornerShape(10.dp)
                )
                DateField(
                    label = "Assigned Date",
                    value = assignedDate,
                    onClick = {
                        showDatePicker = true
                        saveAssigned = true
                    }
                )
                DateField(
                    label = "Deadline",
                    value = deadline,
                    onClick = {
                        showDatePicker = true
                        saveAssigned = false
                    }
                )
            }
            Column {
                OutlinedTextField(
                    value = "Accepted By",
                    onValueChange = {
                        assignedDate = it
                    },
                    readOnly = true,
                    label = {
                        Text("Assigned By")
                    },
                    shape = RoundedCornerShape(10.dp)
                )
                DateField(
                    label = "Accepted Date",
                    value = assignedDate,
                    onClick = {
                        showDatePicker = true
                        saveAssigned = true
                    }
                )

            }
        }
    }

    if(showDatePicker){
        DatePickerModal(
            onDateSelected = { date ->
                if (saveAssigned){
                    assignedDate = date
                }
                else{
                    deadline = date
                }

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