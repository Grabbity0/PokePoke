package com.example.pokemondictionary.customwiget

import android.content.Context
import android.util.AttributeSet
import android.widget.ProgressBar

class CustomProgressbar : ProgressBar {

    constructor(context: Context?) : super(context) {
        init();
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init();
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init();
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init();
    }

    private fun init() {

    }
}