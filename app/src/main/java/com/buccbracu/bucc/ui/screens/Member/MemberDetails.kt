package com.buccbracu.bucc.ui.screens.Member

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Cake
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import com.buccbracu.bucc.backend.remote.models.Member
import com.buccbracu.bucc.backend.viewmodels.UserVM
import com.buccbracu.bucc.components.ButtonLoading
import com.buccbracu.bucc.components.EditableTextField
import kotlinx.coroutines.launch

@Composable
fun MemberDetails(
    allFields: Boolean,
    member: Member
) {
    var edit by remember { mutableStateOf(false) }
    val tempMember by remember { mutableStateOf(member.copy()) }
    var isLoading by remember { mutableStateOf(false) }

    val (name, setName) = rememberSaveable { mutableStateOf(member.name) }
    val (studentId, setStudentId) = rememberSaveable { mutableStateOf(member.studentId) }
    val (email, setEmail) = rememberSaveable { mutableStateOf(member.email) }
    val (buccDepartment, setBuccDepartment) = rememberSaveable { mutableStateOf(member.buccDepartment) }
    val (designation, setDesignation) = rememberSaveable { mutableStateOf(member.designation) }
    val (personalEmail, setPersonalEmail) = rememberSaveable { mutableStateOf(member.personalEmail) }
    val (contactNumber, setContactNumber) = rememberSaveable { mutableStateOf(member.contactNumber) }
    val (joinedBracu, setJoinedBracu) = rememberSaveable { mutableStateOf(member.joinedBracu) }
    val (departmentBracu, setDepartmentBracu) = rememberSaveable { mutableStateOf(member.departmentBracu) }
    val (profileImage, setProfileImage) = rememberSaveable { mutableStateOf(member.profileImage) }
    val (birthDate, setBirthDate) = rememberSaveable { mutableStateOf(member.birthDate.slice(0..9)) }
    val (bloodGroup, setBloodGroup) = rememberSaveable { mutableStateOf(member.bloodGroup) }
    val (gender, setGender) = rememberSaveable { mutableStateOf(member.gender) }
    val (emergencyContact, setEmergencyContact) = rememberSaveable { mutableStateOf(member.emergencyContact) }
    val (joinedBucc, setJoinedBucc) = rememberSaveable { mutableStateOf(member.joinedBucc) }
    val (memberStatus, setMemberStatus) = rememberSaveable { mutableStateOf(member.memberStatus) }

    val (facebook, setFacebook) = rememberSaveable { mutableStateOf(member.memberSocials.Facebook) }
    val (linkedin, setLinkedin) = rememberSaveable { mutableStateOf(member.memberSocials.Linkedin) }
    val (github, setGithub) = rememberSaveable { mutableStateOf(member.memberSocials.Github) }

    Box(
        modifier = Modifier
            .padding(bottom = 50.dp)
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                AsyncImage(
                    model =
                    if (profileImage.isEmpty()) R.drawable.empty_person
                    else profileImage,
                    contentDescription = "Profile image",
                    modifier = Modifier
                        .size(100.dp)
                        .padding(10.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.FillWidth
                )

                // Profile Details
                Text(
                    text = if (name.isEmpty()) "No Name Provided" else name,
                    modifier = Modifier.padding(top = 10.dp),
                    fontWeight = FontWeight.W900
                )
                // Group: Personal Information
                Text("Personal Information", modifier = Modifier.padding(10.dp))
                // Modify the EditableTextField calls to include leading icons
                if (allFields) {
                    EditableTextField(
                        text = name,
                        label = "Name",
                        isEditable = false,
                        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Name Icon") },
                        onEdit = {}
                    )

                    EditableTextField(
                        text = studentId,
                        label = "Student ID",
                        isEditable = false,
                        leadingIcon = { Icon(imageVector = Icons.Default.Badge, contentDescription = "Student ID Icon") },
                        onEdit = {}
                    )
                }

                EditableTextField(
                    text = email,
                    label = "Email",
                    isEditable = false,
                    leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "Email Icon") },
                    onEdit = {}
                )

                if (allFields) {
                    EditableTextField(
                        text = personalEmail,
                        label = "Personal Email",
                        isEditable = edit,
                        leadingIcon = { Icon(imageVector = Icons.Default.AlternateEmail, contentDescription = "Personal Email Icon") }
                    ) {
                        setPersonalEmail(it)
                        tempMember.personalEmail = it
                    }

                    EditableTextField(
                        text = contactNumber,
                        label = "Contact Number",
                        isEditable = edit,
                        leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = "Contact Number Icon") }
                    ) {
                        setContactNumber(it)
                        tempMember.contactNumber = it
                    }

                    EditableTextField(
                        text = birthDate,
                        label = "Birth Date",
                        isEditable = edit,
                        leadingIcon = { Icon(imageVector = Icons.Default.Cake, contentDescription = "Birth Date Icon") }
                    ) {
                        setBirthDate(it)
                        tempMember.birthDate = it
                    }
                }

                if (allFields) {
                    // Group: Academic Information
                    Text("Academic Information", modifier = Modifier.padding(8.dp))
                    EditableTextField(
                        text = joinedBracu,
                        label = "Joined BRACU",
                        isEditable = false,
                        leadingIcon = { Icon(imageVector = Icons.Default.CalendarToday, contentDescription = "Joined BRACU Icon") },
                        onEdit = {}
                    )
                    EditableTextField(
                        text = departmentBracu,
                        label = "Department BRACU",
                        isEditable = false,
                        leadingIcon = { Icon(imageVector = Icons.Default.School, contentDescription = "Department BRACU Icon") },
                        onEdit = {}
                    )
                }

