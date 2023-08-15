package com.example.devicesinventory.fragment.userfragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.devicesinventory.MainActivity
import com.example.devicesinventory.R
import com.example.devicesinventory.data.DatabaseOperator
import com.example.devicesinventory.device.Device
import com.example.devicesinventory.device.AdaptedDevice
import com.example.devicesinventory.device.DeviceAdapter
import com.example.devicesinventory.user.AdaptedUser
import com.example.devicesinventory.user.User
import com.example.devicesinventory.user.UserAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [UserRecycleViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserRecycleViewFragment : Fragment(R.layout.main_userrecyclerviewfragment) {

    lateinit var activity: MainActivity
    lateinit var recyclerView : RecyclerView
    lateinit var userList : MutableList<AdaptedUser>
    lateinit var userAdapter : UserAdapter

    //view binding and recycler view setup
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.user_recyclerview)
        CoroutineScope(Dispatchers.IO).launch(){
            setupDeviceList()
            activity = requireActivity() as MainActivity
            userAdapter = UserAdapter(userList,activity,this@UserRecycleViewFragment)
            recyclerView.adapter = userAdapter
            recyclerView.layoutManager = LinearLayoutManager(activity)
        }

    }

    fun refresh(){
        CoroutineScope(Dispatchers.IO).launch(){
            requireActivity().runOnUiThread(){
                userAdapter.clearData()
                userAdapter.notifyDataSetChanged()
                setupDeviceList()
                userAdapter.notifyDataSetChanged()
            }

        }
    }

    //retrieve device list from database and convert it to a list of UµserDevice used by recycler view
    fun setupDeviceList() {
        var dbUserList : List<User> = DatabaseOperator.db.userDAO().get()
        userList = mutableListOf()
        for(user in dbUserList) {
            userList += AdaptedUser(user)
        }
    }





}