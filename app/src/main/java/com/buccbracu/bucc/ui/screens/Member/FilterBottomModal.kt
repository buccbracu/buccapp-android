package com.buccbracu.bucc.ui.screens.Member

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoneOutline
import androidx.compose.material.icons.filled.FilterAlt
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.buccbracu.bucc.allDepartments
import com.buccbracu.bucc.allDesignations
import com.buccbracu.bucc.bloodGroups
import com.buccbracu.bucc.components.OutlinedDropDownMenu
import com.buccbracu.bucc.prevYearsList
import com.buccbracu.bucc.ui.theme.paletteGreen

@Composable
fun FilterScreen(
    onApply: () -> Unit
)
{
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val sheetHeight = screenHeight - 150.dp // 80% of the screen height

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

    LazyColumn(
        modifier = Modifier

            .fillMaxHeight(0.82f)

    ) {
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
//            ItemCard {
//                OutlinedTextField(
//                    value = contactNumber,
//                    onValueChange = setContactNumber,
//                    label = {
//                        Text("Contact Number")
//                    },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                )
//            }
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
//            ItemCard {
//                OutlinedDropDownMenu(
//                    dropdownItems = bloodGroups,
//                    selectedText = bloodGroup,
//                    parentHorizontalPadding = 100,
//                    onItemClick = setBloodGroup,
//                    label = "Blood Group",
//                )
//            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                ){
                    IconButton(
                        onClick = {

                        },
                        modifier = Modifier
                            .padding(top = 10.dp),
                    ) {

                        Icon(
                            imageVector = Icons.Filled.FilterAlt,
                            contentDescription = "Filter",
                            tint = paletteGreen,
                        )

                    }
                    Text("Apply")
                }
            }
        }
    }



}

@Composable
fun ItemCard(content: @Composable () -> Unit){
//    Card(
//        modifier = Modifier
//            .padding(10.dp),
//
//    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)
        ){
            content()
        }
//    }
}