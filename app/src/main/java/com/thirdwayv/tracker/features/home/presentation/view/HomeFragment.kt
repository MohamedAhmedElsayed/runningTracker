package com.thirdwayv.tracker.features.home.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.thirdwayv.tracker.core.base.view.screen.BaseBindingFragment
import com.thirdwayv.tracker.databinding.FragmentHomeBinding
import com.thirdwayv.tracker.features.home.presentation.viewmodel.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseBindingFragment<FragmentHomeBinding, HomeViewState, HomeViewEvent, HomeViewAction, HomeResult>() {
    override val viewModel: HomeViewModel by viewModels()
    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun handleViewState(state: HomeViewState) {
    }

}