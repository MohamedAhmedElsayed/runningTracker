package com.thirdwayv.tracker.features.home.presentation.viewmodel

import com.thirdwayv.tracker.core.base.view.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() :
    BaseViewModel<HomeViewState, HomeViewEvent, HomeViewAction, HomeResult>(
        HomeViewState.InitState
    ) {
    override fun handle(action: HomeViewAction): Flow<HomeResult> {
        TODO("Not yet implemented")
    }

    override fun reduce(result: HomeResult): HomeViewState {
        TODO("Not yet implemented")
    }

}