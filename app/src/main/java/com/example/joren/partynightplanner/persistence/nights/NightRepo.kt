package com.example.joren.partynightplanner.persistence.nights

import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.persistence.Database
import com.example.joren.partynightplanner.util.DummyData

class NightRepo {
    companion object {
        //TODO sort by date
        fun getAllNights(): List<Night>{
            return DummyData.nights
        }

        fun addNight(night: Night){
            if(!getAllNights().contains(night))
                DummyData.nights.add(night)
            else
                DummyData.nights.map { if (it == night) night else it }
        }

        fun getNightsByUser(id: String): List<Night> {
            //TODO firebase request to filter by id field
            return DummyData.nights
        }
    }
}