package com.thirdwayv.tracker.features.home.domain.di

import com.thirdwayv.tracker.features.home.domain.trackingtimer.TimerHandler
import com.thirdwayv.tracker.features.home.domain.trackingtimer.TimerHandlerImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TimerModule {
    @Binds
    abstract fun bindsTimerHandler(timerHandlerImp: TimerHandlerImp): TimerHandler
}