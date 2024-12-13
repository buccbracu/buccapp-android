package com.buccbracu.bucc.components.models


data class Filter(
    var buccDepartment: String = "",
    var designation: String = "",
    var contactNumber: String = "",
    var joinedBracu: String = "",
    var bloodGroup: String = "",
    var emergencyContact: String = "",
    var joinedBucc: String = "",
    var lastPromotion: String = "",
){
    fun isEmpty(): Boolean {
        return  buccDepartment.isEmpty() &&
                designation.isEmpty() &&
                contactNumber.isEmpty() &&
                joinedBracu.isEmpty() &&
                bloodGroup.isEmpty() &&
                emergencyContact.isEmpty() &&
                joinedBucc.isEmpty() &&
                lastPromotion.isEmpty()
    }
}
