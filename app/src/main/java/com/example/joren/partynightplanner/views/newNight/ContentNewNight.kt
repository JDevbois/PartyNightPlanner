package com.example.joren.partynightplanner.views.newNight

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joren.partynightplanner.MainActivity
import com.example.joren.partynightplanner.R
import com.example.joren.partynightplanner.adapters.EventAdapter
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.persistence.events.EventRepo
import kotlinx.android.synthetic.main.content_new_night.*

class ContentNewNight: Fragment() {

    // TODO: addEvent click handler
    // TODO: Save event in real time
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<EventAdapter.ViewHolder>? = null
    private var night: Night = Night()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState : Bundle?): View?{
        return inflater.inflate(R.layout.content_new_night, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        initUi()
        layoutManager = LinearLayoutManager(this.context)
        adapter = EventAdapter(EventRepo.getEventsForNight(night), this.activity)

        newNightEventRecycleView.layoutManager = layoutManager
        newNightEventRecycleView.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
        layoutManager = null
        adapter = null
    }

    private fun initUi(){
        fabSaveNight.setOnClickListener {
            night.name = editAddName.text.toString()
            night.desc = editAddDesc.text.toString()
            //TODO datetime
            (activity as MainActivity).saveNight(night)
        }

        //TODO
        btnSelectDateTime.setOnClickListener{

        }
    }

    companion object {

        fun newInstance(): ContentNewNight {
            return ContentNewNight()
        }
    }
}