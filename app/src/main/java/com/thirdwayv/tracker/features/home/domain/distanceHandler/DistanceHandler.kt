package com.thirdwayv.tracker.features.home.domain.distanceHandler

import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * class that emit saved distance for the current tracking trip
 * */
@Singleton
class DistanceHandler @Inject constructor() {
    private val distanceFlow = MutableStateFlow(0f)
    fun getDistanceFlow() = distanceFlow
    fun updateDistance(distance: Float) {
        distanceFlow.value = distance
    }
}