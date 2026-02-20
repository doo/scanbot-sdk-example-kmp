package io.scanbot.sdk.example.kmp.doc_code_snippets.barcode.scanner.visual_changes

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration

fun rtuUiLocalizationConfig(): BarcodeScannerScreenConfiguration {
    // Create the default configuration object.
    val configuration = BarcodeScannerScreenConfiguration()

    // Configure localization parameters.
    configuration.localization.barcodeInfoMappingErrorStateCancelButton = "Custom Cancel title"
    configuration.localization.cameraPermissionCloseButton = "Custom Close title"

    // Configure other strings as needed.

    return configuration
}

fun startLocalizationScanning() {
    ScanbotSDK.barcode.startScanner(
        configuration = rtuUiLocalizationConfig(),
        onResult = {
            it.onSuccess { TODO("Handle scanned result") }
            it.onFailure { TODO("Handle error") }
        }
    )
}
