package com.endava.budgetplanner.features.initialbudget.models

import androidx.lifecycle.ViewModel
import com.endava.budgetplanner.features.initialbudget.InitialBudgetState
import com.endava.budgetplanner.features.initialbudget.utils.inputValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class InitialBudgetViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(InitialBudgetState())
    val uiState: StateFlow<InitialBudgetState> = _uiState.asStateFlow()

    fun onInputValueChange(inputValue: String) {
        if (inputValidator(inputValue)) {
            _uiState.update {
                it.copy(
                    budgetInput = inputValue,
                    continueButtonEnabled = inputValue.isNotEmpty()
                )
            }
        }
    }
}
