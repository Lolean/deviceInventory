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

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingShownDevice = MainDeviceshowfragmentBinding.bind(view)
        activity = requireActivity() as MainActivity

        CoroutineScope(Dispatchers.IO).launch {
                deviceDB = DatabaseOperator.db.deviceDAO().get(activity.chosenDevice)
            var device = AdaptedDevice(deviceDB)
            if(device.type==FieldConstraint.type.PHONE){
                bindingShownDevice.deviceshowIvType.setImageResource(R.drawable.phone)
            }
            else{
                bindingShownDevice.deviceshowIvType.setImageResource(R.drawable.tablet)
            }
            bindingShownDevice.deviceshowTvModel.text = device.model
            bindingShownDevice.deviceshowTvBrand.text = device.brand
            bindingShownDevice.deviceshowTvWebsite.text = device.website
            bindingShownDevice.deviceshowTvSerial.text = device.serial
            if(device.state==FieldConstraint.state.AVAILABLE){
                bindingShownDevice.deviceshowTvState.text = "Available"
            }
            else{
                bindingShownDevice.deviceshowTvState.text = "taken by ${device.username}"
            }
        }






    }



}