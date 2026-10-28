package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_vin

// @Tag("VIN Launching The Scanner")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.vin.configuration.VinScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.vin.configuration.VinScannerUiResult

fun startVinScanner(
    onResultHandler: (VinScannerUiResult) -> Unit,
    onErrorHandler: (Throwable) -> Unit
) {
    val configuration = VinScannerScreenConfiguration()
    ScanbotSDK.vin.startScanner(
        configuration = configuration,
        onResult = { result ->
            result.onSuccess(onResultHandler).onFailure(onErrorHandler)
        }
    )
}
// @EndTag("VIN Launching The Scanner")

