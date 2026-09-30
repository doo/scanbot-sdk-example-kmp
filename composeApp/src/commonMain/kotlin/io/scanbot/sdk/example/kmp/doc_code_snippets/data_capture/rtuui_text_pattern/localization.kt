package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_text_pattern

// @Tag("Text Pattern Localization")
import io.scanbot.sdk.kmp.ui_v2.textpattern.configuration.TextPatternScannerScreenConfiguration

fun textPatternLocalizationConfiguration(): TextPatternScannerScreenConfiguration {
    return TextPatternScannerScreenConfiguration().apply {
        localization.topBarTitle = "Text Pattern Scanner"
        localization.topUserGuidance = "Localized topUserGuidance"
        localization.finderViewUserGuidance = "Localized finderViewUserGuidance"
        localization.cameraPermissionCloseButton = "Localized cameraPermissionCloseButton"
        localization.completionOverlaySuccessMessage = "Localized completionOverlaySuccessMessage"
    }
}
// @EndTag("Text Pattern Localization")

