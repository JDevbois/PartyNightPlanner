package com.example.joren.partynightplanner.views

import android.support.v4.app.Fragment
import com.example.joren.partynightplanner.domain.Night

class ContentNightDetail: Fragment() {
    companion object {
        fun newInstance(item: Night): ContentNightDetail{
            return ContentNightDetail()
        }
    }
}