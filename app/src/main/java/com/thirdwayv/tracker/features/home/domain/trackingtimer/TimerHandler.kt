package com.thirdwayv.tracker.features.home.domain.trackingtimer

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow

interface TimerHandler {
    fun getTimerFlow(): MutableStateFlow<Int>
    fun startTimer(
        timeInterval: Long = 1000,
        coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default)
    )

    fun cancelTimer()
    fun resetTimer()
}