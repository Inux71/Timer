package com.example.timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    lateinit var clockView: ClockView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clockView = findViewById(R.id.clock_view)

        if (savedInstanceState != null) {
            clockView.dozensMinutes.CurrentValue = savedInstanceState.getInt("dozens_minutes")
            clockView.unitsMinutes.CurrentValue = savedInstanceState.getInt("units_minutes")
            clockView.dozensSeconds.CurrentValue = savedInstanceState.getInt("dozens_seconds")
            clockView.unitsSeconds.CurrentValue = savedInstanceState.getInt("units_seconds")
            clockView.timer?.IsRunning = savedInstanceState.getBoolean("isRunning")

            clockView.dozensMinutes.update()
            clockView.unitsMinutes.update()
            clockView.dozensSeconds.update()
            clockView.unitsSeconds.update()

            if (clockView.timer?.IsRunning == true) {
                clockView.timer?.start()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("dozens_minutes", clockView.dozensMinutes.CurrentValue)
        outState.putInt("units_minutes", clockView.unitsMinutes.CurrentValue)
        outState.putInt("dozens_seconds", clockView.dozensSeconds.CurrentValue)
        outState.putInt("units_seconds", clockView.unitsSeconds.CurrentValue)
        outState.putBoolean("isRunning", clockView.timer?.IsRunning == true)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        clockView.dozensMinutes.CurrentValue = savedInstanceState.getInt("dozens_minutes")
        clockView.unitsMinutes.CurrentValue = savedInstanceState.getInt("units_minutes")
        clockView.dozensSeconds.CurrentValue = savedInstanceState.getInt("dozens_seconds")
        clockView.unitsSeconds.CurrentValue = savedInstanceState.getInt("units_seconds")
        clockView.timer?.IsRunning = savedInstanceState.getBoolean("isRunning")

        clockView.dozensMinutes.update()
        clockView.unitsMinutes.update()
        clockView.dozensSeconds.update()
        clockView.unitsSeconds.update()

        if (clockView.timer?.IsRunning == true) {
            clockView.timer?.start()
        }
    }
}
