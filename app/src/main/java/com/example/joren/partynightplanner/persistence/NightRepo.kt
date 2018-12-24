package com.example.joren.partynightplanner.persistence

import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.util.DummyData

class NightRepo {
    companion object {
        fun getAllNights(): List<Night>{
            return DummyData.getNights()
        }
    }
}