package com.thirdwayv.tracker.features.trackingdetails.presentation.view

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import com.thirdwayv.tracker.R
import com.thirdwayv.tracker.core.base.extentions.toFormattedTimeString
import com.thirdwayv.tracker.core.base.view.screen.BaseFragment
import com.thirdwayv.tracker.databinding.FragmentDetailsBinding
import com.thirdwayv.tracker.features.home.data.entity.StepLocation
import com.thirdwayv.tracker.features.home.data.entity.TrackingTripModel
import io.realm.RealmList


class DetailsFragment : BaseFragment<FragmentDetailsBinding>(), OnMapReadyCallback {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        fillTripDetailsData()
    }

    private fun fillTripDetailsData() {
        val trackingTripModel = getTrackingTripArgs()
        with(binding.detailsCard) {
            stepsTv.text = trackingTripModel.steps.toString()
            durationTv.text = trackingTripModel.duration.toFormattedTimeString()
            distanceTv.text = String.format("%.2f", trackingTripModel.distace)
        }
    }

    override fun onMapReady(map: GoogleMap) {
        setupMap(map)
        setupTrackingLine(map)
    }

    private fun setupTrackingLine(map: GoogleMap) {
        val steps = mapLocationsToLatLang(getTrackingTripArgs().locations)
        if (steps.isNullOrEmpty()) return

        val line = map.addPolyline(
            PolylineOptions()
                .addAll(steps)
                .width(8f)
                .color(Color.RED)
        )
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(steps.first(), 18f))

    }

    private fun mapLocationsToLatLang(locations: RealmList<StepLocation>) =
        locations.map {
            LatLng(it.lat, it.lang)
        }


    private fun setupMap(map: GoogleMap) {
        map.apply {
            isBuildingsEnabled = true
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                isMyLocationEnabled = true
            }

            uiSettings.apply {
                isZoomGesturesEnabled = true
            }
        }
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDetailsBinding.inflate(layoutInflater)

    private fun getTrackingTripArgs(): TrackingTripModel =
        DetailsFragmentArgs.fromBundle(arguments!!).trackingDetails


}