package com.example.joren.partynightplanner.domain

import java.util.*

data class Notification(val id: String, val from: String, val to: String, val message: String, val isAccepted: Boolean, val date: Date){
    constructor(): this("", "", "", "", false, Calendar.getInstance().time)
}