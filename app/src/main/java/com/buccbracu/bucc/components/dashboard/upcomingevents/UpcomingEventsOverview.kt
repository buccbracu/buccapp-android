package com.buccbracu.bucc.components.dashboard.upcomingevents

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.buccbracu.bucc.backend.remote.models.TaskOverview
import com.buccbracu.bucc.backend.viewmodels.TaskVM
import com.buccbracu.bucc.components.CircularLoadingBasic
import com.buccbracu.bucc.components.ExpandableCard
import com.buccbracu.bucc.components.dashboard.taskoverview.TaskGraph
import kotlinx.coroutines.launch

@SuppressLint("MutableCollectionMutableState")
@Composable
fun UpcomingEventsOverview(

){

//    val taskvm: TaskVM = hiltViewModel()
//    var overView by remember{
//        mutableStateOf(TaskOverview())
//    }
    val scope = rememberCoroutineScope()
    var isLoading by remember {
        mutableStateOf(true)
    }
//    LaunchedEffect(Unit){
//        scope.launch {
//            taskvm.getTaskOverview(
//                setTasks = {
//                    overView = it
//                },
//                onSuccess = {
//                    isLoading = false
//                }
//            )
//        }
//    }

    ExpandableCard(
        title = "Upcoming Events",
        description = "Upcoming events at a glance"
    ) {
        if(isLoading){
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Column(
                    modifier = Modifier
                        .padding(vertical = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Filled.Construction,
                        contentDescription = "Under Construction",
                        modifier = Modifier
                            .size(50.dp),
                        tint = MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = "This section is under construction",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.W700
                    )
                }
            }
        }
        else{
            Column {


            }
        }
    }

}