package com.example.devicesinventory.data

import androidx.room.*

@Entity(tableName = "UserTable")
data class User(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "email") var email: String,
    @ColumnInfo(name = "username") var username: String,
    @ColumnInfo(name="password") var password: String,
    @ColumnInfo(name="credential") var credential : FieldConstraint.credential)
{


}