package com.buccbracu.bucc.backend.local.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId


class Session: RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var studentID: String = "0"
    var name: String = "0"
    var designation: String = "0"
    var department: String = "0"
}