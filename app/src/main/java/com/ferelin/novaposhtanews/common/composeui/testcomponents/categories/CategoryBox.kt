package com.endava.budgetplanner.common.composeui.components.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.theme.AppTheme

private const val CURRENCY_ALPHA = 0.3f
private const val SPACER_WEIGHT = 1f

@Composable
fun CategoryBox(
    modifier: Modifier = Modifier,
    categoryName: String,
    backColor: Color,
    categorySum: Int,
    currency: String,
    percent: Int,
    onClickCategory: () -> Unit
) {
    Box(
        modifier = modifier
            .size(
                dimensionResource(id = R.dimen.categories_box_width),
                dimensionResource(id = R.dimen.categories_box_height)
            )
            .background(backColor, shape = AppTheme.shapes.small)
            .clickable(onClick = onClickCategory)
    ) {
        Column(
            modifier = Modifier,
            Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.categories_box_padding_standard),
                        top = dimensionResource(id = R.dimen.categories_box_padding_standard),
                        end = dimensionResource(id = R.dimen.categories_box_padding_standard)
                    ),
                style = AppTheme.typography.boldSubheadline,
                text = categoryName
            )

            Spacer(modifier = Modifier.weight(SPACER_WEIGHT))

            Text(
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.categories_box_padding_standard),
                        bottom = dimensionResource(id = R.dimen.categories_box_padding_standard)
                    ),
                style = AppTheme.typography.regularSubheadline,
                text = stringResource(id = R.string.categories_percent, percent)
            )

            Text(
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.categories_box_padding_standard)),
                color = AppTheme.colors.text1.copy(alpha = CURRENCY_ALPHA),
                style = AppTheme.typography.boldHeadline,
                text = currency
            )

            Text(
                modifier = Modifier
                    .padding(
                        start = dimensionResource(id = R.dimen.categories_box_padding_standard),
                        bottom = dimensionResource(id = R.dimen.categories_box_padding_standard)
                    ),
                style = AppTheme.typography.boldHeadline,
                text = String.format("%,d", categorySum)
            )
        }
    }
}
