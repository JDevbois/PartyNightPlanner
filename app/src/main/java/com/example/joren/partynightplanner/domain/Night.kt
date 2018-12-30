package com.example.joren.partynightplanner.domain

import java.io.Serializable
import java.util.*

class Night(var name: String, var desc: String, var events: List<Event>, var date: Date, var friends: List<String>): Serializable {

    fun friendsToString(): String {
        return this.friends.joinToString(separator = ", ")
    }
}