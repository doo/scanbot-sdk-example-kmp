package io.scanbot.sdk.example.kmp.ui.data_capture

import androidx.compose.runtime.Composable
import io.scanbot.sdk.kmp.documentdata.DocumentDataExtractionResult
import io.scanbot.sdk.kmp.ui_v2.documentdata.configuration.DocumentDataExtractorUiResult

@Composable
fun DocumentDataResultPreview(
    result: DocumentDataExtractorUiResult,
    onPopBackStack: () -> Unit,
) {
    DocumentDataResultContent(
        sections = result.toResultSections(),
        rawJson = result.toJson().prettyPrinted(),
        image = result.croppedImage,
        onPopBackStack = onPopBackStack
    )
}

@Composable
fun DocumentDataResultPreview(
    result: DocumentDataExtractionResult,
    onPopBackStack: () -> Unit,
) {
    DocumentDataResultContent(
        sections = result.toResultSections(),
        rawJson = result.toJson().prettyPrinted(),
        image = result.croppedImage,
        onPopBackStack = onPopBackStack
    )
}

@Composable
private fun DocumentDataResultContent(
    sections: List<ResultSection>,
    rawJson: String,
    image: io.scanbot.sdk.kmp.image.ImageRef?,
    onPopBackStack: () -> Unit,
) {
    DataCapturePreview(
        title = "Document Data Result",
        sections = sections,
        rawJson = rawJson,
        image = image,
        onPopBackStack = onPopBackStack
    )
}
