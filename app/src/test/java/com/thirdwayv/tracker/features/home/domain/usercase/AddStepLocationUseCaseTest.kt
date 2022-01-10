package com.thirdwayv.tracker.features.home.domain.usercase

import android.location.Location
import com.thirdwayv.tracker.features.home.data.repository.TrackingTripsRepoImp
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)

class AddStepLocationUseCaseTest {
    lateinit var addStepLocationUseCase: AddStepLocationUseCase

    @Mock
    lateinit var tripsRepoImp: TrackingTripsRepoImp

    @Before
    fun setUp() {
        addStepLocationUseCase = AddStepLocationUseCase(tripsRepoImp)
    }

    @Test
    fun `input null expected 0 distance`() {
        val input = null
        assertEquals(0.0f, addStepLocationUseCase.execute(input))
        verifyNoInteractions(tripsRepoImp)
    }

    @Test
    fun `input location expected distance`() {
        val input = Location("")
        val output = 3f
        whenever(tripsRepoImp.addStepLocation(input)).thenReturn(output)
        assertEquals(output, addStepLocationUseCase.execute(input))
        verify(tripsRepoImp, times(1)).addStepLocation(input)
    }
}