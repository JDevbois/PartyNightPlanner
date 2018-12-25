package com.example.joren.partynightplanner.util

import com.example.joren.partynightplanner.domain.Event
import com.example.joren.partynightplanner.domain.Night
import java.util.*

class DummyData {
    companion object {
        fun getEvents() : List<Event>{
            return listOf(
                    Event("ShotjesAvond", "22u - 23u", Calendar.getInstance().time, "", ""),
                    Event("Watercantus", "20u - 00u", Calendar.getInstance().time, "", ""),
                    Event("All Star On Repeat", "8u - 20u", Calendar.getInstance().time, "", ""),
                    Event("All Star On Repeat", "8u - 20u", Calendar.getInstance().time, "", ""),
                    Event("All Star On Repeat", "8u - 20u", Calendar.getInstance().time, "", ""),
                    Event("All Star On Repeat", "8u - 20u", Calendar.getInstance().time, "", "")
            )
        }

        fun getNights(): List<Night> {
            //TODO
            return listOf(
                    Night("", "", getEvents(), Calendar.getInstance().time, listOf(0, 0))
            )
        }
    }
}