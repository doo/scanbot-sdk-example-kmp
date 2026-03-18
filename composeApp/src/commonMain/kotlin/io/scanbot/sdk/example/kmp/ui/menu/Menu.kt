package io.scanbot.sdk.example.kmp.ui.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
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
import io.scanbot.sdk.example.kmp.ui.common.Footer
import io.scanbot.sdk.example.kmp.ui.common.LicenseGuard
import io.scanbot.sdk.example.kmp.ui.common.LicenseInfoDialog
import io.scanbot.sdk.example.kmp.ui.common.MenuItem
import io.scanbot.sdk.example.kmp.ui.common.TopBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun MenuScreen(
    navigateToBarcodeUseCases: () -> Unit,
    navigateToDocumentUseCases: () -> Unit,
    navigateToBarcodeCustomUI: () -> Unit
) {
    var showLicenseDialog by rememberSaveable { mutableStateOf(false) }

    val factory: PermissionsControllerFactory = rememberPermissionsControllerFactory()
    val controller: PermissionsController =
        remember(factory) { factory.createPermissionsController() }
    val coroutineScope: CoroutineScope = rememberCoroutineScope()

    BindEffect(controller)

    LicenseGuard { checkLicense ->
        Scaffold(topBar = {
            TopBar(title = "Scanbot SDK KMP Example")
        }, bottomBar = {
            Footer()
        }) { paddingValues ->
            Column(
                modifier = Modifier.padding(paddingValues).fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MenuItem("Barcode Use Cases", {
                    checkLicense {
                        navigateToBarcodeUseCases()
                    }
                })

                MenuItem("Document Use Cases", {
                    checkLicense {
                        navigateToDocumentUseCases()
                    }
                })

                // TODO Yurii: should we move this in Barcode Use Cases section ?
                MenuItem("Barcode Custom UI", {
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
                })

                Spacer(modifier = Modifier.weight(1f))
                MenuItem("View License Info", { showLicenseDialog = true })
            }

            if (showLicenseDialog) {
                LicenseInfoDialog(onDismiss = { showLicenseDialog = false })
            }
        }
    }
}