package com.endava.budgetplanner.common.composeui.components.apptoolbar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.endava.budgetplanner.common.composeui.theme.AppTheme

@Composable
fun AppToolbarIconAction(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            painter = painter,
            contentDescription = contentDescription,
            tint = AppTheme.colors.iconPrimary
        )
    }
}
