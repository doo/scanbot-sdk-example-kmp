package io.scanbot.sdk.example.kmp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import io.scanbot.sdk.example.kmp.ui.MenuScreen
import io.scanbot.sdk.example.kmp.ui.barcode.BarcodePreviewScreen
import io.scanbot.sdk.example.kmp.ui.barcode.BarcodeUseCasesScreen
import io.scanbot.sdk.example.kmp.ui.custom.BarcodeCustomUIScreen
import io.scanbot.sdk.example.kmp.ui.document.DocumentPreviewScreen
import io.scanbot.sdk.example.kmp.ui.document.DocumentUseCasesScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Route.Menu::class, Route.Menu.serializer())
                    subclass(Route.BarcodeUseCases::class, Route.BarcodeUseCases.serializer())
                    subclass(Route.DocumentUseCases::class, Route.DocumentUseCases.serializer())
                    subclass(Route.BarcodePreview::class, Route.BarcodePreview.serializer())
                    subclass(Route.DocumentPreview::class, Route.DocumentPreview.serializer())
                    subclass(Route.BarcodeCustomUI::class, Route.BarcodeCustomUI.serializer())
                }
            }
        },
        Route.Menu,
    )

    val currentRoute = backStack.last() as Route

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(currentRoute.title)
                },
                navigationIcon = {
                    if (backStack.size > 1) {
                        TextButton(
                            onClick = { backStack.removeLast() },
                            contentPadding = PaddingValues(start = 16.dp)
                        ) {
                            Text("<-")
                        }
                    }
                }
            )
        }
    ) { padding ->
        NavDisplay(
            modifier = Modifier.padding(padding),
            backStack = backStack,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
            ),
            entryProvider = entryProvider {
                entry<Route.Menu> {
                    MenuScreen(
                        navigateToBarcodeUseCases = { backStack.add(Route.BarcodeUseCases) },
                        navigateToDocumentUseCases = { backStack.add(Route.DocumentUseCases) }
                    )
                }
                entry<Route.BarcodeUseCases> {
                    BarcodeUseCasesScreen(
                        onResultPreview = { backStack.add(Route.BarcodePreview(it)) }
                    )
                }
                entry<Route.DocumentUseCases> {
                    DocumentUseCasesScreen(
                        onResultPreview = { backStack.add(Route.DocumentPreview(it)) }
                    )
                }
                entry<Route.BarcodePreview> {
                    BarcodePreviewScreen(it.resultJson)
                }
                entry<Route.DocumentPreview> {
                    DocumentPreviewScreen(it.resultJson)
                }
                entry<Route.BarcodeCustomUI> {
                    BarcodeCustomUIScreen()
                }
            }
        )
    }
}