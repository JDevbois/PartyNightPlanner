package com.example.joren.partynightplanner.views.newNight

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joren.partynightplanner.R
import com.example.joren.partynightplanner.domain.Event

class ContentAddEventToNight : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.content_add_event_to_night, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
    }

    private fun initUi() {

    }

    companion object {
        fun getInstance(): ContentAddEventToNight{
            return ContentAddEventToNight()
        }
    }
}