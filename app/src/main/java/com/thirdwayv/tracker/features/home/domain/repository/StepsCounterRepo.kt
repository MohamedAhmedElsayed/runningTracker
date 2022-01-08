package com.thirdwayv.tracker.features.home.domain.repository

interface StepsCounterRepo {
    fun getPreviousTotalSteps(): Float
    fun savePreviousTotalSteps(steps: Float)
}