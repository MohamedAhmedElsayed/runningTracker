package com.thirdwayv.tracker.features.home.data.repository

import android.location.Location
import com.thirdwayv.tracker.features.home.data.local.trips.TripsLocalDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TrackingTripsRepoImpTest {
    lateinit var trackingTripsRepoImp: TrackingTripsRepoImp

    @Mock
    lateinit var tripsLocalDataSource: TripsLocalDataSource

    @Before
    fun setUp() {
        trackingTripsRepoImp = TrackingTripsRepoImp(tripsLocalDataSource)
    }

    @Test
    fun saveTripData() {
        val steps = 12
        val duration = 12
        tripsLocalDataSource.saveTripData(steps, duration)
        Mockito.verify(tripsLocalDataSource, Mockito.times(1)).saveTripData(steps, duration)

    }

    @Test
    fun addStepLocation() {
        val distance = 3f
        val location = Location("")
        whenever(tripsLocalDataSource.addStepLocation(location)).thenReturn(distance)
        Assert.assertEquals(trackingTripsRepoImp.addStepLocation(location), distance)
        Mockito.verify(tripsLocalDataSource, Mockito.times(1)).addStepLocation(location)

    }

    @Test
    fun createNewTrip() {
        tripsLocalDataSource.createNewTrip()
        Mockito.verify(tripsLocalDataSource, Mockito.times(1)).createNewTrip()

    }


}