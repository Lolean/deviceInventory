package com.example.devicesinventory.data

import androidx.room.*

@Entity(tableName = "DeviceTable")
data class Device(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id : Int = 0,
    @ColumnInfo(name="type") var type: FieldConstraint.type,
    @ColumnInfo(name = "serial") var serial: String = "none",
    @ColumnInfo(name = "brand") var brand : String,
    @ColumnInfo(name = "website") var website : String,
    @ColumnInfo(name = "device_name") var name : String,
    @ColumnInfo(name= "state") var state : FieldConstraint.state)
{


}