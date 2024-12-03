package com.buccbracu.bucc.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.buccbracu.bucc.R
import com.buccbracu.bucc.backend.local.preferences.Preferences
import com.buccbracu.bucc.backend.viewmodels.LoginVM
import com.buccbracu.bucc.backend.viewmodels.UserVM
import com.buccbracu.bucc.components.models.NavDrawerItem
import kotlinx.coroutines.launch


@Composable
fun NavDrawer(
    scrollState: ScrollState,
    selectedIndex: Int,
    onClick: (item: NavDrawerItem) -> Unit,
    login: Boolean,
    darkMode: Boolean
){
    val items = if(login) NavDrawerItem.navDrawerItemsLogin else NavDrawerItem.navDrawerItemsGuest
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .width(250.dp)
    ) {

        MiniProfile()

        Row(
            modifier = Modifier
                .fillMaxWidth(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "BUCC",
                color = Color(28, 48, 92),
                modifier = Modifier
                    .padding(start = 16.dp, end = 20.dp, bottom = 10.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            DarkModeToggle(
                darkModeEnabled = darkMode,
                onChange = {
                    scope.launch {
                        Preferences.get().setDarkMode(!darkMode)
                    }
                }
            )

        }


        items.forEachIndexed{ index, item ->
            when {
                item.isDivider -> {
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(bottom = 20.dp, top = 20.dp)
                    )
                }

                item.isHeader -> {
                    Text(
                        text = item.title,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                            .padding(start = 20.dp, bottom = 20.dp, top = 20.dp)
                    )
                }

                else -> {
                    NavigationDrawerItem(
                        label = { Text(text = item.title) },
                        selected = selectedIndex == index,
                        onClick = { onClick(item) },
                        icon = {
                            if (selectedIndex == index){
                                Icon(imageVector = item.selectedIcon!!, contentDescription = item.title)
                            }
                            else{
                                Icon(imageVector = item.unselectedIcon!!, contentDescription = item.title)
                            }
                        }
                    )
                }
            }

        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MiniProfile() {

    val uservm: UserVM = hiltViewModel()
    val dataTemp = uservm.profileData.value
    val profileData by uservm.profileData.collectAsState(initial = dataTemp)

    Column(
        modifier = Modifier
            .padding(start = 16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(0.87f),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            AsyncImage(
                model =
                if (profileData == null || profileData!!.profileImage == "") R.drawable.empty_person
                else profileData!!.profileImage,
                contentDescription = "Profile image",
                modifier = Modifier
                    .size(100.dp)
                    .padding(10.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillWidth
            )
            Text(
                text =
                if (profileData == null || profileData!!.name == "") "Login to See Profile"
                else profileData!!.name,
                modifier = Modifier
                    .padding(top = 10.dp),
                fontWeight = FontWeight.W900,
            )
            Text(
                text =
                if (profileData == null || profileData!!.buccDepartment == "") "Login to See BUCC Designation"
                else profileData!!.designation,
                modifier = Modifier
                    .padding(top = 5.dp),
                fontWeight = FontWeight.W700,
                fontSize = 12.sp
            )
            Text(
                text =
                if (profileData == null || profileData!!.buccDepartment == "") "Login to See BUCC Department"
                else profileData!!.buccDepartment,
                modifier = Modifier
                    .padding(top = 0.dp),
                fontWeight = FontWeight.W500,
                fontSize = 10.sp
            )
        }


    }
    HorizontalDivider(
        modifier = Modifier
            .padding(bottom = 20.dp, top = 20.dp)
    )

}