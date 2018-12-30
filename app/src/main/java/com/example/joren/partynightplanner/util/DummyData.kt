package com.example.joren.partynightplanner.util

import com.example.joren.partynightplanner.domain.Event
import com.example.joren.partynightplanner.domain.Night
import java.util.*

class DummyData {
    companion object {
        fun getEvents() : List<Event>{
            return listOf(
                    Event("ShotjesAvond", "22u - 23u", Calendar.getInstance().time, "http://www.vaultads.com/wp-content/uploads/2011/03/google-adsense.jpg", ""),
                    Event("Watercantus", "20u - 00u", Calendar.getInstance().time, "http://www.vaultads.com/wp-content/uploads/2011/03/google-adsense.jpg", ""),
                    Event("All Star On Repeat", "8u - 20u", Calendar.getInstance().time, "http://www.vaultads.com/wp-content/uploads/2011/03/google-adsense.jpg", ""),
                    Event("All Star On Repeat", "8u - 20u", Calendar.getInstance().time, "http://www.vaultads.com/wp-content/uploads/2011/03/google-adsense.jpg", ""),
                    Event("All Star On Repeat", "8u - 20u", Calendar.getInstance().time, "http://www.vaultads.com/wp-content/uploads/2011/03/google-adsense.jpg", ""),
                    Event("All Star On Repeat", "8u - 20u", Calendar.getInstance().time, "http://www.vaultads.com/wp-content/uploads/2011/03/google-adsense.jpg", "")
            )
        }

        fun getNights(): List<Night> {
            return listOf(
                    Night("Party @ Ovp aa skaan", "tzalwel aa skaan", getEvents(),
                            Calendar.getInstance().time, listOf("Joren Debois", "Miriam Plugge")),
                    Night("Party @ Ovp aa skaan", "tzalwel aa skaan", getEvents(),
                            Calendar.getInstance().time, listOf("Joren Debois", "Miriam Plugge"))
            )
        }
    }
}