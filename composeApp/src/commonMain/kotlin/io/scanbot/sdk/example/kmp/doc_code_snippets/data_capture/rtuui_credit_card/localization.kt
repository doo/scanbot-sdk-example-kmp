package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_credit_card

// @Tag("Credit Card Localization")
import io.scanbot.sdk.kmp.ui_v2.creditcard.configuration.CreditCardScannerScreenConfiguration

fun creditCardLocalizationConfiguration(): CreditCardScannerScreenConfiguration {
    return CreditCardScannerScreenConfiguration().apply {
        localization.topBarTitle = "Credit Card Scanner"
        localization.topUserGuidance = "Localized topUserGuidance"
        localization.cameraPermissionCloseButton = "Localized cameraPermissionCloseButton"
        localization.completionOverlaySuccessMessage = "Localized completionOverlaySuccessMessage"
        localization.creditCardUserGuidanceNoCardFound = "Localized creditCardUserGuidanceNoCardFound"
    }
}
// @EndTag("Credit Card Localization")

