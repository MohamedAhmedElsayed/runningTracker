package com.thirdwayv.tracker.features.home.presentation.domain.stepscounter.di

import com.thirdwayv.tracker.features.home.presentation.domain.stepscounter.StepsCounter
import com.thirdwayv.tracker.features.home.presentation.domain.stepscounter.StepsCounterImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StepsCounterModule {
    @Binds
    abstract fun bindsStepsCounter(stepsCounterImp: StepsCounterImp): StepsCounter

}