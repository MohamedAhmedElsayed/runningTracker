package com.thirdwayv.tracker.features.history.domain.usecase

import com.thirdwayv.tracker.features.history.domain.repository.HistoryRepo
import com.thirdwayv.tracker.features.history.presentation.viewmodel.HistoryResult
import com.thirdwayv.tracker.features.home.data.entity.TrackingTripModel
import io.realm.RealmList
import junit.framework.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class GetHistoryUseCaseTest {
    lateinit var getHistoryUseCase: GetHistoryUseCase

    @Mock
    lateinit var historyRepo: HistoryRepo

    @Before
    fun setup() {
        getHistoryUseCase = GetHistoryUseCase(historyRepo)
    }

    @Test
    fun getFinishedTrips() {
        val repoResult = RealmList(
            TrackingTripModel(id = "1"),
            TrackingTripModel(id = "2"),
            TrackingTripModel(id = "3"),
        )
        whenever(historyRepo.getFinishedTrips()).thenReturn(repoResult)
        val expectedResult = getHistoryUseCase.execute()
        Assert.assertEquals(
            expectedResult,
            HistoryResult.LoadedHistory(
                listOf(
                    repoResult[2]!!,
                    repoResult[1]!!,
                    repoResult[0]!!,
                )
            )
        )
        verify(historyRepo, times(1)).getFinishedTrips()

    }
}