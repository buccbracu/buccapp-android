package com.buccbracu.bucc

import com.buccbracu.bucc.backend.local.models.User.Profile
import com.buccbracu.bucc.backend.local.models.User.ProfileSocial
import com.buccbracu.bucc.backend.remote.models.Member
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

val skills = listOf(
    "Android Development",
    "iOS Development",
    "Web Development",
    "Frontend Development",
    "Backend Development",
    "Full Stack Development",
    "Game Development",
    "UI/UX Design",
    "Graphic Design",
    "Product Management",
    "Project Management",
    "Quality Assurance",
    "DevOps",
    "Data Science",
    "Machine Learning",
    "Artificial Intelligence",
    "Cybersecurity",
    "Blockchain",
    "IoT",
    "AR/VR",
    "Cloud Computing",
    "Robotics",
    "JavaScript",
    "TypeScript",
    "React",
    "Angular",
    "Vue",
    "Node.js",
    "Express",
    "NestJS",
    "Python",
    "Django",
    "Flask",
    "Ruby",
    "Java",
    "Spring",
    "Kotlin",
    "Swift",
    "Flutter",
    "Dart",
    "C#",
    "Unity",
    "C++",
    "PHP",
    "Laravel",
    "Ruby on Rails",
    "HTML",
    "CSS",
    "Sass",
    "Less",
    "Bootstrap",
    "Tailwind CSS",
    "Material-UI",
    "Ant Design",
    "GraphQL",
    "Apollo",
    "REST API",
    "Firebase",
    "MongoDB",
    "MySQL",
    "PostgreSQL",
    "SQLite",
    "Docker",
    "Kubernetes",
    "Git",
    "AWS",
    "Azure",
    "Google Cloud",
    "Heroku",
    "Netlify",
    "Vercel",
    "DigitalOcean",
    "Socket.IO",
    "WebSockets",
    "WebRTC"
)

val alwaysPermittedDepartments = listOf(
    "Research and Development",
    "Governing Body",
)
val alwaysPermittedDesignations = listOf(
    "Director",
    "Assistant Director",
    "President",
    "Vice President",
    "General Secretary",
    "Treasurer",
)

val allDepartments = listOf(
    "Governing Body",
    "Communication and Marketing",
    "Creative",
    "Event Management",
    "Finance",
    "Human Resources",
    "Press Release and Publications",
    "Research and Development"
)
val allDesignations = listOf(
    "President",
    "Vice President",
    "General Secretary",
    "Treasurer",
    "Director",
    "Assistant Director",
    "Senior Executive",
    "Executive",
    "General Member",
)


val ebgb = listOf(
    "President",
    "Vice President",
    "General Secretary",
    "Treasurer",
    "Director",
    "Assistant Director",
)

val gb = ebgb.slice(0..3)
val eb = ebgb.slice(4..5)

val gmex = listOf(
    "Executive",
    "General Member"
)

val bloodGroups = listOf(
    "A+",
    "A-",
    "B+",
    "B-",
    "AB+",
    "AB-",
    "O+",
    "O-"
)

fun shortForm(abbrv: String): String {
    val deptMap = mapOf(
        "Governing Body" to "GB",
        "Communication and Marketing" to "C&M",
        "Creative" to "Creative",
        "Event Management" to "EM",
        "Finance" to "Finance",
        "Human Resources" to "HR",
        "Press Release and Publications" to "PR",
        "Research and Development" to "R&D"
    )

    val designationMap = mapOf(
        "President" to "P",
        "Vice President" to "VP",
        "General Secretary" to "GS",
        "Treasurer" to "TR",
        "Director" to "D",
        "Assistant Director" to "AD",
        "Senior Executive" to "SE",
        "Executive" to "E",
        "General Member" to "GM"
    )

    // Check the maps for the short form
    return deptMap[abbrv] ?: designationMap[abbrv] ?: "UNKNOWN"
}


fun allMemberPermission(dept: String, des: String): Boolean {
    println("PERMISSION $dept $des")
    return gb.contains(des) || dept == "Human Resources" || (dept == "Research and Development" && eb.contains(des))
}


fun formatDate(inputDate: String): String {
    // Define the input and output date formats
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd MMMM, yyyy", Locale.getDefault())

    return try {
        // Parse the input date and format it to the desired output format
        val date = inputFormat.parse(inputDate)
        outputFormat.format(date!!)
    } catch (e: Exception) {
        // Return a default value or handle the error if parsing fails
        "Invalid date"
    }
}



fun prevYearsList(prev: Int = 4): List<String> {
    val terms = listOf("Fall", "Spring", "Summer")
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)

    return (0..prev).flatMap { yearOffset ->
        terms.map { term -> "$term ${currentYear - yearOffset}" }
    }
}

fun notIgnoredRoute(route: String?): Boolean {

    val ignoredRoutes = listOf(
        "Login Landing",
        "Login",
        "Edit Profile"
    )

    return if(route == null) false
    else !ignoredRoutes.contains(route) && !route.contains("BlogView")


}

fun memberToProfile(member: Member): Profile {
    return Profile().apply {
        _id = member._id
        name = member.name
        studentId = member.studentId
        email = member.email
        buccDepartment = member.buccDepartment
        designation = member.designation
        personalEmail = member.personalEmail
        contactNumber = member.contactNumber
        joinedBracu = member.joinedBracu
        departmentBracu = member.departmentBracu
        profileImage = member.profileImage
        birthDate = member.birthDate
        bloodGroup = member.bloodGroup
        gender = member.gender
        emergencyContact = member.emergencyContact
        joinedBucc = member.joinedBucc
        lastPromotion = member.lastPromotion
        memberStatus = member.memberStatus
        memberSkills.addAll(member.memberSkills)
        // Convert the MemberSocials object to a RealmList of ProfileSocial
        memberSocials = ProfileSocial().apply {
            Facebook = member.memberSocials.Facebook
            Github = member.memberSocials.Github
            LinkedIn = member.memberSocials.Linkedin
        }
    }
}