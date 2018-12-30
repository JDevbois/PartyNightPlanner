package com.example.joren.partynightplanner.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joren.partynightplanner.R

class ContentNewNight: Fragment() {

    // TODO: addEvent click handler
    // TODO: Save event in real time

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState : Bundle?): View?{
        return inflater.inflate(R.layout.content_new_night, container, false)
    }

    companion object {
        fun newInstance(): ContentNewNight{
            return ContentNewNight()
        }
    }
}