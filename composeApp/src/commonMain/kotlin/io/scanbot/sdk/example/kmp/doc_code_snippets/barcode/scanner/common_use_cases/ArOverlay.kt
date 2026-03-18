package io.scanbot.sdk.example.kmp.doc_code_snippets.barcode.scanner.common_use_cases

// @Tag("AR Overlay")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerUiResult
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.CollapsedVisibleHeight
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.MultipleBarcodesScanningMode
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.MultipleScanningMode
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.SheetMode

fun rtuUiArOverlayScanningUseCase(): BarcodeScannerScreenConfiguration {
    // Create configuration object.
    val configuration = BarcodeScannerScreenConfiguration().apply {

        // Initialize the use case for multiple scanning.
        useCase = MultipleScanningMode().apply {
            mode = MultipleBarcodesScanningMode.UNIQUE
            sheet.mode = SheetMode.COLLAPSED_SHEET
            sheet.collapsedVisibleHeight = CollapsedVisibleHeight.SMALL

            // Configure AR Overlay.
            arOverlay.visible = true
            arOverlay.automaticSelectionEnabled = false
        }

        // Configure other parameters as needed.
    }
    return configuration;
}

fun startArOverlayScanning(
    onResultHandler: (barcodeResult: BarcodeScannerUiResult) -> Unit, onErrorHandler: (error: Throwable) -> Unit
) {
    ScanbotSDK.barcode.startScanner(
        configuration = rtuUiArOverlayScanningUseCase(), onResult = { result ->
            result.onSuccess {
                onResultHandler(it)
            }.onFailure {
                onErrorHandler(it)
            }
        })
}
// @EndTag("AR Overlay")
