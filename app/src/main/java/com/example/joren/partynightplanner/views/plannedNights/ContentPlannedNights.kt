package com.example.joren.partynightplanner.views.plannedNights

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joren.partynightplanner.MainActivity
import com.example.joren.partynightplanner.R
import com.example.joren.partynightplanner.adapters.NightAdapter
import com.example.joren.partynightplanner.persistence.nights.NightRepo
import com.example.joren.partynightplanner.util.InjectorUtils
import kotlinx.android.synthetic.main.content_plannednights.*

class ContentPlannedNights: Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<NightAdapter.ViewHolder>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState : Bundle?): View?{
        return inflater.inflate(R.layout.content_plannednights, container, false)
    }

    override fun onStart() {
        super.onStart()
        initUi()
    }

    override fun onStop() {
        super.onStop()
        layoutManager = null
        adapter = null
        fabAdd.setOnClickListener(null)
    }

    private fun initUi(){
        layoutManager = LinearLayoutManager(this.context)
        nightRecyclerView.layoutManager = layoutManager
        val factory = InjectorUtils.providePlannedNightsViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory).get(PlannedNightsViewModel::class.java)

        //TODO actually pass userid via FB api
        viewModel.getNightsForUser("").observe(this, Observer { nights ->
            adapter = NightAdapter(nights!!, activity)
            nightRecyclerView.adapter = adapter
        })
        initFab()
    }

    private fun initFab(){
        fabAdd.setOnClickListener {
            (activity as MainActivity).openNewNightPanel()
        }
    }

    companion object {
        fun newInstance(): ContentPlannedNights {
            return ContentPlannedNights()
        }
    }
}