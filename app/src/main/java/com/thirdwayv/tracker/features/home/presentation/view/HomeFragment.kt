package com.thirdwayv.tracker.features.home.presentation.view

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.thirdwayv.tracker.R
import com.thirdwayv.tracker.core.base.extentions.toFormattedTimeString
import com.thirdwayv.tracker.core.base.permissions.PermissionsHandler
import com.thirdwayv.tracker.core.base.view.screen.MVIBaseFragment
import com.thirdwayv.tracker.databinding.FragmentHomeBinding
import com.thirdwayv.tracker.features.home.domain.distanceHandler.DistanceHandler
import com.thirdwayv.tracker.features.home.domain.stepscounter.StepsCounter
import com.thirdwayv.tracker.features.home.domain.trackingtimer.TimerHandler
import com.thirdwayv.tracker.features.home.presentation.services.ForegroundOnlyLocationService
import com.thirdwayv.tracker.features.home.presentation.viewmodel.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment :
    MVIBaseFragment<FragmentHomeBinding, HomeViewState, HomeViewEvent, HomeViewAction, HomeResult>() {
    @Inject
    lateinit var timerHandler: TimerHandler


    @Inject
    lateinit var permissionsHandler: PermissionsHandler

    @Inject
    lateinit var stepsCounter: StepsCounter
    override val viewModel: HomeViewModel by activityViewModels()

    @Inject
    lateinit var distanceHandler: DistanceHandler
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewClickListeners()
        initPermissionContract()
        handleViewEvents()
        observeTimer()
        observeStepsCounter()
        observeDistance()

    }

    private fun handleViewEvents() {
        initViewEvents {
            when (it) {
                HomeViewEvent.StartTracking -> startTracking()
                HomeViewEvent.PauseTracking -> pauseTracking()
                HomeViewEvent.ResumeTracking -> startTracking()
                HomeViewEvent.StopTracking -> stopTracking()
            }
        }
    }

    private fun pauseTracking() {
        timerHandler.cancelTimer()
        stepsCounter.pauseReceivingSteps()
        startLocationService(true)

    }

    private fun stopTracking() {
        timerHandler.resetTimer()
        stepsCounter.stopCounter()
        startLocationService(true)
        navigateToHistory()
    }

    private fun navigateToHistory() {
        navigateTo(HomeFragmentDirections.actionHomeFragmentToHistoryFragment())
    }

    private fun initPermissionContract() {
        permissionsHandler.registerPermissionCallBacks(this, onAllPermissionsGranted = {
            viewModel dispatch HomeViewAction.StartTracking
        }, onPermissionsDenied = {
            Toast.makeText(
                requireContext(),
                getString(R.string.permissin_denaied_message),
                Toast.LENGTH_LONG
            ).show()
        })
    }

    private fun initViewClickListeners() {
        with(binding.cardContainer) {
            trackingFab.setOnClickListener {
                when (viewModel.currentState.trackingState) {
                    HomeTrackingState.Initial -> requestTrackingPermissions()
                    HomeTrackingState.Paused -> viewModel dispatch HomeViewAction.ResumeTracking
                    HomeTrackingState.Resumed -> viewModel dispatch HomeViewAction.PauseTracking
                    HomeTrackingState.Started -> viewModel dispatch HomeViewAction.PauseTracking
                }

            }
            endTrackingFab.setOnClickListener {
                viewModel dispatch HomeViewAction.StopTracking
            }
            binding.historyFab.setOnClickListener { navigateToHistory() }
        }
    }

    private fun requestTrackingPermissions() {
        permissionsHandler.launchPermissions(
            getPermissionsList()
        )
    }

    private fun startTracking() {
        timerHandler.startTimer()
        startStepsCounter()
        startLocationService(false)

    }

    private fun startLocationService(cancelService: Boolean) {
        val intent = Intent(
            requireContext().applicationContext,
            ForegroundOnlyLocationService::class.java
        )
        intent.putExtra(
            ForegroundOnlyLocationService.EXTRA_CANCEL_LOCATION_TRACKING_FROM_NOTIFICATION,
            cancelService
        )
        requireActivity().startService(
            intent
        )
    }

    private fun startStepsCounter() {
        stepsCounter.startStepCounter { registeredSuccessfully ->
            if (!registeredSuccessfully)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.start_counting_error_message),
                    Toast.LENGTH_LONG
                ).show()
        }
    }

    private fun observeTimer() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                timerHandler.getTimerFlow().collect { seconds ->
                    if (viewModel.currentState.trackingState != HomeTrackingState.Initial)
                        viewModel dispatch HomeViewAction.UpdateSeconds(seconds)
                }
            }
        }
    }

    private fun observeDistance() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                distanceHandler.getDistanceFlow().collect { distance ->
                    if (viewModel.currentState.trackingState != HomeTrackingState.Initial)
                        viewModel dispatch HomeViewAction.UpdateDistance(distance)
                }
            }
        }
    }

    private fun observeStepsCounter() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                stepsCounter.getStepsCounterObserver().collect { steps ->
                    if (viewModel.currentState.trackingState != HomeTrackingState.Initial)
                        viewModel dispatch HomeViewAction.UpdateSteps(steps)

                }
            }
        }
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun handleViewState(state: HomeViewState) {
         setViewsText(state)
        when (state.trackingState) {
            HomeTrackingState.Initial -> setButtonsInitialState()
            HomeTrackingState.Paused -> setButtonsPausedState()
            HomeTrackingState.Started -> setButtonsStartedState()
            HomeTrackingState.Resumed -> setButtonsStartedState()
        }
    }

    private fun setButtonsInitialState() {
        with(binding.cardContainer) {
            trackingFab.visibility = View.VISIBLE
            endTrackingFab.visibility = View.GONE
            trackingFab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        }
    }

    private fun setButtonsPausedState() {
        with(binding.cardContainer) {
             endTrackingFab.visibility = View.VISIBLE
            trackingFab.setImageResource(R.drawable.ic_baseline_play_arrow_24)
        }
    }

    private fun setButtonsStartedState() {
        with(binding.cardContainer) {
            endTrackingFab.visibility = View.VISIBLE
            trackingFab.setImageResource(R.drawable.ic_baseline_pause_24)
        }
    }

    private fun setViewsText(state: HomeViewState) {
        with(binding.cardContainer) {
            stepsTv.text = state.totalSteps.toString()
            durationTv.text = state.numberOfSeconds.toFormattedTimeString()
            distanceTv.text = String.format("%.2f", state.distance)
//            distanceTv.text =   state.distance.toString()
        }
    }

    private fun getPermissionsList(): Array<String> {
        val permissions = arrayListOf<String>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            permissions.add(Manifest.permission.ACTIVITY_RECOGNITION)
        }
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION)
        return permissions.toTypedArray()
    }


}