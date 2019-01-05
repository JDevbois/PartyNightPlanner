package com.example.joren.partynightplanner.views.newNight

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.joren.partynightplanner.MainActivity
import com.example.joren.partynightplanner.R
import com.example.joren.partynightplanner.adapters.EventAdapter
import com.example.joren.partynightplanner.domain.Night
import com.example.joren.partynightplanner.util.InjectorUtils
import com.example.joren.partynightplanner.views.details.ContentNightDetail
import kotlinx.android.synthetic.main.content_new_night.*
import java.text.SimpleDateFormat
import java.util.*

class ContentNewNight: Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<EventAdapter.ViewHolder>? = null
    private lateinit var viewModel: NightViewModel

    private val myCalendar = Calendar.getInstance()!!
    private val year : Int = myCalendar.get(Calendar.YEAR)
    private val month : Int = myCalendar.get(Calendar.MONTH)
    private val day : Int = myCalendar.get(Calendar.DAY_OF_MONTH)

    private val hour : Int = myCalendar.get(Calendar.HOUR_OF_DAY)
    private val minute : Int = myCalendar.get(Calendar.MINUTE)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState : Bundle?): View?{
        return inflater.inflate(R.layout.content_new_night, container, false)
    }

    override fun onStart() {
        super.onStart()
        initUi()
    }

    override fun onStop() {
        super.onStop()
        layoutManager = null
        adapter = null

        newNightEventRecycleView.layoutManager = null
        newNightEventRecycleView.adapter = null
    }

    private fun initUi(){
        val factory = InjectorUtils.provideNightViewModelFactory()
        viewModel = ViewModelProviders.of(this, factory).get(NightViewModel::class.java)

        arguments?.let{
            if (it.containsKey(ContentNightDetail.ARG_NIGHT)){
                viewModel.parseNight(it.getSerializable(ContentNightDetail.ARG_NIGHT) as Night)
            }
        }

        //TODO: observe from viewmodel
        adapter = EventAdapter(viewModel.night!!.events, this.activity)
        newNightEventRecycleView.adapter = adapter

        layoutManager = LinearLayoutManager(this.context)
        newNightEventRecycleView.layoutManager = layoutManager

        editAddName.setText(viewModel.night!!.name)
        editAddDesc.setText(viewModel.night!!.desc)

        fabSaveNight.setOnClickListener {
            updateNightView()
            if(noFieldsAreNull(viewModel.night!!))
                (activity as MainActivity).saveNight(viewModel.night!!)
            else
                Toast.makeText(context, "You can't leave any of the fields empty. A night also needs at least one event", Toast.LENGTH_LONG).show()
        }

        btnSelectDateTime.setOnClickListener{
                val datePickerDialog = DatePickerDialog(context!!, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    //on date set
                    myCalendar.set(Calendar.YEAR, year)
                    myCalendar.set(Calendar.MONTH, month)
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    updateLabel()
                    val timePickerDialog = TimePickerDialog(context, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                        // on time set
                        myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        myCalendar.set(Calendar.MINUTE, minute)
                        updateLabel()
                    }, hour, minute, true)
                    timePickerDialog.show()
                }, year, month, day)
            datePickerDialog.show()
        }

        btnAddEvent.setOnClickListener{
            updateNightView()
            (activity as MainActivity).openAddEventToNightPanel(viewModel.night!!)
        }

        updateLabel()
    }

    private fun noFieldsAreNull(night: Night): Boolean {
        return (night.name != "" && night.desc != "" && night.events.isNotEmpty())
    }

    private fun updateLabel(){
        val sdf = SimpleDateFormat("MM/dd/yyyy hh:mm a", Locale.US)

        txtDateAdd.text = sdf.format(myCalendar.time)
    }

    private fun updateNightView(){
        viewModel.night!!.name = editAddName.text.toString()
        viewModel.night!!.desc = editAddDesc.text.toString()
        viewModel.night!!.date = myCalendar.time
    }

    companion object {
        const val ARG_NIGHT = "item_id"

        fun newInstance(): ContentNewNight {
            return ContentNewNight()
        }

        fun newInstance(night: Night): ContentNewNight {
            val fragment = ContentNewNight()
            val args = Bundle()
            args.putSerializable(ARG_NIGHT, night)
            fragment.arguments = args
            return fragment
        }
    }
}