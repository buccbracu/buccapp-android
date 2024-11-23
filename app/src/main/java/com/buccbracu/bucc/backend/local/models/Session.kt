package com.buccbracu.bucc.backend.local.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Session : RealmObject {
    @PrimaryKey var objectid: Int = 1
    var name: String = ""
    var email: String = ""
    var image: String? = ""
    var id: String = ""
    var designation: String = ""
    var buccDepartment: String = ""
    var expires: String = ""
    var authJsToken: String = ""
}