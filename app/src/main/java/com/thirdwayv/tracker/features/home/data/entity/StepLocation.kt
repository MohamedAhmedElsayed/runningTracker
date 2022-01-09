package com.thirdwayv.tracker.features.home.data.entity

import android.location.Location
import io.realm.RealmObject
import java.util.*

open class StepLocation(var lat: Double = 0.0, var lang: Double = 0.0) : RealmObject() {
    fun mapToLocation(): Location {
        val location = Location(UUID.randomUUID().toString())
        location.latitude = lat
        location.longitude = lang
        return location

    }
}