package com.thirdwayv.tracker.features.home.domain.distanceHandler

import com.thirdwayv.tracker.MainCoroutineRule
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DistanceHandlerTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()
    private lateinit var distanceHandler: DistanceHandler

    @Before
    fun setup() {
        distanceHandler = DistanceHandler()
    }

    @Test
    fun checkEmittedValue() {
        val input = 12f
        distanceHandler.updateDistance(input)
        runBlockingTest {
            assertEquals(distanceHandler.getDistanceFlow().first(), input)
        }
    }
}