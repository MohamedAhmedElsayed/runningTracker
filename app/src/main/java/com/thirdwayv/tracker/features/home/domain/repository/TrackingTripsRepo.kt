package com.thirdwayv.tracker.features.home.domain.repository

import android.location.Location

interface TrackingTripsRepo {
    fun saveTripData(steps: Int, duration: Int)
    fun addStepLocation(location: Location): Float
    fun createNewTrip()
}