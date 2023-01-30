package com.endava.budgetplanner.common.composeui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun AppIconButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String,
    enabledTint: Color,
    disabledTint: Color,
    enabled: Boolean,
    onClick: () -> Unit
) {
    val iconColor by animateColorAsState(
        targetValue = if (enabled) enabledTint else disabledTint
    )

    IconButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick
    ) {
        Icon(
            painter = painter,
            contentDescription = contentDescription,
            tint = iconColor
        )
    }
}
