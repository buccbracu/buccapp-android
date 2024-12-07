package com.buccbracu.bucc.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.buccbracu.bucc.R
import com.buccbracu.bucc.backend.local.models.Session
import com.buccbracu.bucc.backend.remote.models.Contributor
import com.buccbracu.bucc.backend.remote.models.Member
import com.buccbracu.bucc.backend.viewmodels.ContributorVM
import com.buccbracu.bucc.components.NoButtonCircularLoadingDialog
import kotlinx.coroutines.launch

val bucc_desc = "A community for tech enthusiasts from BRAC University, where we explore the latest advancements in computer science and technology."

@Composable
fun AboutUs(){
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
                    .padding(top = 70.dp, bottom = 30.dp)
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


@Composable
fun AboutClub(){
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(top = 80.dp)
    ){
        GradientText(text = "BRAC UNIVERSITY COMPUTER CLUB")
        Text(
            text = bucc_desc,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp,start = 16.dp,end = 16.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.hero_banner_image),
            contentDescription = "Panel24",
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .clip(RoundedCornerShape(20.dp))

        )


    }

}

@Composable
fun GradientText(text: String) {
    val gradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF3B82F6), // Blue-500
            Color(0xFF2DD4BF)  // Teal-400
        )
    )

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    // Adjust the text size based on the screen width
    val fontSize = when {
        screenWidthDp >= 900 -> 64f
        screenWidthDp >= 600 -> 48f
        screenWidthDp >= 400 -> 26f
        else -> 24f
    }

        Text(
            text = text,
            style = TextStyle(
                fontSize = fontSize.sp,
                fontWeight = FontWeight.Bold,
                brush = gradient
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

}


