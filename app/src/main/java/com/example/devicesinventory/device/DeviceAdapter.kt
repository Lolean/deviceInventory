package com.example.devicesinventory.device

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.devicesinventory.MainActivity
import com.example.devicesinventory.R
import com.example.devicesinventory.data.FieldConstraint
import com.example.devicesinventory.databinding.DeviceEntryBinding
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text
import java.lang.reflect.Field

class DeviceAdapter constructor(devices : List<AdaptedDevice>,activity : Activity) : RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {

    var deviceList : List<AdaptedDevice> = devices
    private var activity = activity as MainActivity



        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
            val view = activity.layoutInflater.inflate(R.layout.device_entry, parent, false)
            return DeviceViewHolder(view)
        }

        override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
            // setup each device entry with accurate data
            if(deviceList[position].type == FieldConstraint.type.PHONE){
                holder.deviceType.setImageResource(R.drawable.phone)
            }
            else{
                holder.deviceType.setImageResource(R.drawable.tablet)
            }
            holder.deviceBrand.text = deviceList[position].brand
            holder.deviceWebsite.text = deviceList[position].website
            holder.deviceSerial.text = deviceList[position].serial
            holder.deviceState.text = deviceList[position].state
            holder.deviceUsername.text = deviceList[position].username
            holder.deviceModel.text = deviceList[position].model

            holder.itemView.setOnClickListener {}

        }

        override fun getItemCount(): Int {
            return deviceList.size
        }

        class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val deviceType : ImageView = itemView.findViewById(R.id.deviceEntry_type)
            val deviceBrand : TextView = itemView.findViewById(R.id.deviceEntry_brand)
            val deviceWebsite : TextView = itemView.findViewById(R.id.deviceEntry_website)
            val deviceSerial : TextView = itemView.findViewById(R.id.deviceEntry_serial)
            val deviceState : TextView = itemView.findViewById(R.id.deviceEntry_state)
            val deviceUsername : TextView = itemView.findViewById(R.id.deviceEntry_username)
            val deviceModel : TextView = itemView.findViewById(R.id.deviceEntry_model)

        }
}