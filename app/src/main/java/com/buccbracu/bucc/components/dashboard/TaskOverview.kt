package com.buccbracu.bucc.components.dashboard

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
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.buccbracu.bucc.backend.remote.models.TaskOverview
import com.buccbracu.bucc.backend.remote.models.UpdateTask
import com.buccbracu.bucc.backend.viewmodels.TaskVM
import com.buccbracu.bucc.components.CircularLoadingBasic
import com.buccbracu.bucc.components.DatePickerModal
import com.buccbracu.bucc.components.ExpandableCard
import com.buccbracu.bucc.components.task.ExpandableTaskCard
import com.buccbracu.bucc.ebgb
import com.buccbracu.bucc.ui.screens.Tasks.DateField
import com.buccbracu.bucc.ui.theme.palette2DarkRed
import com.buccbracu.bucc.ui.theme.paletteGreen
import kotlinx.coroutines.launch

@SuppressLint("MutableCollectionMutableState")
@Composable
fun TaskOverview(

){

    val taskvm: TaskVM = hiltViewModel()
    var overView by remember{
        mutableStateOf(TaskOverview())
    }
    val scope = rememberCoroutineScope()
    var isLoading by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(Unit){
        scope.launch {
            taskvm.getTaskOverview(
                setTasks = {
                    overView = it
                },
                onSuccess = {
                    isLoading = false
                }
            )
        }
    }

    ExpandableCard(
        title = "Tasks Overview",
        description = "Departmental tasks status at a glance"
    ) {
        if(isLoading){
            CircularLoadingBasic("Loading Task Overview")
        }
        else{
            Column {
//                FieldCard(
//                    label = "Accepted",
//                    text = overView.accepted.toString()
//                )
//                FieldCard(
//                    label = "Pending",
//                    text = overView.pending.toString()
//                )
//                FieldCard(
//                    label = "Completed",
//                    text = overView.completed.toString()
//                )
//                FieldCard(
//                    label = "Due Tomorrow",
//                    text = overView.dueTomorrow.toString()
//                )
                TaskGraph(
                    taskCounts = overView
                )
            }
        }
    }

}