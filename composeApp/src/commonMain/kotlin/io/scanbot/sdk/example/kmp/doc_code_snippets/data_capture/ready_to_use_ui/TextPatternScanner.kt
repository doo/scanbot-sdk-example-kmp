package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ready_to_use_ui

// @Tag("Text Pattern Scanner")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.common.configuration.FinderCorneredStyle
import io.scanbot.sdk.kmp.ui_v2.common.configuration.StatusBarMode
import io.scanbot.sdk.kmp.ui_v2.common.configuration.TopBarMode
import io.scanbot.sdk.kmp.ui_v2.textpattern.configuration.TextPatternIntroGeneralField
import io.scanbot.sdk.kmp.ui_v2.textpattern.configuration.TextPatternScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.textpattern.configuration.TextPatternScannerUiResult

fun rtuUiTextPatternScannerUseCase(): TextPatternScannerScreenConfiguration {
    return TextPatternScannerScreenConfiguration().apply {
        palette.sbColorPrimary = ScanbotColor("#C8193C")
        palette.sbColorOnPrimary = ScanbotColor("#FFFFFF")

        localization.topBarTitle = "Text Pattern Scanner"
        localization.topUserGuidance = "Locate the text you want to scan"
        localization.finderViewUserGuidance = "Align the text inside the finder"

        introScreen.showAutomatically = true
        introScreen.title.text = "How to scan a text pattern"
        introScreen.image = TextPatternIntroGeneralField()
        introScreen.explanation.text =
            "Align a single line of text inside the finder until it is recognized."
        introScreen.doneButton.text = "Start Scanning"
        introScreen.doneButton.background.fillColor = ScanbotColor("#C8193C")

        topBar.mode = TopBarMode.GRADIENT
        topBar.statusBarMode = StatusBarMode.LIGHT
        topBar.cancelButton.text = "Cancel"

        topUserGuidance.visible = true
        topUserGuidance.title.text = "Locate the text you want to scan"
        finderViewUserGuidance.visible = true
        finderViewUserGuidance.title.text = "Align the text inside the finder"

        viewFinder.style = FinderCorneredStyle(
            strokeColor = ScanbotColor("#C8193C"),
            strokeWidth = 3.0
        )

        actionBar.flashButton.visible = true
        actionBar.flashButton.backgroundColor = ScanbotColor("#C8193C")
        actionBar.flashButton.foregroundColor = ScanbotColor("#FFFFFF")
        actionBar.zoomButton.visible = true
        actionBar.flipCameraButton.visible = false

        scannerConfiguration.optimizeSingleLine = true
        scannerConfiguration.minimumNumberOfRequiredFramesWithEqualScanningResult = 2
    }
}

fun startTextPatternScanner(
    onResultHandler: (TextPatternScannerUiResult) -> Unit,
    onErrorHandler: (Throwable) -> Unit
) {
    ScanbotSDK.textPattern.startScanner(
        configuration = rtuUiTextPatternScannerUseCase(),
        onResult = { result ->
            result.onSuccess(onResultHandler).onFailure(onErrorHandler)
        }
    )
}
// @EndTag("Text Pattern Scanner")
