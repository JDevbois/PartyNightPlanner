package com.example.joren.partynightplanner.views.details

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joren.partynightplanner.MainActivity
import com.example.joren.partynightplanner.R
import com.example.joren.partynightplanner.adapters.EventAdapter
import com.example.joren.partynightplanner.adapters.NightAdapter
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.util.InjectorUtils
import com.example.joren.partynightplanner.views.newNight.NightViewModel
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.facebook.GraphResponse
import com.facebook.HttpMethod
import kotlinx.android.synthetic.main.night_detail.*
import java.text.SimpleDateFormat
import java.util.*

class ContentNightDetail: Fragment() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<EventAdapter.ViewHolder>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.night_detail, container, false)
    }

    override fun onStart() {
        super.onStart()
        initUi()
    }

    override fun onStop() {
        super.onStop()

        layoutManager = null
        adapter = null

        nightDetailRecyclerview.layoutManager = null
        nightDetailRecyclerview.adapter = null

        btnDetailInviteFriends.setOnClickListener(null)
    }

    private fun initUi(){
        val factory = InjectorUtils.provideNightViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory).get(NightViewModel::class.java)

        arguments!!.let{
            if (it.containsKey(ARG_NIGHT)){
                viewModel.parseNight(it.getSerializable(ARG_NIGHT) as Night)
            }
        }

        viewModel.night.let {
            nightDetailName.text = it.name
            nightDetailDesc.text = it.desc
            nightDetailStartDateAndTime.text = SimpleDateFormat("dd MM yyyy hh:mm a", Locale.US).format(it.retrieveSortedEvents()[0].startDate.time)
            nightDetailEndDateAndTime.text = SimpleDateFormat("dd MM yyyy hh:mm a", Locale.US).format(it.retrieveSortedEvents()[it.retrieveSortedEvents().size - 1].endDate.time)
        }

        // deliberately no livedata as night's events can't change once it's created
        with(viewModel){
            adapter = EventAdapter(getEvents(), activity)
            nightDetailRecyclerview.adapter = adapter
        }

        viewModel.getFriendNames().observe(this, android.arch.lifecycle.Observer {
            nightDetailFriends.text = it!!.joinToString(separator = ", ")
        })

        layoutManager = LinearLayoutManager(context)
        nightDetailRecyclerview.layoutManager = layoutManager

        btnDetailInviteFriends.setOnClickListener {
            (activity as MainActivity).openInviteFriendsPanel(viewModel.night)
        }
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