package com.buccbracu.bucc.backend.local.models.User

import io.realm.kotlin.types.RealmObject

class UserSocial : RealmObject {
    var Facebook: String = ""
    var LinkedIn: String = ""
    var Github: String = ""
}