package com.example.devicesinventory.device

import androidx.room.*
import com.example.devicesinventory.data.FieldConstraint

@Entity(tableName = "DeviceTable")
data class Device(
    @ColumnInfo(name = "serial") @PrimaryKey var serial: String = "none",
    @ColumnInfo(name="type") var type: FieldConstraint.type,
    @ColumnInfo(name = "brand") var brand : String,
    @ColumnInfo(name = "website") var website : String,
    @ColumnInfo(name = "model") var model : String,
    @ColumnInfo(name= "state") var state : FieldConstraint.state,
    @ColumnInfo(name="username") var username : String = "",

)
{


}