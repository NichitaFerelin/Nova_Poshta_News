package com.endava.budgetplanner.common.composeui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.theme.AppTheme

@Composable
fun AppCircularProgressBar(
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.backgroundSecondary
) {
    CircularProgressIndicator(
        modifier = modifier.size(dimensionResource(id = R.dimen.circular_progress_bar_size)),
        color = color,
        strokeWidth = dimensionResource(id = R.dimen.circular_progress_bar_stroke_width)
    )
}
