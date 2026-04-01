package io.scanbot.sdk.example.kmp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.scanbot.sdk.example.kmp.ui.document.DocumentPagePreviewScreen
import io.scanbot.sdk.example.kmp.ui.document.DocumentPreviewScreen
import io.scanbot.sdk.example.kmp.ui.document.DocumentUseCasesScreen

@Composable
fun NavigationRoot() {
    val navController = rememberNavController()
    val onPopBackStack: () -> Unit = { navController.popBackStack() }

    NavHost(
        navController = navController,
        startDestination = Route.DocumentUseCases
    ) {

        composable<Route.DocumentUseCases> {
            DocumentUseCasesScreen(
                onResultPreview = { documentData ->
                    navController.navigate(Route.DocumentPreview(documentData.uuid))
                }
            )
        }

        composable<Route.DocumentPreview> { backStackEntry ->
            val screen: Route.DocumentPreview = backStackEntry.toRoute()
            DocumentPreviewScreen(
                documentUuid = screen.documentUuid,
                navigateToPagePreview = { documentUuid, pageUuid ->
                    navController.navigate(Route.DocumentPagePreview(documentUuid, pageUuid))
                },
                onPopBackStack = onPopBackStack
            )
        }

        composable<Route.DocumentPagePreview> { backStackEntry ->
            val screen: Route.DocumentPagePreview = backStackEntry.toRoute()
            DocumentPagePreviewScreen(
                documentUuid = screen.documentUuid,
                pageUuid = screen.pageUuid,
                onPopBackStack = onPopBackStack
            )
        }
    }
}