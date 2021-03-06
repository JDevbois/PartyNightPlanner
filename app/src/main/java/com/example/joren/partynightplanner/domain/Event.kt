package com.example.joren.partynightplanner.domain

import java.io.Serializable
import java.util.*

data class Event(
        var title: String,
        var desc: String,
        var startDate: Date,
        var endDate: Date,
        var imgSrc: String,
        var organiser: String
) : Serializable {
    var id = ""
    constructor(): this("", "", Calendar.getInstance().time, Calendar.getInstance().time, "http://www.vaultads.com/wp-content/uploads/2011/03/google-adsense.jpg", "")
}