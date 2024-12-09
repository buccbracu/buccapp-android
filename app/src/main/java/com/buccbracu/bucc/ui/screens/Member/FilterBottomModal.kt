package com.buccbracu.bucc.ui.screens.Member

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoneOutline
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.buccbracu.bucc.allDepartments
import com.buccbracu.bucc.allDesignations
import com.buccbracu.bucc.bloodGroups
import com.buccbracu.bucc.components.OutlinedDropDownMenu
import com.buccbracu.bucc.prevYearsList
import com.buccbracu.bucc.ui.theme.paletteGreen

@Composable
fun FilterBottomModal(
    onApply: () -> Unit
)
{
    var designations by remember{
        mutableStateOf(allDesignations)
    }
    val years by remember {
        mutableStateOf(prevYearsList(4))
    }
    val (department, setDepartment) = rememberSaveable { mutableStateOf("") }
    val (designation, setDesignation) = rememberSaveable { mutableStateOf("") }
    val (contactNumber, setContactNumber) = rememberSaveable { mutableStateOf("") }
    val (joinedBracu, setJoinedBracu) = rememberSaveable { mutableStateOf("") }
    val (bloodGroup, setBloodGroup) = rememberSaveable { mutableStateOf("") }
    val (joinedBucc, setJoinedBucc) = rememberSaveable { mutableStateOf("") }
    val (lastPromotion, setLastPromotion) = rememberSaveable { mutableStateOf("") }

    LaunchedEffect(department) {
        designations = if(department.lowercase() ==  allDepartments[0].lowercase()) {
            allDesignations.slice(0..3)
        } else{
            allDesignations.slice(4..<allDesignations.size)
        }
    }



    LazyColumn {
        item{
            ItemCard {
                OutlinedDropDownMenu(
                    dropdownItems = allDepartments,
                    selectedText = department,
                    parentHorizontalPadding = 10,
                    onItemClick = setDepartment,
                    label = "Department",
                )
            }
            ItemCard {
                OutlinedDropDownMenu(
                    dropdownItems = designations,
                    selectedText = designation,
                    parentHorizontalPadding = 10,
                    onItemClick = setDesignation,
                    label = "Designation",
                )
            }
            ItemCard {
                OutlinedTextField(
                    value = contactNumber,
                    onValueChange = setContactNumber,
                    label = {
                        Text("Contact Number")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            ItemCard {
                OutlinedDropDownMenu(
                    dropdownItems = years,
                    selectedText = joinedBucc,
                    parentHorizontalPadding = 100,
                    onItemClick = setJoinedBucc,
                    label = "Joined BUCC",
                )
            }
            ItemCard {
                OutlinedDropDownMenu(
                    dropdownItems = bloodGroups,
                    selectedText = bloodGroup,
                    parentHorizontalPadding = 100,
                    onItemClick = setBloodGroup,
                    label = "Blood Group",
                )
            }
            ItemCard {
                OutlinedDropDownMenu(
                    dropdownItems = years,
                    selectedText = joinedBracu,
                    parentHorizontalPadding = 100,
                    onItemClick = setJoinedBracu,
                    label = "Joined BRACU",
                )
            }
            ItemCard {
                OutlinedDropDownMenu(
                    dropdownItems = years,
                    selectedText = lastPromotion,
                    parentHorizontalPadding = 100,
                    onItemClick = setLastPromotion,
                    label = "Last Promotion",
                )
            }
            ItemCard {
                OutlinedDropDownMenu(
                    dropdownItems = bloodGroups,
                    selectedText = bloodGroup,
                    parentHorizontalPadding = 100,
                    onItemClick = setBloodGroup,
                    label = "Blood Group",
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = {

                    },
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .padding(start = 10.dp),
                ) {

                    Icon(
                        imageVector = Icons.Filled.DoneOutline,
                        contentDescription = "Cancel",
                        tint = paletteGreen,
                    )

                }
            }
        }
    }



}

@Composable
fun ItemCard(content: @Composable () -> Unit){
    Card(
        modifier = Modifier
            .padding(10.dp),

    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
        ){
            content()
        }
    }
}