package com.thirdwayv.tracker.features.history.data.repository

import com.thirdwayv.tracker.features.history.data.local.HistoryLocalDataSource
import com.thirdwayv.tracker.features.home.data.entity.TrackingTripModel
import io.realm.RealmList
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class HistoryRepoImpTest {
    @Mock
    lateinit var historyLocalDataSource: HistoryLocalDataSource
    lateinit var historyRepoImp: HistoryRepoImp

    @Before
    fun setup() {
        historyRepoImp = HistoryRepoImp(historyLocalDataSource)
    }

    @Test
    fun returnEmptyListFromDB() {
        testDBResult(RealmList())
    }

    @Test
    fun returnNotEmptyListFromDB() {
        testDBResult(RealmList(TrackingTripModel()))
    }

    private fun testDBResult(dbResult: RealmList<TrackingTripModel>) {

        whenever(historyLocalDataSource.getFinishedTrips()).thenReturn(dbResult)
        val expectedResult = historyRepoImp.getFinishedTrips()
        assertEquals(expectedResult, dbResult)
        verify(historyLocalDataSource, times(1)).getFinishedTrips()

    }


}