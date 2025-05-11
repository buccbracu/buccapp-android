package com.buccbracu.bucc.backend.adapters

import com.buccbracu.bucc.backend.remote.models.Member
import com.buccbracu.bucc.backend.remote.models.MemberSocials
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class MemberAdapter {

    @FromJson
    fun fromJson(json: Map<String, Any?>): Member {
        return Member(
            _id = json["_id"] as? String ?: "",
            name = json["name"] as? String ?: "",
            studentId = json["studentId"] as? String ?: "",
            email = json["email"] as? String ?: "",
            buccDepartment = json["buccDepartment"] as? String ?: "",
            designation = json["designation"] as? String ?: "",
            personalEmail = json["personalEmail"] as? String ?: "",
            contactNumber = json["contactNumber"] as? String ?: "",
            joinedBracu = json["joinedBracu"] as? String ?: "",
            departmentBracu = json["departmentBracu"] as? String ?: "",
            profileImage = json["profileImage"] as? String ?: "",
            birthDate = json["birthDate"] as? String ?: "",
            bloodGroup = json["bloodGroup"] as? String ?: "",
            gender = json["gender"] as? String ?: "",
            emergencyContact = json["emergencyContact"] as? String ?: "",
            joinedBucc = json["joinedBucc"] as? String ?: "",
            lastPromotion = json["lastPromotion"] as? String ?: "",
            memberStatus = json["memberStatus"] as? String ?: "",
            memberSkills = (json["memberSkills"] as? List<String>).orEmpty(),
            memberSocials = parseMemberSocials(json["memberSocials"] as? Map<String, String?>)
        )
    }

    private fun parseMemberSocials(socials: Map<String, String?>?): MemberSocials {
        return MemberSocials(
            Facebook = socials?.get("Facebook") ?: "",
            Linkedin = socials?.get("Linkedin") ?: "",
            Github = socials?.get("Github") ?: ""
        )
    }

    @ToJson
    fun toJson(member: Member): Map<String, Any?> {
        return mapOf(
            "_id" to member._id,
            "name" to member.name,
            "studentId" to member.studentId,
            "email" to member.email,
            "buccDepartment" to member.buccDepartment,
            "designation" to member.designation,
            "personalEmail" to member.personalEmail,
            "contactNumber" to member.contactNumber,
            "joinedBracu" to member.joinedBracu,
            "departmentBracu" to member.departmentBracu,
            "profileImage" to member.profileImage,
            "birthDate" to member.birthDate,
            "bloodGroup" to member.bloodGroup,
            "gender" to member.gender,
            "emergencyContact" to member.emergencyContact,
            "joinedBucc" to member.joinedBucc,
            "lastPromotion" to member.lastPromotion,
            "memberStatus" to member.memberStatus,
            "memberSkills" to member.memberSkills,
            "memberSocials" to mapOf(
                "Facebook" to member.memberSocials.Facebook,
                "Linkedin" to member.memberSocials.Linkedin,
                "Github" to member.memberSocials.Github
            )
        )
    }
}
