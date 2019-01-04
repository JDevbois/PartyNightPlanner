package com.example.joren.partynightplanner.views.newNight

import android.arch.lifecycle.Observer
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
import com.example.joren.partynightplanner.adapters.SelectEventAdapter
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.util.InjectorUtils
import kotlinx.android.synthetic.main.content_add_event_to_night.*

class ContentAddEventToNight : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<EventAdapter.ViewHolder>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.content_add_event_to_night, container, false)
    }

    override fun onStart() {
        super.onStart()
        initUi()
    }

    override fun onStop() {
        super.onStop()

        layoutManager = null
        adapter = null
        selectEventRecyclerView.layoutManager = null
        selectEventRecyclerView.adapter = null
    }

    private fun initUi() {
        val factory = InjectorUtils.provideNewNightViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory).get(NewNightViewModel::class.java)

        arguments!!.let{
            if (it.containsKey(ARG_NIGHT)){
                viewModel.parseNight(it.getSerializable(ARG_NIGHT) as Night)
            }
        }

        fabAddEvent.setOnClickListener {
            (activity as MainActivity).addEventToNight((adapter as SelectEventAdapter).selectedItem, viewModel.night!!)
        }

        layoutManager = LinearLayoutManager(this.context)
        selectEventRecyclerView.layoutManager = layoutManager

        viewModel.getAvailableEvents().observe(this, Observer {events ->
            adapter = SelectEventAdapter(events!!, this.activity)
            //DEBUG
            Log.i("ContentAddEventToNight", events.toString())
            selectEventRecyclerView.adapter = adapter
        })
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