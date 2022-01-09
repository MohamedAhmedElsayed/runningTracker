package com.thirdwayv.tracker.features.home.data.local.trips

import android.location.Location
import android.util.Log
import com.thirdwayv.tracker.features.home.data.entity.StepLocation
import com.thirdwayv.tracker.features.home.data.entity.TrackingTripModel
import io.realm.Realm
import javax.inject.Inject

class TripsLocalDataSourceImp @Inject constructor() : TripsLocalDataSource {
    override fun saveTripData(steps: Int, duration: Int) {
        Realm.getDefaultInstance().use {
            val notCompletedTrip = getNotCompletedTrip(it)
            it.executeTransaction {
                notCompletedTrip?.apply {
                    this.duration = duration
                    this.steps = steps
                    isCompleted = true
                }
            }
        }
    }

    override fun addStepLocation(location: Location): Float {
        var distance = 0.0f
        Realm.getDefaultInstance().use {
            val notCompletedTrip = getNotCompletedTrip(it)
            it.executeTransaction {
                notCompletedTrip?.locations?.add(
                    StepLocation(
                        location.latitude,
                        location.longitude
                    )
                )

                if ((notCompletedTrip?.locations?.size ?: -1) > 0)
                    for (i in 0 until (notCompletedTrip?.locations?.size ?: 0) - 1) {


                        distance += notCompletedTrip?.locations?.get(i)?.mapToLocation()
                            ?.distanceTo(notCompletedTrip.locations[i + 1]?.mapToLocation()) ?: 0f
                        Log.e(
                            "TT",
                            "${notCompletedTrip?.locations?.size} ${i} ${
                                notCompletedTrip?.locations?.get(i)
                            } ${notCompletedTrip?.locations?.get(i + 1)}"
                        )

                    }

                notCompletedTrip?.distace = distance

            }
        }
        return distance
    }

    private fun getNotCompletedTrip(realm: Realm) =
        realm.where(TrackingTripModel::class.java).equalTo("isCompleted", false).findAll().last()


    override fun createNewTrip() {
        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                realm.insertOrUpdate(TrackingTripModel())
            }
        }
    }
}