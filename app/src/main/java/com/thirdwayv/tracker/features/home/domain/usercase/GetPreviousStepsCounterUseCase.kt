package com.thirdwayv.tracker.features.home.domain.usercase

import com.thirdwayv.tracker.core.base.domain.usecase.UseCase
import com.thirdwayv.tracker.features.home.domain.repository.StepsCounterRepo
import javax.inject.Inject

class GetPreviousStepsCounterUseCase @Inject constructor(private val stepsCounterRepo: StepsCounterRepo) :
    UseCase<Unit, Float> {
    override fun execute(input: Unit?) =
        stepsCounterRepo.getPreviousTotalSteps()

}