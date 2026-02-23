package io.scanbot.sdk.example.kmp.ui.barcode

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.scanbot.sdk.example.kmp.doc_code_snippets.barcode.scanner.common_use_cases.startArOverlayScanning
import io.scanbot.sdk.example.kmp.doc_code_snippets.barcode.scanner.common_use_cases.startFindAndPickScanning
import io.scanbot.sdk.example.kmp.doc_code_snippets.barcode.scanner.common_use_cases.startMappingItemScanning
import io.scanbot.sdk.example.kmp.doc_code_snippets.barcode.scanner.common_use_cases.startMultiScanning
import io.scanbot.sdk.example.kmp.doc_code_snippets.barcode.scanner.common_use_cases.startSingleScanning
import io.scanbot.sdk.example.kmp.ui.common.LicenseGuard
import io.scanbot.sdk.example.kmp.ui.common.MenuItem
import io.scanbot.sdk.example.kmp.ui.common.TopBar

@Composable
fun BarcodeUseCasesScreen(
    onResultPreview: (String) -> Unit,
    onPopBackStack: () -> Unit,
) {
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
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MenuItem("Single Scan with confirmation", { checkLicense { startSingleScanning(onResultPreview) } })
                MenuItem("Multiple Scan", { checkLicense { startMultiScanning(onResultPreview) } })
                MenuItem("Find and Pick", { checkLicense { startFindAndPickScanning(onResultPreview) } })
                MenuItem("Multiple Scan With AR Overlay", { checkLicense { startArOverlayScanning(onResultPreview) } })
                MenuItem("Multiple Scan with Info Mapping", { checkLicense { startMappingItemScanning(onResultPreview) } })
            }
        }
    }
}
