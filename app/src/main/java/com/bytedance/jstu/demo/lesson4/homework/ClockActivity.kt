package com.bytedance.jstu.demo.lesson4.homework

import android.app.Activity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bytedance.jstu.demo.R

/**
 *  author : neo
 *  time   : 2021/10/30
 *  desc   :
 */
class ClockActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clock)
        var auto_btn = findViewById<Button>(R.id.auto_button)
        auto_btn.setOnClickListener(this)
        var man_btn = findViewById<Button>(R.id.man_button)
        man_btn.setOnClickListener(this)
//        var tvclock = findViewById<TextView>(R.id.tv_clock)
//        var clock = findViewById<ClockView>(R.id.clock)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.auto_button -> {  //自动时间
                Toast.makeText(this, "切换自动时间", Toast.LENGTH_SHORT).show()
//                autoTime(findViewById<ClockView>(R.id.tv_clock), findViewById<TextView>(R.id.clock))
            }
            R.id.man_button -> {  //手动时间
                Toast.makeText(this, "切换手动时间", Toast.LENGTH_SHORT).show()

            }
        }
    }
}
