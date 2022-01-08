package com.thirdwayv.tracker.features.home.presentation.data.di

import com.thirdwayv.tracker.features.home.presentation.data.local.StepsCounterLocalDataSource
import com.thirdwayv.tracker.features.home.presentation.data.local.StepsCounterLocalDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class StepsCounterDataSourceModule {
    @Binds
    abstract fun bindsStepsCounterLocalDataSource(stepsCounterLocalDataSourceImp: StepsCounterLocalDataSourceImp): StepsCounterLocalDataSource

}
