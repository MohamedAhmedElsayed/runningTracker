package com.thirdwayv.tracker.features.history.presentation.viewmodel

import com.thirdwayv.tracker.core.base.view.viewmodel.BaseViewModel
import com.thirdwayv.tracker.features.history.domain.usecase.GetHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val getHistoryUseCase: GetHistoryUseCase) :
    BaseViewModel<HistoryViewState, HistoryViewEvent, HistoryAction, HistoryResult>(
        HistoryViewState()
    ) {
    override fun handle(action: HistoryAction): Flow<HistoryResult> = flow {
        when (action) {
            HistoryAction.LoadTripsHistory -> emit(getHistoryUseCase.execute())
        }
    }

    override fun reduce(result: HistoryResult): HistoryViewState {
        return when (result) {
            is HistoryResult.LoadedHistory -> HistoryViewState(result.tripsList)
        }
    }
}