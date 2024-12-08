package com.buccbracu.bucc.components.filters

import com.buccbracu.bucc.backend.remote.models.Member

fun memberFilter(query: String, list: List<Member>): List<Member> {
    return list.filter { member ->
        member.name.contains(query, ignoreCase = true) ||
//        member.buccDepartment.contains(query, ignoreCase = true) ||
        member.designation.contains(query, ignoreCase = true)
    }
}