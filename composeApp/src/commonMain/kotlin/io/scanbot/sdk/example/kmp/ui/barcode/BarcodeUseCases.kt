package io.scanbot.sdk.example.kmp.ui.barcode

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.scanbot.sdk.example.kmp.snippets.barcode.scanner.common_use_cases.startArOverlayScanning
import io.scanbot.sdk.example.kmp.snippets.barcode.scanner.common_use_cases.startFindAndPickScanning
import io.scanbot.sdk.example.kmp.snippets.barcode.scanner.common_use_cases.startMappingItemScanning
import io.scanbot.sdk.example.kmp.snippets.barcode.scanner.common_use_cases.startMultiScanning
import io.scanbot.sdk.example.kmp.snippets.barcode.scanner.common_use_cases.startSingleScanning
import io.scanbot.sdk.example.kmp.ui.common.MenuItem
import io.scanbot.sdk.example.kmp.ui.common.TopBar

@Composable
fun BarcodeUseCasesScreen(
    onResultPreview: (String) -> Unit,
    onPopBackStack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(title = "Barcode Use cases", showBackButton = true, onPopBackStack)
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MenuItem("Single Scan with confirmation dialog", { startSingleScanning { onResultPreview(it) }})
            MenuItem("Multiple Scan", { startMultiScanning { onResultPreview(it) }})
            MenuItem("Find and Pick", { startFindAndPickScanning { onResultPreview(it) }})
            MenuItem("Multiple Scan With AR Overlay", { startArOverlayScanning { onResultPreview(it) }})
            MenuItem("Multiple Scan with Info Mapping", { startMappingItemScanning { onResultPreview(it) }})
        }
    }
}
