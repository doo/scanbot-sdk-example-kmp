package io.scanbot.sdk.example.kmp.snippets.barcode.scanner.common_use_cases

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.CollapsedVisibleHeight
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.MultipleBarcodesScanningMode
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.MultipleScanningMode
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.SheetMode
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor

fun rtuUiMutliScanningUseCase(): BarcodeScannerScreenConfiguration {
    // Create the default configuration object.
    val configuration = BarcodeScannerScreenConfiguration()

    // Initialize the use case for multiple scanning.
    val scanningMode = MultipleScanningMode()

    // Set the counting mode.
    scanningMode.mode = MultipleBarcodesScanningMode.COUNTING

    // Set the sheet mode for the barcodes preview.
    scanningMode.sheet.mode = SheetMode.COLLAPSED_SHEET

    // Set the height for the collapsed sheet.
    scanningMode.sheet.collapsedVisibleHeight = CollapsedVisibleHeight.LARGE

    // Enable manual count change.
    scanningMode.sheetContent.manualCountChangeEnabled = true

    // Set the delay before same barcode counting repeat.
    scanningMode.countingRepeatDelay = 1000

    // Configure the submit button.
    scanningMode.sheetContent.submitButton.text = "Submit"
    scanningMode.sheetContent.submitButton.foreground.color =
        ScanbotColor("#000000")

    // Configure other parameters, pertaining to multiple-scanning mode as needed.

    configuration.useCase = scanningMode

    // Configure other parameters as needed.

    return configuration
}

fun startMultiScanning(
    onResultHandler: (barcodeResult: String) -> Unit
) {
    ScanbotSDK.barcode.startScanner(
        configuration = rtuUiMutliScanningUseCase(),
        onResult = { result ->
            result.onSuccess {
                onResultHandler(it.toJson().toString())
            }
        }
    )
}