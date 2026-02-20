package io.scanbot.sdk.example.kmp.doc_code_snippets.barcode.scanner.common_use_cases

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.CollapsedVisibleHeight
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.MultipleBarcodesScanningMode
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.MultipleScanningMode
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.SheetMode

fun rtuUiArOverlayScanningUseCase(): BarcodeScannerScreenConfiguration {
    // Create the default configuration object.
    val configuration = BarcodeScannerScreenConfiguration();

    // Initialize the use case for multiple scanning.
    val scanningMode = MultipleScanningMode();

    scanningMode.mode = MultipleBarcodesScanningMode.UNIQUE;
    scanningMode.sheet.mode = SheetMode.COLLAPSED_SHEET;
    scanningMode.sheet.collapsedVisibleHeight = CollapsedVisibleHeight.SMALL;
    // Configure AR Overlay.
    scanningMode.arOverlay.visible = true;
    scanningMode.arOverlay.automaticSelectionEnabled = false;

    configuration.useCase = scanningMode;

    // Configure other parameters as needed.

    return configuration;
}

fun startArOverlayScanning(
    onResultHandler: (barcodeResult: String) -> Unit
) {
    ScanbotSDK.barcode.startScanner(
        configuration = rtuUiArOverlayScanningUseCase(),
        onResult = { result ->
            result.onSuccess {
                onResultHandler(it.toJson().toString())
            }
        }
    )
}

