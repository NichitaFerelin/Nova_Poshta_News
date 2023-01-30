package com.endava.budgetplanner.common.composeui.components.apptoolbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.endava.budgetplanner.R

@Composable
fun AppToolbarBack(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: () -> Unit
) {
    AppToolbar(
        modifier = modifier,
        title = { externalModifier ->
            AppToolbarTitle(
                modifier = externalModifier,
                title = title
            )
        },
        leftAction = { externalModifier ->
            AppToolbarIconAction(
                modifier = externalModifier,
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = stringResource(id = R.string.description_back_icon),
                onClick = onBackClick
            )
        }
    )
}
