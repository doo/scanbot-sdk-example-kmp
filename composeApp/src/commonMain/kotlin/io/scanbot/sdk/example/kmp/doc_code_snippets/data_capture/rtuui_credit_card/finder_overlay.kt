package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_credit_card

// @Tag("Credit Card Finder Overlay")
import io.scanbot.sdk.kmp.ui_v2.common.configuration.FinderCorneredStyle
import io.scanbot.sdk.kmp.ui_v2.creditcard.configuration.CreditCardScannerScreenConfiguration

fun creditCardFinderOverlayConfiguration(): CreditCardScannerScreenConfiguration {
    return CreditCardScannerScreenConfiguration().apply {
        exampleOverlayVisible = true
        viewFinder.style = FinderCorneredStyle(
            cornerRadius = 8.0,
            strokeWidth = 2.0
        )
    }
}
// @EndTag("Credit Card Finder Overlay")

