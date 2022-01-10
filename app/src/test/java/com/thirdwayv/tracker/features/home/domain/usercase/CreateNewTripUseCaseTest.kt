package com.thirdwayv.tracker.features.home.domain.usercase

import com.thirdwayv.tracker.features.home.domain.repository.TrackingTripsRepo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class CreateNewTripUseCaseTest {
    lateinit var createNewTripUseCase: CreateNewTripUseCase

    @Mock
    lateinit var tripsRepo: TrackingTripsRepo

    @Before
    fun setUp() {
        createNewTripUseCase = CreateNewTripUseCase(tripsRepo)
    }
    @Test
    fun createNewTrip(){
        createNewTripUseCase.execute()
        verify(tripsRepo, times(1)).createNewTrip()
    }
}