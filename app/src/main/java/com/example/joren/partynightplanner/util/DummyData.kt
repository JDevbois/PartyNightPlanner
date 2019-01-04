package com.example.joren.partynightplanner.util

import com.example.joren.partynightplanner.domain.Event
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.domain.User
import java.util.*

class DummyData {
    /*
    Test user details:
    Name: Professor Professorson
    Email: professor_deynwjz_professorson@tfbnw.net
    Password: Professor123
    */
    companion object {
        // TODO: this should not be a thing
        private const val YEAR_OFFSET: Int = 1900
        private val CURR_YEAR: Int = 2019 - YEAR_OFFSET

        var events: MutableList<Event> = mutableListOf(
                Event("ShotjesAvond", "22u - 23u", Date(CURR_YEAR, 2, 3), Date(CURR_YEAR, 2, 3), "http://www.vaultads.com/wp-content/uploads/2011/03/google-adsense.jpg", ""),
                Event("Watercantus", "20u - 00u", Date(CURR_YEAR, 2, 3), Date(CURR_YEAR, 2, 4), "http://www.vaultads.com/wp-content/uploads/2011/03/google-adsense.jpg", ""),
                Event("All Star On Repeat", "8u - 20u", Date(CURR_YEAR, 2, 3), Date(CURR_YEAR, 2, 7), "http://www.vaultads.com/wp-content/uploads/2011/03/google-adsense.jpg", ""),
                Event("All Star On Repeat", "8u - 20u", Date(CURR_YEAR, 2, 3), Date(CURR_YEAR, 2, 7), "http://www.vaultads.com/wp-content/uploads/2011/03/google-adsense.jpg", ""),
                Event("All Star On Repeat", "8u - 20u", Date(CURR_YEAR, 2, 3), Date(CURR_YEAR, 2, 7), "http://www.vaultads.com/wp-content/uploads/2011/03/google-adsense.jpg", ""),
                Event("All Star On Repeat", "8u - 20u", Calendar.getInstance().time, Calendar.getInstance().time, "http://www.vaultads.com/wp-content/uploads/2011/03/google-adsense.jpg", "")
        )

        var nights: MutableList<Night> = mutableListOf(
                Night("Party @ Ovp aa skaan", "tzalwel aa skaan", events,
                        Calendar.getInstance().time, listOf("2239758839369841", "2236388479761767")),
                Night("Party @ Ovp aa skaan", "tzalwel aa skaan", events,
                        Calendar.getInstance().time, listOf("2239758839369841", "2236388479761767"))
        )

        //TODO rework with facebook api test users
        var users: MutableList<User> = mutableListOf(
                User("2239758839369841", "Joren Debois", "https://scontent-bru2-1.xx.fbcdn.net/v/t31.0-8/17545174_1489914657687600_2380384967662063903_o.jpg?_nc_cat=106&_nc_ht=scontent-bru2-1.xx&oh=b7164a3287f3801f1b7e8005ea07ec3c&oe=5CD055E0"),
                User("2236388479761767", "Miriam Plugge", "https://scontent-bru2-1.xx.fbcdn.net/v/t1.0-1/c5.0.160.160a/p160x160/45282917_2151860321547917_1694150558576803840_n.jpg?_nc_cat=103&_nc_ht=scontent-bru2-1.xx&oh=d770a1f7e76ac69c17be7dc051af83c7&oe=5C9865B5")
        )
    }
}