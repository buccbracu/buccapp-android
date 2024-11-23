package com.buccbracu.bucc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.buccbracu.bucc.backend.local.models.User.ProfileSocial
import com.buccbracu.bucc.backend.local.models.emptyMember
import com.buccbracu.bucc.backend.viewmodels.UserVM
import com.buccbracu.bucc.components.EditableTextField
import io.realm.kotlin.ext.realmListOf
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Profile(session: Session) {
    val context = LocalContext.current
    val uservm: UserVM = hiltViewModel()
    val scope = rememberCoroutineScope()

    var profileData by remember {
        mutableStateOf(uservm.profileData.value)
    }
    var edit by remember{
        mutableStateOf(false)
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

    val (memberSkills, setMemberSkills) = remember { mutableStateOf(realmListOf<String>()) }
    val (memberSocials, setMemberSocials) = remember { mutableStateOf<ProfileSocial?>(null) }

    val (facebook, setFacebook) = remember { mutableStateOf("") }
    val (linkedin, setLinkedin) = remember { mutableStateOf("") }
    val (github, setGithub) = remember { mutableStateOf("") }

    LaunchedEffect(profileData) {

        if(profileData != null){
            val data = profileData!!
            val socials = data.memberSocials!!

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

            setFacebook(socials.Facebook)
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
                    .padding(top = 30.dp)
            ){
                item {
                    Box(modifier = Modifier
                        .fillMaxWidth(),
                        contentAlignment = Alignment.TopEnd
                    ){
                        Button(
                            onClick = {
                                edit = !edit
                            },

                            ) {
                            Text(if (edit) "Save" else "Edit")
                        }
                    }
                    EditableTextField(
                        text = name,
                        label = "Name",
                        isEditable = edit,
                        onEdit = { value -> setName(value) }
                    )

                    EditableTextField(
                        text = studentId,
                        label = "Student ID",
                        isEditable = edit,
                        onEdit = { value -> setStudentId(value) }
                    )

                    EditableTextField(
                        text = email,
                        label = "Email",
                        isEditable = edit,
                        onEdit = { value -> setEmail(value) }
                    )

                    EditableTextField(
                        text = buccDepartment,
                        label = "BUCC Department",
                        isEditable = edit,
                        onEdit = { value -> setBuccDepartment(value) }
                    )

                    EditableTextField(
                        text = designation,
                        label = "Designation",
                        isEditable = edit,
                        onEdit = { value -> setDesignation(value) }
                    )

                    EditableTextField(
                        text = personalEmail,
                        label = "Personal Email",
                        isEditable = edit,
                        onEdit = { value -> setPersonalEmail(value) }
                    )

                    EditableTextField(
                        text = contactNumber,
                        label = "Contact Number",
                        isEditable = edit,
                        onEdit = { value -> setContactNumber(value) }
                    )

                    EditableTextField(
                        text = joinedBracu,
                        label = "Joined Bracu",
                        isEditable = edit,
                        onEdit = { value -> setJoinedBracu(value) }
                    )

                    EditableTextField(
                        text = departmentBracu,
                        label = "Department at Bracu",
                        isEditable = edit,
                        onEdit = { value -> setDepartmentBracu(value) }
                    )

                    EditableTextField(
                        text = birthDate,
                        label = "Birth Date",
                        isEditable = edit,
                        onEdit = { value -> setBirthDate(value) }
                    )

                    EditableTextField(
                        text = bloodGroup,
                        label = "Blood Group",
                        isEditable = edit,
                        onEdit = { value -> setBloodGroup(value) }
                    )

                    EditableTextField(
                        text = gender,
                        label = "Gender",
                        isEditable = edit,
                        onEdit = { value -> setGender(value) }
                    )

                    EditableTextField(
                        text = emergencyContact,
                        label = "Emergency Contact",
                        isEditable = edit,
                        onEdit = { value -> setEmergencyContact(value) }
                    )

                    EditableTextField(
                        text = joinedBucc,
                        label = "Joined BUCC",
                        isEditable = edit,
                        onEdit = { value -> setJoinedBucc(value) }
                    )

                    EditableTextField(
                        text = lastPromotion,
                        label = "Last Promotion",
                        isEditable = edit,
                        onEdit = { value -> setLastPromotion(value) }
                    )

                    EditableTextField(
                        text = memberStatus,
                        label = "Member Status",
                        isEditable = edit,
                        onEdit = { value -> setMemberStatus(value) }
                    )

                    EditableTextField(
                        text = facebook,
                        label = "Facebook",
                        isEditable = edit, // Set based on your edit condition
                        onEdit = { setFacebook(it) }
                    )

                    EditableTextField(
                        text = linkedin,
                        label = "LinkedIn",
                        isEditable = edit, // Set based on your edit condition
                        onEdit = { setLinkedin(it) }
                    )

                    EditableTextField(
                        text = github,
                        label = "GitHub",
                        isEditable = edit, // Set based on your edit condition
                        onEdit = { setGithub(it) }
                    )

                }
            }
        }
    }
}

