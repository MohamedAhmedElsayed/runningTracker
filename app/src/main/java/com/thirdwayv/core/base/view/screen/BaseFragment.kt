package com.thirdwayv.core.base.view.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.thirdwayv.core.base.view.viewmodel.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class BaseBindingFragment<ViewBindingType : ViewBinding, STATE : ViewState, EVENT : ViewEvent, ACTION : Action, RESULT : Result> :
    Fragment() {
    companion object {
        private const val TAG = "BaseBindingFragment"
    }

    private var _binding: ViewBindingType? = null
    abstract val viewModel: BaseViewModel<STATE, EVENT, ACTION, RESULT>

    // Binding variable to be used for accessing views.
    protected val binding
        get() = requireNotNull(_binding)

    // Calls the abstract function to return the ViewBinding.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = setupViewBinding(inflater, container)
        return requireNotNull(_binding).root
    }

    //abstract function to get ViewBinding
    abstract fun setupViewBinding(
        inflater: LayoutInflater, container: ViewGroup?,
    ): ViewBindingType

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

    /**
     * clear view binding
     * */
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
