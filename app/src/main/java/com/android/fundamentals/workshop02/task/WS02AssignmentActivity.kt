package com.android.fundamentals.workshop02.task

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.fundamentals.R
import com.android.fundamentals.workshop02.WS02RootFragment
import com.android.fundamentals.workshop02.WS02SecondFragment

class WS02AssignmentActivity : AppCompatActivity(), WS02RootFragment.TransactionsFragmentClicks {

    private val rootFragment =
        WS02RootFragment().apply { setClickListener(this@WS02AssignmentActivity) }
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ws02_ws03)

        supportFragmentManager.beginTransaction()
            .add(R.id.persistent_container, rootFragment)
            .commit()
    }


    override fun addRedFragment() {
        count++
        supportFragmentManager.beginTransaction()
            .add(R.id.fragments_container, WS02SecondFragment.newInstance(count, R.color.red))
            .commit()
        //TODO(WS3:1) Add red fragment like SecondFragmentWS3.newInstance(countId, R.color.red)
    }

    override fun addBlueFragment() {
        count++
        supportFragmentManager.beginTransaction()
            .add(R.id.fragments_container, WS02SecondFragment.newInstance(count, R.color.blue))
            .commit()
        //TODO(WS3:2) Add blue fragment like SecondFragmentWS3.newInstance(countId, R.color.blue)
    }

    override fun removeLast() {
        if (supportFragmentManager.fragments.size > 1) {
            count--
            supportFragmentManager.beginTransaction()
                .remove(supportFragmentManager.fragments.last())
                .commit()
            //TODO(WS3:3) Remove fragment
        }
    }

    override fun replaceFragment() {
        count = 1
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragments_container, WS02SecondFragment.newInstance(count, R.color.green))
            .commit()
        //TODO(WS3:4) Replace current fragment green fragment SecondFragmentWS3.newInstance(countId, R.color.green)
    }

}