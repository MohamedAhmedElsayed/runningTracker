package com.thirdwayv.tracker.features.home.presentation.viewmodel

import com.thirdwayv.tracker.core.base.view.viewmodel.BaseViewModel
import com.thirdwayv.tracker.features.home.domain.usercase.CreateNewTripUseCase
import com.thirdwayv.tracker.features.home.domain.usercase.SaveTripDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val saveTripDataUseCase: SaveTripDataUseCase,
    private val createNewTripUseCase: CreateNewTripUseCase
) :
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
                createNewTripUseCase.execute()
                onViewEvent(HomeViewEvent.StartTracking)
                emit(HomeResult.UpdatedViewStateResult(HomeViewState(trackingState = HomeTrackingState.Started)))
            }
            HomeViewAction.StopTracking -> {
                saveTripDataUseCase.execute(currentViewState)
                onViewEvent(HomeViewEvent.StopTracking)
                emit(HomeResult.UpdatedViewStateResult(HomeViewState()))

            }
            is HomeViewAction.UpdateDistance -> emit(
                HomeResult.UpdatedViewStateResult(
                    currentViewState.copy(distance = action.distance)
                )
            )

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

//save lsat state to be used when open app again from service notification
object LastHomeState {
    var lastViewState = HomeViewState()
}