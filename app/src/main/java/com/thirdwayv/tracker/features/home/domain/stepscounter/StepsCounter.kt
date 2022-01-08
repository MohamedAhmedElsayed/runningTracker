package com.thirdwayv.tracker.features.home.domain.stepscounter

import kotlinx.coroutines.flow.MutableStateFlow

interface StepsCounter {
    fun getStepsCounterObserver(): MutableStateFlow<Float>
    fun stopCounter()
    fun startStepCounter(registrationResult: (registeredSuccessfully: Boolean) -> Unit)
    fun pauseReceivingSteps()
}