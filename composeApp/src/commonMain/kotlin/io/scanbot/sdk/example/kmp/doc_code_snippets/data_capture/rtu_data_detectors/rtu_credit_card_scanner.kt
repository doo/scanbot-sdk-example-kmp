package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtu_data_detectors

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.common.configuration.StatusBarMode
import io.scanbot.sdk.kmp.ui_v2.common.configuration.TopBarMode
import io.scanbot.sdk.kmp.ui_v2.creditcard.configuration.CreditCardScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.creditcard.configuration.CreditCardScannerUiResult

// @Tag("RTU Credit Card Scanner")
fun startRtuCreditCardScanner(
    onResultHandler: (CreditCardScannerUiResult) -> Unit,
    onErrorHandler: (Throwable) -> Unit,
) {
    // Always make sure you have a valid license on runtime via ScanbotSDK.getLicenseInfo()
    val configuration = CreditCardScannerScreenConfiguration().apply {
        // GRADIENT and HIDDEN modes are deprecated since v10.0.0. Use SOLID with a fully opaque background color instead.
        topBar.mode = TopBarMode.SOLID
        topBar.statusBarMode = StatusBarMode.LIGHT
        topBar.cancelButton.text = "Cancel"
    }
    // Configure other parameters as needed.

    ScanbotSDK.creditCard.startScanner(
        configuration = configuration,
        onResult = { result ->
            result.onSuccess(onResultHandler).onFailure(onErrorHandler)
        },
    )
}
// @EndTag("RTU Credit Card Scanner")
