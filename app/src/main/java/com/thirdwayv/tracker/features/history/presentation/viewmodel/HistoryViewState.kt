package com.thirdwayv.tracker.features.history.presentation.viewmodel

 import com.thirdwayv.tracker.core.base.view.viewmodel.ViewState
 import com.thirdwayv.tracker.features.home.data.entity.TrackingTripModel

data class HistoryViewState constructor(val tripsList:List<TrackingTripModel> = arrayListOf()) : ViewState