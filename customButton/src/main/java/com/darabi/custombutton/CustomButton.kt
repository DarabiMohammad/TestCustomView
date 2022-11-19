package com.darabi.custombutton

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View


class CustomButton : View {

    private val iconPositionStart = 0x00000000
    private val iconPositionTop = 0x00000001
    private val iconPositionEnd = 0x00001001
    private val iconPositionBottom = 0x00002001

    private var drawable: Drawable? = null
    private var iconPosition: Int = -1
    private var textSize: Float = -1f
    private var text: String? = null
    private var subtext: String? = null

    private var textPaint: TextPaint? = null
    private var subTextPaint: TextPaint? = null
    private var staticLayout: StaticLayout? = null

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {

        setupAttributes(context, attrs)

        textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
        setupTextPaint(textPaint)
        setupGenerics()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {

        setupAttributes(context, attrs)

        textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
        setupTextPaint(textPaint)
        setupGenerics()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

//        canvas?.drawText(text!!, 10f, 25f, textPaint!!)

        canvas?.save()
        canvas?.translate(paddingLeft.toFloat(), paddingTop.toFloat())
        staticLayout?.draw(canvas)
        canvas?.restore()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        var width: Int
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthRequirement = MeasureSpec.getSize(widthMeasureSpec)

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthRequirement
        } else {
            width = staticLayout?.width!! + paddingLeft + paddingRight
            if (widthMode == MeasureSpec.AT_MOST) {
                if (width > widthRequirement) {
                    width = widthRequirement
                    staticLayout = StaticLayout(
                        text, textPaint, width, Layout.Alignment.ALIGN_NORMAL,
                        1.0f, 0f, false
                    )
                }
            }
        }

        var height: Int
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightRequirement = MeasureSpec.getSize(heightMeasureSpec)
        
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightRequirement
        } else {
            height = staticLayout?.height!! + paddingTop + paddingBottom
            if (heightMode == MeasureSpec.AT_MOST) {
                height = height.coerceAtMost(heightRequirement)
            }
        }

        setMeasuredDimension(width, height)
    }

    private fun setupAttributes(context: Context, attrs: AttributeSet) {

        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.CustomButton, 0, 0)

        drawable = typedArray.getDrawable(R.styleable.CustomButton_iconSrc)
        iconPosition = typedArray.getInt(R.styleable.CustomButton_iconPosition, iconPositionStart)
        text = typedArray.getString(R.styleable.CustomButton_text)
        subtext = typedArray.getString(R.styleable.CustomButton_subtext)
        textSize = typedArray.getDimension(R.styleable.CustomButton_textSize, resources.displayMetrics.density * 16)

        if (typedArray.getBoolean(R.styleable.CustomButton_rippleEnabled, false)) {

            val ripple = context.theme.obtainStyledAttributes(intArrayOf(android.R.attr.selectableItemBackground)).getResourceId(0, 0)
            setBackgroundResource(ripple)
        }
    }

    private fun setupTextPaint(paint: TextPaint?) = paint?.apply {

        isAntiAlias = true
        density = resources.displayMetrics.density
        textSize = this@CustomButton.textSize
    }

    private fun setupGenerics() {

        staticLayout = StaticLayout(
            text, textPaint, textPaint!!.measureText(text).toInt(), Layout.Alignment.ALIGN_NORMAL,
            1.0f, 0f, false
        )
    }
}