package com.example.joren.partynightplanner.views

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
import com.example.joren.partynightplanner.persistence.NightRepo
import com.facebook.AccessToken
import kotlinx.android.synthetic.main.content_plannednights.*

class ContentPlannedNights: Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<NightAdapter.ViewHolder>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState : Bundle?): View?{
        return inflater.inflate(R.layout.content_plannednights, container, false)
    }

    override fun onStart() {
        super.onStart()
        layoutManager = LinearLayoutManager(this.context)
        //TODO get by userid
        adapter = NightAdapter(NightRepo.getAllNights(), this.activity)

        initFab()

        nightRecyclerView.layoutManager = layoutManager
        nightRecyclerView.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
        layoutManager = null
        adapter = null
    }

    private fun initFab(){
        fabAdd.setOnClickListener {
            (activity as MainActivity).openNewNightPanel()
        }
    }

    companion object {
        fun newInstance(): ContentPlannedNights{
            return ContentPlannedNights()
        }
    }
}