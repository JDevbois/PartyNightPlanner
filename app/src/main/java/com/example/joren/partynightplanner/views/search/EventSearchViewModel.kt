package com.example.joren.partynightplanner.views.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.graphics.Color
import android.text.method.KeyListener
import com.example.joren.partynightplanner.domain.Event
import com.example.joren.partynightplanner.persistence.events.MyEventRepo
import kotlinx.android.synthetic.main.content_search.*
import java.text.SimpleDateFormat
import java.util.*

class EventSearchViewModel(private val eventRepo: MyEventRepo): ViewModel() {
    fun getFilteredEvents(option: Int, query: String) = eventRepo.getFilteredEvents(option, query)
}