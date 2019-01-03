package com.example.joren.partynightplanner.views.details

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.joren.partynightplanner.R
import com.example.joren.partynightplanner.domain.Event
import kotlinx.android.synthetic.main.event_detail.view.*
import com.example.joren.partynightplanner.util.DownloadImageTask
import java.text.SimpleDateFormat
import java.util.*


class EventDetailFragment : Fragment() {
    private lateinit var event: Event

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments!!.let{
            if (it.containsKey(ARG_EVENT)){
                event = it.getSerializable(ARG_EVENT) as Event
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.event_detail, container, false)

        event.let {
            rootView.eventDetailTitle.text = event.title
            rootView.eventDetailDesc.text = event.desc
            rootView.eventDetailStartDateAndTime.text = SimpleDateFormat("dd MM yyyy hh:mm a", Locale.US).format(event.startDate)
            rootView.eventDetailEndDateAndTime.text = SimpleDateFormat("dd MM yyyy hh:mm a", Locale.US).format(event.endDate)

            DownloadImageTask(rootView.eventDetailImg).execute(event.imgSrc)
        }

        return rootView
    }

    companion object {
        const val ARG_EVENT = "item_id"

        fun newInstance(event: Event): EventDetailFragment {
            val args = Bundle()
            args.putSerializable(ARG_EVENT, event)
            val fragment = EventDetailFragment()
            fragment.arguments = args

            return fragment
        }
    }
}