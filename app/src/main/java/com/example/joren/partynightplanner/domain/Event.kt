package com.example.joren.partynightplanner.domain

import java.io.Serializable
import java.util.*

class Event(
        var title: String,
        var desc: String,
        var date: Date,
        var imgSrc: String,
        var organiser: String
) : Serializable {

    constructor(): this("", "", Calendar.getInstance().time, "", "")
}