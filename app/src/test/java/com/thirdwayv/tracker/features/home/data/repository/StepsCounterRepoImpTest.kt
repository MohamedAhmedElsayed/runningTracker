package com.thirdwayv.tracker.features.home.data.repository

import com.thirdwayv.tracker.features.home.data.local.steps.StepsCounterLocalDataSource
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class StepsCounterRepoImpTest {
    lateinit var stepsCounterRepoImp: StepsCounterRepoImp

    @Mock
    lateinit var stepsCounterLocalDataSource: StepsCounterLocalDataSource

    @Before
    fun setUp() {
        stepsCounterRepoImp = StepsCounterRepoImp(stepsCounterLocalDataSource)
    }

    @Test
    fun getPreviousSteps() {
        val result = 12f
        Mockito.`when`(stepsCounterLocalDataSource.getPreviousTotalSteps())
            .thenReturn(result)
        assertEquals(result, stepsCounterRepoImp.getPreviousTotalSteps())
        Mockito.verify(stepsCounterLocalDataSource, Mockito.times(1)).getPreviousTotalSteps()
     }

    @Test
    fun savePreviousSteps() {
        val input = 12f
        stepsCounterRepoImp.savePreviousTotalSteps(input)
         Mockito.verify(stepsCounterLocalDataSource, Mockito.times(1)).savePreviousTotalSteps(input)
    }

}