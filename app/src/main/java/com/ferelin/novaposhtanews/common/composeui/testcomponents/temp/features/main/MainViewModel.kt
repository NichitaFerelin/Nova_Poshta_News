package com.endava.budgetplanner.features.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.endava.budgetplanner.common.composeui.navigation.AppDestinations
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private var isAuthenticated = false

    private var hasInitialBudgetSet = false

    private val _authenticationFetchingState = MutableStateFlow(false)
    private val _initialBudgetFetchingState = MutableStateFlow(false)

    private val _loadingState = MutableStateFlow(true)
    val loadingState = _loadingState.asStateFlow()

    private val _appDestinations = mutableStateOf<AppDestinations>(AppDestinations.SelectionScreen)
    val appDestinations = _appDestinations

    init {
        fetchAuthState()
        fetchInitialBudgetState()

        combine(
            flow = _authenticationFetchingState,
            flow2 = _initialBudgetFetchingState,
            transform = ::isFetchingComplete
        )
            .filter { it }
            .also { setStartingDestination() }
            .onEach { _loadingState.value = false }
            .launchIn(viewModelScope)
    }

    private fun setStartingDestination() {
        _appDestinations.value = when {
            isAuthenticated && hasInitialBudgetSet -> {
                // TODO change this to the main screen destination
                AppDestinations.SelectionScreen
            }
            isAuthenticated && !hasInitialBudgetSet -> {
                AppDestinations.OnboardingInitialBudget
            }
            else -> {
                AppDestinations.SelectionScreen
            }
        }
    }

    private fun fetchAuthState() {
        isAuthenticated = true
        _authenticationFetchingState.value = true
    }

    // TODO get initial budget state when it will be implemented
    private fun fetchInitialBudgetState() {
        hasInitialBudgetSet = false
        // TODO set change fetching state when done
        _initialBudgetFetchingState.value = true
    }

    private fun isFetchingComplete(
        authFetchState: Boolean,
        initialBudgetFetchState: Boolean
    ) = authFetchState && initialBudgetFetchState
}
