package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_credit_card

// @Tag("Credit Card Launching The Scanner")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.creditcard.configuration.CreditCardScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.creditcard.configuration.CreditCardScannerUiResult

fun startCreditCardScanner(
    onResultHandler: (CreditCardScannerUiResult) -> Unit,
    onErrorHandler: (Throwable) -> Unit
) {
    val configuration = CreditCardScannerScreenConfiguration()
    ScanbotSDK.creditCard.startScanner(
        configuration = configuration,
        onResult = { result ->
            result.onSuccess(onResultHandler).onFailure(onErrorHandler)
        }
    )
}
// @EndTag("Credit Card Launching The Scanner")

