package com.thirdwayv.tracker.features.home.presentation.viewmodel

import com.thirdwayv.tracker.core.base.view.viewmodel.Action

sealed class HomeViewAction : Action {
    data class UpdateSeconds constructor(val second: Int) : HomeViewAction()
    data class UpdateDistance constructor(val distance: Float) : HomeViewAction()
    data class UpdateSteps constructor(val steps: Float) : HomeViewAction()
    object StartTracking : HomeViewAction()
    object PauseTracking : HomeViewAction()
    object ResumeTracking : HomeViewAction()
    object StopTracking : HomeViewAction()
}