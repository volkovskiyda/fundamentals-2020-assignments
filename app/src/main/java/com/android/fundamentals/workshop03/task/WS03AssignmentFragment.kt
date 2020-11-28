package com.android.fundamentals.workshop03.task

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.android.fundamentals.R

//TODO(WS2:1) Create fragment class, extend Fragment
class WS03AssignmentFragment : Fragment() {

    private var btnIncrement: Button? = null
    private var btnChangeBackground: Button? = null
    private var listener: ClickListener? = null
    //TODO(WS2:5) Create a variable ClickListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? ClickListener
    }

    //TODO(WS2:2) Override onCreateView() method
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_root_ws_03, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnIncrement = view.findViewById<Button>(R.id.btn_increment).apply {
            setOnClickListener { listener?.onIncrement() }
            //TODO(WS2:7) Set button OnClickListener
        }
        btnChangeBackground = view.findViewById<Button>(R.id.btn_change_background).apply {
            setOnClickListener { listener?.onChangeBackground() }
            //TODO(WS2:8) Set button OnClickListener
        }

    }

    //TODO(WS2:6) Create fun setListener() initialize clickListener in Fragment
    fun setListener(listener: ClickListener) {
        this.listener = listener
    }

    //TODO(WS2:4) Create interface ClickListener
    interface ClickListener {
        fun onIncrement()
        fun onChangeBackground()
    }
}