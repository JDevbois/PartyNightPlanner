package com.example.joren.partynightplanner.util

import com.example.joren.partynightplanner.domain.Event
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.domain.User
import com.example.joren.partynightplanner.persistence.Database
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
                        Calendar.getInstance().time, mutableListOf("2239758839369841", "2236388479761767")),
                Night("Party @ Ovp aa skaan", "tzalwel aa skaan", events,
                        Calendar.getInstance().time, mutableListOf("2239758839369841", "2236388479761767"))
        )

        //TODO rework with facebook api test users
        var users: MutableList<User> = mutableListOf(
                User("2239758839369841", "Joren Debois"),
                User("2236388479761767", "Miriam Plugge")
        )

        //TODO: delete for release build
        fun loadDummyDataToDB(){
            for (e: Event in events){
                val key = Database.eventCloudEndPoint.push().key
                if (key != null){
                    Database.eventCloudEndPoint.child(key).setValue(e.also { e.id = key })
                }
            }

            for (n: Night in nights){
                val key = Database.nightCloudEndPoint.push().key
                if (key != null){
                    Database.nightCloudEndPoint.child(key).setValue(n.also { n.id = key })
                }
            }
        }
    }
}