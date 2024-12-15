package com.buccbracu.bucc.components.member

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterAlt
import androidx.compose.material.icons.filled.FilterAltOff
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buccbracu.bucc.allDepartments
import com.buccbracu.bucc.allDesignations
import com.buccbracu.bucc.bloodGroups
import com.buccbracu.bucc.components.OutlinedDropDownMenu
import com.buccbracu.bucc.components.filters.models.MemberFilter
import com.buccbracu.bucc.prevYearsList
import com.buccbracu.bucc.ui.theme.palette2DarkRed
import com.buccbracu.bucc.ui.theme.paletteGreen

@Composable
fun FilterScreen(
    allFields: Boolean,
    onApply: (MemberFilter) -> Unit,
    onReset: () -> Unit
)
{
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val modalHeight = screenHeight(allFields, screenHeight.value)

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

            .height(modalHeight)

    ) {
        item{
            Text(
                text = "Filter Members",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontWeight = FontWeight.W500,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(10.dp))
            if(allFields){
                ItemCard {
                    OutlinedDropDownMenu(
                        dropdownItems = allDepartments,
                        selectedText = department,
                        parentHorizontalPadding = 10,
                        onItemClick = setDepartment,
                        label = "Department",
                    )
                }
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
            if(allFields){
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
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                ){
                    IconButton(
                        onClick = {
                            val filter = MemberFilter()
                            setDesignation("")
                            setDepartment("")
                            setContactNumber("")
                            setJoinedBucc("")
                            setBloodGroup("")
                            setJoinedBracu("")
                            setLastPromotion("")
                            onApply(filter)
                            onReset()
                        },
                        modifier = Modifier
                            .padding(top = 10.dp),
                    ) {

                        Icon(
                            imageVector = Icons.Filled.FilterAltOff,
                            contentDescription = "Filter",
                            tint = palette2DarkRed,
                        )

                    }
                    Text("Reset")
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                ){
                    IconButton(
                        onClick = {
                            val filter =
                            if(allFields) MemberFilter(
                                buccDepartment = department,
                                designation = designation,
                                contactNumber = contactNumber,
                                joinedBracu = joinedBracu,
                                bloodGroup = bloodGroup,
                                emergencyContact = contactNumber,
                                joinedBucc = joinedBucc,
                                lastPromotion = lastPromotion,
                            )
                            else MemberFilter(
                                designation = designation,
                                contactNumber = contactNumber,
                                emergencyContact = contactNumber,
                                bloodGroup = bloodGroup,

                            )
                            onApply(filter)
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
                .padding(vertical = 5.dp)
                .padding(horizontal = 15.dp)
        ){
            content()
        }
//    }
}

fun screenHeight(allFields: Boolean, totalHeight: Float): Dp {
    val offset = if(allFields) 0.15 else 0.45
    return (totalHeight - totalHeight*offset).dp
}