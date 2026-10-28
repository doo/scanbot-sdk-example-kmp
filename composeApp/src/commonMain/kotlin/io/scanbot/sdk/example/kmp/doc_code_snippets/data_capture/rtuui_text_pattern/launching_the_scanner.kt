package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_text_pattern

// @Tag("Text Pattern Launching The Scanner")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.textpattern.configuration.TextPatternScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.textpattern.configuration.TextPatternScannerUiResult

fun startTextPatternScanner(
    onResultHandler: (TextPatternScannerUiResult) -> Unit,
    onErrorHandler: (Throwable) -> Unit
) {
    val configuration = TextPatternScannerScreenConfiguration()
    ScanbotSDK.textPattern.startScanner(
        configuration = configuration,
        onResult = { result ->
            result.onSuccess(onResultHandler).onFailure(onErrorHandler)
        }
    )
}
// @EndTag("Text Pattern Launching The Scanner")

