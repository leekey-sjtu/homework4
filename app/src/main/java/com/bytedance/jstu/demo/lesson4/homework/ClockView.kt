package com.bytedance.jstu.demo.lesson4.homework

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.bytedance.jstu.demo.R
import org.w3c.dom.Text
import java.lang.Math.*
import java.util.*
import kotlin.math.cos
import kotlin.math.sin

/**
 *  author : wudewei
 *  time   : 2022/03/13
 *  desc   :
 */
class ClockView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var hPaint: Paint = Paint()  //时针
    private var mPaint: Paint = Paint()  //分针
    private var sPaint: Paint = Paint()  //秒针
    private var wPaint: Paint = Paint()  //表盘
    private var wPaintBold: Paint = Paint()  //表盘

    init {  //时针
        hPaint.color = Color.RED
        hPaint.style = Paint.Style.FILL
        hPaint.isAntiAlias = true
        hPaint.strokeWidth = 12F
        hPaint.strokeJoin = Paint.Join.ROUND
    }

    init {  //分针
        mPaint.color = Color.BLUE
        mPaint.style = Paint.Style.FILL
        mPaint.isAntiAlias = true
        mPaint.strokeWidth = 10F
    }

    init {  //秒针
        sPaint.color = Color.WHITE
        sPaint.style = Paint.Style.FILL
        sPaint.isAntiAlias = true
        sPaint.strokeWidth = 6F
    }

    init {  //表盘
        wPaint.color = Color.parseColor("#6a6a6a")
        wPaint.style = Paint.Style.FILL
        wPaint.isAntiAlias = true
        wPaint.strokeWidth = 6F
    }

    init {  //表盘-粗线
        wPaintBold.color = Color.WHITE
        wPaintBold.style = Paint.Style.FILL
        wPaintBold.isAntiAlias = true
        wPaintBold.strokeWidth = 8F
        wPaintBold.textSize = 100F
        wPaintBold.textAlign = Paint.Align.CENTER
    }

    private var curX: Float? = 0F
    private var curY: Float? = 0F
    private var hdeg: Double = 162.5  //时针角度
    private var mdeg: Double = 60.0  //分针角度
    private var sdeg: Double = 270.0  //秒针角度
    private val ox: Float = 500F  //圆心坐标
    private val oy: Float = 1000F  //圆心坐标
    private val r: Float = 500F  //半径

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        Toast.makeText(context, "width=$width,height=$height", Toast.LENGTH_SHORT).show()

        //画时针
        var x: Float = ((0.4 * r * cos(hdeg * PI / 180)) + ox).toFloat()
        var y: Float = ((0.4 * r  * sin(hdeg * PI / 180)) + oy).toFloat()
        canvas?.drawLine(ox, oy, x, y, hPaint)
        canvas?.drawCircle(x, y, 6F, hPaint)

        //画分针
        x = ((0.65 * r * cos(mdeg * PI / 180)) + ox).toFloat()
        y = ((0.65 * r * sin(mdeg * PI / 180)) + oy).toFloat()
        canvas?.drawLine(ox, oy, x, y, mPaint)
        canvas?.drawCircle(x, y, 5F, mPaint)

        //画秒针
        x = ((0.8 * r * cos(sdeg * PI / 180)) + ox).toFloat()
        y = ((0.8 * r * sin(sdeg * PI / 180)) + oy).toFloat()
        canvas?.drawLine(ox, oy, x, y, sPaint)
        canvas?.drawCircle(x, y, 3F, sPaint)
        canvas?.drawCircle(ox, oy, 10F, sPaint)

        //画时间刻度线
        for(deg in 0..354 step 6) {
            val x1: Float = (0.9 * r * cos(deg.toDouble() * PI / 180)).toFloat() + ox
            val y1: Float = (0.9 * r * sin(deg.toDouble() * PI / 180)).toFloat() + oy
            val x2: Float = (r * cos(deg.toDouble() * PI / 180)).toFloat() + ox
            val y2: Float = (r * sin(deg.toDouble() * PI / 180)).toFloat() + oy
            if(deg % 30 == 0) canvas?.drawLine(x1, y1, x2, y2, wPaintBold)
            else canvas?.drawLine(x1, y1, x2, y2, wPaint)
        }

        //画时间数字
        x = (0.8 * r * cos( 5.5 * PI / 180)).toFloat() + ox
        y = (0.8 * r * sin( 5.5 * PI / 180)).toFloat() + oy
        canvas?.drawText("3", x, y, wPaintBold)
        x = (0.82 * r * cos( 34 * PI / 180)).toFloat() + ox
        y = (0.82 * r * sin( 34 * PI / 180)).toFloat() + oy
        canvas?.drawText("4", x, y, wPaintBold)
        x = (0.84 * r * cos( 62 * PI / 180)).toFloat() + ox
        y = (0.84 * r * sin( 62 * PI / 180)).toFloat() + oy
        canvas?.drawText("5", x, y, wPaintBold)
        x = (0.85 * r * cos( 90 * PI / 180)).toFloat() + ox
        y = (0.85 * r * sin( 90 * PI / 180)).toFloat() + oy
        canvas?.drawText("6", x, y, wPaintBold)
        x = (0.84 * r * cos( 118 * PI / 180)).toFloat() + ox
        y = (0.84 * r * sin( 118 * PI / 180)).toFloat() + oy
        canvas?.drawText("7", x, y, wPaintBold)
        x = (0.82 * r * cos( 145 * PI / 180)).toFloat() + ox
        y = (0.82 * r * sin( 145 * PI / 180)).toFloat() + oy
        canvas?.drawText("8", x, y, wPaintBold)
        x = (0.8 * r * cos( 174 * PI / 180)).toFloat() + ox
        y = (0.8 * r * sin( 174 * PI / 180)).toFloat() + oy
        canvas?.drawText("9", x, y, wPaintBold)
        x = (0.71 * r * cos( 205 * PI / 180)).toFloat() + ox
        y = (0.71 * r * sin( 205 * PI / 180)).toFloat() + oy
        canvas?.drawText("10", x, y, wPaintBold)
        x = (0.69 * r * cos( 239 * PI / 180)).toFloat() + ox
        y = (0.69 * r * sin( 239 * PI / 180)).toFloat() + oy
        canvas?.drawText("11", x, y, wPaintBold)
        x = (0.69 * r * cos( 270 * PI / 180)).toFloat() + ox
        y = (0.69 * r * sin( 270 * PI / 180)).toFloat() + oy
        canvas?.drawText("12", x, y, wPaintBold)
        x = (0.72 * r * cos( 304 * PI / 180)).toFloat() + ox
        y = (0.72 * r * sin( 304 * PI / 180)).toFloat() + oy
        canvas?.drawText("1", x, y, wPaintBold)
        x = (0.76 * r * cos( 335 * PI / 180)).toFloat() + ox
        y = (0.76 * r * sin( 335 * PI / 180)).toFloat() + oy
        canvas?.drawText("2", x, y, wPaintBold)

        //画电子时钟
        var hour = (hdeg / 30 + 3).toInt()
        if(hour > 12) hour -= 12
        var min: Int = (mdeg / 6 + 15).toInt() % 60
        var sec: Int = (sdeg / 6 + 15).toInt() % 60

        //保持时间两位数
        var shour = hour.toString()
        if (hour < 10)  shour = "0" + shour
        var smin = min.toString()
        if (min < 10)  smin = "0" + smin
        var ssec = sec.toString()
        if (sec < 10)  ssec = "0" + ssec
        canvas?.drawText("$shour : $smin : $ssec", 500F, 300F, wPaintBold)

