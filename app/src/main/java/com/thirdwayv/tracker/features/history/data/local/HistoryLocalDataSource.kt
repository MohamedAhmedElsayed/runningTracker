package com.thirdwayv.tracker.features.history.data.local

import com.thirdwayv.tracker.features.home.data.entity.TrackingTripModel

interface HistoryLocalDataSource {
    fun getFinishedTrips():List<TrackingTripModel>
}