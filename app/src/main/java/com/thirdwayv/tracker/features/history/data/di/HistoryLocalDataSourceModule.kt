package com.thirdwayv.tracker.features.history.data.di

import com.thirdwayv.tracker.features.history.data.local.HistoryLocalDataSource
import com.thirdwayv.tracker.features.history.data.local.HistoryLocalDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HistoryLocalDataSourceModule {
    @Binds
    abstract fun bindsHistoryLocalDataSourceLocalDataSource(historyLocalDataSourceImp: HistoryLocalDataSourceImp): HistoryLocalDataSource

}