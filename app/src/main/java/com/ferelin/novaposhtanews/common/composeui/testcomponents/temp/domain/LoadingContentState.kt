package com.endava.budgetplanner.domain

sealed class LoadingContentState {
    object None : LoadingContentState()
    object Content : LoadingContentState()
    object Loading : LoadingContentState()
    data class Error(val exception: Exception? = null) : LoadingContentState()
}
