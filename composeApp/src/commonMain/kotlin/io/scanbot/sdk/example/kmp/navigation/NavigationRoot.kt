package io.scanbot.sdk.example.kmp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.scanbot.sdk.example.kmp.ui.menu.MenuScreen
import io.scanbot.sdk.example.kmp.ui.barcode.BarcodePreviewScreen
import io.scanbot.sdk.example.kmp.ui.barcode.BarcodeUseCasesScreen
import io.scanbot.sdk.example.kmp.ui.custom.BarcodeCustomUIScreen
import io.scanbot.sdk.example.kmp.ui.document.DocumentPreviewScreen
import io.scanbot.sdk.example.kmp.ui.document.DocumentUseCasesScreen

@Composable
fun NavigationRoot() {
    val navController = rememberNavController()
    val onPopBackStack: () -> Unit = { navController.popBackStack() }

    NavHost(
        navController = navController,
        startDestination = Route.Menu
    ) {
        composable<Route.Menu> {
            MenuScreen(
                navigateToBarcodeUseCases = { navController.navigate(Route.BarcodeUseCases) },
                navigateToDocumentUseCases = { navController.navigate(Route.DocumentUseCases) },
                navigateToBarcodeCustomUI = { navController.navigate(Route.BarcodeCustomUI) }
            )
        }

        composable<Route.BarcodeUseCases> {
            BarcodeUseCasesScreen(
                onResultPreview = { barcodeJson ->
                    navController.navigate(Route.BarcodePreview(barcodeJson))
                },
                onPopBackStack = onPopBackStack
            )
        }

        composable<Route.DocumentUseCases> {
            DocumentUseCasesScreen(
                onResultPreview = { documentDataJson ->
                    navController.navigate(Route.DocumentPreview(documentDataJson))
                },
                onPopBackStack = onPopBackStack
            )
        }

        composable<Route.BarcodePreview> { backStackEntry ->
            val screen: Route.BarcodePreview = backStackEntry.toRoute()
            BarcodePreviewScreen(
                resultJson  = screen.barcodeJson,
                onPopBackStack = onPopBackStack
            )
        }

        composable<Route.DocumentPreview> { backStackEntry ->
            val screen: Route.DocumentPreview = backStackEntry.toRoute()
            DocumentPreviewScreen(
                resultJson  = screen.documentDataJson,
                onPopBackStack = onPopBackStack
            )
        }

        composable<Route.BarcodeCustomUI> {
            BarcodeCustomUIScreen(
                onPopBackStack = onPopBackStack
            )
        }
    }
}