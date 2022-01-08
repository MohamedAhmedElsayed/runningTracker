package com.thirdwayv.tracker.features.home.presentation.data.local

interface StepsCounterLocalDataSource {
    fun getPreviousTotalSteps(): Float
    fun savePreviousTotalSteps(steps: Float)
 }