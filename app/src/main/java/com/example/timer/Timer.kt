package com.example.timer

import android.os.CountDownTimer

class Timer(private val dozensMinutes: Int, private val unitsMinutes: Int, private val dozensSeconds: Int, private val unitsSeconds: Int, callback: (time: Long) -> Unit, callback2: () -> Unit) {

    private var timer: CountDownTimer? = null
    private var isRunning: Boolean = false
    var IsRunning
        get() = isRunning
        set(value) {
            isRunning = value
        }

    private val onPause = callback
    var remainingTime: Long = 0

    init {
        val totalTime = (dozensMinutes * 600 + unitsMinutes * 60 + dozensSeconds * 10 + unitsSeconds) * 1000

        timer = object : CountDownTimer(totalTime.toLong(), 1000) {
            override fun onTick(remaining: Long) {
                callback(remaining / 1000)
                remainingTime = remaining / 1000
            }

            override fun onFinish() {
                stop()
                callback2()
            }
        }
    }

    fun start() {
        if (!isRunning) {
            timer?.start()
            isRunning = true
        }
    }

    fun pause() {
        timer?.cancel()
        isRunning = false
        onPause(remainingTime)
    }

    fun stop() {
        timer?.cancel()
        isRunning = false
    }
}