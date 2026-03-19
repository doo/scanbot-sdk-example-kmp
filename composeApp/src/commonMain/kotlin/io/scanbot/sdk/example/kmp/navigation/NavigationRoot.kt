package io.scanbot.sdk.example.kmp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import io.scanbot.sdk.example.kmp.ui.barcode.BarcodeCustomUIScreen
import io.scanbot.sdk.example.kmp.ui.menu.MenuScreen
import io.scanbot.sdk.example.kmp.ui.barcode.BarcodePreviewScreen
import io.scanbot.sdk.example.kmp.ui.barcode.BarcodeUseCasesScreen
import io.scanbot.sdk.example.kmp.ui.document.DocumentPagePreviewScreen
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
            )
        }

        composable<Route.BarcodeUseCases> {
            BarcodeUseCasesScreen(
                onResultPreview = { result ->
                    navController.navigate(Route.BarcodePreview(result.toJsonString()))
                },
                navigateToBarcodeCustomUI = { navController.navigate(Route.BarcodeCustomUI) },
                onPopBackStack = onPopBackStack
            )
        }

        composable<Route.DocumentUseCases> {
            DocumentUseCasesScreen(
                onResultPreview = { documentData ->
                    navController.navigate(Route.DocumentPreview(documentData.uuid))
                }, onPopBackStack = onPopBackStack
            )
        }

        composable<Route.BarcodePreview> { backStackEntry ->
            val screen: Route.BarcodePreview = backStackEntry.toRoute()
            BarcodePreviewScreen(
                resultJson = screen.barcodeJson, onPopBackStack = onPopBackStack
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

        composable<Route.BarcodeCustomUI> {
            BarcodeCustomUIScreen(
                onPopBackStack = onPopBackStack
            )
        }
    }
}