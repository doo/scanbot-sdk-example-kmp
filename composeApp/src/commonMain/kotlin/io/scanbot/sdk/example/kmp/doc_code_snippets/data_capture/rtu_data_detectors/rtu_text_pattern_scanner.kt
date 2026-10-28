package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtu_data_detectors

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.textpattern.PatternContentValidator
import io.scanbot.sdk.kmp.textpattern.PatternGrammar
import io.scanbot.sdk.kmp.ui_v2.textpattern.configuration.TextPatternScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.textpattern.configuration.TextPatternScannerUiResult

// @Tag("RTU Text Pattern Scanner")
fun startRtuTextPatternScanner(
    onResultHandler: (TextPatternScannerUiResult) -> Unit,
    onErrorHandler: (Throwable) -> Unit,
) {
    // Always make sure you have a valid license on runtime via ScanbotSDK.getLicenseInfo()
    val configuration = TextPatternScannerScreenConfiguration().apply {
        topUserGuidance.visible = true
        topUserGuidance.title.text = "Customized title"
        scannerConfiguration.validator = PatternContentValidator(
            pattern = "^[a-zA-Z]",
            patternGrammar = PatternGrammar.REGEX,
            matchSubstring = true,
        )
    }
    // Configure other parameters as needed.

    ScanbotSDK.textPattern.startScanner(
        configuration = configuration,
        onResult = { result ->
            result.onSuccess(onResultHandler).onFailure(onErrorHandler)
        },
    )
}
// @EndTag("RTU Text Pattern Scanner")
