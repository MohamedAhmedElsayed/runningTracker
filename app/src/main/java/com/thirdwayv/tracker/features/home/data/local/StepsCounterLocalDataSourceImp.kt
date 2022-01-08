package com.thirdwayv.tracker.features.home.data.local

import com.thirdwayv.tracker.features.home.data.entity.StepsCounterModel
import io.realm.Realm
import javax.inject.Inject

class StepsCounterLocalDataSourceImp @Inject constructor() :
    StepsCounterLocalDataSource {
    override fun getPreviousTotalSteps(): Float =
        Realm.getDefaultInstance().use {
            it?.where(StepsCounterModel::class.java)?.findFirst()?.previousStepsCounter ?: 0f
        }


    override fun savePreviousTotalSteps(steps: Float) {
        Realm.getDefaultInstance().use {
            it.executeTransactionAsync { realm ->
                val counterMode = realm.where(StepsCounterModel::class.java).findFirst()
                if (counterMode == null) {
                    realm.insert(StepsCounterModel(steps))
                } else {
                    counterMode.previousStepsCounter = steps
                }

            }
        }
    }


}