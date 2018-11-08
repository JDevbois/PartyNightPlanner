package com.example.joren.partynightplanner

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.content_main.*

class ContentMain : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState : Bundle?): View?{
        return inflater.inflate(R.layout.content_main, container, false)
    }

    companion object {
        fun newInstance(): ContentMain{
            return ContentMain()
        }
    }
}