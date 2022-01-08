package com.thirdwayv.tracker.features.home.domain.di

import com.thirdwayv.tracker.features.home.data.repository.StepsCounterRepoImp
import com.thirdwayv.tracker.features.home.domain.repository.StepsCounterRepo
import com.thirdwayv.tracker.features.home.domain.stepscounter.StepsCounter
import com.thirdwayv.tracker.features.home.domain.stepscounter.StepsCounterImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StepsCounterModule {
    @Binds
    abstract fun bindsStepsCounter(stepsCounterImp: StepsCounterImp): StepsCounter

    @Binds
    abstract fun bindsStepsCounterRepo(stepsCounterRepoImp: StepsCounterRepoImp): StepsCounterRepo
}