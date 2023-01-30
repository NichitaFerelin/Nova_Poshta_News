package com.endava.budgetplanner.common.composeui.components.animation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun <S> AnimatedContentBlink(
    modifier: Modifier = Modifier,
    targetState: S,
    content: @Composable AnimatedVisibilityScope.(S) -> Unit
) {
    AnimatedContent(
        modifier = modifier,
        targetState = targetState,
        transitionSpec = { fadeIn() with fadeOut() },
        content = content
    )
}
