package com.thirdwayv.tracker.features.history.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.thirdwayv.tracker.core.base.view.screen.BaseBindingFragment
import com.thirdwayv.tracker.databinding.FragmentHistoryBinding
import com.thirdwayv.tracker.features.history.presentation.viewmodel.*
import com.thirdwayv.tracker.features.home.data.entity.TrackingTripModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment :
    BaseBindingFragment<FragmentHistoryBinding, HistoryViewState, HistoryViewEvent, HistoryAction, HistoryResult>(),
    HistoryAdapter.Interaction {
    override val viewModel: HistoryViewModel by viewModels()
    lateinit var historyAdapter: HistoryAdapter
    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHistoryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel dispatch HistoryAction.LoadTripsHistory
        historyAdapter = HistoryAdapter(this)
        binding.historyRv.adapter = historyAdapter
    }

    override fun handleViewState(state: HistoryViewState) {
        if (state.tripsList.isNullOrEmpty())
            binding.emptyDataTv.visibility = View.VISIBLE
        else
            binding.emptyDataTv.visibility = View.GONE
        historyAdapter.submitList(state.tripsList)
    }

    override fun onItemSelected(position: Int, item: TrackingTripModel) {
//todo navigate to details
    }


}