package com.buccbracu.bucc.components.permissions

data class MemberPermissions(
    var admin: Boolean,
    var allFields: Boolean
) {
    constructor(dept: String, des: String) : this(
        admin = determineAdminPermission(dept, des),
        allFields = determineAdminPermission(dept, des) || determineAllFieldsPermission(dept, des)
    )

    companion object {
        // Determine admin permission
        private fun determineAdminPermission(dept: String, des: String): Boolean {
            return dept == "Governing Body" ||
                    ((dept == "Human Resources" || dept == "Research and Development") &&
                            (des == "Director" || des == "Assistant Director"))
        }

        // Determine allFields permission
        private fun determineAllFieldsPermission(dept: String, des: String): Boolean {
            return dept !in listOf("Governing Body", "Human Resources", "Research and Development") &&
                    (des == "Director" || des == "Assistant Director")
        }
    }
}
