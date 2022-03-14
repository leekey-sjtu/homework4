package com.bytedance.jstu.demo.lesson4.homework

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.lang.Math.PI
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

    var hx: Float = 1000F
    var hy: Float = 1000F

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

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val w: Float = width.toFloat()
        val h: Float = height.toFloat()
        val r: Float = w / 2
        val hr: Float = (0.4 * r).toFloat()
        val mr: Float = (0.65 * r).toFloat()
        val sr: Float = (0.8 * r).toFloat()
        var hdeg: Double = 215.0
        var mdeg: Double = 90.0
        var sdeg: Double = -30.0
        var x: Float = ((hr * cos(hdeg * PI / 180)) + r).toFloat()
        var y: Float = ((hr * sin(hdeg * PI / 180)) + r).toFloat()
        canvas?.drawLine(r, r, hx, hy, hPaint) //test时针
        canvas?.drawLine(r, r, x, y, hPaint)
        canvas?.drawCircle(x, y, 6F, hPaint)
        x = ((mr * cos(mdeg * PI / 180)) + r).toFloat()
        y = ((mr * sin(mdeg * PI / 180)) + r).toFloat()
        canvas?.drawLine(r, r, x, y, mPaint)
        canvas?.drawCircle(x, y, 5F, mPaint)
        x = ((sr * cos(sdeg * PI / 180)) + r).toFloat()
        y = ((sr * sin(sdeg * PI / 180)) + r).toFloat()
        canvas?.drawLine(r, r, x, y, sPaint)
        canvas?.drawCircle(x, y, 3F, sPaint)
        canvas?.drawCircle(r, r, 8F, sPaint)

        for(deg in 0..359 step 6) {  //画刻度线
            val x1: Float = (0.9 * r * cos(deg.toDouble() * PI / 180)).toFloat() + r
            val y1: Float = (0.9 * r * sin(deg.toDouble() * PI / 180)).toFloat() + r
            val x2: Float = (r * cos(deg.toDouble() * PI / 180)).toFloat() + r
            val y2: Float = (r * sin(deg.toDouble() * PI / 180)).toFloat() + r
            if(deg % 30 == 0) canvas?.drawLine(x1, y1, x2, y2, wPaintBold)
            else canvas?.drawLine(x1, y1, x2, y2, wPaint)
        }

        x = ((0.8 * r * cos( 5.5 * PI / 180)) + r).toFloat()
        y = ((0.8 * r * sin( 5.5 * PI / 180)) + r).toFloat()
        canvas?.drawText("3", x, y, wPaintBold)
        x = (0.82 * r * cos( 34 * PI / 180)).toFloat() + r
        y = (0.82 * r * sin( 34 * PI / 180)).toFloat() + r
        canvas?.drawText("4", x, y, wPaintBold)
        x = (0.84 * r * cos( 62 * PI / 180)).toFloat() + r
        y = (0.84 * r * sin( 62 * PI / 180)).toFloat() + r
        canvas?.drawText("5", x, y, wPaintBold)
        x = (0.85 * r * cos( 90 * PI / 180)).toFloat() + r
        y = (0.85 * r * sin( 90 * PI / 180)).toFloat() + r
        canvas?.drawText("6", x, y, wPaintBold)
        x = (0.84 * r * cos( 118 * PI / 180)).toFloat() + r
        y = (0.84 * r * sin( 118 * PI / 180)).toFloat() + r
        canvas?.drawText("7", x, y, wPaintBold)
        x = (0.82 * r * cos( 145 * PI / 180)).toFloat() + r
        y = (0.82 * r * sin( 145 * PI / 180)).toFloat() + r
        canvas?.drawText("8", x, y, wPaintBold)
        x = (0.8 * r * cos( 174 * PI / 180)).toFloat() + r
        y = (0.8 * r * sin( 174 * PI / 180)).toFloat() + r
        canvas?.drawText("9", x, y, wPaintBold)
        x = (0.71 * r * cos( 205 * PI / 180)).toFloat() + r
        y = (0.71 * r * sin( 205 * PI / 180)).toFloat() + r
        canvas?.drawText("10", x, y, wPaintBold)
        x = (0.69 * r * cos( 239 * PI / 180)).toFloat() + r
        y = (0.69 * r * sin( 239 * PI / 180)).toFloat() + r
        canvas?.drawText("11", x, y, wPaintBold)
        x = (0.69 * r * cos( 270 * PI / 180)).toFloat() + r
        y = (0.69 * r * sin( 270 * PI / 180)).toFloat() + r
        canvas?.drawText("12", x, y, wPaintBold)
        x = (0.72 * r * cos( 304 * PI / 180)).toFloat() + r
        y = (0.72 * r * sin( 304 * PI / 180)).toFloat() + r
        canvas?.drawText("1", x, y, wPaintBold)
        x = (0.76 * r * cos( 335 * PI / 180)).toFloat() + r
        y = (0.76 * r * sin( 335 * PI / 180)).toFloat() + r
        canvas?.drawText("2", x, y, wPaintBold)

//        canvas?.drawCircle(r, r, (0.9 * r).toFloat(), mPaint)
//        canvas?.drawCircle(r, r, (0.86 * r).toFloat(), mPaint)
//        canvas?.drawCircle(r, r, (0.7 * r).toFloat(), mPaint)

//        for(hour in 3..14) {
//            val x1: Float = (450 * cos(i.toDouble() * PI / 180)).toFloat() + w / 2
//            val y1: Float = (450 * sin(i.toDouble() * PI / 180)).toFloat() + h / 2
//            val x2: Float = (500 * cos(i.toDouble() * PI / 180)).toFloat() + w / 2
//            val y2: Float = (500 * sin(i.toDouble() * PI / 180)).toFloat() + h / 2
//            if(t % 30 == 0) {
//                canvas?.drawLine(x1, y1, x2, y2, wPaintBold)
//                val path: Path = Path()
//                path.moveTo(x1, y1)
//                path.lineTo(x1 + 20F, y1)
//                canvas?.drawTextOnPath("$hour", path, 0F, 0F, wPaintBold)
//        }

//        canvas?.drawLines(pts.toFloatArray(), wPaint)
//        canvas?.drawPoint(500F,500F, mPaint)
//        canvas?.drawLine(500F,500F, 933F, 250F, mPaint)
//        canvas?.drawOval(200F, 200F, 100F, 100F,  mPaint)
//        canvas?.drawText("texttext", 100F, 100F, mPaint)
//        canvas?.drawRect(0F,0F,1000F,1000F,mPaint)
//        canvas?.drawCircle(500F, 500F, 10F, mPaint)
//        val oval: RectF = RectF(0F, 0F, width.toFloat(), height.toFloat())
//        canvas?.drawArc(RectF(0F, 0F, width.toFloat(), height.toFloat()), 0F, 360F, true, wPaint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        super.onTouchEvent(event)
        this.hx = event?.getX()!!
        this.hy = event.getY()
        this.invalidate()
        return true
    }
}
