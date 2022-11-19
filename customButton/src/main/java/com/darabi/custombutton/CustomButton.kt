package com.darabi.custombutton

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton

class CustomButton : AppCompatButton {

    private var drawable: Drawable? = null
    private var subtext: String? = null

    constructor(context: Context): super(context)

    constructor(context: Context, attrs: AttributeSet): super(context, attrs) {
        setupAttributes(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr) {
        setupAttributes(attrs)
    }

    private fun setupAttributes(attrs: AttributeSet) {
        subtext = ""
    }
}