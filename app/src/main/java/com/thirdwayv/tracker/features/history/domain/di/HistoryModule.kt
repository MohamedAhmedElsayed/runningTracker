package com.thirdwayv.tracker.features.history.domain.di

import com.thirdwayv.tracker.features.history.data.repository.HistoryRepoImp
import com.thirdwayv.tracker.features.history.domain.repository.HistoryRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HistoryModule {

    @Binds
    abstract fun bindsHistoryRepo(historyRepoImp: HistoryRepoImp): HistoryRepo

}