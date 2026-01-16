package io.scanbot.sdk.example.kmp.ui.barcode

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.scanbot.sdk.example.kmp.snippets.barcode.scanner.common_use_cases.startArOverlayScanning
import io.scanbot.sdk.example.kmp.snippets.barcode.scanner.common_use_cases.startFindAndPickScanning
import io.scanbot.sdk.example.kmp.snippets.barcode.scanner.common_use_cases.startMappingItemScanning
import io.scanbot.sdk.example.kmp.snippets.barcode.scanner.common_use_cases.startMultiScanning
import io.scanbot.sdk.example.kmp.snippets.barcode.scanner.common_use_cases.startSingleScanning
import io.scanbot.sdk.example.kmp.ui.components.MenuItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BarcodeUseCasesScreen(
    onResultPreview: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MenuItem("Single Scan with confirmation dialog") {
            startSingleScanning { onResultPreview(it) }
        }
        MenuItem("Multiple Scan") {
            startMultiScanning { onResultPreview(it) }
        }
        MenuItem("Find and Pick") {
            startFindAndPickScanning { onResultPreview(it) }
        }
        MenuItem("Multiple Scan With AR Overlay") {
            startArOverlayScanning { onResultPreview(it) }
        }
        MenuItem("Multiple Scan with Info Mapping") {
            startMappingItemScanning { onResultPreview(it) }
        }
    }
}
