package com.buccbracu.bucc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
import com.buccbracu.bucc.components.EditableTextField
import com.buccbracu.bucc.components.dashboard.ProfileCard
import com.buccbracu.bucc.components.dashboard.TaskOverview
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition", "StateFlowValueCalledInComposition")
@Composable
fun Dashboard() {
    val context = LocalContext.current
    val uservm: UserVM = hiltViewModel()
    val scope = rememberCoroutineScope()

    val dataTemp = uservm.profileData.value

    val profileData by uservm.profileData.collectAsState(initial = dataTemp)

    var edit by remember { mutableStateOf(false) }
    val tempMember by remember { mutableStateOf(uservm.patchMemberFromProfile(profileData!!)) }
    var isLoading by remember { mutableStateOf(false) }
    var isProfileLoading by remember { mutableStateOf(false) }
    val (name, setName) = rememberSaveable { mutableStateOf("") }
    val (studentId, setStudentId) = rememberSaveable { mutableStateOf("") }
    val (email, setEmail) = rememberSaveable { mutableStateOf("") }
    val (buccDepartment, setBuccDepartment) = rememberSaveable { mutableStateOf("") }
    val (designation, setDesignation) = rememberSaveable { mutableStateOf("") }
    val (personalEmail, setPersonalEmail) = rememberSaveable { mutableStateOf("") }
    val (contactNumber, setContactNumber) = rememberSaveable { mutableStateOf("") }
    val (joinedBracu, setJoinedBracu) = rememberSaveable { mutableStateOf("") }
    val (departmentBracu, setDepartmentBracu) = rememberSaveable { mutableStateOf("") }
    val (profileImage, setProfileImage) = rememberSaveable { mutableStateOf("") }
    val (birthDate, setBirthDate) = rememberSaveable { mutableStateOf("") }
    val (bloodGroup, setBloodGroup) = rememberSaveable { mutableStateOf("") }
    val (gender, setGender) = rememberSaveable { mutableStateOf("") }
    val (emergencyContact, setEmergencyContact) = rememberSaveable { mutableStateOf("") }
    val (joinedBucc, setJoinedBucc) = rememberSaveable { mutableStateOf("") }
    val (memberStatus, setMemberStatus) = rememberSaveable { mutableStateOf("") }


    val (facebook, setFacebook) = remember { mutableStateOf("") }
    val (linkedin, setLinkedin) = remember { mutableStateOf("") }
    val (github, setGithub) = remember { mutableStateOf("") }

    LaunchedEffect(profileData) {
        if (profileData != null) {
            val data = profileData!!
            val socials = data.memberSocials
            setName(data.name)
            setStudentId(data.studentId)
            setEmail(data.email)
            setBuccDepartment(data.buccDepartment)
            setDesignation(data.designation)
            setPersonalEmail(data.personalEmail)
            setContactNumber(data.contactNumber)
            setJoinedBracu(data.joinedBracu)
            setDepartmentBracu(data.departmentBracu)
            setProfileImage(data.profileImage)
            setBirthDate(data.birthDate)
            setBloodGroup(data.bloodGroup)
            setGender(data.gender)
            setEmergencyContact(data.emergencyContact)
            setJoinedBucc(data.joinedBucc)
            setMemberStatus(data.memberStatus)

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
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 70.dp)
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item{
                        ProfileCard(profileData!!)
                        TaskOverview()
                    }
                }
            }
        }
    }
}

