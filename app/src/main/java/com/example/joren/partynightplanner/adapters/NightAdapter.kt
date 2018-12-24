package com.example.joren.partynightplanner.adapters

import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joren.partynightplanner.MainActivity
import com.example.joren.partynightplanner.R
import com.example.joren.partynightplanner.domain.Night

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
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val night = dataSet[position]

        //TODO: init

        with(holder.itemView){
            tag = night
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = dataSet.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        init{
            // itemImage = itemView.findViewById(R.id.eventImg)
        }
    }
}