package com.thirdwayv.tracker.core.base.view.screen

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.thirdwayv.tracker.core.base.view.viewmodel.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class MVIBaseFragment<ViewBindingType : ViewBinding, STATE : ViewState, EVENT : ViewEvent, ACTION : Action, RESULT : Result> :
    BaseFragment<ViewBindingType>() {
    companion object {
        private const val TAG = "BaseBindingFragment"
    }

    abstract val viewModel: BaseViewModel<STATE, EVENT, ACTION, RESULT>


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    handleViewState(it)
                }
            }
        }
    }

    /**
     * abstract function need to be implemented, used to receive new [ViewState] from the view model
     *
     * @param state is the new state of the ui to be handled
     */
    abstract fun handleViewState(state: STATE)

    /**
     * function used to initialize view events listener
     *
     * @param handleViewEvent callback function to receive new [ViewEvent] from the view model
     */
    fun initViewEvents(handleViewEvent: (event: EVENT) -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiEvent.collect {
                    handleViewEvent(it)
                }
            }
        }
    }


}
