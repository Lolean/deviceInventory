package com.example.devicesinventory.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.devicesinventory.MainActivity
import com.example.devicesinventory.R
import com.example.devicesinventory.data.DatabaseOperator
import com.example.devicesinventory.device.Device
import com.example.devicesinventory.device.AdaptedDevice
import com.example.devicesinventory.device.DeviceAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [DeviceRecycleViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DeviceRecycleViewFragment : Fragment(R.layout.main_devicerecyclerviewfragment) {

    lateinit var activity: MainActivity
    lateinit var recyclerView : RecyclerView
    lateinit var deviceList : MutableList<AdaptedDevice>
    lateinit var deviceAdapter : DeviceAdapter

    //view binding and recycler view setup
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.device_recyclerview)
        CoroutineScope(Dispatchers.IO).launch(){
            setupDeviceList()
            activity = requireActivity() as MainActivity
            deviceAdapter = DeviceAdapter(deviceList,activity)
            recyclerView.adapter = deviceAdapter
            recyclerView.layoutManager = LinearLayoutManager(activity)
        }

    }

    //retrieve device list from database and convert it to a list of AdaptedDevice used by recycler view
    fun setupDeviceList() {
        var dbDeviceList : List<Device> = DatabaseOperator.db.deviceDAO().get()
        deviceList = mutableListOf()
        for(device in dbDeviceList) {
            deviceList += AdaptedDevice(device)
        }
    }



}