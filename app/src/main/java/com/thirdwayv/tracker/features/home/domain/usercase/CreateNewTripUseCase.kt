package com.thirdwayv.tracker.features.home.domain.usercase

import com.thirdwayv.tracker.core.base.domain.usecase.UseCase
import com.thirdwayv.tracker.features.home.domain.repository.TrackingTripsRepo
import javax.inject.Inject

class CreateNewTripUseCase @Inject constructor(private val tripsRepo: TrackingTripsRepo) :
    UseCase<Unit, Unit> {
    override fun execute(input: Unit?) {
        tripsRepo.createNewTrip()
    }
}