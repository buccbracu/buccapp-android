package com.buccbracu.bucc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilterAlt
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.buccbracu.bucc.allMemberPermission
import com.buccbracu.bucc.backend.remote.models.Member
import com.buccbracu.bucc.backend.viewmodels.DeptMemVM
import com.buccbracu.bucc.components.NoButtonCircularLoadingDialog
import com.buccbracu.bucc.components.SearchBar
import com.buccbracu.bucc.components.filters.allMemberSearch
import com.buccbracu.bucc.components.filters.filterMembers
import com.buccbracu.bucc.components.filters.memberSearch
import com.buccbracu.bucc.components.member.FilterScreen
import com.buccbracu.bucc.components.member.MemberCard
import com.buccbracu.bucc.components.models.Filter
import com.buccbracu.bucc.ebgb
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DeptMemScreen(
    designation: String,
    department: String
) {

    val deptMemVM: DeptMemVM = hiltViewModel()
    var memberList by remember {
        mutableStateOf(listOf<Member>())
    }
    var filteredList by remember {
        mutableStateOf(listOf<Member>())
    }
    var filterValues by remember{
        mutableStateOf(Filter())
    }

    val (query, setQuery) = remember{ mutableStateOf("") }
    val scope = rememberCoroutineScope()
    var isLoading by remember {
        mutableStateOf(false)
    }
    val allMemberPermission = allMemberPermission(dept = department, des = designation)
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        if(memberList.isEmpty()){
            scope.launch {
                isLoading = true

                if(allMemberPermission){
                    deptMemVM.getAllMembers(
                        setMembers = { list ->
                            memberList = list
                            filteredList = list
                        },
                        setLoading = { loading ->
                            isLoading = loading

                        }
                    )
                }
                else{
                    deptMemVM.getMembers(
                        setMembers = { list ->
                            memberList = list
                            filteredList = list
                        },
                        setLoading = { loading ->
                            isLoading = loading

                        }
                    )
                }


            }
        }
    }
    LaunchedEffect(query) {
        if(query != ""){
            scope.launch {
                filteredList =
                    if(allMemberPermission) allMemberSearch(
                        query = query,
                        list =
                        if(filterValues.isEmpty()) memberList
                        else filteredList)
                    else memberSearch(
                        query = query,
                        list =
                        if(filterValues.isEmpty()) memberList
                        else filteredList
                    )

            }
        }
    }
    if(isLoading){
        NoButtonCircularLoadingDialog(
            title = "Loading Department Members",
            message = "Please wait..."
        )
    }
    else{
        BottomSheetScaffold(
            sheetContent = {
                FilterScreen(
                    onApply = { filter ->
                        filterValues = filter
                        scope.launch {
                            filteredList = filterMembers(filter, memberList)
                            sheetState.hide()
                        }
                    },
                    onReset = {
                        setQuery("")
                    }
                )
            },
            scaffoldState = rememberBottomSheetScaffoldState(sheetState),
            sheetPeekHeight = 0.dp,
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(top = 70.dp, bottom = 50.dp),

            ){
                Row(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .height(70.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    SearchBar(
                        query = query,
                        onChange = setQuery,
                        label = "Search Member",
                        placeholder =
                        if (allMemberPermission) "Name || Designation || Department"
                        else "Name || Designation ",
                        onClear = {
                            setQuery("")
                            filteredList = memberList
                        },
                        leadingIcon = Icons.Outlined.Person,
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth(
                                if(ebgb.contains(designation)) 0.85f
                                else 1f
                            )
                    )
                    if(ebgb.contains(designation)){
                        IconButton(
                            onClick = {
                                scope.launch {
                                    if (sheetState.currentValue.name == "PartiallyExpanded") {
                                        sheetState.expand()
                                    } else {
                                        sheetState.hide()
                                    }
                                    showBottomSheet = !showBottomSheet
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.FilterAlt,
                                contentDescription = "Filter",
                                modifier = Modifier
                                    .size(30.dp)
                            )
                        }
                    }
                }
                if(filteredList.isEmpty()){
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Text("No Blogs containing $query is found.")
                    }
                }
                else{
                    LazyColumn(

                    ) {
                        items(filteredList) { member ->
                            MemberCard(member)
                        }
                    }
                }
            }

        }


    }

}


