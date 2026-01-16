package io.scanbot.sdk.example.kmp.ui.custom

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
import androidx.compose.ui.unit.dp
import io.scanbot.sdk.compose.bundle.classic.BarcodeCameraConfiguration
import io.scanbot.sdk.compose.bundle.classic.SelectionOverlay
import io.scanbot.sdk.compose.bundle.classic.ui.BarcodeScannerView

@Composable
fun BarcodeCustomUIScreen() {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(32.dp))
            Text(
                text = "Barcode Custom UI",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            BarcodeScannerView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                configuration = BarcodeCameraConfiguration(
                    overlayConfiguration = SelectionOverlay(
                        overlayEnabled = true,
                    )
                ),
                onBarcodesDetected = { result -> null },
            )
        }
    }
}