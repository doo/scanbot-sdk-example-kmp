package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_check

// @Tag("Check Launching The Scanner")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.check.configuration.CheckScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.check.configuration.CheckScannerUiResult

fun startCheckScanner(
    onResultHandler: (CheckScannerUiResult) -> Unit,
    onErrorHandler: (Throwable) -> Unit
) {
    val configuration = CheckScannerScreenConfiguration()
    ScanbotSDK.check.startScanner(
        configuration = configuration,
        onResult = { result ->
            result.onSuccess(onResultHandler).onFailure(onErrorHandler)
        }
    )
}
// @EndTag("Check Launching The Scanner")

