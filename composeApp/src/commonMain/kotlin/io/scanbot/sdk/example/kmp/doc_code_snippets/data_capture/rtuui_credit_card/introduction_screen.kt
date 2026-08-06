package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_credit_card

// @Tag("Credit Card Introduction Screen")
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.creditcard.configuration.CreditCardIntroOneSideImage
import io.scanbot.sdk.kmp.ui_v2.creditcard.configuration.CreditCardScannerScreenConfiguration

fun creditCardIntroductionScreenConfiguration(): CreditCardScannerScreenConfiguration {
    return CreditCardScannerScreenConfiguration().apply {
        introScreen.showAutomatically = true
        introScreen.title.text = "Credit Card Scanner"
        introScreen.image = CreditCardIntroOneSideImage()
        introScreen.explanation.color = ScanbotColor("#000000")
        introScreen.explanation.text =
            "To scan a credit card, hold your device over the card and align it in the finder."
        introScreen.doneButton.text = "Start Scanning"
        introScreen.doneButton.background.fillColor = ScanbotColor("#C8193C")
    }
}
// @EndTag("Credit Card Introduction Screen")

