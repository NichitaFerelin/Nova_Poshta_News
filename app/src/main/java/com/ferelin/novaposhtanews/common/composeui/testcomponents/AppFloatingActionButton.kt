package com.endava.budgetplanner.common.composeui.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.endava.budgetplanner.common.composeui.theme.AppTheme

@Composable
fun AppFloatingActionButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        backgroundColor = AppTheme.colors.contendAccentPrimary,
        contentColor = AppTheme.colors.onContendAccentPrimary
    ) {
        Icon(
            painter = painter,
            contentDescription = contentDescription
        )
    }
}
