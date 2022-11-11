package com.example.timer

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView


@SuppressLint("Recycle")
class DigitView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private var min = 0
    private var max = 0
    private var currentValue = 0

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.digit_layout, this, true)

        val a = context.obtainStyledAttributes(attrs, R.styleable.DigitView)
        val n = a.indexCount
        for (i in 0 until n) {
            when (val attr = a.getIndex(i)) {
                R.styleable.DigitView_min -> {
                    min = a.getInt(attr, 0)
                }
                R.styleable.DigitView_max -> {
                    max = a.getInt(attr, 9)
                }
            }
        }
        a.recycle()

        val digitBox = view.findViewById<TextView>(R.id.digit_box)
        currentValue = min
        digitBox.text = currentValue.toString()

        val plusButton = view.findViewById<Button>(R.id.plus_button)
        plusButton.setOnClickListener {
            currentValue = if (++currentValue <= max) currentValue else min

            digitBox.text = currentValue.toString()
        }

        val minusButton = view.findViewById<Button>(R.id.minus_button)
        minusButton.setOnClickListener {
            currentValue = if (--currentValue >= min) currentValue else max

            digitBox.text = currentValue.toString()
        }
    }
}