// Group: Membership Information
                Text("Membership Information", modifier = Modifier.padding(8.dp))

                EditableTextField(
                    text = buccDepartment,
                    label = "BUCC Department",
                    isEditable = false,
                    leadingIcon = { Icon(imageVector = Icons.Default.Group, contentDescription = "BUCC Department Icon") },
                    onEdit = {}
                )

                EditableTextField(
                    text = designation,
                    label = "Designation",
                    isEditable = false,
                    leadingIcon = { Icon(imageVector = Icons.Default.Work, contentDescription = "Designation Icon") },
                    onEdit = {}
                )

                if (allFields) {
                    EditableTextField(
                        text = joinedBucc,
                        label = "Joined BUCC",
                        isEditable = false,
                        leadingIcon = { Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = "Joined BUCC Icon") },
                        onEdit = {}
                    )
                    EditableTextField(
                        text = memberStatus,
                        label = "Member Status",
                        isEditable = false,
                        leadingIcon = { Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Member Status Icon") },
                        onEdit = {}
                    )
                }

// Group: Social Links
                Text("Social Links", modifier = Modifier.padding(8.dp))

                EditableTextField(
                    text = github,
                    label = "Github",
                    isEditable = edit,
                    leadingIcon = { Icon(imageVector = Icons.Default.Code, contentDescription = "Github Icon") }
                ) {
                    setGithub(it)
                    tempMember.memberSocials.Github = it
                }

                EditableTextField(
                    text = linkedin,
                    label = "LinkedIn",
                    isEditable = edit,
                    leadingIcon = { Icon(imageVector = Icons.Default.Business, contentDescription = "LinkedIn Icon") }
                ) {
                    setLinkedin(it)
                    tempMember.memberSocials.Linkedin = it
                }

                EditableTextField(
                    text = facebook,
                    label = "Facebook",
                    isEditable = edit,
                    leadingIcon = { Icon(imageVector = Icons.Default.Facebook, contentDescription = "Facebook Icon") }
                ) {
                    setFacebook(it)
                    tempMember.memberSocials.Facebook = it
                }

            }
        }
    }
}
