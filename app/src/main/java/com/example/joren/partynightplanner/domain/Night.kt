package com.example.joren.partynightplanner.domain

import java.io.Serializable
import java.util.*

data class Night(var name: String, var desc: String, var events: MutableList<Event>, var date: Date, var friends: List<String>): Serializable {

    var id = ""

    fun friendsToString(): String {
        return this.friends.joinToString(separator = ", ")
    }

    fun getSortedEvents(): MutableList<Event>{
        return events.asSequence().sortedWith(compareBy {it.startDate.time}).toMutableList()
    }

    constructor(): this("", "", mutableListOf(), Calendar.getInstance().time, mutableListOf())
}