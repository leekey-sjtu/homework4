package com.bytedance.jstu.demo.lesson4.homework

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bytedance.jstu.demo.R

/**
 *  author : wudewei
 *  time   : 2022/03/15
 *  desc   :
 */
class ClockActivity : AppCompatActivity(), View.OnClickListener {

    var handler: Handler = Handler(Looper.getMainLooper())
    var runnable: Runnable = Runnable() {
        run() {
            var autoClock = findViewById<AutoClockView>(R.id.auto_clock)
            autoClock.setDeg()
            handler.postDelayed(runnable, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clock)
        var man_btn = findViewById<Button>(R.id.man_button)
        var auto_btn = findViewById<Button>(R.id.auto_button)
        man_btn.setOnClickListener(this)
        auto_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.auto_button -> {  //自动时间
                Toast.makeText(this, "切换自动时间", Toast.LENGTH_SHORT).show()
                var clock = findViewById<ClockView>(R.id.clock)
                var autoClock = findViewById<AutoClockView>(R.id.auto_clock)
                clock.visibility = View.INVISIBLE  //切换ClockView
                autoClock.visibility = View.VISIBLE
                handler.post(runnable)  //启动runnable任务
            }
            R.id.man_button -> {  //手动时间
                Toast.makeText(this, "切换手动时间", Toast.LENGTH_SHORT).show()
                var clock = findViewById<ClockView>(R.id.clock)
                var autoClock = findViewById<AutoClockView>(R.id.auto_clock)
                autoClock.visibility = View.INVISIBLE  //切换ClockView
                clock.visibility = View.VISIBLE
                handler.removeCallbacks(runnable)  //停止runnable任务
            }
        }
    }

}
