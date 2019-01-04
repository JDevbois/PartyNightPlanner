package com.example.joren.partynightplanner

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.joren.partynightplanner.persistence.nights.NightRepo

class MainViewModelFactory(private val nightRepo: NightRepo): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(nightRepo) as T
    }
}