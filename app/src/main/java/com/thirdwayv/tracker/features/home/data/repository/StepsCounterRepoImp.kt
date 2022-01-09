package com.thirdwayv.tracker.features.home.data.repository

import com.thirdwayv.tracker.features.home.data.local.steps.StepsCounterLocalDataSource
import com.thirdwayv.tracker.features.home.domain.repository.StepsCounterRepo
import javax.inject.Inject

class StepsCounterRepoImp @Inject constructor(private val localDataSource: StepsCounterLocalDataSource) :
    StepsCounterRepo {
    override fun getPreviousTotalSteps(): Float =
        localDataSource.getPreviousTotalSteps()


    override fun savePreviousTotalSteps(steps: Float) {
        localDataSource.savePreviousTotalSteps(steps)
    }
}