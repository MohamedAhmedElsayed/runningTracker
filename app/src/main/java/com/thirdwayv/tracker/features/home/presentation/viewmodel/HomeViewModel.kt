package com.thirdwayv.tracker.features.home.presentation.viewmodel

import com.thirdwayv.tracker.core.base.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() :
    BaseViewModel<HomeViewState, HomeViewEvent, HomeViewAction, HomeResult>(LastHomeState.lastViewState) {
    private var currentViewState = initialState
    override fun handle(action: HomeViewAction): Flow<HomeResult> = flow {
        when (action) {
            is HomeViewAction.UpdateSeconds -> {
                emit(HomeResult.UpdatedViewStateResult(currentViewState.copy(numberOfSeconds = action.second)))
            }
            is HomeViewAction.UpdateSteps -> {
                emit(HomeResult.UpdatedViewStateResult(currentViewState.copy(totalSteps = action.steps)))
            }
            HomeViewAction.PauseTracking -> {
                onViewEvent(HomeViewEvent.PauseTracking)
                emit(HomeResult.UpdatedViewStateResult(currentViewState.copy(trackingState = HomeTrackingState.Paused)))
            }
            HomeViewAction.ResumeTracking -> {
                onViewEvent(HomeViewEvent.ResumeTracking)
                emit(HomeResult.UpdatedViewStateResult(currentViewState.copy(trackingState = HomeTrackingState.Resumed)))
            }
            HomeViewAction.StartTracking -> {
                onViewEvent(HomeViewEvent.StartTracking)
                emit(HomeResult.UpdatedViewStateResult(HomeViewState(trackingState = HomeTrackingState.Started)))
            }
            HomeViewAction.StopTracking -> {
                //todo save data
                onViewEvent(HomeViewEvent.StopTracking)
                emit(HomeResult.UpdatedViewStateResult(HomeViewState()))

            }
        }
    }

    override fun reduce(result: HomeResult) =
        when (result) {
            is HomeResult.UpdatedViewStateResult -> {
                currentViewState = result.state
                currentViewState
            }
        }

    override fun onCleared() {
        LastHomeState.lastViewState = currentState
        super.onCleared()
    }
}

object LastHomeState {
    var lastViewState = HomeViewState()

}