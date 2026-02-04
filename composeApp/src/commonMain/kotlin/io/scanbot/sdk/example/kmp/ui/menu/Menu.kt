package io.scanbot.sdk.example.kmp.ui.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.scanbot.sdk.example.kmp.ui.common.Footer
import io.scanbot.sdk.example.kmp.ui.common.LicenseInfoDialog
import io.scanbot.sdk.example.kmp.ui.common.MenuItem
import io.scanbot.sdk.example.kmp.ui.common.TopBar

@Composable
fun MenuScreen(
    navigateToBarcodeUseCases: () -> Unit,
    navigateToDocumentUseCases: () -> Unit,
    navigateToBarcodeCustomUI: () -> Unit
) {
    var showLicenseDialog by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBar(title = "Scanbot SDK KMP Example")
        },
        bottomBar = {
            Footer()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MenuItem("Barcode SDK Menu", navigateToBarcodeUseCases)
            MenuItem("Document SDK Menu", navigateToDocumentUseCases)
            MenuItem("Barcode Custom UI", navigateToBarcodeCustomUI)

            Spacer(modifier = Modifier.weight(1f))

            MenuItem("License Info", { showLicenseDialog = true })
        }

        if (showLicenseDialog) {
            LicenseInfoDialog(onDismiss = { showLicenseDialog = false })
        }
    }
}
