package com.buccbracu.bucc.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.buccbracu.bucc.backend.remote.models.Contributor
import com.buccbracu.bucc.backend.viewmodels.ContributorVM
import com.buccbracu.bucc.components.NoButtonCircularLoadingDialog
import kotlinx.coroutines.launch

@Composable
fun ContributorScreen(){
    val contributorvm: ContributorVM = hiltViewModel()

    var list by remember{
        mutableStateOf<List<Contributor>?>(emptyList())
    }
    var isLoading by remember{
        mutableStateOf(true)
    }
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        scope.launch {
            contributorvm.getContributors { data ->
                list = data
                isLoading = false

            }
        }
    }
    if(isLoading){
        NoButtonCircularLoadingDialog(
            title = "Loading Contributors",
            message = "Please Wait..."
        )
    }
    else{
        if(list != null ){
            LazyColumn(
                modifier = Modifier
                    .padding(top = 70.dp, bottom = 50.dp)
            ) {
                items(list!!) { person ->
                    ContributorCard(person)
                }
            }
        }
    }

}


@Composable
fun ContributorCard(data: Contributor){
    val context = LocalContext.current
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainerHigh),
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.html_url))
            context.startActivity(intent)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = data.avatar_url,
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape),
                alignment = Alignment.Center
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                Text(
                    text = data.login,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W900
                )
            }
        }
    }
}
