package com.example.joren.partynightplanner.views.search

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
import com.example.joren.partynightplanner.R
import com.example.joren.partynightplanner.util.InjectorUtils
import kotlinx.android.synthetic.main.content_main.*

class ContentSearchResult : Fragment(){

    private var option: Int = -1
    private var query: String = ""

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<EventAdapter.ViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments!!.let {
            if (it.containsKey(ARG_OPTION)){
                option = it.getInt(ARG_OPTION)
            }
            if (it.containsKey(ARG_QUERY)){
                query = it.getString(ARG_QUERY)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        initUi()
    }

    override fun onStop() {
        super.onStop()

        layoutManager = null
        adapter = null

        eventRecycleView.adapter = null
        eventRecycleView.layoutManager = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.content_searchresult, container, false)
    }

    private fun initUi(){
        val factory = InjectorUtils.provideEventSearchViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory).get(EventSearchViewModel::class.java)

        layoutManager = LinearLayoutManager(this.context)
        eventRecycleView.layoutManager = layoutManager

        viewModel.getFilteredEvents(option, query).observe(this, Observer { events ->
            adapter = EventAdapter(events!!, this.activity)
            eventRecycleView.adapter = adapter
        })
    }

    companion object {
        const val ARG_OPTION = "option_id"
        const val ARG_QUERY = "query_id"

        fun newInstance(option: Int, query: String): ContentSearchResult {
            val args = Bundle()
            args.putInt(ARG_OPTION, option)
            args.putString(ARG_QUERY, query)
            val fragment = ContentSearchResult()
            fragment.arguments = args

            return fragment
        }
    }
}