package com.thirdwayv.tracker.features.history.data.local

import com.thirdwayv.tracker.features.home.data.entity.TrackingTripModel
import io.realm.Realm
import io.realm.RealmList
import javax.inject.Inject

class HistoryLocalDataSourceImp @Inject constructor() : HistoryLocalDataSource {
    override fun getFinishedTrips(): List<TrackingTripModel> {
        Realm.getDefaultInstance().use {
            return it.copyFromRealm(
                it.where(TrackingTripModel::class.java).equalTo("isCompleted", true).findAll()
                    ?: RealmList<TrackingTripModel>()
            )
        }
    }
}