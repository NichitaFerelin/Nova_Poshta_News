package com.endava.budgetplanner.features.authentication.login

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.endava.budgetplanner.common.composeui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LogInActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                LogInScreenRoute(
                    onBackRoute = {},
                    onLogInRoute = {},
                    onSignUpRoute = {}
                )
            }
        }
    }
}
