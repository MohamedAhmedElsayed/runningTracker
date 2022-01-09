package com.thirdwayv.tracker.features.home.data.repository

import android.location.Location
import com.thirdwayv.tracker.features.home.data.local.trips.TripsLocalDataSource
import com.thirdwayv.tracker.features.home.domain.repository.TrackingTripsRepo
import javax.inject.Inject

class TrackingTripsRepoImp @Inject constructor(private val tripsLocalDataSource: TripsLocalDataSource) :
    TrackingTripsRepo {
    override fun saveTripData(steps: Int, duration: Int) {
        tripsLocalDataSource.saveTripData(steps, duration)
    }

    override fun addStepLocation(location: Location) =
        tripsLocalDataSource.addStepLocation(location)


    override fun createNewTrip() {
        tripsLocalDataSource.createNewTrip()
    }
}