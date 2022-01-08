package com.thirdwayv.tracker.features.home.presentation.domain.stepscounter

import androidx.lifecycle.LiveData

interface StepsCounter {
      fun getStepsCounterObserver(): LiveData<Float>
}