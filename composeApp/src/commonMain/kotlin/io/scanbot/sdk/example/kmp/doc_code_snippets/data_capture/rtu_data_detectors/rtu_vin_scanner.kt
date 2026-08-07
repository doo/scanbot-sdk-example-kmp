package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtu_data_detectors

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.vin.configuration.VinScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.vin.configuration.VinScannerUiResult

// @Tag("RTU VIN Scanner")
fun startRtuVinScanner(
    onResultHandler: (VinScannerUiResult) -> Unit,
    onErrorHandler: (Throwable) -> Unit,
) {
    // Always make sure you have a valid license on runtime via ScanbotSDK.getLicenseInfo()
    val configuration = VinScannerScreenConfiguration().apply {
        introScreen.explanation.text =
            "Quickly and securely scan the VIN by holding your device over the vehicle identification number or vehicle identification barcode\n" +
                "The scanner will guide you to the optimal scanning position." +
                "Once the scan is complete, your VIN details will automatically be extracted and processed."
        introScreen.doneButton.text = "Start Scanning"
        introScreen.doneButton.background.fillColor = ScanbotColor("#C8193C")
    }
    // Configure other parameters as needed.

    ScanbotSDK.vin.startScanner(
        configuration = configuration,
        onResult = { result ->
            result.onSuccess(onResultHandler).onFailure(onErrorHandler)
        },
    )
}
// @EndTag("RTU VIN Scanner")
