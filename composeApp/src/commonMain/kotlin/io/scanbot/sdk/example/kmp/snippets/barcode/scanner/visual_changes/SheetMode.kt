package io.scanbot.sdk.example.kmp.snippets.barcode.scanner.visual_changes

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.CollapsedVisibleHeight
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.MultipleScanningMode
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.SheetMode
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor

fun rtuUiMultipleScanningPreviewConfig(): BarcodeScannerScreenConfiguration {
    // Create the default configuration object.
    val config = BarcodeScannerScreenConfiguration()

    // Initialize the use case for multiple scanning.
    val scanningMode = MultipleScanningMode()

    // Set the sheet mode for the barcodes preview.
    scanningMode.sheet.mode = SheetMode.COLLAPSED_SHEET

    // Set the height for the collapsed sheet.
    scanningMode.sheet.collapsedVisibleHeight = CollapsedVisibleHeight.LARGE

    // Configure the submit button on the sheet.
    scanningMode.sheetContent.submitButton.text = "Submit"
    scanningMode.sheetContent.submitButton.foreground.color = ScanbotColor("#000000")

    // Configure localization parameters.
    config.localization.barcodeInfoMappingErrorStateCancelButton = "Custom Cancel title"
    config.localization.cameraPermissionCloseButton = "Custom Close title"
    // Configure other strings as needed.

    // Configure other parameters, pertaining to multiple-scanning mode as needed.

    config.useCase = scanningMode

    return config
}

fun startMultipleScanningPreview() {
    ScanbotSDK.barcode.startScanner(
        configuration = rtuUiMultipleScanningPreviewConfig(),
        onResult = {
            it.onSuccess { TODO("Handle scanned result") }
            it.onFailure { TODO("Handle error") }
        }
    )
}
