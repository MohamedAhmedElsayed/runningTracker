package com.thirdwayv.tracker.features.home.data.local

interface StepsCounterLocalDataSource {
    fun getPreviousTotalSteps(): Float
    fun savePreviousTotalSteps(steps: Float)
 }