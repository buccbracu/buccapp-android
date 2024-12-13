package com.buccbracu.bucc.components.filters

import com.buccbracu.bucc.backend.remote.models.Member
import com.buccbracu.bucc.components.models.Filter

fun memberSearch(query: String, list: List<Member>): List<Member> {
    return list.filter { member ->
        member.name.contains(query, ignoreCase = true) ||
        member.designation.contains(query, ignoreCase = true)
    }
}

fun allMemberSearch(query: String, list: List<Member>): List<Member> {
    return list.filter { member ->
        member.name.contains(query, ignoreCase = true) ||
        member.buccDepartment.contains(query, ignoreCase = true) ||
        member.designation.contains(query, ignoreCase = true)
    }
}

fun filterMembers(filter: Filter, members: List<Member>): List<Member> {
    return members.filter { member ->
        (filter.buccDepartment.isEmpty() || member.buccDepartment.equals(filter.buccDepartment, ignoreCase = true)) &&
        (filter.designation.isEmpty() || member.designation.equals(filter.designation, ignoreCase = true)) &&
        (filter.contactNumber.isEmpty() || member.contactNumber.contains(filter.contactNumber)) &&
        (filter.joinedBracu.isEmpty() || member.joinedBracu.contains(filter.joinedBracu, ignoreCase = true)) &&
        (filter.bloodGroup.isEmpty() || member.bloodGroup.contains(filter.bloodGroup, ignoreCase = true)) &&
        (filter.emergencyContact.isEmpty() || member.emergencyContact.contains(filter.emergencyContact)) &&
        (filter.joinedBucc.isEmpty() || member.joinedBucc.contains(filter.joinedBucc, ignoreCase = true)) &&
        (filter.lastPromotion.isEmpty() || member.lastPromotion.contains(filter.lastPromotion, ignoreCase = true))
    }
}



