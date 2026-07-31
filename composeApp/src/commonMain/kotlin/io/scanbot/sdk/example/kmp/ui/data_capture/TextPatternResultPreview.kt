package io.scanbot.sdk.example.kmp.ui.data_capture

import androidx.compose.runtime.Composable
import io.scanbot.sdk.kmp.ui_v2.textpattern.configuration.TextPatternScannerUiResult

@Composable
fun TextPatternResultPreview(
    result: TextPatternScannerUiResult,
    onPopBackStack: () -> Unit,
) {
    DataCapturePreview(
        title = "Text Pattern Result",
        sections = result.toResultSections(),
        rawJson = result.toJson().prettyPrinted(),
        image = null,
        onPopBackStack = onPopBackStack
    )
}
