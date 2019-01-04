package com.example.joren.partynightplanner.views.details

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
import kotlinx.android.synthetic.main.night_detail.*
import kotlinx.android.synthetic.main.night_detail.view.*
import java.text.SimpleDateFormat
import java.util.*

//TODO: MVVM
class ContentNightDetail: Fragment() {
    private lateinit var night: Night
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<EventAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments!!.let{
            if (it.containsKey(ARG_NIGHT)){
                night = it.getSerializable(ARG_NIGHT) as Night
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.night_detail, container, false)

        // init ui
        // TODO instead of night: viewmodel
        night.let {
            rootView.nightDetailName.text = night.name
            rootView.nightDetailDesc.text = night.desc
            rootView.nightDetailStartDateAndTime.text = SimpleDateFormat("dd MM yyyy hh:mm a", Locale.US).format(night.retrieveSortedEvents()[0].startDate.time)
            rootView.nightDetailEndDateAndTime.text = SimpleDateFormat("dd MM yyyy hh:mm a", Locale.US).format(night.retrieveSortedEvents()[night.retrieveSortedEvents().size - 1].endDate.time)
        }

        return rootView
    }

    override fun onStart() {
        super.onStart()

        layoutManager = LinearLayoutManager(this.context)
        adapter = EventAdapter(night.events, this.activity)

        nightDetailRecyclerview.layoutManager = layoutManager
        nightDetailRecyclerview.adapter = adapter

        btnDetailInviteFriends.setOnClickListener {
            (activity as MainActivity).openInviteFriendsPanel(night)
        }
    }

    override fun onStop() {
        super.onStop()

        layoutManager = null
        adapter = null

        nightDetailRecyclerview.layoutManager = null
        nightDetailRecyclerview.adapter = null

        btnDetailInviteFriends.setOnClickListener(null)
    }

    companion object {
        fun newInstance(item: Night): ContentNightDetail {
            val args = Bundle()
            args.putSerializable(ARG_NIGHT, item)
            val fragment = ContentNightDetail()
            fragment.arguments = args

            return fragment
        }

        const val ARG_NIGHT = "item_id"
    }
}