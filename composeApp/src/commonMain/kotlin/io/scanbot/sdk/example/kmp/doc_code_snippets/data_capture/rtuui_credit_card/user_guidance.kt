package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_credit_card

// @Tag("Credit Card User Guidance")
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.creditcard.configuration.CreditCardScannerScreenConfiguration

fun creditCardUserGuidanceConfiguration(): CreditCardScannerScreenConfiguration {
    return CreditCardScannerScreenConfiguration().apply {
        topUserGuidance.visible = true
        topUserGuidance.title.text = "Customized title"
        topUserGuidance.title.color = ScanbotColor("#000000")
        topUserGuidance.background.fillColor = ScanbotColor("#C8193C")

        scanStatusUserGuidance.statesTitles.noCardFound = "No card found."
        scanStatusUserGuidance.statesTitles.scanningProgress = "Scanning..."
        scanStatusUserGuidance.title.text = "Customized title"
        scanStatusUserGuidance.title.color = ScanbotColor("#000000")
        scanStatusUserGuidance.background.fillColor = ScanbotColor("#C8193C")
    }
}
// @EndTag("Credit Card User Guidance")

