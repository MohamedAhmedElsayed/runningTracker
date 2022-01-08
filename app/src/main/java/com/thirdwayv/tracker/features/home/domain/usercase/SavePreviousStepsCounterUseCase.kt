package com.thirdwayv.tracker.features.home.domain.usercase


import com.thirdwayv.tracker.core.base.domain.usecase.UseCase
import com.thirdwayv.tracker.features.home.domain.repository.StepsCounterRepo
import javax.inject.Inject

class SavePreviousStepsCounterUseCase @Inject constructor(private val stepsCounterRepo: StepsCounterRepo) :
    UseCase<Float, Unit> {
    override fun execute(input: Float?) {
        input?.let {
            stepsCounterRepo.savePreviousTotalSteps(it)
        }
    }
}