package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_dde

// @Tag("DDE Launching The Scanner")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.documentdata.configuration.DocumentDataExtractorScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.documentdata.configuration.DocumentDataExtractorUiResult

fun startDocumentDataExtractor(
    onResultHandler: (DocumentDataExtractorUiResult) -> Unit,
    onErrorHandler: (Throwable) -> Unit
) {
    val configuration = DocumentDataExtractorScreenConfiguration()
    ScanbotSDK.documentDataExtractor.startExtractorScreen(
        configuration = configuration,
        onResult = { result ->
            result.onSuccess(onResultHandler).onFailure(onErrorHandler)
        }
    )
}
// @EndTag("DDE Launching The Scanner")

