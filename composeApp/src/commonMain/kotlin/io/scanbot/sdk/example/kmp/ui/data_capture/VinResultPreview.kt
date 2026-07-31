package io.scanbot.sdk.example.kmp.ui.data_capture

import androidx.compose.runtime.Composable
import io.scanbot.sdk.kmp.ui_v2.vin.configuration.VinScannerUiResult

@Composable
fun VinResultPreview(
    result: VinScannerUiResult,
    onPopBackStack: () -> Unit,
) {
    DataCapturePreview(
        title = "VIN Result",
        sections = result.toResultSections(),
        rawJson = result.toJson().prettyPrinted(),
        image = null,
        onPopBackStack = onPopBackStack
    )
}
