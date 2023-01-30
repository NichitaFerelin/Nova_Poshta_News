package com.endava.budgetplanner.features.initialbudget

import com.endava.budgetplanner.domain.LoadingContentState
import javax.annotation.concurrent.Immutable

@Immutable
data class InitialBudgetState(
    val continueButtonEnabled: Boolean = false,
    val loadingContentState: LoadingContentState = LoadingContentState.None,
    val budgetInput: String = ""
)
