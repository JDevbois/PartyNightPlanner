package com.example.joren.partynightplanner.domain

import com.google.firebase.database.IgnoreExtraProperties
import java.io.Serializable
import java.util.*

data class Event(
        var title: String,
        var desc: String,
        var date: Date,
        var imgSrc: String,
        var organiser: String
) : Serializable {
    var id: String = ""
    constructor(): this("", "", Calendar.getInstance().time, "http://www.vaultads.com/wp-content/uploads/2011/03/google-adsense.jpg", "")
}