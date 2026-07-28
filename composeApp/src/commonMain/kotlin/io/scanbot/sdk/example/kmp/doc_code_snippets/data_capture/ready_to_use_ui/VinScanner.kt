package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ready_to_use_ui

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("VIN Scanner")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.common.configuration.FinderCorneredStyle
import io.scanbot.sdk.kmp.ui_v2.common.configuration.StatusBarMode
import io.scanbot.sdk.kmp.ui_v2.common.configuration.TopBarMode
import io.scanbot.sdk.kmp.ui_v2.vin.configuration.VinIntroDefaultImage
import io.scanbot.sdk.kmp.ui_v2.vin.configuration.VinScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.vin.configuration.VinScannerUiResult

fun rtuUiVinScannerUseCase(): VinScannerScreenConfiguration {
    return VinScannerScreenConfiguration().apply {
        palette.sbColorPrimary = ScanbotColor("#C8193C")
        palette.sbColorOnPrimary = ScanbotColor("#FFFFFF")

        localization.topBarTitle = "VIN Scanner"
        localization.topUserGuidance = "Locate the VIN you are looking for"
        localization.finderViewUserGuidance = "Scanning for VIN..."

        introScreen.showAutomatically = true
        introScreen.title.text = "How to scan a VIN"
        introScreen.image = VinIntroDefaultImage()
        introScreen.explanation.text =
            "Align the complete vehicle identification number inside the finder."
        introScreen.doneButton.text = "Start Scanning"
        introScreen.doneButton.background.fillColor = ScanbotColor("#C8193C")

        topBar.mode = TopBarMode.GRADIENT
        topBar.statusBarMode = StatusBarMode.LIGHT
        topBar.cancelButton.text = "Cancel"
        topBar.cancelButton.foreground.color = ScanbotColor("#C8193C")

        topUserGuidance.visible = true
        topUserGuidance.title.text = "Locate the VIN you are looking for"
        finderViewUserGuidance.title.text = "Scanning for VIN..."

        viewFinder.style = FinderCorneredStyle(
            cornerRadius = 8.0,
            strokeWidth = 2.0
        )

        actionBar.flashButton.visible = true
        actionBar.flashButton.backgroundColor = ScanbotColor("#C8193C")
        actionBar.flashButton.foregroundColor = ScanbotColor("#FFFFFF")
        actionBar.zoomButton.visible = true
        actionBar.flipCameraButton.visible = false

        scannerConfiguration.optimizeSingleLine = true
        scannerConfiguration.extractVINFromBarcode = true
    }
}

fun startVinScanner(
    onResultHandler: (VinScannerUiResult) -> Unit,
    onErrorHandler: (Throwable) -> Unit
) {
    ScanbotSDK.vin.startScanner(
        configuration = rtuUiVinScannerUseCase(),
        onResult = { result ->
            result.onSuccess(onResultHandler).onFailure(onErrorHandler)
        }
    )
}
// @EndTag("VIN Scanner")
