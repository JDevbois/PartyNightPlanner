package com.example.joren.partynightplanner

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class ContentMain : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<EventAdapter.ViewHolder>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState : Bundle?): View?{
        return inflater.inflate(R.layout.content_main, container, false)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ContentMain", "starting")
        layoutManager = LinearLayoutManager(this.context)
        eventRecycleView.layoutManager = layoutManager

        adapter = EventAdapter(DummyData.getEvents(), this.activity)
        eventRecycleView.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
        Log.i("ContentMain", "stopping")
    }

    companion object {
        fun newInstance(): ContentMain{
            return ContentMain()
        }
    }
}