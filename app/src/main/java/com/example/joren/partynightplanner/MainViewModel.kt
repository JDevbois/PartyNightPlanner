package com.example.joren.partynightplanner

import android.arch.lifecycle.ViewModel
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.persistence.nights.MyNightRepo

class MainViewModel(private val nightRepo: MyNightRepo): ViewModel() {
    fun addNight(night: Night){
        nightRepo.addNight(night)
    }
}