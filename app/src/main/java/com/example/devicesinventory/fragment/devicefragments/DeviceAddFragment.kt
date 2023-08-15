package com.example.devicesinventory.fragment.devicefragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.devicesinventory.MainActivity
import com.example.devicesinventory.R
import com.example.devicesinventory.data.DatabaseOperator
import com.example.devicesinventory.data.FieldConstraint
import com.example.devicesinventory.databinding.MainDeviceaddfragmentBinding
import com.example.devicesinventory.device.Device
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [DeviceAddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DeviceAddFragment : Fragment(R.layout.main_deviceaddfragment) {

    lateinit var binding : MainDeviceaddfragmentBinding
    lateinit var activity: MainActivity

    //view binding and current activity retrieval
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity = requireActivity() as MainActivity
        if(!activity.isAllowed()) activity.setFragment(activity.menuFragment,true,null,true)
        binding = MainDeviceaddfragmentBinding.bind(view)
        binding.deviceaddRgState.setOnCheckedChangeListener { group, checkedId -> changeImage() }
        binding.deviceaddBtAdd.setOnClickListener {
            addDevice(deviceToAdd())
            activity.setFragment(activity.menuFragment,false,null,true)
        }

    }

    //change device icon when radio button is changed
    fun changeImage(){
        if(binding.deviceaddRbPhone.isChecked) binding.deviceaddIvType.setImageResource(R.drawable.phone)
        else binding.deviceaddIvType.setImageResource(R.drawable.tablet)
    }

    //add device to database
    fun addDevice(device : Device){
        CoroutineScope(Dispatchers.IO).launch(){
            DatabaseOperator.db.deviceDAO().add(device)
        }
    }

    //return device from form
    fun deviceToAdd(): Device{
        var website = binding.deviceaddEtWebsite.text.toString()
        var brand = binding.deviceaddEtBrand.text.toString()
        var model = binding.deviceadEtModel.text.toString()
        var serial = binding.deviceaddEtSerial.text.toString()
        var type = if(binding.deviceaddRbPhone.isChecked) FieldConstraint.type.PHONE
        else FieldConstraint.type.TABLET
        var device = Device(serial,type,brand,website,model,FieldConstraint.state.AVAILABLE,"")

        return device
    }





}


