package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ready_to_use_ui

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("MRZ Scanner")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.common.configuration.FinderCorneredStyle
import io.scanbot.sdk.kmp.ui_v2.common.configuration.StatusBarMode
import io.scanbot.sdk.kmp.ui_v2.common.configuration.TopBarMode
import io.scanbot.sdk.kmp.ui_v2.mrz.configuration.MrzIntroDefaultImage
import io.scanbot.sdk.kmp.ui_v2.mrz.configuration.MrzScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.mrz.configuration.MrzScannerUiResult
import io.scanbot.sdk.kmp.ui_v2.mrz.configuration.ThreeLineMrzFinderLayoutPreset

fun rtuUiMrzScannerUseCase(): MrzScannerScreenConfiguration {
    return MrzScannerScreenConfiguration().apply {
        palette.sbColorPrimary = ScanbotColor("#C8193C")
        palette.sbColorOnPrimary = ScanbotColor("#FFFFFF")

        localization.topBarTitle = "MRZ Scanner"
        localization.topUserGuidance = "Scan the machine-readable zone"
        localization.finderViewUserGuidance = "Align the MRZ inside the finder"

        introScreen.showAutomatically = true
        introScreen.title.text = "How to scan an MRZ"
        introScreen.image = MrzIntroDefaultImage()
        introScreen.explanation.text =
            "Hold the camera over the document and align the machine-readable zone."
        introScreen.doneButton.text = "Start Scanning"
        introScreen.doneButton.background.fillColor = ScanbotColor("#C8193C")

        topBar.mode = TopBarMode.GRADIENT
        topBar.statusBarMode = StatusBarMode.LIGHT
        topBar.cancelButton.text = "Cancel"

        topUserGuidance.visible = true
        topUserGuidance.title.text = "Scan the machine-readable zone"
        finderViewUserGuidance.visible = true
        finderViewUserGuidance.title.text = "Align the MRZ inside the finder"

        mrzExampleOverlay = ThreeLineMrzFinderLayoutPreset(
            mrzTextLine1 = "I<USA2342353464<<<<<<<<<<<<<<<",
            mrzTextLine2 = "9602300M2904076USA<<<<<<<<<<<2",
            mrzTextLine3 = "SMITH<<JACK<<<<<<<<<<<<<<<<<<<"
        )
        viewFinder.style = FinderCorneredStyle(
            cornerRadius = 8.0,
            strokeWidth = 2.0
        )

        actionBar.flashButton.visible = true
        actionBar.flashButton.backgroundColor = ScanbotColor("#C8193C")
        actionBar.flashButton.foregroundColor = ScanbotColor("#FFFFFF")
        actionBar.zoomButton.visible = true
        actionBar.flipCameraButton.visible = false

        scannerConfiguration.returnCrops = true
    }
}

fun startMrzScanner(
    onResultHandler: (MrzScannerUiResult) -> Unit,
    onErrorHandler: (Throwable) -> Unit
) {
    ScanbotSDK.mrz.startScanner(
        configuration = rtuUiMrzScannerUseCase(),
        onResult = { result ->
            result.onSuccess(onResultHandler).onFailure(onErrorHandler)
        }
    )
}
// @EndTag("MRZ Scanner")
