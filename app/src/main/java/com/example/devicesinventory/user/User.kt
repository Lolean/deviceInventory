package com.example.devicesinventory.user

import androidx.room.*
import com.example.devicesinventory.data.FieldConstraint

@Entity(tableName = "UserTable")
data class User(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name="password") var password: String,
    @ColumnInfo(name="credential") var credential : FieldConstraint.credential
)
{


}