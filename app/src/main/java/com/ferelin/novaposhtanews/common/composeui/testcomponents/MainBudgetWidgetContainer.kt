package com.endava.budgetplanner.common.composeui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.components.animation.AnimatedContentBlink
import com.endava.budgetplanner.common.composeui.theme.AppTheme

private const val TEXT_WEIGHT = 0.5f

@Composable
fun MainBudgetWidgetContainer(
    modifier: Modifier = Modifier,
    leftValue: String,
    leftValueTitle: String,
    rightValue: String,
    rightValueTitle: String,
    date: String,
    previousDateExist: Boolean,
    nextDateExist: Boolean,
    onPreviousDateClick: () -> Unit,
    onNextDateClick: () -> Unit,
    bodySection: @Composable ColumnScope.() -> Unit
) {
    AppWidget(
        modifier = modifier,
        backgroundColor = AppTheme.colors.backgroundTertiary
    ) {
        TopSection(
            leftValue = leftValue,
            leftValueTitle = leftValueTitle,
            rightValue = rightValue,
            rightValueTitle = rightValueTitle
        )
        Spacer(
            modifier = Modifier.height(
                dimensionResource(id = R.dimen.main_budget_sections_padding)
            )
        )
        bodySection()
        Spacer(
            modifier = Modifier.height(
                dimensionResource(id = R.dimen.main_budget_sections_padding)
            )
        )
        BottomSection(
            date = date,
            previousDateExist = previousDateExist,
            nextDateExist = nextDateExist,
            onPreviousDateClick = onPreviousDateClick,
            onNextDateClick = onNextDateClick
        )
    }
}

@Composable
private fun TopSection(
    modifier: Modifier = Modifier,
    leftValue: String,
    leftValueTitle: String,
    rightValue: String,
    rightValueTitle: String
) {
    Row(modifier = modifier.fillMaxWidth()) {
        AppDoubleRowText(
            modifier = Modifier.weight(TEXT_WEIGHT),
            textTop = leftValueTitle,
            textTopColor = AppTheme.colors.text1,
            textTopStyle = AppTheme.typography.regularCaption1,
            textBottom = leftValue,
            textBottomColor = AppTheme.colors.text1,
            textBottomStyle = AppTheme.typography.regularHeadline
        )
        AppDoubleRowText(
            modifier = Modifier.weight(TEXT_WEIGHT),
            textTop = rightValueTitle,
            textTopColor = AppTheme.colors.text1,
            textTopStyle = AppTheme.typography.regularCaption1,
            textBottom = rightValue,
            textBottomColor = AppTheme.colors.text1,
            textBottomStyle = AppTheme.typography.regularHeadline,
            alignment = Alignment.End
        )
    }
}

@Composable
private fun BottomSection(
    modifier: Modifier = Modifier,
    date: String,
    previousDateExist: Boolean,
    nextDateExist: Boolean,
    onPreviousDateClick: () -> Unit,
    onNextDateClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = AppTheme.colors.backgroundSecondary,
                shape = AppTheme.shapes.small
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIconButton(
            painter = painterResource(id = R.drawable.ic_arrow_previous),
            contentDescription = stringResource(id = R.string.description_previous_option),
            enabledTint = AppTheme.colors.iconTertiary,
            disabledTint = AppTheme.colors.iconSecondary,
            enabled = previousDateExist,
            onClick = onPreviousDateClick
        )
        AnimatedContentBlink(targetState = date) {
            Text(
                text = date,
                color = AppTheme.colors.text1,
                style = AppTheme.typography.regularCallout
            )
        }
        AppIconButton(
            painter = painterResource(id = R.drawable.ic_arrow_next),
            contentDescription = stringResource(id = R.string.description_next_option),
            enabledTint = AppTheme.colors.iconTertiary,
            disabledTint = AppTheme.colors.iconSecondary,
            enabled = nextDateExist,
            onClick = onNextDateClick
        )
    }
}
