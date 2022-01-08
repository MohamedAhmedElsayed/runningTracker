package com.thirdwayv.tracker.features.home.presentation.domain.repository

interface StepsCounterRepo {
    fun getPreviousTotalSteps(): Float
    fun savePreviousTotalSteps(steps: Float)
}