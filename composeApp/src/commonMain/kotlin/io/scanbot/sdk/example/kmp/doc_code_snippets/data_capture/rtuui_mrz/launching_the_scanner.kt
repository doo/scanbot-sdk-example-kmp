package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_mrz

// @Tag("MRZ Launching The Scanner")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.mrz.configuration.MrzScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.mrz.configuration.MrzScannerUiResult

fun startMrzScanner(
    onResultHandler: (MrzScannerUiResult) -> Unit,
    onErrorHandler: (Throwable) -> Unit
) {
    val configuration = MrzScannerScreenConfiguration()
    ScanbotSDK.mrz.startScanner(
        configuration = configuration,
        onResult = { result ->
            result.onSuccess(onResultHandler).onFailure(onErrorHandler)
        }
    )
}
// @EndTag("MRZ Launching The Scanner")

