package com.endava.budgetplanner.common.composeui.components.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun <S> AnimatedContentScale(
    modifier: Modifier = Modifier,
    targetState: S,
    content: @Composable AnimatedVisibilityScope.(S) -> Unit
) {
    AnimatedContent(
        modifier = modifier,
        targetState = targetState,
        transitionSpec = {
            ContentTransform(
                targetContentEnter = scaleIn(),
                initialContentExit = scaleOut()
            )
        },
        content = content
    )
}
