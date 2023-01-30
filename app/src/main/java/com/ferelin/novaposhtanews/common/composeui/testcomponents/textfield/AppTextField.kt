package com.endava.budgetplanner.common.composeui.components.textfield

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import com.endava.budgetplanner.R
import com.endava.budgetplanner.common.composeui.theme.AppTheme

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    readOnly: Boolean,
    isError: Boolean,
    error: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    Column(modifier = modifier) {
        CompositionLocalProvider(LocalTextStyle provides AppTheme.typography.regularBodyLarge) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = value,
                textStyle = AppTheme.typography.regularBodyLarge,
                singleLine = true,
                enabled = !readOnly,
                readOnly = readOnly,
                isError = isError,
                visualTransformation = visualTransformation,
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                onValueChange = onValueChange,
                leadingIcon = leadingIcon,
                trailingIcon = {
                    if (isError) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_error_trailing),
                            contentDescription = stringResource(id = R.string.description_error_icon)
                        )
                    } else {
                        trailingIcon?.invoke()
                    }
                },
                label = { Text(text = label) },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = AppTheme.colors.text1,
                    disabledTextColor = AppTheme.colors.text2,
                    backgroundColor = if (readOnly) {
                        AppTheme.colors.textDisableBg
                    } else {
                        AppTheme.colors.backgroundSecondary
                    },
                    cursorColor = AppTheme.colors.textClickable,
                    errorCursorColor = AppTheme.colors.fail,
                    focusedIndicatorColor = AppTheme.colors.textClickable,
                    unfocusedIndicatorColor = AppTheme.colors.text1,
                    disabledIndicatorColor = AppTheme.colors.text1,
                    errorIndicatorColor = AppTheme.colors.fail,
                    errorLabelColor = AppTheme.colors.fail,
                    focusedLabelColor = AppTheme.colors.textClickable,
                    unfocusedLabelColor = AppTheme.colors.text2,
                    disabledLabelColor = AppTheme.colors.text2,
                    leadingIconColor = AppTheme.colors.iconSecondary,
                    disabledLeadingIconColor = AppTheme.colors.iconSecondary,
                    errorLeadingIconColor = AppTheme.colors.fail,
                    trailingIconColor = AppTheme.colors.iconSecondary,
                    disabledTrailingIconColor = AppTheme.colors.iconSecondary,
                    errorTrailingIconColor = AppTheme.colors.fail,
                    placeholderColor = AppTheme.colors.text2,
                    disabledPlaceholderColor = AppTheme.colors.text2
                )
            )
        }
        AnimatedVisibility(
            visible = isError,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Text(
                modifier = Modifier.padding(
                    top = dimensionResource(id = R.dimen.text_field_error_padding_top),
                    start = dimensionResource(id = R.dimen.text_field_error_padding_start),
                    end = dimensionResource(id = R.dimen.text_field_error_padding_end)
                ),
                text = error,
                style = AppTheme.typography.regularBodySmall,
                color = AppTheme.colors.fail
            )
        }
    }
}
