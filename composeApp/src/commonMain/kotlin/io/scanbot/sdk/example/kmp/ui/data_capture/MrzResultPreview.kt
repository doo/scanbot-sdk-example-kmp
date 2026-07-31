package io.scanbot.sdk.example.kmp.ui.data_capture

import androidx.compose.runtime.Composable
import io.scanbot.sdk.kmp.mrz.MrzScannerResult
import io.scanbot.sdk.kmp.ui_v2.mrz.configuration.MrzScannerUiResult

@Composable
fun MrzResultPreview(
    result: MrzScannerUiResult,
    onPopBackStack: () -> Unit,
) {
    MrzResultContent(
        sections = result.toResultSections(),
        rawJson = result.toJson().prettyPrinted(),
        image = result.mrzDocument?.crop,
        onPopBackStack = onPopBackStack
    )
}

@Composable
fun MrzResultPreview(
    result: MrzScannerResult,
    onPopBackStack: () -> Unit,
) {
    MrzResultContent(
        sections = result.toResultSections(),
        rawJson = result.toJson().prettyPrinted(),
        image = result.document?.crop,
        onPopBackStack = onPopBackStack
    )
}

@Composable
private fun MrzResultContent(
    sections: List<ResultSection>,
    rawJson: String,
    image: io.scanbot.sdk.kmp.image.ImageRef?,
    onPopBackStack: () -> Unit,
) {
    DataCapturePreview(
        title = "MRZ Result",
        sections = sections,
        rawJson = rawJson,
        image = image,
        onPopBackStack = onPopBackStack
    )
}
