package com.android.fundamentals.workshop03.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.R
import com.android.fundamentals.workshop03.WS03SecondFragment

//TODO(WS2:9) Implement interface in Activity
class WS03AssignmentActivity : AppCompatActivity(), WS03AssignmentFragment.ClickListener {

    //TODO(WS2:9) Create root fragment and set listener
    private var secondFragment = WS03SecondFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ws02_ws03)

        if (savedInstanceState == null)
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.persistent_container,
                    WS03AssignmentFragment().apply { setListener(this@WS03AssignmentActivity) })
                .add(R.id.fragments_container, secondFragment, "secondFragment")
                .commit()
        else secondFragment = supportFragmentManager.findFragmentByTag("secondFragment") as WS03SecondFragment
    }

    //TODO(WS2:10) Change the text in secondFragment
    //TODO(WS2:11) Change fragment text background in secondFragment
    override fun onIncrement() {
        secondFragment.increaseValue()
    }

    override fun onChangeBackground() {
        secondFragment.changeBackground()
    }
}