package com.example.devicesinventory.data

import androidx.room.*
import com.example.devicesinventory.device.Device
import com.example.devicesinventory.device.DeviceDAO
import com.example.devicesinventory.user.User
import com.example.devicesinventory.user.UserDAO

@androidx.room.Database(entities = [(User::class),(Device::class)],version = 1)
abstract class Database : RoomDatabase() {

    abstract fun deviceDAO(): DeviceDAO
    abstract fun userDAO(): UserDAO
}