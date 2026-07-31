package io.scanbot.sdk.example.kmp.ui.data_capture

import androidx.compose.runtime.Composable
import io.scanbot.sdk.kmp.check.CheckScanningResult
import io.scanbot.sdk.kmp.ui_v2.check.configuration.CheckScannerUiResult

@Composable
fun CheckResultPreview(
    result: CheckScannerUiResult,
    onPopBackStack: () -> Unit,
) {
    CheckResultContent(
        sections = result.toResultSections(),
        rawJson = result.toJson().prettyPrinted(),
        image = result.croppedImage,
        onPopBackStack = onPopBackStack
    )
}

@Composable
fun CheckResultPreview(
    result: CheckScanningResult,
    onPopBackStack: () -> Unit,
) {
    CheckResultContent(
        sections = result.toResultSections(),
        rawJson = result.toJson().prettyPrinted(),
        image = result.croppedImage,
        onPopBackStack = onPopBackStack
    )
}

@Composable
private fun CheckResultContent(
    sections: List<ResultSection>,
    rawJson: String,
    image: io.scanbot.sdk.kmp.image.ImageRef?,
    onPopBackStack: () -> Unit,
) {
    DataCapturePreview(
        title = "Check Result",
        sections = sections,
        rawJson = rawJson,
        image = image,
        onPopBackStack = onPopBackStack
    )
}