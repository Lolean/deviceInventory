package com.example.devicesinventory.user

import androidx.room.*
import com.example.devicesinventory.data.FieldConstraint

@Dao
interface UserDAO {

    @Query("SELECT * FROM usertable ORDER BY credential ASC, id ASC")
    fun get(): List<User>

    @Query("SELECT * FROM usertable WHERE email = :email LIMIT 1")
    fun get(email: String): User

    @Query("SELECT credential FROM usertable WHERE email = :email LIMIT 1")
    fun getCredential(email: String): FieldConstraint.credential

    @Insert
    fun add(vararg user: User)

    @Update
    fun modify(vararg user: User)

    @Delete
    fun delete(vararg user: User)
}