package com.example.devicesinventory.user

import com.example.devicesinventory.data.FieldConstraint

class AdaptedUser(user : User): java.io.Serializable {

    var email: String = user.email
    var username: String = user.username
    var credential : FieldConstraint.credential = user.credential
}