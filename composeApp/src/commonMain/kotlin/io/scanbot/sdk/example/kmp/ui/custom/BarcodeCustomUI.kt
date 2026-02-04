package io.scanbot.sdk.example.kmp.ui.custom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.scanbot.sdk.compose.bundle.classic.BarcodeCameraConfiguration
import io.scanbot.sdk.compose.bundle.classic.SelectionOverlay
import io.scanbot.sdk.compose.bundle.classic.ui.BarcodeScannerView
import io.scanbot.sdk.example.kmp.ui.common.TopBar


@Composable
fun BarcodeCustomUIScreen(
    onPopBackStack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(title = "Barcode Custom UI", showBackButton = true, onPopBackStack)
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BarcodeScannerView(
                modifier = Modifier.fillMaxSize(),
                configuration = BarcodeCameraConfiguration(
                    overlayConfiguration = SelectionOverlay(
                        overlayEnabled = true,
                    )
                ),
                onBarcodesDetected = { null },
            )
        }
    }
}
