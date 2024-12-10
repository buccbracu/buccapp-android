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
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition", "StateFlowValueCalledInComposition")
@Composable
fun Profile() {
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
                        .padding(8.dp), // Added padding for scrolling
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.TopEnd
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
                            ) {
                                Button(
                                    onClick = {
                                        scope.launch {
                                            uservm.refreshProfile()
                                        }
                                    }
                                ) {
                                    Text("Refresh")
                                }

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
                                    if (edit) {
                                        Button(
                                            onClick = {
                                                edit = !edit
                                            }
                                        ) {
                                            Text("Cancel")
                                        }
                                    }
                                }
                            }
                        }

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
                            fontWeight = FontWeight.W600,
                            fontSize = 12.sp
                        )
                        Text(
                            text =
                            if (profileData == null || profileData!!.buccDepartment == "") "Login to See BUCC Department"
                            else profileData!!.buccDepartment,
                            modifier = Modifier
                                .padding(top = 0.dp),
                            fontWeight = FontWeight.W400,
                            fontSize = 10.sp
                        )

                        // Group: Personal Information
                        Text("Personal Information", modifier = Modifier.padding(10.dp))
                        EditableTextField(text = name, label = "Name", isEditable = false, onEdit = {})

                        EditableTextField(text = studentId, label = "Student ID", isEditable = false, onEdit = {})
                        EditableTextField(text = email, label = "Email", isEditable = false, onEdit = {})
                        EditableTextField(text = personalEmail, label = "Personal Email", isEditable = edit) {
                            setPersonalEmail(it)
                            tempMember!!.personalEmail = it
                        }
                        EditableTextField(text = contactNumber, label = "Contact Number", isEditable = edit) {
                            setContactNumber(it)
                            tempMember!!.contactNumber = it
                        }
                        EditableTextField(text = birthDate, label = "Birth Date", isEditable = edit) {
                            setBirthDate(it)
                            tempMember!!.birthDate = it
                        }

                        // Group: Academic Information
                        Text("Academic Information", modifier = Modifier.padding(8.dp))
                        EditableTextField(text = joinedBracu, label = "Joined BRACU", isEditable = false, onEdit = {})
                        EditableTextField(text = departmentBracu, label = "Department BRACU", isEditable = false, onEdit = {})
                        EditableTextField(text = buccDepartment, label = "BUCC Department", isEditable = false, onEdit = {})
                        EditableTextField(text = designation, label = "Designation", isEditable = false, onEdit = {})

                        // Group: Membership Information
                        Text("Membership Information", modifier = Modifier.padding(8.dp))
                        EditableTextField(text = joinedBucc, label = "Joined BUCC", isEditable = false, onEdit = {})
                        EditableTextField(text = memberStatus, label = "Member Status", isEditable = false, onEdit = {})

                        // Group: Social Links
                        Text("Social Links", modifier = Modifier.padding(8.dp))
                        EditableTextField(text = github, label = "Github", isEditable = edit) {
                            setGithub(it)
                            tempMember!!.memberSocials.Github = it
                        }
                        EditableTextField(text = linkedin, label = "LinkedIn", isEditable = edit) {
                            setLinkedin(it)
                            tempMember!!.memberSocials.Linkedin = it
                        }
                        EditableTextField(text = facebook, label = "Facebook", isEditable = edit) {
                            setFacebook(it)
                            tempMember!!.memberSocials.Facebook = it
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LabeledText(label:String, text: String){
    Row{
        Text(
            text= "$label : ",
            fontWeight = FontWeight.W700
        )
        Text(text)
    }
}
