package com.endava.budgetplanner.common.composeui.components.apptoolbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.theme.AppTheme

@Composable
fun AppToolbar(
    modifier: Modifier = Modifier,
    title: @Composable (Modifier) -> Unit,
    leftAction: @Composable ((Modifier) -> Unit)? = null,
    rightAction: @Composable ((Modifier) -> Unit)? = null
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = AppTheme.colors.contendAccentPrimary,
        elevation = dimensionResource(id = R.dimen.app_toolbar_elevation),
        contentPadding = PaddingValues(
            horizontal = dimensionResource(id = R.dimen.app_toolbar_content_horizontal_padding)
        )
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            leftAction?.invoke(Modifier.align(Alignment.CenterStart))

            title.invoke(
                Modifier
                    .align(Alignment.Center)
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.default_icon_size) +
                            dimensionResource(id = R.dimen.default_icon_button_ripple_diameter) +
                            dimensionResource(id = R.dimen.app_toolbar_padding_from_icon)
                    )
            )

            rightAction?.invoke(Modifier.align(Alignment.CenterEnd))
        }
    }
}
