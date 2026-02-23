package io.scanbot.sdk.example.kmp.doc_code_snippets.document.scanner.common_use_cases

// @Tag("Auto filtering")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.imageprocessing.ScanbotBinarizationFilter
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow

fun autoFilteringUseCase(): DocumentScanningFlow {
    // Create the default configuration instance
    val configuration = DocumentScanningFlow().apply {
        // Set any `ParametricFilter` type to default filter.
        outputSettings.defaultFilter = ScanbotBinarizationFilter();
    }
    return configuration
}

fun startScanningWithAutoFiltering() {
    ScanbotSDK.document.startScanner(
        configuration = rtuUiMultiPageScanningUseCase(),
        onResult = {
            it.onSuccess { TODO("Handle scanned document result") }
            it.onFailure { TODO("Handle error") }
        }
    )
}
// @EndTag("Auto filtering")