package com.mewz.thelibrary.views.components

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import com.mewz.thelibrary.R

class BannerCustomStraightProgressBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object{
        private const val DEFAULT_PROGRESS = 0
        private const val DEFAULT_PROGRESS_MAX = 0
    }

    private var availableColor = context.let { ContextCompat.getColor(it, R.color.white) } ?: 0
    private var unavailableColor = context.let { ContextCompat.getColor(it,R.color.black) } ?: 0
    private var progress = DEFAULT_PROGRESS
    private var progressMax = DEFAULT_PROGRESS_MAX

    private val paintForIndicatorColor = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = unavailableColor
        style = Paint.Style.FILL
        strokeWidth = 10f
    }

    private val paintForTrackColor = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = availableColor
        style = Paint.Style.FILL
        strokeWidth = 10f
    }

    init {
        setUpAttributes(attrs)
    }

    private fun setUpAttributes(attrs: AttributeSet?) {
        context.withStyledAttributes(attrs, R.styleable.CustomProgressBar){
            availableColor = getColor(R.styleable.CustomProgressBar_customTrackColor, availableColor)
            unavailableColor = getColor(R.styleable.CustomProgressBar_customIndicatorColor, unavailableColor)
            progress = getInteger(R.styleable.CustomProgressBar_customProgress, DEFAULT_PROGRESS)
            progressMax = getInteger(R.styleable.CustomProgressBar_customProgressMax, DEFAULT_PROGRESS_MAX)
        }
    }

    override fun onDraw(canvas: Canvas?) {

        canvas?.drawLine(0f,0f,width.toFloat()*progressMax,0f,paintForIndicatorColor)
        canvas?.drawLine(0f,0f, progress.toFloat(),0f,paintForTrackColor)

        super.onDraw(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val size = measuredWidth.coerceAtMost(measuredHeight)
        setMeasuredDimension(size,size)
    }
}