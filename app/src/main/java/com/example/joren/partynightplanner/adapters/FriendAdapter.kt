package com.example.joren.partynightplanner.adapters

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.joren.partynightplanner.R
import com.example.joren.partynightplanner.domain.User
import com.facebook.login.widget.ProfilePictureView
import kotlinx.android.synthetic.main.friend_fragment.view.*

class FriendAdapter(private val dataSet: List<User>, private val parentActivity: FragmentActivity?) : RecyclerView.Adapter<FriendAdapter.ViewHolder>() {
    var selectedFriends: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = LayoutInflater.from(parent.context)
                .inflate(R.layout.friend_fragment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val friend = dataSet[position]
        holder.friendName.text = friend.name
        holder.friendImg.profileId = friend.id

        with(holder.itemView){
            tag = friend
            friendCheckbox.setOnClickListener {
                if (selectedFriends.contains(friend.id)) {
                    selectedFriends.remove(friend.id)
                }
                else {
                    selectedFriends.add(friend.id)
                }
                Log.d("FRIENDS", selectedFriends.toString())
            }
        }
    }

    override fun getItemCount() = dataSet.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var friendName: TextView = itemView.findViewById(R.id.friendName)
        var friendImg: ProfilePictureView = itemView.findViewById(R.id.friendPic)
    }
}