package com.example.joren.partynightplanner.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joren.partynightplanner.R

class LoggedInFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState : Bundle?): View?{
        return inflater.inflate(R.layout.logged_in_fragment, container, false)
    }

    companion object {
        fun newInstance(): LoggedInFragment {
            return LoggedInFragment()
        }
    }
}