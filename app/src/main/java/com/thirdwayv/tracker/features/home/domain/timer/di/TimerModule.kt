package com.thirdwayv.tracker.features.home.domain.timer.di

import com.thirdwayv.tracker.features.home.domain.timer.TimerHandler
import com.thirdwayv.tracker.features.home.domain.timer.TimerHandlerImp
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