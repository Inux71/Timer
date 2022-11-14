package com.example.timer

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.util.SparseArray
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout

class ClockView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    var timer: Timer? = null

    var dozensMinutes: DigitView
    var unitsMinutes: DigitView
    var dozensSeconds: DigitView
    var unitsSeconds: DigitView

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.clock_layout, this, true)

        dozensMinutes = view.findViewById<DigitView>(R.id.dozensMinutes)
        unitsMinutes = view.findViewById<DigitView>(R.id.unitsMinutes)
        dozensSeconds = view.findViewById<DigitView>(R.id.dozensSeconds)
        unitsSeconds = view.findViewById<DigitView>(R.id.unitsSeconds)

        val startButton = view.findViewById<Button>(R.id.start_button)
        startButton.setOnClickListener {
            if (timer?.IsRunning == false || timer == null) {
                timer = Timer(dozensMinutes.CurrentValue, unitsMinutes.CurrentValue, dozensSeconds.CurrentValue, unitsSeconds.CurrentValue, {updateView(it)}, {resetActivity()})
                timer?.start()

                dozensMinutes.IsActive = true
                unitsMinutes.IsActive = true
                dozensSeconds.IsActive = true
                unitsSeconds.IsActive = true
            }
        }

        val pauseButton = view.findViewById<Button>(R.id.pause_button)
        pauseButton.setOnClickListener {
            timer?.pause()
        }

        val stopButton = view.findViewById<Button>(R.id.stop_button)
        stopButton.setOnClickListener {
            timer?.stop()
            timer?.remainingTime = 0

            dozensMinutes.reset()
            unitsMinutes.reset()
            dozensSeconds.reset()
            unitsSeconds.reset()
        }
    }

    fun updateView(time: Long): Unit {
        val minutes: Int = time.toInt() / 60
        val seconds: Int = time.toInt() % 60

        dozensMinutes.CurrentValue = minutes / 10
        dozensMinutes.update()

        unitsMinutes.CurrentValue = minutes % 10
        unitsMinutes.update()

        dozensSeconds.CurrentValue = seconds / 10
        dozensSeconds.update()

        unitsSeconds.CurrentValue = seconds % 10
        unitsSeconds.update()
    }

    fun resetActivity() {
        dozensMinutes.IsActive = false
        unitsMinutes.IsActive = false
        dozensSeconds.IsActive = false
        unitsSeconds.IsActive = false
    }
}