//        canvas?.drawPoint(500F,500F, mPaint)
//        canvas?.drawLine(500F, 500F, 933F, 250F, mPaint)
//        canvas?.drawOval(200F, 200F, 100F, 100F, mPaint)
//        canvas?.drawText("texttext", 100F, 100F, mPaint)
//        canvas?.drawRect(0F,0F,1000F,1000F,mPaint)
//        canvas?.drawCircle(500F, 500F, 10F, mPaint)
//        val oval: RectF = RectF(0F, 0F, width.toFloat(), height.toFloat())
//        canvas?.drawArc(RectF(0F, 0F, width.toFloat(), height.toFloat()), 0F, 360F, true, wPaint)
    }

    //实现手动触摸切换时间
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)
        this.curX = event?.getX()!!
        this.curY = event?.getY()!!

        //当前触摸点相对于时钟中心的角度
        var deg: Double = toDegrees(atan(((curY!! - oy) / (curX!! - ox)).toDouble()))
        if(curX!! >= ox && curY!! >= oy){  //第一象限
            //值不变
        }else if(curX!! <= ox && curY!! >= oy){  //第二象限
            deg += 180.0
        }else if(curX!! <= ox && curY!! <= oy){  //第三象限
            deg += 180.0
        }else if(curX!! >= ox && curY!! <= oy){  //第四象限
            deg += 360.0
        }

        //用触摸点的角度来判断应该滑动哪个针
        if(abs(deg - sdeg) <= 6 || abs(deg - sdeg) >= 354){  //滑动秒针，时针、分针应该相应转动
            if(sdeg - deg >= 354)  {
                mdeg += (deg + 360 - sdeg) / 60
                hdeg += (deg + 360 - sdeg) / 720
            } else if(deg - mdeg >= 354) {
                mdeg += (deg - 360 - sdeg) / 60
                hdeg += (deg - 360 - sdeg) / 720
            } else {
                mdeg += (deg - sdeg) / 60
                hdeg += (deg - sdeg) / 720
            }
            sdeg = deg
        } else if(abs(deg - mdeg) <= 6 || abs(deg - mdeg) >= 354){  //滑动分针，时针、秒针应该相应转动
            if(mdeg - deg >= 354)  {
                hdeg += (deg + 360 - mdeg) / 12
//                sdeg += (deg + 360 - mdeg) * 60
            } else if(deg - mdeg >= 354) {
                hdeg += (deg - 360 - mdeg) / 12
//                sdeg += (deg - 360 - mdeg) * 60
            } else {
                hdeg += (deg - mdeg) / 12
//                sdeg += (deg - mdeg) * 60
            }
            mdeg = deg
        } else if(abs(deg - hdeg) <= 6 || abs(deg - hdeg) >= 354){  //滑动时针
            if(hdeg - deg >= 354)  {
                mdeg += (deg + 360 - hdeg) * 12
//                sdeg += (deg + 360 - hdeg) * 720
            } else if(deg - hdeg >= 354) {
                mdeg += (deg - 360 - hdeg) * 12
//                sdeg += (deg - 360 - hdeg) * 720
            } else {
                mdeg += (deg - hdeg) * 12
//                sdeg += (deg - hdeg) * 720
            }
            hdeg = deg
        }

        //重绘onDraw
        this.invalidate()
        return true
    }

}
