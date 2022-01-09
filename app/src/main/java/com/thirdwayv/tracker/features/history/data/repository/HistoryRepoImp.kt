package com.thirdwayv.tracker.features.history.data.repository

import com.thirdwayv.tracker.features.history.data.local.HistoryLocalDataSource
import com.thirdwayv.tracker.features.history.domain.repository.HistoryRepo
import javax.inject.Inject

class HistoryRepoImp @Inject constructor(private val historyLocalDataSource: HistoryLocalDataSource) : HistoryRepo {
    override fun getFinishedTrips() = historyLocalDataSource.getFinishedTrips()
}