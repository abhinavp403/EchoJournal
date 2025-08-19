package dev.abhinav.echojournal.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.abhinav.echojournal.echos.presentation.create_echo.CreateEchoRoot
import dev.abhinav.echojournal.echos.presentation.echos.EchosRoot
import dev.abhinav.echojournal.echos.presentation.settings.SettingsRoot
import dev.abhinav.echojournal.echos.presentation.util.toCreateEchoRoute

@Composable
fun NavigationRoot(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.Echos
    ) {
        composable<NavigationRoute.Echos> {
            EchosRoot(
                onNavigateToCreateEcho = { details ->
                    navController.navigate(details.toCreateEchoRoute())
                },
                onNavigateToSettings = {
                    navController.navigate(NavigationRoute.Settings)
                }
            )
        }

        composable<NavigationRoute.CreateEcho> {
            CreateEchoRoot(
                onConfirmLeave = navController::navigateUp
            )
        }

        composable<NavigationRoute.Settings> {
            SettingsRoot(
                onGoBack = navController::navigateUp
            )
        }
    }
}