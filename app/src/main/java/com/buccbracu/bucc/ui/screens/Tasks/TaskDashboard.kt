package com.buccbracu.bucc.ui.screens.Tasks

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditCalendar
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Task
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.buccbracu.bucc.backend.remote.models.TaskData
import com.buccbracu.bucc.backend.viewmodels.TaskVM
import com.buccbracu.bucc.components.BasicDialog
import com.buccbracu.bucc.components.MovableFloatingActionButton
import com.buccbracu.bucc.components.NoButtonCircularLoadingDialog
import com.buccbracu.bucc.components.SearchBar
import com.buccbracu.bucc.components.appendBulletPoint
import com.buccbracu.bucc.components.filters.allMemberSearch
import com.buccbracu.bucc.components.filters.filterTask
import com.buccbracu.bucc.components.filters.memberSearch
import com.buccbracu.bucc.components.task.TaskCard
import com.buccbracu.bucc.ebgb
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun TaskDashboard(navController:  NavHostController){
    val taskvm: TaskVM = hiltViewModel()
    val dataTemp = taskvm.profile.value
    val profile by taskvm.profile.collectAsState(initial = dataTemp)
    val scope = rememberCoroutineScope()
    var allTasks by remember{
        mutableStateOf<List<TaskData>>(emptyList())
    }
    var filteredTasks by remember{
        mutableStateOf(allTasks)
    }
    val (query, setQuery) = remember{ mutableStateOf("") }
    var taskUpdateStatus by remember{
        mutableStateOf(false)
    }
    var taskCompleteStatus by remember{
        mutableStateOf(false)
    }
    var taskDeleteStatus by remember{
        mutableStateOf(false)
    }
    var isLoading by remember {
        mutableStateOf(true)
    }
    var showHelp by remember{
        mutableStateOf(false)
    }

    fun fetchTasks(){
        scope.launch {
            isLoading = true
            taskvm.getAllTasks(
                setTasks = {
                    allTasks = it
                    filteredTasks = it
                },
                onSuccess = {
                    isLoading = false
                }
            )
        }
    }

    LaunchedEffect(taskDeleteStatus) {
        if(!taskDeleteStatus){
            fetchTasks()
        }
    }
    LaunchedEffect(taskCompleteStatus) {
        if(!taskCompleteStatus){
            fetchTasks()
        }
    }
    LaunchedEffect(query) {
        if(query != ""){
            scope.launch {
                filteredTasks = filterTask(query, allTasks)
            }
        }
    }
    if(isLoading){
        NoButtonCircularLoadingDialog(
            title = "Loading Tasks",
            message = "Please wait..."
        )
    }
    else{
        Box(
            modifier = Modifier
                .fillMaxSize()
//            .padding(horizontal = 10.dp)
                .padding(top = 70.dp),
        ) {


            if(allTasks.isEmpty()){
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        "No tasks has been assigned to your department"
                    )
                }
            }
            else{
                Column{
                    SearchBar(
                        query = query,
                        onChange = setQuery,
                        label = "Search Tasks",
                        placeholder = "",
                        onClear = {
                            setQuery("")
                            filteredTasks = allTasks
                        },
                        leadingIcon = Icons.Outlined.Task,
                        isLeadingIconButton = true,
                        leadingIconAction = {
                            showHelp = true
                        },
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .fillMaxWidth()
                    )
                    LazyColumn(
                        modifier = Modifier
                            .padding(bottom = 30.dp),
                        verticalArrangement = Arrangement.Top
                    ) {
                        items(filteredTasks) { task ->
                            profile?.let {
                                TaskCard(
                                    task = task,
                                    userName = profile!!.name,
                                    userDept = profile!!.buccDepartment,
                                    userDesignation = profile!!.designation,
                                    onUpdate = { task ->
                                        scope.launch {
                                            taskUpdateStatus = true
                                            taskvm.updateTask(task) {
                                                taskUpdateStatus = false
                                            }
                                        }

                                    },
                                    onDone = { task ->
                                        scope.launch {
                                            taskCompleteStatus = true
                                            taskvm.updateTask(task) {
                                                taskCompleteStatus = false
                                            }
                                        }
                                    },
                                    onDelete = {
                                        scope.launch {
                                            taskDeleteStatus = true
                                            taskvm.deleteTask(task._id!!) {
                                                taskDeleteStatus = false
                                            }
                                        }
                                    },
                                    updateLoading = taskUpdateStatus,
                                    completeLoading = taskCompleteStatus,
                                    deleteLoading = taskDeleteStatus
                                )
                            }
                        }
                    }
                }
            }
            MovableFloatingActionButton(
                onClick = {
                    navController.navigate("Create Task")
                }
            )
            if(showHelp){
                BasicDialog(
                    onDismiss = {
                        showHelp = false
                    }
                ) {
                    Card(
                        modifier = Modifier
                            .padding(16.dp),
                        elevation = CardDefaults.cardElevation(5.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Text(
                                text = "Task Search Guide",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "You can search for tasks based on the following information:",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            // Searchable fields list
                            Text(
                                text = buildAnnotatedString {
                                    appendBulletPoint("Task title")
                                    appendBulletPoint("Task description")
                                    appendBulletPoint("Department (from/to): ", "Must use full department names (e.g., \"Event Management\", not \"EM\")")
                                    appendBulletPoint("Designation (from/to): ", "Must use full designations (e.g., \"Senior Executive\", not \"SE\")")
                                    appendBulletPoint("Deadline: ", "Search by date values")
                                    appendBulletPoint("Assigned to: ", "Names of accepted users")
                                    appendBulletPoint("Status: ", "Must be one of the following - Accepted, Completed, or Pending")
                                    appendBulletPoint("Comments")
                                },
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Spacer(modifier = Modifier.height(12.dp))

                            // Note
                            Text(
                                text = "Note: For accurate results, ensure you use exact phrases or full terms. Abbreviations may not yield results.",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                            )
                        }
                    }

                }
            }

        }
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