package com.example.joren.partynightplanner.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joren.partynightplanner.adapters.EventAdapter
import com.example.joren.partynightplanner.MainActivity
import com.example.joren.partynightplanner.R
import com.example.joren.partynightplanner.util.DummyData
import kotlinx.android.synthetic.main.content_main.*

class ContentMain : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<EventAdapter.ViewHolder>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState : Bundle?): View?{
        return inflater.inflate(R.layout.content_main, container, false)
    }

    private fun initFab(){
        fabSearch.setOnClickListener {
            (activity as MainActivity).openSearchPanel()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("ContentMain", "starting")

        layoutManager = LinearLayoutManager(this.context)
        adapter = EventAdapter(DummyData.getEvents(), this.activity)

        if(this.activity is MainActivity){
            (this.activity as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.loggedInFragment, LoggedInFragment.newInstance()).commit()
        }

        initFab()

        eventRecycleView.layoutManager = layoutManager
        eventRecycleView.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
        Log.i("ContentMain", "stopping")

        eventRecycleView.layoutManager = null
        eventRecycleView.adapter = null
    }

    companion object {
        fun newInstance(): ContentMain {
            return ContentMain()
        }
    }
}