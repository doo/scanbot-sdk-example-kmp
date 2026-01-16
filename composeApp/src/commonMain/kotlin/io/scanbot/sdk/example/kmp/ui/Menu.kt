package io.scanbot.sdk.example.kmp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.scanbot.sdk.example.kmp.ui.components.LicenseInfoDialog
import io.scanbot.sdk.example.kmp.ui.components.MenuItem

@Composable
fun MenuScreen(
    navigateToBarcodeUseCases: () -> Unit,
    navigateToDocumentUseCases: () -> Unit
) {
    var showLicenseDialog by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuItem("Barcode SDK Menu", navigateToBarcodeUseCases)
        MenuItem("Document SDK Menu", navigateToDocumentUseCases)
        MenuItem("Barcode Custom UI") {}

        Spacer(modifier = Modifier.weight(1f))

        MenuItem("License Info") { showLicenseDialog = true }
        MenuItem("3rd-party Libs & Licenses") {}
    }

    if (showLicenseDialog) {
        LicenseInfoDialog(onDismiss = { showLicenseDialog = false })
    }
}
