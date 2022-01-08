package com.thirdwayv.tracker.features.home.data.entity

import io.realm.RealmObject

open class StepsCounterModel(var previousStepsCounter: Float = 0.0f) : RealmObject()
