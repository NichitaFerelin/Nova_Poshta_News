package com.endava.budgetplanner.common.composeui.components.textfield

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import com.endava.budgetplanner.R

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppTextFieldWithClearIcon(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    error: String,
    isReadOnly: Boolean,
    isError: Boolean,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    onValueChange: (String) -> Unit,
    onClearClick: () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    AppTextField(
        modifier = modifier,
        value = value,
        label = label,
        readOnly = isReadOnly,
        isError = isError,
        error = error,
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        leadingIcon = leadingIcon,
        trailingIcon = {
            AnimatedVisibility(
                visible = value.isNotEmpty(),
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut()
            ) {
                IconButton(
                    onClick = onClearClick,
                    enabled = !isReadOnly
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_xmark_fill),
                        contentDescription = stringResource(id = R.string.description_clear_text)
                    )
                }
            }
        }
    )
}
