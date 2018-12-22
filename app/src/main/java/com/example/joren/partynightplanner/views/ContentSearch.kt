package com.example.joren.partynightplanner.views

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.content_search.*
import java.util.*
import android.app.DatePickerDialog
import java.text.SimpleDateFormat
import android.text.method.KeyListener
import android.view.KeyEvent
import com.example.joren.partynightplanner.persistence.EventRepo
import com.example.joren.partynightplanner.MainActivity
import com.example.joren.partynightplanner.R
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.inputmethod.EditorInfo


class ContentSearch : Fragment() {
    private var selectedOption: Int = -1
    private var query: String = ""

    private val myCalendar = Calendar.getInstance()!!
    private val year : Int = myCalendar.get(Calendar.YEAR)
    private val month : Int = myCalendar.get(Calendar.MONTH)
    private val day : Int = myCalendar.get(Calendar.DAY_OF_MONTH)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.content_search, container, false)
    }

    override fun onStart() {
        super.onStart()

        if(this.activity is MainActivity){
            (this.activity as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.loggedInFragment, LoggedInFragment.newInstance()).commit()
        }

        editTextSearch.tag = editTextSearch.keyListener

        initOptions()
        setSearchOption(EventRepo.BY_NAME)

        //TODO handle enter
        btnSearch.setOnClickListener {
            search()
        }

        editTextSearch.setOnKeyListener { v, keyCode, event ->
            if(keyCode == KeyEvent.KEYCODE_ENTER){
                search()
                false
            } else
                true
        }

        editTextSearch.setOnClickListener{
            var isDataSet = false
            if (selectedOption == EventRepo.BY_DATE){
                val datePickerDialog = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    myCalendar.set(Calendar.YEAR, year)
                    myCalendar.set(Calendar.MONTH, month)
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    updateLabel()
                    isDataSet = true
                }, year, month, day)
                datePickerDialog.show()
            }
            if(isDataSet){
                txtSearchTitle.requestFocus()
            }
        }

        editTextSearch.setOnFocusChangeListener { v, hasFocus ->
            var isDataSet = false
            if (selectedOption == EventRepo.BY_DATE){
                val datePickerDialog = DatePickerDialog(context, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    myCalendar.set(Calendar.YEAR, year)
                    myCalendar.set(Calendar.MONTH, month)
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    updateLabel()
                    isDataSet = true
                }, year, month, day)
                datePickerDialog.show()
            }
            if(isDataSet){
                txtSearchTitle.requestFocus()
            }
        }
    }

    fun search(){
        query = editTextSearch.text.toString()
        if(this.activity is MainActivity) {
            (this.activity as MainActivity).openSearchResultPanel(selectedOption, query)
        }
    }

    private fun initOptions(){
        optionDatum.setOnClickListener {
            setSearchOption(EventRepo.BY_DATE)
        }
        optionEventname.setOnClickListener {
            setSearchOption(EventRepo.BY_NAME)
        }
        optionOrganiser.setOnClickListener {
            setSearchOption(EventRepo.BY_ORGANISER)
        }
    }

    private fun setSearchOption(index: Int) {
        selectedOption = index
        updateOptionViews()
        txtSearchTitle.requestFocus()
    }

    private fun updateOptionViews(){
        when (selectedOption){
            EventRepo.BY_DATE -> {
                optionDatum.setTextColor(Color.RED)
                optionEventname.setTextColor(Color.BLACK)
                optionOrganiser.setTextColor(Color.BLACK)

                editTextSearch.keyListener = null

                editTextSearch.isClickable = false
                editTextSearch.isFocusable = true

            }
            EventRepo.BY_NAME -> {
                optionDatum.setTextColor(Color.BLACK)
                optionEventname.setTextColor(Color.RED)
                optionOrganiser.setTextColor(Color.BLACK)

                editTextSearch.keyListener = editTextSearch.tag as KeyListener

            }
            EventRepo.BY_ORGANISER -> {
                optionDatum.setTextColor(Color.BLACK)
                optionEventname.setTextColor(Color.BLACK)
                optionOrganiser.setTextColor(Color.RED)

                editTextSearch.keyListener = editTextSearch.tag as KeyListener
            }
            else -> {
                optionDatum.setTextColor(Color.BLACK)
                optionEventname.setTextColor(Color.BLACK)
                optionOrganiser.setTextColor(Color.BLACK)
            }
        }
    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yy"
        val sdf = SimpleDateFormat(myFormat, Locale.US)

        editTextSearch.setText(sdf.format(myCalendar.time))
    }

    companion object {
        fun newInstance(): ContentSearch {
            return ContentSearch()
        }
    }
}
