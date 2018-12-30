package com.example.joren.partynightplanner.persistence

import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.util.DummyData

class NightRepo {
    companion object {
        //TODO sort by date
        fun getAllNights(): List<Night>{
            return DummyData.getNights()
        }

        fun getNightsByUser(i: Int): List<Night> {
            //TODO firebase request to filter by id field
            return DummyData.getNights()
        }
    }
}