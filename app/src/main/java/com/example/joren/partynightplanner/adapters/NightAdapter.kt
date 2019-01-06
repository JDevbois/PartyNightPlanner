package com.example.joren.partynightplanner.adapters

import android.arch.lifecycle.MutableLiveData
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.joren.partynightplanner.MainActivity
import com.example.joren.partynightplanner.R
import com.example.joren.partynightplanner.domain.Night
import kotlinx.android.synthetic.main.night_fragment.view.*
import java.text.SimpleDateFormat
import com.facebook.GraphResponse
import com.facebook.GraphRequest
import com.facebook.HttpMethod
import com.facebook.AccessToken
import java.util.*


class NightAdapter(private val dataSet: List<Night>, private val parentActivity: FragmentActivity?) : RecyclerView.Adapter<NightAdapter.ViewHolder>() {
    private val onClickListener: View.OnClickListener
    private val friendNamesList: MutableList<String> = mutableListOf()
    private val friendNames = MutableLiveData<MutableList<String>>()

    init {
        friendNames.value = friendNamesList

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

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val night = dataSet[position]

        holder.itemName.text = night.name
        holder.itemDesc.text = night.desc
        holder.itemDate.text = SimpleDateFormat("MM/dd/yy", Locale.US).format(night.date)
        night.friends.forEach {
            addFriendNameToView(it)
        }
        friendNames.observe(parentActivity!!, android.arch.lifecycle.Observer {
            if (it != null)
                holder.itemFriends.text = it.joinToString(separator = ", ")
        })

        with(holder.itemView){
            tag = night
            setOnClickListener(onClickListener)
            btnInvite.setOnClickListener{
                tag = night
                if(parentActivity is MainActivity){
                    val item = tag as Night
                    parentActivity.openInviteFriendsPanel(item)
                }
            }
        }
    }

    fun addFriendNameToView(s: String){
        GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/$s/",
                null,
                HttpMethod.GET,
                GraphRequest.Callback {
                    handleResult(it)
                }
        ).executeAsync()
    }

    private fun handleResult(it: GraphResponse) {
        Log.d("FB", it.jsonObject.toString())
        val name = it.jsonObject.getString("name").substring(0, it.jsonObject.getString("name").indexOf(' '))
        friendNamesList.add(name)
        friendNames.value = friendNamesList
    }

    override fun getItemCount() = dataSet.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var itemName: TextView = itemView.findViewById(R.id.nightName)
        var itemDesc: TextView = itemView.findViewById(R.id.nightDesc)
        var itemDate: TextView = itemView.findViewById(R.id.nightDate)
        var itemFriends: TextView = itemView.findViewById(R.id.nightFriends)
    }
}