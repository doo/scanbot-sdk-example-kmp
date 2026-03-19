package io.scanbot.sdk.example.kmp.ui.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.scanbot.sdk.example.kmp.ui.common.Footer
import io.scanbot.sdk.example.kmp.ui.common.InfoDialog
import io.scanbot.sdk.example.kmp.ui.common.LicenseGuard
import io.scanbot.sdk.example.kmp.ui.common.LicenseInfoDialog
import io.scanbot.sdk.example.kmp.ui.common.MenuItem
import io.scanbot.sdk.example.kmp.ui.common.TopBar
import io.scanbot.sdk.kmp.ScanbotSDK

@Composable
fun MenuScreen(
    navigateToBarcodeUseCases: () -> Unit,
    navigateToDocumentUseCases: () -> Unit,
) {
    var showLicenseDialog by rememberSaveable { mutableStateOf(false) }
    var showCleanupConfirmation by rememberSaveable { mutableStateOf(false) }
    var cleanupStorageResult by rememberSaveable { mutableStateOf<String?>(null) }

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
                MenuItem("Barcode Use Cases") {
                    checkLicense {
                        navigateToBarcodeUseCases()
                    }
                }

                MenuItem("Document Use Cases") {
                    checkLicense {
                        navigateToDocumentUseCases()
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
                MenuItem("Clean up Storage") {
                    showCleanupConfirmation = true
                }

                MenuItem("View License Info") { showLicenseDialog = true }
            }

            if (showCleanupConfirmation) {
                AlertDialog(
                    onDismissRequest = { showCleanupConfirmation = false },
                    title = { Text("Clean up Storage") },
                    text = { Text("Are you sure you want to clean up the storage?") },
                    confirmButton = {
                        TextButton(onClick = {
                            showCleanupConfirmation = false
                            ScanbotSDK.cleanupStorage().fold(
                                onSuccess = { cleanupStorageResult = "Storage cleaned up successfully." },
                                onFailure = { cleanupStorageResult = it.message }
                            )
                        }) {
                            Text("Clean up", color = MaterialTheme.colorScheme.error)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showCleanupConfirmation = false }) {
                            Text("Cancel")
                        }
                    }
                )
            }


            if (showLicenseDialog) {
                LicenseInfoDialog(onDismiss = { showLicenseDialog = false })
            }

            cleanupStorageResult?.let {
                InfoDialog(
                    title = "Clean up Storage",
                    text = it,
                    onDismiss = { cleanupStorageResult = null }
                )
            }
        }
    }
}