package com.thirdwayv.tracker.features.home.presentation.view

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.thirdwayv.tracker.core.base.extentions.toFormattedTimeString
import com.thirdwayv.tracker.core.base.permissions.PermissionsHandler
import com.thirdwayv.tracker.core.base.view.screen.BaseBindingFragment
import com.thirdwayv.tracker.databinding.FragmentHomeBinding
import com.thirdwayv.tracker.features.home.presentation.domain.timer.TimerHandler
import com.thirdwayv.tracker.features.home.presentation.viewmodel.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment :
    BaseBindingFragment<FragmentHomeBinding, HomeViewState, HomeViewEvent, HomeViewAction, HomeResult>() {
    @Inject
    lateinit var timerHandler: TimerHandler

    @Inject
    lateinit var permissionsHandler: PermissionsHandler
    override val viewModel: HomeViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                timerHandler.getTimerFlow().collect { seconds ->
                    binding.cardContainer.durationTv.text =
                        seconds.toFormattedTimeString()
                }
            }
        }
        timerHandler.startTimer()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            permissionsHandler.requestPermissions(
                this,
                getPermissionsList(), {
                    Log.e("TT", "granted")

                }, {
                    Log.e("TT", "denaiied")
                }
            )
        }
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun handleViewState(state: HomeViewState) {
    }

    private fun getPermissionsList(): Array<String> {
        val permissions = arrayListOf<String>()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            permissions.add(Manifest.permission.ACTIVITY_RECOGNITION)
        }
        return permissions.toTypedArray()
    }
}