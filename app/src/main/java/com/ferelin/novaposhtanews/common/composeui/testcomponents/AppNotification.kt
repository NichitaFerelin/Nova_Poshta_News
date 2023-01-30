package com.endava.budgetplanner.common.composeui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.theme.AppTheme
import com.endava.budgetplanner.common.composeui.theme.LightColorScheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun AppNotification(
    isVisible: Boolean,
    color: Color,
    title: String
) {
    val density = LocalDensity.current
    val systemUiController = rememberSystemUiController()
    val colorScheme = LightColorScheme

    systemUiController.setStatusBarColor(
        color = if (isVisible) {
            color
        } else {
            colorScheme.statusBarPrimary
        }
    )

    val slideInFromTop = dimensionResource(id = R.dimen.app_notification_slide_from_top_distance)

    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically {
            // Slide in from 40 dp from the top.
            with(density) { slideInFromTop.roundToPx() }
        } + expandVertically(
            // Expand from the top.
            expandFrom = Alignment.Top
        ) + fadeIn(
            // Fade in with the initial alpha of 0.3f.
            initialAlpha = 0.3f
        ),
        exit = slideOutVertically() + shrinkVertically() + fadeOut()
    ) {
        TopAppBar(
            backgroundColor = color
        ) {
            Box {
                Text(
                    text = title,
                    style = AppTheme.typography.regularCallout,
                    color = AppTheme.colors.iconPrimary,
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.app_notification_padding_medium),
                        end = dimensionResource(
                            id = R.dimen.app_notification_padding_medium
                        )
                    )
                )
            }
        }
    }
}
