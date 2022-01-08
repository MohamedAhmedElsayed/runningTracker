package com.thirdwayv.tracker.features.home.presentation.data.entity

import io.realm.RealmObject

open class StepsCounterModel(var previousStepsCounter: Float = 0.0f) : RealmObject()
