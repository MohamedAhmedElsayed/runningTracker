package com.thirdwayv.tracker.features.home.presentation.viewmodel

import com.thirdwayv.tracker.core.base.view.viewmodel.ViewState

sealed class HomeViewState :ViewState{
object InitState:HomeViewState()
}
