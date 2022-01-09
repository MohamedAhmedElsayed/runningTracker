package com.thirdwayv.tracker.features.history.domain.repository

import com.thirdwayv.tracker.features.home.data.entity.TrackingTripModel

interface HistoryRepo {
    fun getFinishedTrips(): List<TrackingTripModel>

}