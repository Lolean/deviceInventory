package com.example.devicesinventory.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.devicesinventory.MainActivity
import com.example.devicesinventory.R
import com.example.devicesinventory.databinding.MainMenufragmentBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MainMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainMenuFragment : Fragment(R.layout.main_menufragment) {

    lateinit var binding: MainMenufragmentBinding
    lateinit var activity: MainActivity

    //main menu view with view binding and username displayed
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainMenufragmentBinding.bind(view)
        activity = requireActivity() as MainActivity
        binding.menuTvWelcome.text = "Welcome ${activity.user.username} !"
        }

}