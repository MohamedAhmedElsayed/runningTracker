package com.thirdwayv.tracker.features.home.presentation.viewmodel

import com.thirdwayv.tracker.core.base.view.viewmodel.ViewState

data class HomeViewState(
    val trackingState: HomeTrackingState = HomeTrackingState.Initial,
    val numberOfSeconds: Int = 0,
    val totalSteps: Float = 0f,
    val distance: Float = 0f
) : ViewState

sealed class HomeTrackingState {
    object Initial : HomeTrackingState()
    object Started : HomeTrackingState()
    object Paused : HomeTrackingState()
    object Resumed : HomeTrackingState()
}
