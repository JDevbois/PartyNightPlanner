package com.example.joren.partynightplanner.domain

import java.io.Serializable
import java.util.*

data class Night(var name: String, var desc: String, var events: MutableList<Event>, var date: Date, var friends: List<String>): Serializable {

    var id = ""

    fun friendsToString(): String {
        return this.friends.joinToString(separator = ", ")
    }

    constructor(): this("", "", mutableListOf(), Calendar.getInstance().time, mutableListOf())
}