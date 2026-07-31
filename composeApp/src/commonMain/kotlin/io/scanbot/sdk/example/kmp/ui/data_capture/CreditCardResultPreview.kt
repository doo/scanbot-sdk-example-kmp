package io.scanbot.sdk.example.kmp.ui.data_capture

import androidx.compose.runtime.Composable
import io.scanbot.sdk.kmp.creditcard.CreditCardScanningResult
import io.scanbot.sdk.kmp.ui_v2.creditcard.configuration.CreditCardScannerUiResult

@Composable
fun CreditCardResultPreview(
    result: CreditCardScannerUiResult,
    onPopBackStack: () -> Unit,
) {
    CreditCardResultContent(
        sections = result.toResultSections(),
        rawJson = result.toJson().prettyPrinted(),
        image = result.creditCard?.crop,
        onPopBackStack = onPopBackStack
    )
}

@Composable
fun CreditCardResultPreview(
    result: CreditCardScanningResult,
    onPopBackStack: () -> Unit,
) {
    CreditCardResultContent(
        sections = result.toResultSections(),
        rawJson = result.toJson().prettyPrinted(),
        image = result.creditCard?.crop,
        onPopBackStack = onPopBackStack
    )
}

@Composable
private fun CreditCardResultContent(
    sections: List<ResultSection>,
    rawJson: String,
    image: io.scanbot.sdk.kmp.image.ImageRef?,
    onPopBackStack: () -> Unit,
) {
    DataCapturePreview(
        title = "Credit Card Result",
        sections = sections,
        rawJson = rawJson,
        image = image,
        onPopBackStack = onPopBackStack
    )
}
