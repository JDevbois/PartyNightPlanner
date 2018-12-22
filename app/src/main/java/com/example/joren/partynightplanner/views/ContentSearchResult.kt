package com.example.joren.partynightplanner.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joren.partynightplanner.adapters.EventAdapter
import com.example.joren.partynightplanner.persistence.EventRepo
import com.example.joren.partynightplanner.R
import kotlinx.android.synthetic.main.content_main.*

class ContentSearchResult : Fragment(){

    private var option: Int = -1

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<EventAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments!!.let {
            if (it.containsKey(ARG_OPTION)){
                option = it.getInt(ARG_OPTION)
            }
        }
    }

    override fun onStart() {
        super.onStart()

        layoutManager = LinearLayoutManager(this.context)
        eventRecycleView.layoutManager = layoutManager

        //TODO
        adapter = EventAdapter(EventRepo.getFilteredEvents(option, ""), this.activity)
        eventRecycleView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()

        layoutManager = LinearLayoutManager(this.context)
        eventRecycleView.layoutManager = layoutManager

        //TODO
        adapter = EventAdapter(EventRepo.getFilteredEvents(option, ""), this.activity)
        eventRecycleView.adapter = adapter
    }

    override fun onStop() {
        super.onStop()

        layoutManager = null
        adapter = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.content_searchresult, container, false)
    }

    companion object {
        const val ARG_OPTION = "option_id"

        fun newInstance(option: Int): ContentSearchResult {
            val args = Bundle()
            args.putInt(ARG_OPTION, option)
            val fragment = ContentSearchResult()
            fragment.arguments = args

            return fragment
        }
    }
}