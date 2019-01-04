package com.example.joren.partynightplanner.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joren.partynightplanner.adapters.EventAdapter
import com.example.joren.partynightplanner.MainActivity
import com.example.joren.partynightplanner.MainViewModel
import com.example.joren.partynightplanner.R
import com.example.joren.partynightplanner.util.InjectorUtils
import kotlinx.android.synthetic.main.content_main.*

class ContentMain : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<EventAdapter.ViewHolder>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState : Bundle?): View?{
        return inflater.inflate(R.layout.content_main, container, false)
    }

    override fun onStart() {
        super.onStart()
        initUi()
    }

    override fun onStop() {
        super.onStop()

        eventRecycleView.layoutManager = null
        eventRecycleView.adapter = null
    }

    private fun initUi(){
        layoutManager = LinearLayoutManager(this.context)
        eventRecycleView.layoutManager = layoutManager

        val factory = InjectorUtils.provideMainViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        viewModel.getUpcomingEvents().observe(this, Observer { events ->
            adapter = EventAdapter(events!!, this.activity)
            eventRecycleView.adapter = adapter
        })

        initFab()
    }

    private fun initFab(){
        fabSearch.setOnClickListener {
            (activity as MainActivity).openSearchPanel()
        }
    }

    companion object {
        fun newInstance(): ContentMain {
            return ContentMain()
        }
    }
}