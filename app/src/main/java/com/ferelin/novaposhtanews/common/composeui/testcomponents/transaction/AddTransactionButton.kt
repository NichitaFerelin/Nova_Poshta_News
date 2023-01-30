package com.endava.budgetplanner.common.composeui.components.transaction

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.components.DashedBorderContainer
import com.endava.budgetplanner.common.composeui.theme.AppTheme

@Composable
fun AddTransactionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val dashedCornerRadius = dimensionResource(id = R.dimen.transaction_add_button_corner_radius)
    val dashedCornerRadiusPx = LocalDensity.current.run { dashedCornerRadius.toPx() }

    DashedBorderContainer(
        modifier = modifier
            .fillMaxWidth()
            .height(dimensionResource(id = R.dimen.transaction_add_button_height))
            .background(color = AppTheme.colors.backgroundSecondary),
        width = dimensionResource(id = R.dimen.transaction_add_button_dashed_width),
        color = AppTheme.colors.text4,
        cornerRadius = CornerRadius(dashedCornerRadiusPx, dashedCornerRadiusPx)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = dimensionResource(R.dimen.transaction_add_button_padding_horizontal),
                    vertical = dimensionResource(id = R.dimen.transaction_add_button_padding_vertical)
                )
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.add_transaction_title),
                style = AppTheme.typography.regularBody,
                color = AppTheme.colors.text1,
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier.height(
                    dimensionResource(id = R.dimen.transaction_add_button_padding_from_title)
                )
            )
            Text(
                text = stringResource(id = R.string.add_transaction_description),
                style = AppTheme.typography.regularSubheadline,
                color = AppTheme.colors.text2,
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier.height(
                    dimensionResource(id = R.dimen.transaction_add_button_padding_from_description)
                )
            )
            TextButton(onClick = onClick) {
                Text(
                    text = stringResource(id = R.string.add_transaction_button_title),
                    style = AppTheme.typography.regularSubheadline,
                    color = AppTheme.colors.textClickable2,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
