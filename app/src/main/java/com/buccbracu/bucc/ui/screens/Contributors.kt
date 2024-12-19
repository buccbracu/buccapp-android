package com.buccbracu.bucc.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.buccbracu.bucc.R
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
        if (list != null) {
            LazyColumn(
                modifier = Modifier
                    .padding(top = 70.dp, bottom = 50.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = stringResource(R.string.about_us),
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }
                item {
                    Text(
                        text = "BUCC Mobile Contributors",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W700
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                }

                // Add contributors in a grid-like structure
                items(list!!.chunked(3)) { rowItems ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        rowItems.forEach { person ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(4.dp)
                            ) {
                                ContributorCard(person)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(0.dp))
                }

            }
        }

    }

}


@Composable
fun ContributorCard(data: Contributor){
    val context = LocalContext.current
    Card(
        modifier = Modifier,
//            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),
//        elevation = CardDefaults.cardElevation(10.dp),
//        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainerHigh),
        colors = CardDefaults.cardColors(Color.Transparent),
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(data.html_url))
            context.startActivity(intent)
        }
    ) {


        Column(
            modifier = Modifier,
//                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                model = data.avatar_url,
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                alignment = Alignment.Center,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text =data.login,
//                if(data.login.length >12) "${data.login.slice(0..9)}..."
//                else data.login,
                fontSize = 12.sp,
                fontWeight = FontWeight.W900,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }

    }
}
