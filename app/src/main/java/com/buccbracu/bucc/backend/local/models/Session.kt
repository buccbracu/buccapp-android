package com.buccbracu.bucc.backend.local.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Session : RealmObject {
    var name: String = ""
    var email: String = ""
    var image: String? = ""
    @PrimaryKey var id: String = ""
    var designation: String = ""
    var buccDepartment: String = ""
    var expires: String = ""
    var authJsToken: String = ""
}