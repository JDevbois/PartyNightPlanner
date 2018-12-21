package com.example.joren.partynightplanner

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.content_search.*

class ContentSearch : Fragment() {
    private var selectedOption: Int = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.content_search, container, false)
    }

    override fun onStart() {
        super.onStart()
        initOptions()
    }

    private fun initOptions(){
        optionDatum.setOnClickListener {
            setSearchOption(0)
        }
        optionEventname.setOnClickListener {
            setSearchOption(1)
        }
        optionOrganisator.setOnClickListener {
            setSearchOption(2)
        }
    }

    private fun setSearchOption(index: Int) {
        selectedOption = index
        updateOptionViews()
    }

    private fun updateOptionViews(){
        when (selectedOption){
            0 -> {
                optionDatum.setTextColor(Color.RED)
                optionEventname.setTextColor(Color.BLACK)
                optionOrganisator.setTextColor(Color.BLACK)
            }
            1 -> {
                optionDatum.setTextColor(Color.BLACK)
                optionEventname.setTextColor(Color.RED)
                optionOrganisator.setTextColor(Color.BLACK)
            }
            2 -> {
                optionDatum.setTextColor(Color.BLACK)
                optionEventname.setTextColor(Color.BLACK)
                optionOrganisator.setTextColor(Color.RED)
            }
            else -> {
                optionDatum.setTextColor(Color.BLACK)
                optionEventname.setTextColor(Color.BLACK)
                optionOrganisator.setTextColor(Color.BLACK)
            }
        }
    }

    companion object {
        fun newInstance(): ContentSearch {
            return ContentSearch()
        }
    }
}
