package com.thirdwayv.tracker.features.home.domain.usercase

import com.thirdwayv.tracker.features.home.domain.repository.StepsCounterRepo
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetPreviousStepsCounterUseCaseTest {
    @Mock
    lateinit var stepsCounterRepo: StepsCounterRepo
    lateinit var getPreviousStepsCounterUseCaseTest: GetPreviousStepsCounterUseCase

    @Before
    fun setUp() {
        getPreviousStepsCounterUseCaseTest = GetPreviousStepsCounterUseCase(stepsCounterRepo)
    }

    @Test
    fun createNewTrip() {
        val output = 1f
        whenever(stepsCounterRepo.getPreviousTotalSteps()).thenReturn(output)
        assertEquals(getPreviousStepsCounterUseCaseTest.execute(), output)
    }
}