package com.example.devicesinventory.device

import com.example.devicesinventory.data.FieldConstraint

class AdaptedDevice(device : Device): java.io.Serializable{

    var serial: String = device.serial
    var type: FieldConstraint.type = device.type
    var brand : String = device.brand
    var website : String = device.website
    var model : String = device.model
    var state : String = device.state.toString()
    var username : String = device.username

}
