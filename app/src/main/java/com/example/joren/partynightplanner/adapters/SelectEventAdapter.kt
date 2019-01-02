package com.example.joren.partynightplanner.adapters

import android.support.v4.app.FragmentActivity
import android.view.View
import com.example.joren.partynightplanner.MainActivity
import com.example.joren.partynightplanner.domain.Event

class SelectEventAdapter(dataSet: List<Event>, parentActivity: FragmentActivity?) : EventAdapter(dataSet, parentActivity) {
    var selectedItem: Event? = null

    init {
        onClickListener = View.OnClickListener { e ->
            selectedItem = e.tag as Event
        }
    }
}