package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ready_to_use_ui

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("Credit Card Scanner")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.common.configuration.FinderCorneredStyle
import io.scanbot.sdk.kmp.ui_v2.common.configuration.StatusBarMode
import io.scanbot.sdk.kmp.ui_v2.common.configuration.TopBarMode
import io.scanbot.sdk.kmp.ui_v2.creditcard.configuration.CreditCardIntroOneSideImage
import io.scanbot.sdk.kmp.ui_v2.creditcard.configuration.CreditCardScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.creditcard.configuration.CreditCardScannerUiResult

fun rtuUiCreditCardScannerUseCase(): CreditCardScannerScreenConfiguration {
    return CreditCardScannerScreenConfiguration().apply {
        palette.sbColorPrimary = ScanbotColor("#C8193C")
        palette.sbColorOnPrimary = ScanbotColor("#FFFFFF")

        localization.topBarTitle = "Credit Card Scanner"
        localization.topUserGuidance = "Fit the card into the frame"
        localization.creditCardUserGuidanceNoCardFound = "No card found"

        introScreen.showAutomatically = true
        introScreen.title.text = "How to scan a credit card"
        introScreen.image = CreditCardIntroOneSideImage()
        introScreen.explanation.text =
            "Hold your device over the card and align its front side with the finder."
        introScreen.doneButton.text = "Start Scanning"
        introScreen.doneButton.background.fillColor = ScanbotColor("#C8193C")

        topBar.mode = TopBarMode.GRADIENT
        topBar.statusBarMode = StatusBarMode.LIGHT
        topBar.cancelButton.text = "Cancel"

        topUserGuidance.visible = true
        topUserGuidance.title.text = "Fit the card into the frame"
        scanStatusUserGuidance.statesTitles.noCardFound = "No card found"
        scanStatusUserGuidance.statesTitles.scanningProgress = "Scanning..."

        exampleOverlayVisible = true
        viewFinder.style = FinderCorneredStyle(
            cornerRadius = 8.0,
            strokeWidth = 2.0
        )

        actionBar.flashButton.visible = true
        actionBar.flashButton.backgroundColor = ScanbotColor("#C8193C")
        actionBar.flashButton.foregroundColor = ScanbotColor("#FFFFFF")
        actionBar.zoomButton.visible = true
        actionBar.flipCameraButton.visible = false

        scannerConfiguration.requireExpiryDate = true
        scannerConfiguration.requireCardholderName = true
        scannerConfiguration.returnCreditCardImage = true
    }
}

fun startCreditCardScanner(
    onResultHandler: (CreditCardScannerUiResult) -> Unit,
    onErrorHandler: (Throwable) -> Unit
) {
    ScanbotSDK.creditCard.startScanner(
        configuration = rtuUiCreditCardScannerUseCase(),
        onResult = { result ->
            result.onSuccess(onResultHandler).onFailure(onErrorHandler)
        }
    )
}
// @EndTag("Credit Card Scanner")
