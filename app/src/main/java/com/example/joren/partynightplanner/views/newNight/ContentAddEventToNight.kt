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
import com.example.joren.partynightplanner.adapters.SelectEventAdapter
import com.example.joren.partynightplanner.domain.Event
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.persistence.events.EventRepo
import com.example.joren.partynightplanner.persistence.nights.NightRepo
import com.example.joren.partynightplanner.views.details.EventDetailFragment
import kotlinx.android.synthetic.main.content_add_event_to_night.*
import kotlinx.android.synthetic.main.content_new_night.*

class ContentAddEventToNight : Fragment() {
    lateinit var night: Night
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<EventAdapter.ViewHolder>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.content_add_event_to_night, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments!!.let{
            if (it.containsKey(ARG_NIGHT)){
                night = it.getSerializable(ARG_NIGHT) as Night
            }
        }
    }

    override fun onStart() {
        super.onStart()
        initUi()
        layoutManager = LinearLayoutManager(this.context)
        adapter = SelectEventAdapter(EventRepo.getEventsAfter(night.date), this.activity)

        selectEventRecyclerView.layoutManager = layoutManager
        selectEventRecyclerView.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
        layoutManager = null
        adapter = null
    }

    private fun initUi() {
        fabAddEvent.setOnClickListener {
            (activity as MainActivity).addEventToNight((adapter as SelectEventAdapter).selectedItem, night)
        }
    }

    companion object {
        fun newInstance(night: Night): ContentAddEventToNight{
            val args = Bundle()
            args.putSerializable(ContentAddEventToNight.ARG_NIGHT, night)
            val fragment = ContentAddEventToNight()
            fragment.arguments = args

            return fragment
        }

        const val ARG_NIGHT = "item_id"
    }
}