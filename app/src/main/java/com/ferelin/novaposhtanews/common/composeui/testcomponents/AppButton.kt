package com.endava.budgetplanner.common.composeui.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.theme.AppTheme

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    isLoading: Boolean,
    onClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    Button(
        modifier = modifier.height(dimensionResource(id = R.dimen.app_button_height)),
        shape = AppTheme.shapes.medium,
        enabled = enabled,
        contentPadding = PaddingValues(
            vertical = dimensionResource(id = R.dimen.app_button_vertical_padding),
            horizontal = dimensionResource(id = R.dimen.app_button_horizontal_padding)
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = AppTheme.colors.buttonPrimary,
            disabledBackgroundColor = AppTheme.colors.buttonDisable
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = dimensionResource(id = R.dimen.app_button_elevation)
        ),
        onClick = onClick
    ) {
        Crossfade(targetState = isLoading) { isLoading ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (isLoading) {
                    AppCircularProgressBar()
                } else {
                    content()
                }
            }
        }
    }
}
