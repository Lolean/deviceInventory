package com.example.devicesinventory.user

class AdaptedUser(user : User): java.io.Serializable {

    var email: String = user.email
    var username: String = user.username
}