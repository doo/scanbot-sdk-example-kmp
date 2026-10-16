package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_mrz

// @Tag("MRZ Localization")
import io.scanbot.sdk.kmp.ui_v2.mrz.configuration.MrzScannerScreenConfiguration

fun mrzLocalizationConfiguration(): MrzScannerScreenConfiguration {
    return MrzScannerScreenConfiguration().apply {
        localization.topBarTitle = "MRZ Scanner"
        localization.topUserGuidance = "Localized topUserGuidance"
        localization.finderViewUserGuidance = "Localized finderViewUserGuidance"
        localization.cameraPermissionCloseButton = "Localized cameraPermissionCloseButton"
        localization.completionOverlaySuccessMessage = "Localized completionOverlaySuccessMessage"
    }
}
// @EndTag("MRZ Localization")

