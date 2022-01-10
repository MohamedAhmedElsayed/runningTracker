package com.thirdwayv.tracker.features.history.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.thirdwayv.tracker.MainCoroutineRule
import com.thirdwayv.tracker.features.history.domain.usecase.GetHistoryUseCase
import com.thirdwayv.tracker.features.home.data.entity.TrackingTripModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@InternalCoroutinesApi
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HistoryViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var getHistoryUseCase: GetHistoryUseCase
    lateinit var historyViewModel: HistoryViewModel

    @Before
    fun setUp() {
        historyViewModel = HistoryViewModel(getHistoryUseCase)

    }

    @Test
    fun `action LoadTripsHistory expected HistoryViewState with loaded trips`() {
        val action = HistoryAction.LoadTripsHistory
        val historyState =
            HistoryViewState(tripsList = listOf(TrackingTripModel()))
        runBlocking {
            Mockito.`when`(getHistoryUseCase.execute())
                .thenReturn(HistoryResult.LoadedHistory(historyState.tripsList))
            historyViewModel.dispatch(action)
            Mockito.verify(getHistoryUseCase, Mockito.times(1)).execute()
            Mockito.verifyNoMoreInteractions(getHistoryUseCase)
            assertEquals(historyViewModel.uiState.value, historyState)
        }
    }

    @Test
    fun `action LoadTripsHistory expected LoadedHistory result with loaded trips`() {
        val action = HistoryAction.LoadTripsHistory
        val loadedHistory =
            HistoryResult.LoadedHistory(tripsList = listOf(TrackingTripModel()))
        runBlocking {
            Mockito.`when`(getHistoryUseCase.execute())
                .thenReturn(loadedHistory)
            var result: HistoryResult? = null
            historyViewModel.handle(action).collect {
                result = it
            }
            Mockito.verify(getHistoryUseCase, Mockito.times(1)).execute()
            Mockito.verifyNoMoreInteractions(getHistoryUseCase)
            assertEquals(loadedHistory, result)
        }
    }

    @Test
    fun `input LoadedHistory result expected HistoryViewState with result trips`() {
        val input = HistoryResult.LoadedHistory(listOf(TrackingTripModel()))
        val expectedResult = HistoryViewState(input.tripsList)
        val actualResult = historyViewModel.reduce(input)
        assertEquals(actualResult, expectedResult)
        Mockito.verifyNoInteractions(getHistoryUseCase)

    }

}