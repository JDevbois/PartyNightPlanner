package com.example.joren.partynightplanner.util

import com.example.joren.partynightplanner.Event

class DummyData {
    companion object {
        fun getEvents() : Array<Event>{
            return arrayOf(
                    Event("ShotjesAvond", "22u - 23u", ""),
                    Event("Watercantus", "20u - 00u", ""),
                    Event("All Star On Repeat", "8u - 20u", "")
            )
        }
    }
}