package com.example.devicesinventory.user

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.devicesinventory.MainActivity
import com.example.devicesinventory.R
import com.example.devicesinventory.data.DatabaseOperator
import com.example.devicesinventory.data.FieldConstraint
import com.example.devicesinventory.databinding.DeviceEntryBinding
import com.example.devicesinventory.fragment.userfragments.UserRecycleViewFragment
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text
import java.lang.reflect.Field

class UserAdapter constructor(users : MutableList<AdaptedUser>, activity : Activity, currentFragment : UserRecycleViewFragment) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var userList : MutableList<AdaptedUser> = users
    private var activity = activity as MainActivity
    private var currentFragment = currentFragment

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
            val view = activity.layoutInflater.inflate(R.layout.user_entry, parent, false)
            return UserViewHolder(view)
        }

        override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
            // setup each user entry with accurate data
            holder.username.text = userList[position].username
            holder.credential.text = userList[position].credential.toString()

            if(activity.user.credential == FieldConstraint.credential.ADMIN){

                if(holder.credential.text == FieldConstraint.credential.MODERATOR.toString()){
                    holder.demote.visibility = View.VISIBLE
                    holder.rankup.visibility = View.GONE
                }
                else if(holder.credential.text == FieldConstraint.credential.BASIC.toString()){
                    holder.demote.visibility = View.GONE
                    holder.rankup.visibility = View.VISIBLE
                }
                else if(holder.credential.text == FieldConstraint.credential.ADMIN.toString()){
                    holder.demote.visibility = View.GONE
                    holder.rankup.visibility = View.GONE
                    holder.delete.visibility = View.GONE
                }
                else{
                    holder.demote.visibility = View.GONE
                    holder.rankup.visibility = View.GONE
                }
            }
            if(activity.user.credential == FieldConstraint.credential.MODERATOR){

                holder.demote.visibility = View.GONE
                holder.rankup.visibility = View.GONE
            }

            holder.delete.setOnClickListener{
                var userToDelete : User = DatabaseOperator.db.userDAO().get(userList[position].email)
                DatabaseOperator.db.userDAO().delete(userToDelete)
                currentFragment.refresh()

            }

            holder.rankup.setOnClickListener{
                var userToRankUp : User = DatabaseOperator.db.userDAO().get(userList[position].email)
                userToRankUp.credential = FieldConstraint.credential.MODERATOR
                DatabaseOperator.db.userDAO().modify(userToRankUp)
                currentFragment.refresh()
            }

            holder.demote.setOnClickListener{
                var userToDemote : User = DatabaseOperator.db.userDAO().get(userList[position].email)
                userToDemote.credential = FieldConstraint.credential.BASIC
                DatabaseOperator.db.userDAO().modify(userToDemote)
                currentFragment.refresh()
            }


        }

        override fun getItemCount(): Int {
            return userList.size
        }

        fun clearData(){
            userList.removeAll(userList)
        }

        inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            val username : TextView = itemView.findViewById(R.id.userEntry_username)
            val credential : TextView = itemView.findViewById(R.id.userEntry_credential)
            val delete : ImageButton = itemView.findViewById(R.id.userEntry_delete)
            val rankup : ImageButton = itemView.findViewById(R.id.userEntry_rankUp)
            val demote : ImageButton = itemView.findViewById(R.id.userEntry_demote)



        }
}