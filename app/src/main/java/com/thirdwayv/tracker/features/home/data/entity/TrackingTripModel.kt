package com.thirdwayv.tracker.features.home.data.entity

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable
import java.util.*

open class TrackingTripModel(
    @PrimaryKey
    var id :String= UUID.randomUUID().toString(),
    var isCompleted: Boolean = false,
    var distace: Float = 0f,
    var steps: Int = 0,
    var duration: Int = 0,
    var locations: RealmList<StepLocation> = RealmList()
) : RealmObject(),Serializable

