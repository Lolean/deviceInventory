package com.example.devicesinventory.data

import androidx.room.*

@androidx.room.Database(entities = [(User::class),(Device::class)],version = 1)
abstract class Database : RoomDatabase() {

    abstract fun deviceDAO(): DeviceDAO
    abstract fun userDAO(): UserDAO
}