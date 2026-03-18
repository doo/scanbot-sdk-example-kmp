package io.scanbot.sdk.example.kmp.ui.barcode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.camera.CAMERA
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import io.scanbot.sdk.example.kmp.doc_code_snippets.barcode.scanner.common_use_cases.startArOverlayScanning
import io.scanbot.sdk.example.kmp.doc_code_snippets.barcode.scanner.common_use_cases.startFindAndPickScanning
import io.scanbot.sdk.example.kmp.doc_code_snippets.barcode.scanner.common_use_cases.startMappingItemScanning
import io.scanbot.sdk.example.kmp.doc_code_snippets.barcode.scanner.common_use_cases.startMultiScanning
import io.scanbot.sdk.example.kmp.doc_code_snippets.barcode.scanner.common_use_cases.startSingleScanning
import io.scanbot.sdk.example.kmp.ui.common.ErrorDialog
import io.scanbot.sdk.example.kmp.ui.common.LicenseGuard
import io.scanbot.sdk.example.kmp.ui.common.MenuItem
import io.scanbot.sdk.example.kmp.ui.common.TopBar
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerUiResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun BarcodeUseCasesScreen(
    onResultPreview: (BarcodeScannerUiResult) -> Unit,
    navigateToBarcodeCustomUI: () -> Unit,
    onPopBackStack: () -> Unit,
) {

    var useCaseError by remember { mutableStateOf<Throwable?>(null) }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    val factory: PermissionsControllerFactory = rememberPermissionsControllerFactory()
    val controller: PermissionsController =
        remember(factory) { factory.createPermissionsController() }

    BindEffect(controller)

    LicenseGuard { checkLicense ->
        Scaffold(
            topBar = {
                TopBar(
                    title = "Barcode Use Cases",
                    showBackButton = true,
                    onPopBackStack = onPopBackStack
                )
            },
        ) { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues).fillMaxSize().padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MenuItem(
                    "Single Scan with confirmation"
                ) {
                    checkLicense {
                        startSingleScanning(
                            onResultPreview, onErrorHandler = { useCaseError = it })
                    }
                }
                MenuItem(
                    "Multiple Scan"
                ) {
                    checkLicense {
                        startMultiScanning(
                            onResultPreview, onErrorHandler = { useCaseError = it })
                    }
                }
                MenuItem(
                    "Find and Pick"
                ) {
                    checkLicense {
                        startFindAndPickScanning(
                            onResultPreview, onErrorHandler = { useCaseError = it })
                    }
                }
                MenuItem(
                    "Multiple Scan With AR Overlay"
                ) {
                    checkLicense {
                        startArOverlayScanning(
                            onResultPreview, onErrorHandler = { useCaseError = it })
                    }
                }
                MenuItem(
                    "Multiple Scan with Info Mapping"
                ) {
                    checkLicense {
                        startMappingItemScanning(
                            onResultPreview, onErrorHandler = { useCaseError = it })
                    }
                }
                MenuItem("Barcode Custom UI") {
                    checkLicense {
                        coroutineScope.launch {
                            try {
                                if (!controller.isPermissionGranted(Permission.CAMERA)) {
                                    controller.providePermission(Permission.CAMERA)
                                }
                                navigateToBarcodeCustomUI()
                            } catch (e: Exception) {
                                // Log or handle permission error
                            }
                        }
                    }
                }
            }

            useCaseError?.let {
                ErrorDialog(message = it.message, onDismiss = { useCaseError = null })
            }
        }
    }
}
