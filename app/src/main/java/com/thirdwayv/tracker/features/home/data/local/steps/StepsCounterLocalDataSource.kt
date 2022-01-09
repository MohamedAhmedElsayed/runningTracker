package com.thirdwayv.tracker.features.home.data.local.steps

interface StepsCounterLocalDataSource {
    fun getPreviousTotalSteps(): Float
    fun savePreviousTotalSteps(steps: Float)
 }