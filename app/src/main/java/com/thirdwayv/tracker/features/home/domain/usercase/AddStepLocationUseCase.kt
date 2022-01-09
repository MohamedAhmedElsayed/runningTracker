package com.thirdwayv.tracker.features.home.domain.usercase

import android.location.Location
import com.thirdwayv.tracker.core.base.domain.usecase.UseCase
import com.thirdwayv.tracker.features.home.domain.repository.TrackingTripsRepo
import javax.inject.Inject

class AddStepLocationUseCase @Inject constructor(private val tripsRepo: TrackingTripsRepo) :
    UseCase<Location, Float> {
    override fun execute(input: Location?): Float {
        input ?: return 0.0f
        return tripsRepo.addStepLocation(input)
    }
}