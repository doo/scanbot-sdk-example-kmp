package io.scanbot.sdk.example.kmp.ui.custom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashOff
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.scanbot.sdk.compose.bundle.classic.BarcodeCameraConfiguration
import io.scanbot.sdk.compose.bundle.classic.BarcodeOverlayTextFormat
import io.scanbot.sdk.compose.bundle.classic.FinderViewConfiguration
import io.scanbot.sdk.compose.bundle.classic.SelectionOverlay
import io.scanbot.sdk.compose.bundle.classic.ui.BarcodeScannerView
import io.scanbot.sdk.example.kmp.ui.common.BarcodeItemCard
import io.scanbot.sdk.example.kmp.ui.common.TopBar
import io.scanbot.sdk.kmp.barcode.BarcodeItem
import io.scanbot.sdk.kmp.barcode.BarcodeScannerConfiguration
import io.scanbot.sdk.kmp.barcode.BarcodeScannerEngineMode
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor

@Composable
fun BarcodeCustomUIScreen(
    onPopBackStack: () -> Unit,
) {
    var barcodes by remember {
        mutableStateOf<List<BarcodeItem>>(emptyList())
    }
    var flashEnabled by remember { mutableStateOf(false) }
    var detectionEnabled by remember { mutableStateOf(true) }
    var showPolygon by remember { mutableStateOf(true) }

    val selectionOverlay = remember(showPolygon) {
        SelectionOverlay(
            overlayEnabled = showPolygon,
            textFormat = BarcodeOverlayTextFormat.CODE,
        )
    }

    val finderConfiguration = remember {
        FinderViewConfiguration(
            enabled = true,
            lineWidth = 3,
            lineColor = ScanbotColor("#2196F3"),
            backgroundColor = ScanbotColor("#33000000"),
        )
    }

    val scannerConfiguration = remember {
        BarcodeScannerConfiguration(
            engineMode = BarcodeScannerEngineMode.NEXT_GEN,
            returnBarcodeImage = true
        )
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Barcode Custom UI",
                showBackButton = true,
                onPopBackStack = onPopBackStack,
                actions = {
                    IconButton(
                        onClick = { flashEnabled = !flashEnabled }
                    ) {
                        Icon(
                            imageVector = if (flashEnabled)
                                Icons.Default.FlashOn
                            else
                                Icons.Default.FlashOff,
                            contentDescription = null
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            BarcodeScannerView(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                configuration = BarcodeCameraConfiguration(
                    detectionEnabled = detectionEnabled,
                    flashEnabled = flashEnabled,
                    cameraZoomFactor = 0.01f,
                    scannerConfiguration = scannerConfiguration,
                    overlayConfiguration = selectionOverlay,
                    finderConfiguration = finderConfiguration
                ),
                onBarcodesDetected = { detected ->
                    barcodes = detected
                    return@BarcodeScannerView null
                }
            )

            if (barcodes.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    itemsIndexed(barcodes) { index, barcode ->
                        Text(
                            text = "Barcode №${index + 1}",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        BarcodeItemCard(barcode)

                        Spacer(Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

