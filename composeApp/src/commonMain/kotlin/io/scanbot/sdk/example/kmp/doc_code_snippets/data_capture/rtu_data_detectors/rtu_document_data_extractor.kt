package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtu_data_detectors

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.documentdata.configuration.DocumentDataExtractorScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.documentdata.configuration.DocumentDataExtractorUiResult

// @Tag("RTU Document Data Extractor")
fun startRtuDocumentDataExtractor(
    onResultHandler: (DocumentDataExtractorUiResult) -> Unit,
    onErrorHandler: (Throwable) -> Unit,
) {
    // Always make sure you have a valid license on runtime via ScanbotSDK.getLicenseInfo()
    val configuration = DocumentDataExtractorScreenConfiguration()
    // Configure other parameters as needed.

    ScanbotSDK.documentDataExtractor.startExtractorScreen(
        configuration = configuration,
        onResult = { result ->
            result.onSuccess(onResultHandler).onFailure(onErrorHandler)
        },
    )
}
// @EndTag("RTU Document Data Extractor")
