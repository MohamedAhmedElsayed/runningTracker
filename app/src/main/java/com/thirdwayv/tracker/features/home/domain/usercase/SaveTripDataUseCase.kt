package com.thirdwayv.tracker.features.home.domain.usercase

import com.thirdwayv.tracker.core.base.domain.usecase.UseCase
import com.thirdwayv.tracker.features.home.domain.repository.TrackingTripsRepo
import com.thirdwayv.tracker.features.home.presentation.viewmodel.HomeViewState
import javax.inject.Inject

class SaveTripDataUseCase @Inject constructor(private val tripsRepo: TrackingTripsRepo) :
    UseCase<HomeViewState, Unit> {
    override fun execute(input: HomeViewState?) {
        input?.let {
            tripsRepo.saveTripData(it.totalSteps.toInt(), it.numberOfSeconds)
        }
    }
}