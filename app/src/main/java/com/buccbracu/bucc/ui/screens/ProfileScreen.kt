package com.buccbracu.bucc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.buccbracu.bucc.backend.local.models.Session
import com.buccbracu.bucc.backend.local.models.emptyProfile
import com.buccbracu.bucc.backend.viewmodels.UserVM
import com.buccbracu.bucc.components.EditableTextField
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition", "StateFlowValueCalledInComposition")
@Composable
fun Profile(session: Session) {
    val context = LocalContext.current
    val uservm: UserVM = hiltViewModel()
    val scope = rememberCoroutineScope()

    val dataTemp = uservm.profileData.value
    val profileData by uservm.profileData.collectAsState(initial = dataTemp)
    var edit by remember{
        mutableStateOf(false)
    }
    val tempMember by remember{
        mutableStateOf(uservm.patchMemberFromProfile(profileData!!))
    }

    var (name, setName) = rememberSaveable { mutableStateOf("") }
    var (studentId, setStudentId) = rememberSaveable { mutableStateOf("") }
    var (email, setEmail) = rememberSaveable { mutableStateOf("") }
    var (buccDepartment, setBuccDepartment) = rememberSaveable { mutableStateOf("") }
    var (designation, setDesignation) = rememberSaveable { mutableStateOf("") }
    var (personalEmail, setPersonalEmail) = rememberSaveable { mutableStateOf("") }
    var (contactNumber, setContactNumber) = rememberSaveable { mutableStateOf("") }
    var (joinedBracu, setJoinedBracu) = rememberSaveable { mutableStateOf("") }
    var (departmentBracu, setDepartmentBracu) = rememberSaveable { mutableStateOf("") }
    var (profileImage, setProfileImage) = rememberSaveable { mutableStateOf("") }
    var (birthDate, setBirthDate) = rememberSaveable { mutableStateOf("") }
    var (bloodGroup, setBloodGroup) = rememberSaveable { mutableStateOf("") }
    var (gender, setGender) = rememberSaveable { mutableStateOf("") }
    var (emergencyContact, setEmergencyContact) = rememberSaveable { mutableStateOf("") }
    var (joinedBucc, setJoinedBucc) = rememberSaveable { mutableStateOf("") }
    var (lastPromotion, setLastPromotion) = rememberSaveable { mutableStateOf("") }
    var (memberStatus, setMemberStatus) = rememberSaveable { mutableStateOf("") }

    val (memberSkills, setMemberSkills) = remember { mutableStateOf(listOf<String>()) }

    val (facebook, setFacebook) = remember { mutableStateOf("") }
    val (linkedin, setLinkedin) = remember { mutableStateOf("") }
    val (github, setGithub) = remember { mutableStateOf("") }

    LaunchedEffect(profileData) {
        println("LAUNCHED EFFECT ${profileData?.personalEmail}")
        if(profileData != null){
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
            setLastPromotion(data.lastPromotion)
            setMemberStatus(data.memberStatus)

            setFacebook(socials!!.Facebook)
            setGithub(socials.Github)
            setLinkedin(socials.LinkedIn)
        }

    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        if(profileData != null){
            LazyColumn(
                modifier = Modifier
                    .padding(top = 70.dp)
            ){
                item {
                    Box(modifier = Modifier
                        .fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ){
                        Row {
                            Button(
                                onClick ={
                                    scope.launch {
                                        uservm.refreshProfile()
                                    }
                                }
                            ) {
                                Text("Refresh")
                            }
                            Button(
                                onClick = {
                                    if(edit){
                                        scope.launch {
                                            uservm.updateUserProfile(
                                                profileData = tempMember,
                                            )
                                        }
                                    }
                                    edit = !edit
                                },

                                ) {
                                Text(if (edit) "Save" else "Edit")
                            }
                        }
                    }
                    EditableTextField(
                        text = name,
                        label = "Name",
                        isEditable = false,
                        onEdit = { value ->

                        }
                    )

                    EditableTextField(
                        text = studentId,
                        label = "Student ID",
                        isEditable = false,
                        onEdit = { value ->

                        }
                    )

                    EditableTextField(
                        text = email,
                        label = "Email",
                        isEditable = false,
                        onEdit = { value ->

                        }
                    )

                    EditableTextField(
                        text = buccDepartment,
                        label = "BUCC Department",
                        isEditable = false,
                        onEdit = { value ->

                        }
                    )

                    EditableTextField(
                        text = designation,
                        label = "Designation",
                        isEditable = false,
                        onEdit = { value ->

                        }
                    )

                    EditableTextField(
                        text = personalEmail,
                        label = "Personal Email",
                        isEditable = edit,
                        onEdit = { value ->
                            setPersonalEmail(value)
                            tempMember!!.personalEmail = value
                        }
                    )

                    EditableTextField(
                        text = contactNumber,
                        label = "Contact Number",
                        isEditable = edit,
                        onEdit = { value ->
                            setContactNumber(value)
                            tempMember!!.contactNumber = value
                        }
                    )

                    EditableTextField(
                        text = joinedBracu,
                        label = "Joined BRACU",
                        isEditable = false,
                        onEdit = { value ->

                        }
                    )

                    EditableTextField(
                        text = departmentBracu,
                        label = "Department BRACU",
                        isEditable = false,
                        onEdit = { value ->

                        }
                    )


                    EditableTextField(
                        text = birthDate,
                        label = "Birth Date",
                        isEditable = edit,
                        onEdit = { value ->
                            setBirthDate(value)
                            tempMember!!.birthDate = value
                        }
                    )

                    EditableTextField(
                        text = bloodGroup,
                        label = "Blood Group",
                        isEditable = edit,
                        onEdit = { value ->
                            setBloodGroup(value)
                            tempMember!!.bloodGroup = value
                        }
                    )

                    EditableTextField(
                        text = gender,
                        label = "Gender",
                        isEditable = edit,
                        onEdit = { value ->
                            setGender(value)
                            tempMember!!.gender = value
                        }
                    )

                    EditableTextField(
                        text = emergencyContact,
                        label = "Emergency Contact",
                        isEditable = edit,
                        onEdit = { value ->
                            setEmergencyContact(value)
                            tempMember!!.emergencyContact = value
                        }
                    )

                    EditableTextField(
                        text = joinedBucc,
                        label = "Joined BUCC",
                        isEditable = false,
                        onEdit = { value ->

                        }
                    )

                    EditableTextField(
                        text = lastPromotion,
                        label = "Last Promotion",
                        isEditable = false,
                        onEdit = { value ->

                        }
                    )

                    EditableTextField(
                        text = memberStatus,
                        label = "Member Status",
                        isEditable = false,
                        onEdit = { value ->

                        }
                    )

                    EditableTextField(
                        text = github,
                        label = "Github",
                        isEditable = edit,
                        onEdit = { value ->
                            setGithub(value)
                            tempMember!!.memberSocials.Github = value
                        }
                    )

                    EditableTextField(
                        text = linkedin,
                        label = "LinkedIn",
                        isEditable = edit,
                        onEdit = { value ->
                            setLinkedin(value)
                            tempMember!!.memberSocials.Linkedin = value
                        }
                    )

                    EditableTextField(
                        text = facebook,
                        label = "Facebook",
                        isEditable = edit,
                        onEdit = { value ->
                            setFacebook(value)
                            tempMember!!.memberSocials.Facebook = value
                        }
                    )


                }
            }
        }
    }
}

