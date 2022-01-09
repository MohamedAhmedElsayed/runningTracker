package com.thirdwayv.tracker.features.history.presentation.viewmodel

import com.thirdwayv.tracker.core.base.view.viewmodel.Result
import com.thirdwayv.tracker.features.home.data.entity.TrackingTripModel

sealed class HistoryResult : Result {
    data class LoadedHistory(val tripsList: List<TrackingTripModel>) : HistoryResult()
}