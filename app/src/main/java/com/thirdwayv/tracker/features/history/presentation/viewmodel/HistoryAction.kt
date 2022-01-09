package com.thirdwayv.tracker.features.history.presentation.viewmodel

import com.thirdwayv.tracker.core.base.view.viewmodel.Action

sealed class HistoryAction : Action {
    object LoadTripsHistory : HistoryAction()
}