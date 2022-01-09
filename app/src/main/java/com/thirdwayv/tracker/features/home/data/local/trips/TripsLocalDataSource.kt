package com.thirdwayv.tracker.features.home.data.local.trips

import android.location.Location

interface TripsLocalDataSource {
    fun saveTripData(steps: Int, duration: Int)
    fun addStepLocation(location: Location): Float
    fun createNewTrip()

}