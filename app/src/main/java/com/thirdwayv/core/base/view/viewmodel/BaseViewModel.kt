package com.thirdwayv.core.base.view.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

/**
 * Abstract class used to define Base View model
 * [ViewState] represents the state of the view returned from [reduce]
 * [ViewEvent] represents the event of the view to emit directly using [onViewEvent]
 * [Action] represents the action of the view to be handled by [handle]
 * [Result] results returned from Use cases to be reduced in viewMode class using [reduce]
 */
abstract class BaseViewModel<STATE : ViewState, EVENT : ViewEvent, ACTION : Action, RESULT : Result>(
    /**
     * instance of [ViewState], holds the initial state of the view
     */
    val initialState: STATE,
) : ViewModel() {

    /**
     * instance of [ViewState], used to get the current state of the view
     */
    val currentState: STATE
        get() = uiState.value

    /**
     * state flow of [ViewState], emits the new state to the ui
     * [_uiState] is Mutable State Flow used by the view model to emit the new state
     * [uiState] is an Immutable State Flow used by the view to listen to state changes
     */
    private val _uiState: MutableStateFlow<STATE> by lazy {
        MutableStateFlow(initialState)
    }
    val uiState = _uiState.asStateFlow()


    private val _uiEvent: Channel<EVENT> = Channel()
    val uiEvent = _uiEvent.receiveAsFlow()


    /**
     * abstract function need to be implemented in order to handle actions from the view
     * @param action the action taken by the user or the system to be handled
     * @return [Flow]<[Result]> the result of the handled action
     */
    abstract fun handle(action: ACTION): Flow<RESULT>

    /**
     * abstract function need to be implemented in order to reduce [Result] to [ViewState]
     * @param result the result returned from handle function
     * @return [ViewState] the view state reduced according to the result
     */
    abstract fun reduce(result: RESULT): STATE

    /**
     * function used to emit new event in [_uiEvent]
     * @param event is the new event to be emitted to the view
     */
    suspend fun onViewEvent(event: EVENT) {
        _uiEvent.send(event)
    }

    /**
     * function used to emit new state in [_uiState]
     * @param state is the new state to be emitted to the view
     */
    private suspend fun onViewState(state: STATE) {
        _uiState.emit(state)
    }


    /**
     * function called from the view to dispatch new action to the [handle] fun in the view model
     * and map the result via [reduce] fun to a new state
     * @param action [Action] the next action dispatched to the [handle] fun
     */
    infix fun dispatch(
        action: ACTION
    ) {
        handle(action).map { result ->
            reduce(result)
        }.onEach { state ->
            onViewState(state)
        }.launchIn(viewModelScope)
    }
}