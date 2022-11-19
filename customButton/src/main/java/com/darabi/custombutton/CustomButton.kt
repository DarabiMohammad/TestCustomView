package com.darabi.custombutton

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.widget.ImageViewCompat
import androidx.core.widget.TextViewCompat

class CustomButton : View {

    private val iconPositionStart = 0x00000000
    private val iconPositionTop = 0x00000001
    private val iconPositionEnd = 0x00001001
    private val iconPositionBottom = 0x00002001

    private var drawable: Drawable? = null
    private var iconPosition: Int = -1
    private var text: String? = null
    private var subtext: String? = null

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        setupAttributes(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {

        setupAttributes(context, attrs)
    }

    private fun setupAttributes(context: Context, attrs: AttributeSet) {

        val typedArray = context.theme.obtainStyledAttributes(attrs, R.styleable.CustomButton, 0, 0)

        drawable = typedArray.getDrawable(R.styleable.CustomButton_iconSrc)
        iconPosition = typedArray.getInt(R.styleable.CustomButton_iconPosition, iconPositionStart)
        text = typedArray.getString(R.styleable.CustomButton_text)
        subtext = typedArray.getString(R.styleable.CustomButton_subtext)

        if (typedArray.getBoolean(R.styleable.CustomButton_rippleEnabled, false)) {

            val ripple = context.theme.obtainStyledAttributes(intArrayOf(android.R.attr.selectableItemBackground)).getResourceId(0, 0)
            setBackgroundResource(ripple)
        }
    }
}