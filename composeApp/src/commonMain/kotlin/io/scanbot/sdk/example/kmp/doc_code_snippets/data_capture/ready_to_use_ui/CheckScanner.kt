package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ready_to_use_ui

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("Check Scanner")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.check.configuration.CheckIntroDefaultImage
import io.scanbot.sdk.kmp.ui_v2.check.configuration.CheckScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.check.configuration.CheckScannerUiResult
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.common.configuration.FinderCorneredStyle
import io.scanbot.sdk.kmp.ui_v2.common.configuration.StatusBarMode
import io.scanbot.sdk.kmp.ui_v2.common.configuration.TopBarMode

fun rtuUiCheckScannerUseCase(): CheckScannerScreenConfiguration {
    return CheckScannerScreenConfiguration().apply {
        palette.sbColorPrimary = ScanbotColor("#C8193C")
        palette.sbColorOnPrimary = ScanbotColor("#FFFFFF")

        localization.topBarTitle = "Check Scanner"
        localization.topUserGuidance = "Fit the complete check into the frame"
        localization.completionOverlaySuccessMessage = "Check scanned"

        introScreen.showAutomatically = true
        introScreen.title.text = "How to scan a check"
        introScreen.image = CheckIntroDefaultImage()
        introScreen.explanation.text =
            "Hold your device over the check and align it with the finder."
        introScreen.doneButton.text = "Start Scanning"
        introScreen.doneButton.background.fillColor = ScanbotColor("#C8193C")

        topBar.mode = TopBarMode.GRADIENT
        topBar.statusBarMode = StatusBarMode.LIGHT
        topBar.cancelButton.text = "Cancel"

        topUserGuidance.visible = true
        topUserGuidance.title.text = "Fit the complete check into the frame"
        scanStatusUserGuidance.title.text = "Scanning check..."

        viewFinder.overlayColor = ScanbotColor("#C8193C")
        viewFinder.style = FinderCorneredStyle(
            cornerRadius = 8.0,
            strokeWidth = 2.0
        )
        exampleOverlayVisible = true

        actionBar.flashButton.visible = true
        actionBar.flashButton.backgroundColor = ScanbotColor("#C8193C")
        actionBar.flashButton.foregroundColor = ScanbotColor("#FFFFFF")
        actionBar.zoomButton.visible = true
        actionBar.flipCameraButton.visible = false
    }
}

fun startCheckScanner(
    onResultHandler: (CheckScannerUiResult) -> Unit,
    onErrorHandler: (Throwable) -> Unit
) {
    ScanbotSDK.check.startScanner(
        configuration = rtuUiCheckScannerUseCase(),
        onResult = { result ->
            result.onSuccess(onResultHandler).onFailure(onErrorHandler)
        }
    )
}
// @EndTag("Check Scanner")
