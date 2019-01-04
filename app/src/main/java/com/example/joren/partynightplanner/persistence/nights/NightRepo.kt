package com.example.joren.partynightplanner.persistence.nights

import com.example.joren.partynightplanner.domain.Night

class NightRepo private constructor(private val nightDao: NightDao) {

    fun addNight(night: Night){
        nightDao.addNight(night)
    }

    fun getNights() = nightDao.getNights()

    fun deleteNight(night: Night){
        nightDao.deleteNight(night)
    }

    fun updateNight(night: Night){
        nightDao.updateNight(night)
    }

    companion object {
        // singleton code
        @Volatile private var instance: NightRepo? = null

        fun getInstance(nightDao: NightDao) =
                instance ?: synchronized(this){
                    instance ?: NightRepo(nightDao).also {
                        instance = it
                    }
                }
    }
}