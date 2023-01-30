package com.endava.budgetplanner.data.model

import androidx.compose.ui.graphics.Color

class TransactionCategoriesState(
    val id: Int,
    val name: String,
    val backColor: Color,
    val sum: Int = 0,
    val currency: String = "",
    val percent: Int = 0,
    val borderColor: Color = Color(0, 0, 0)
)
