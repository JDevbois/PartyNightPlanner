package com.example.joren.partynightplanner.util

import com.example.joren.partynightplanner.domain.Event
import java.util.*

class DummyData {
    companion object {
        fun getEvents() : List<Event>{
            return arrayOf(
                    Event("ShotjesAvond", "22u - 23u", Calendar.getInstance().time, "", ""),
                    Event("Watercantus", "20u - 00u", Calendar.getInstance().time, "", ""),
                    Event("All Star On Repeat", "8u - 20u", Calendar.getInstance().time, "", ""),
                    Event("All Star On Repeat", "8u - 20u", Calendar.getInstance().time, "", ""),
                    Event("All Star On Repeat", "8u - 20u", Calendar.getInstance().time, "", ""),
                    Event("All Star On Repeat", "8u - 20u", Calendar.getInstance().time, "", "")
            ).toList()
        }
    }
}