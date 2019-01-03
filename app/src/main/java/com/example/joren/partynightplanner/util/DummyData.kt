package com.example.joren.partynightplanner.util

import com.example.joren.partynightplanner.domain.Event
import com.example.joren.partynightplanner.domain.Night
import java.util.*

class DummyData {
    companion object {
        var events: MutableList<Event> = mutableListOf(
                Event("ShotjesAvond", "22u - 23u", Date(2019, 2, 3), Date(2019, 2, 3), "http://www.vaultads.com/wp-content/uploads/2011/03/google-adsense.jpg", ""),
                Event("Watercantus", "20u - 00u", Date(2019, 2, 4), Date(2019, 2, 3), "http://www.vaultads.com/wp-content/uploads/2011/03/google-adsense.jpg", ""),
                Event("All Star On Repeat", "8u - 20u", Date(2019, 2, 7), Date(2019, 2, 3), "http://www.vaultads.com/wp-content/uploads/2011/03/google-adsense.jpg", ""),
                Event("All Star On Repeat", "8u - 20u", Date(2019, 2, 7), Date(2019, 2, 3), "http://www.vaultads.com/wp-content/uploads/2011/03/google-adsense.jpg", ""),
                Event("All Star On Repeat", "8u - 20u", Date(2019, 2, 7), Date(2019, 2, 3), "http://www.vaultads.com/wp-content/uploads/2011/03/google-adsense.jpg", ""),
                Event("All Star On Repeat", "8u - 20u", Calendar.getInstance().time, Calendar.getInstance().time, "http://www.vaultads.com/wp-content/uploads/2011/03/google-adsense.jpg", "")
        )

        var nights: MutableList<Night> = mutableListOf(
                Night("Party @ Ovp aa skaan", "tzalwel aa skaan", events,
                        Calendar.getInstance().time, listOf("Joren Debois", "Miriam Plugge")),
                Night("Party @ Ovp aa skaan", "tzalwel aa skaan", events,
                        Calendar.getInstance().time, listOf("Joren Debois", "Miriam Plugge"))
        )
    }
}