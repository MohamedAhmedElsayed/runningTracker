package com.thirdwayv.tracker.features.home.presentation.viewmodel

import com.thirdwayv.tracker.core.base.view.viewmodel.ViewEvent

sealed class HomeViewEvent : ViewEvent {
    object StartTracking : HomeViewEvent()
    object PauseTracking : HomeViewEvent()
    object ResumeTracking : HomeViewEvent()
    object StopTracking : HomeViewEvent()
}