package com.buccbracu.bucc.ui.screens.Dashboard



import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.buccbracu.bucc.R
import com.buccbracu.bucc.backend.viewmodels.UserVM
import com.buccbracu.bucc.components.ButtonLoading
import com.buccbracu.bucc.components.DatePickerModal
import com.buccbracu.bucc.components.EditableTextField
import com.buccbracu.bucc.components.MovableFloatingActionButton
import com.buccbracu.bucc.ui.screens.Tasks.DateField
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition", "StateFlowValueCalledInComposition")
@Composable
fun EditProfile(navController: NavHostController) {
    val context = LocalContext.current
    val uservm: UserVM = hiltViewModel()
    val scope = rememberCoroutineScope()

    val dataTemp = uservm.profileData.value

    val profileData by uservm.profileData.collectAsState(initial = dataTemp)
    var showDatePicker by remember{
        mutableStateOf(false)
    }

    var edit by remember { mutableStateOf(true) }
    val tempMember by remember { mutableStateOf(uservm.patchMemberFromProfile(profileData!!)) }
    var isLoading by remember { mutableStateOf(false) }
    var isProfileLoading by remember { mutableStateOf(false) }
    val (personalEmail, setPersonalEmail) = rememberSaveable { mutableStateOf("") }
    val (contactNumber, setContactNumber) = rememberSaveable { mutableStateOf("") }
    val (birthDate, setBirthDate) = rememberSaveable { mutableStateOf("") }


    val (facebook, setFacebook) = remember { mutableStateOf("") }
    val (linkedin, setLinkedin) = remember { mutableStateOf("") }
    val (github, setGithub) = remember { mutableStateOf("") }

    LaunchedEffect(profileData) {
        if (profileData != null) {
            val data = profileData!!
            val socials = data.memberSocials
            setPersonalEmail(data.personalEmail)
            setContactNumber(data.contactNumber)
            setBirthDate(data.birthDate.slice(0..9))

            setFacebook(socials!!.Facebook)
            setGithub(socials.Github)
            setLinkedin(socials.LinkedIn)
        }
    }

    if (isProfileLoading) {
        // Loading UI (placeholder for future logic)
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Added padding for overall layout
            contentAlignment = Alignment.Center
        ) {
            if (profileData != null) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Spacer(modifier = Modifier.height(70.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Row(horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)) {
                            Button(
                                onClick = {
                                    if (edit) {
                                        scope.launch {
                                            isLoading = true
                                            uservm.updateUserProfile(
                                                profileData = tempMember,
                                                setLoading = { loading ->
                                                    isLoading = loading
                                                }
                                            )
                                        }
                                    }
                                    edit = !edit
                                },
                                enabled = !isLoading
                            ) {
                                if (isLoading) {
                                    ButtonLoading()
                                } else {
                                    Text(if (edit) "Save" else "Edit")
                                }
                            }
                        }
                    }
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(0.8f)
                            .padding(8.dp), // Added padding for scrolling
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        item {
                            AsyncImage(
                                model =
                                if (profileData!!.profileImage == "") R.drawable.empty_person
                                else profileData!!.profileImage,
                                contentDescription = "Profile image",
                                modifier = Modifier
                                    .size(100.dp)
                                    .padding(10.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.FillWidth
                            )
                            Text(
                                text = profileData!!.name,
                                modifier = Modifier
                                    .padding(top = 10.dp),
                                fontWeight = FontWeight.W900,
                            )
                            Text(
                                text = profileData!!.designation,
                                modifier = Modifier
                                    .padding(top = 5.dp),
                                fontWeight = FontWeight.W600,
                                fontSize = 12.sp
                            )
                            Text(
                                text = profileData!!.buccDepartment,
                                modifier = Modifier
                                    .padding(top = 0.dp),
                                fontWeight = FontWeight.W400,
                                fontSize = 10.sp
                            )

                            Text("Personal Information", modifier = Modifier.padding(10.dp))
                            EditableTextField(
                                text = personalEmail,
                                label = "Personal Email",
                                isEditable = edit
                            ) {
                                setPersonalEmail(it)
                                tempMember.personalEmail = it
                            }
                            EditableTextField(
                                text = contactNumber,
                                label = "Contact Number",
                                isEditable = edit
                            ) {
                                setContactNumber(it)
                                tempMember.contactNumber = it
                            }
                            DateField(
                                value = birthDate,
                                label = "Date of Birth"
                            ) {
                                showDatePicker = true
                            }


                            // Group: Social Links
                            Text("Social Links", modifier = Modifier.padding(8.dp))
                            EditableTextField(text = github, label = "Github", isEditable = edit) {
                                setGithub(it)
                                tempMember.memberSocials.Github = it
                            }
                            EditableTextField(
                                text = linkedin,
                                label = "LinkedIn",
                                isEditable = edit
                            ) {
                                setLinkedin(it)
                                tempMember.memberSocials.Linkedin = it
                            }
                            EditableTextField(
                                text = facebook,
                                label = "Facebook",
                                isEditable = edit
                            ) {
                                setFacebook(it)
                                tempMember.memberSocials.Facebook = it
                            }
                        }
                    }
                }
            }
        }
    }
    if(showDatePicker){
        DatePickerModal(
            onDateSelected = { date ->
                setBirthDate(date)
            },
            onDismiss = {
                showDatePicker = false
            }
        )
    }
    MovableFloatingActionButton(
        onClick = {
            navController.navigate("Dashboard")
        },
        icon = Icons.Filled.ArrowBackIosNew
    )
}

