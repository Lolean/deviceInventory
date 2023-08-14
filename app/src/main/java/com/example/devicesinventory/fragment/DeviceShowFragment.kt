package com.example.devicesinventory.fragment

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.devicesinventory.MainActivity
import com.example.devicesinventory.R
import com.example.devicesinventory.data.DatabaseOperator
import com.example.devicesinventory.data.FieldConstraint
import com.example.devicesinventory.databinding.MainDeviceshowfragmentBinding
import com.example.devicesinventory.device.AdaptedDevice
import com.example.devicesinventory.device.Device
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * A simple [Fragment] subclass.
 * Use the [DeviceShowFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DeviceShowFragment : Fragment(R.layout.main_deviceshowfragment) {

    lateinit var deviceDB : Device
    lateinit var bindingShownDevice : MainDeviceshowfragmentBinding
    lateinit var activity : MainActivity
    lateinit var device : AdaptedDevice


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingShownDevice = MainDeviceshowfragmentBinding.bind(view)
        activity = requireActivity() as MainActivity
        display() //initial information displayed


        //the following code is used to modify the device information and refresh the view with accurate data
        bindingShownDevice.deviceshowBtBorrow.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                DatabaseOperator.db.deviceDAO().modify(deviceToModify(deviceDB,FieldConstraint.state.TAKEN,activity.user.username))

            }
            activity.chosenDevice = deviceDB.serial.toString()
            display()
        }

        bindingShownDevice.deviceshowBtGiveback.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                DatabaseOperator.db.deviceDAO().modify(deviceToModify(deviceDB,FieldConstraint.state.AVAILABLE,""))
            }
            activity.chosenDevice = deviceDB.serial.toString()
            display()
        }

        bindingShownDevice.deviceshowBtDelete.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                DatabaseOperator.db.deviceDAO().delete(deviceDB)
            }
            activity.setFragment(activity.menuFragment,false,null,true)
        }

    }

    //deviceToModify is used to modify the device information
    fun deviceToModify(device: Device, state : FieldConstraint.state, username : String) : Device {
        deviceDB.state = state
        deviceDB.username = username
        return deviceDB
    }

    //display function is used to display the device information
    fun display(){
        CoroutineScope(Dispatchers.IO).launch {
            deviceDB = DatabaseOperator.db.deviceDAO().get(activity.chosenDevice!!)
            device = AdaptedDevice(deviceDB)
            withContext(Dispatchers.Main)
            {
                if (device.type == FieldConstraint.type.PHONE) {
                    bindingShownDevice.deviceshowIvType.setImageResource(R.drawable.phone)
                } else {
                    bindingShownDevice.deviceshowIvType.setImageResource(R.drawable.tablet)
                }
                bindingShownDevice.deviceshowTvModel.text = device.model
                bindingShownDevice.deviceshowTvBrand.text = device.brand
                bindingShownDevice.deviceshowTvWebsite.text = device.website
                bindingShownDevice.deviceshowTvSerial.text = device.serial
                if (device.state == FieldConstraint.state.AVAILABLE) {
                    bindingShownDevice.deviceshowTvState.text = "Available"
                } else {
                    bindingShownDevice.deviceshowTvState.text = "taken by ${device.username}"
                }
                if (device.state == FieldConstraint.state.TAKEN) bindingShownDevice.deviceshowBtBorrow.visibility =
                    View.GONE
                else bindingShownDevice.deviceshowBtBorrow.visibility = View.VISIBLE

                if (device.username == activity.user.username) bindingShownDevice.deviceshowBtGiveback.visibility =
                    View.VISIBLE
                else bindingShownDevice.deviceshowBtGiveback.visibility = View.GONE

                if(activity.isAllowed()) bindingShownDevice.deviceshowBtDelete.visibility = View.VISIBLE
            }
        }
    }

}