package com.example.devicesinventory.data

import androidx.room.*

@Dao
interface DeviceDAO {

    @Query("SELECT * FROM DeviceTable")
    fun get(): List<Device>

    @Query("SELECT * FROM DeviceTable WHERE serial = :serial")
    fun get(serial: String): Device

    @Insert
    fun add(vararg device: Device)

    @Update
    fun modify(vararg device: Device)

    @Delete
    fun delete(vararg device: Device)

}