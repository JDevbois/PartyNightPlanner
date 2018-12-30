package com.example.joren.partynightplanner.adapters

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.joren.partynightplanner.MainActivity
import com.example.joren.partynightplanner.R
import com.example.joren.partynightplanner.domain.Night
import im.getsocial.sdk.ui.GetSocialUi
import java.text.SimpleDateFormat
import java.util.*

class NightAdapter(private val dataSet: List<Night>, private val parentActivity: FragmentActivity?) : RecyclerView.Adapter<NightAdapter.ViewHolder>() {
    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { n ->
            val item = n.tag as Night
            if(parentActivity is MainActivity){
                parentActivity.openNightDetailPanel(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = LayoutInflater.from(parent.context)
                .inflate(R.layout.night_fragment, parent, false)

        // TODO: have an actual invite friends screen
        view.findViewById<Button>(R.id.btnInvite).setOnClickListener{
            val wasShown = GetSocialUi.createInvitesView().show()
            Log.i("GetSocial", "GetSocial Smart Invites UI was shown: $wasShown")
        }
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val night = dataSet[position]

        holder.itemName.text = night.name
        holder.itemDesc.text = night.desc
        holder.itemDate.text = SimpleDateFormat("MM/dd/yy", Locale.US).format(night.date)
        holder.itemFriends.text = night.friendsToString()

        with(holder.itemView){
            tag = night
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = dataSet.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var itemName: TextView = itemView.findViewById(R.id.nightName)
        var itemDesc: TextView = itemView.findViewById(R.id.nightDesc)
        var itemDate: TextView = itemView.findViewById(R.id.nightDate)
        var itemFriends: TextView = itemView.findViewById(R.id.nightFriends)

        init{
            // itemImage = itemView.findViewById(R.id.eventImg)
        }
    }
}