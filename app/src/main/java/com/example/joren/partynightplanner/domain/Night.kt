package com.example.joren.partynightplanner.domain

import com.example.joren.partynightplanner.persistence.users.UserRepo
import java.io.Serializable
import java.util.*

data class Night(var name: String, var desc: String, var events: MutableList<Event>, var date: Date, var friends: MutableList<String>): Serializable {

    var id = ""

    fun friendsToString(): String {
        var out = mutableListOf<String>()
        for (s: String in friends){
            out.add(UserRepo.findUserById(s).name)
        }
        return out.joinToString(separator = ", ")
    }

    fun retrieveSortedEvents(): MutableList<Event>{
        return events.asSequence().sortedWith(compareBy {it.startDate.time}).toMutableList()
    }

    constructor(): this("", "", mutableListOf(), Calendar.getInstance().time, mutableListOf())
}