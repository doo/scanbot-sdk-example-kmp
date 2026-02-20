package io.scanbot.sdk.example.kmp.doc_code_snippets.barcode.scanner.common_use_cases

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.CollapsedVisibleHeight
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.ExpectedBarcode
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.FindAndPickScanningMode
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.SheetMode
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor

fun rtuUiFindAndPickScanningUseCase(): BarcodeScannerScreenConfiguration {
    // Create the default configuration object.
    val configuration = BarcodeScannerScreenConfiguration()

    // Initialize the use case for find and pick scanning.
    val scanningMode = FindAndPickScanningMode()

    // Set the sheet mode for the barcodes preview.
    scanningMode.sheet.mode = SheetMode.COLLAPSED_SHEET;

    // Enable AR Overlay
    scanningMode.arOverlay.visible = true

    // Enable/Disable the automatic selection.
    scanningMode.arOverlay.automaticSelectionEnabled = false

    // Set the height for the collapsed sheet.
    scanningMode.sheet.collapsedVisibleHeight = CollapsedVisibleHeight.LARGE;

    // Enable manual count change.
    scanningMode.sheetContent.manualCountChangeEnabled = true

    // Set the delay before same barcode counting repeat.
    scanningMode.countingRepeatDelay = 1000

    // Configure the submit button.
    scanningMode.sheetContent.submitButton.text = "Submit"
    scanningMode.sheetContent.submitButton.foreground.color =
        ScanbotColor("#000000");

    // Configure other parameters, pertaining to findAndPick-scanning mode as needed.
    // Set the expected barcodes.
    scanningMode.expectedBarcodes = listOf(
        ExpectedBarcode(
            barcodeValue = "123456",
            title = "numeric barcode",
            image = "https://avatars.githubusercontent.com/u/1454920",
            count = 4
        ),
        ExpectedBarcode(
            barcodeValue = "SCANBOT",
            title = "value barcode",
            image = "https://avatars.githubusercontent.com/u/1454920",
            count = 3
        ),
    )

    configuration.useCase = scanningMode

    // Configure other parameters as needed.

    return configuration;
}

fun startFindAndPickScanning(
    onResultHandler: (barcodeResult: String) -> Unit
) {
    ScanbotSDK.barcode.startScanner(
        configuration = rtuUiFindAndPickScanningUseCase(),
        onResult = { result ->
            result.onSuccess {
                onResultHandler(it.toJson().toString())
            }
        }
    )
}