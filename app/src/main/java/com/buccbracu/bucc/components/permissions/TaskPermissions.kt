package com.buccbracu.bucc.components.permissions

data class TaskPermissions(
    var edit: Boolean,
    var delete: Boolean,
    var join: Boolean,
) {
    constructor(
        fromDept: String,
        toDept: String,
        userDept: String,
        userDes: String,
        fromDes: String,
        toDes: String
    ) : this(
        edit = false,
        delete = false,
        join = false
    ) {
        // Define governing bodies and eligible designations (EBGB)
        val governingBodies = listOf("Governing Body", "Research and Development")
        val ebgb = listOf("President", "Vice President", "General Secretary", "Treasurer")

        // Logic for "edit" permission
        edit = (fromDept == userDept && fromDes == userDes) ||
                (userDept in governingBodies && userDes in ebgb)

        // Logic for "delete" permission
        delete = userDept in governingBodies && userDes in ebgb

        // Logic for "join" permission
        join = toDept == userDept && toDes == userDes
    }
}
