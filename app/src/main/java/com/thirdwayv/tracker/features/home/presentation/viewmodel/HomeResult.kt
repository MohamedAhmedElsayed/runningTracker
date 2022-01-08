package com.thirdwayv.tracker.features.home.presentation.viewmodel

import com.thirdwayv.tracker.core.base.view.viewmodel.Result

sealed class HomeResult : Result {
    data class UpdatedViewStateResult(val state: HomeViewState) : HomeResult()
}