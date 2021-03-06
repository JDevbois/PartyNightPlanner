package com.example.joren.partynightplanner.adapters

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.joren.partynightplanner.MainActivity
import com.example.joren.partynightplanner.R
import com.example.joren.partynightplanner.domain.Event
import com.example.joren.partynightplanner.util.DownloadImageTask

open class EventAdapter (private val dataSet: List<Event>, private val parentActivity: FragmentActivity?) : RecyclerView.Adapter<EventAdapter.ViewHolder>(){

    var onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { e ->
            val item = e.tag as Event
            if(parentActivity is MainActivity){
                parentActivity.openEventDetailPanel(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = LayoutInflater.from(parent.context)
                .inflate(R.layout.event_fragment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event = dataSet[position]
        holder.itemDesc.text = event.desc
        holder.itemTitle.text = event.title
        DownloadImageTask(holder.itemImage).execute(event.imgSrc)

        with(holder.itemView){
            tag = event
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = dataSet.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var itemImage : ImageView
        var itemTitle : TextView
        var itemDesc : TextView
        init{
            itemImage = itemView.findViewById(R.id.eventImg)
            itemTitle = itemView.findViewById(R.id.eventTitle)
            itemDesc = itemView.findViewById(R.id.eventDesc)
        }
    }
}