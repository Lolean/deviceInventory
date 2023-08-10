package com.example.devicesinventory.data

import androidx.room.*

@Dao
interface UserDAO {

    @Query("SELECT * FROM usertable ORDER BY credential ASC, id ASC")
    fun get(): List<User>

    @Query("SELECT * FROM usertable WHERE email = :email")
    fun get(email: String): User

    @Insert
    fun add(vararg user: User)

    @Update
    fun modify(vararg user: User)

    @Delete
    fun delete(vararg user: User)
}