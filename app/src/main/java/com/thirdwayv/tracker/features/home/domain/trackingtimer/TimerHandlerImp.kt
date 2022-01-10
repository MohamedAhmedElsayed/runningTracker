package com.thirdwayv.tracker.features.home.domain.trackingtimer

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * class to count tracking duration
 * */
@Singleton
class TimerHandlerImp @Inject constructor() : TimerHandler {
    private val _timerSharedFlow = MutableStateFlow(0)
    private var currentSecond = 0
    private var timerJob: Job? = null

    override fun getTimerFlow() = _timerSharedFlow

    /**
     * create new coroutine job and start counting
     * */
    override fun startTimer(
        timeInterval: Long,
        coroutineScope: CoroutineScope
    ) {
        timerJob = coroutineScope.launch {
            while (isActive) {
                _timerSharedFlow.emit(currentSecond++)
                delay(timeInterval)
            }
        }
    }

    override fun cancelTimer() {
        timerJob?.cancel()
    }

    override fun resetTimer() {
        cancelTimer()
        currentSecond = 0
    }
}