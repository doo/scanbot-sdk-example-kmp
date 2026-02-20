package io.scanbot.sdk.example.kmp.doc_code_snippets.barcode.scanner.common_use_cases

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.SingleScanningMode
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor

fun rtuUiSingleScanningUseCase(): BarcodeScannerScreenConfiguration {
    val configuration = BarcodeScannerScreenConfiguration()

    configuration.scannerConfiguration.returnBarcodeImage = true

    // Single-scanning mode
    val scanningMode = SingleScanningMode().apply {

        // Enable confirmation sheet
        confirmationSheetEnabled = true
        sheetColor = ScanbotColor("#FFFFFF")

        // Show barcode image preview
        barcodeImageVisible = true

        // Title config
        barcodeTitle.visible = true
        barcodeTitle.color = ScanbotColor("#000000")

        // Subtitle config
        barcodeSubtitle.visible = true
        barcodeSubtitle.color = ScanbotColor("#000000")

        // Cancel button config
        cancelButton.text = "Close"
        cancelButton.foreground.color = ScanbotColor("#C8193C")
        cancelButton.background.fillColor = ScanbotColor("#00000000")

        // Submit button config
        submitButton.text = "Submit"
        submitButton.foreground.color = ScanbotColor("#FFFFFF")
        submitButton.background.fillColor = ScanbotColor("#C8193C")
    }

    configuration.useCase = scanningMode

    return configuration
}

fun startSingleScanning(
    onResultHandler: (barcodeResult: String) -> Unit
) {
    ScanbotSDK.barcode.startScanner(
        configuration = rtuUiSingleScanningUseCase(),
        onResult = { result ->
            result.onSuccess {
                onResultHandler(it.toJson().toString())
            }
        }
    )
}