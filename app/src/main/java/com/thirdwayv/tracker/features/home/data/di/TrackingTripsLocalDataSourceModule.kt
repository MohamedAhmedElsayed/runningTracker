package com.thirdwayv.tracker.features.home.data.di

import com.thirdwayv.tracker.features.home.data.local.trips.TripsLocalDataSource
import com.thirdwayv.tracker.features.home.data.local.trips.TripsLocalDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TrackingTripsLocalDataSourceModule {
    @Binds
    abstract fun bindsTripsLocalDataSource(tripsLocalDataSourceImp: TripsLocalDataSourceImp): TripsLocalDataSource

}