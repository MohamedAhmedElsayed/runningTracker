package com.thirdwayv.tracker.features.history.domain.usecase

import com.thirdwayv.tracker.core.base.domain.usecase.UseCase
import com.thirdwayv.tracker.features.history.domain.repository.HistoryRepo
import com.thirdwayv.tracker.features.history.presentation.viewmodel.HistoryResult
import javax.inject.Inject

class GetHistoryUseCase @Inject constructor(private val historyRepo: HistoryRepo) :
    UseCase<Unit, HistoryResult> {
    override fun execute(input: Unit?) = HistoryResult.LoadedHistory(historyRepo.getFinishedTrips().reversed())
}