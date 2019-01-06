package com.example.joren.partynightplanner.domain

import java.io.Serializable
import java.util.*

data class Night(var name: String, var desc: String, var events: MutableList<Event>, var date: Date, var friends: MutableList<String>): Serializable {

    var id = ""

    fun retrieveSortedEvents(): MutableList<Event>{
        return events.asSequence().sortedWith(compareBy {it.startDate.time}).toMutableList()
    }

    constructor(): this("", "", mutableListOf(), Calendar.getInstance().time, mutableListOf())
}