package com.endava.budgetplanner.common.composeui.components.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.theme.AppTheme
import com.endava.budgetplanner.features.initialbudget.utils.AmountVisualTransformation

@Composable
fun AppNumberTextField(
    modifier: Modifier = Modifier,
    inputValue: String,
    onValueChange: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        OutlinedTextField(
            value = inputValue,
            onValueChange = onValueChange,
            textStyle = AppTheme.typography.boldLargeTitle.copy(textAlign = TextAlign.Center),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                cursorColor = AppTheme.colors.categoryShopping,
                placeholderColor = AppTheme.colors.text3,
                unfocusedBorderColor = AppTheme.colors.iconPrimary,
                focusedBorderColor = AppTheme.colors.iconPrimary,
                backgroundColor = AppTheme.colors.iconPrimary
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = {
                Text(
                    text = stringResource(
                        id = R.string.initial_budget_screen_input_placeholder_text
                    ),
                    style = AppTheme.typography.boldLargeTitle,
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.input_placeholder_centering_start_padding)
                    )
                )
            },
            singleLine = true,
            visualTransformation = AmountVisualTransformation()
        )
        Spacer(modifier = Modifier.padding(end = dimensionResource(id = R.dimen.input_spacer_small)))
        Box(
            modifier = Modifier
                .background(
                    color = AppTheme.colors.iconPrimary,
                    shape = AppTheme.shapes.small
                )
                .padding(
                    horizontal = dimensionResource(id = R.dimen.input_currency_horizontal_padding),
                    vertical = dimensionResource(id = R.dimen.input_currency_vertical_padding)
                )
        ) {
            Text(
                text = stringResource(id = R.string.input_currency_eur_text),
                style = AppTheme.typography.regularBodyLarge,
                color = AppTheme.colors.text3
            )
        }
    }
}
