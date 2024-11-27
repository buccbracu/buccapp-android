package com.buccbracu.bucc.backend.local.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Session : RealmObject {
    @PrimaryKey var objectid: Int = 1
    var email: String = ""
    var password: String = ""
    var expires: String = ""
    var authJsToken: String = ""
}