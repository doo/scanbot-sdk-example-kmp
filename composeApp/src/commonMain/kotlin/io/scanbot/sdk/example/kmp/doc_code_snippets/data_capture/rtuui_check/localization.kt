package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_check

// @Tag("Check Localization")
import io.scanbot.sdk.kmp.ui_v2.check.configuration.CheckScannerScreenConfiguration

fun checkLocalizationConfiguration(): CheckScannerScreenConfiguration {
    return CheckScannerScreenConfiguration().apply {
        localization.topBarTitle = "Check Scanner"
        localization.topUserGuidance = "Localized topUserGuidance"
        localization.cameraPermissionCloseButton = "Localized cameraPermissionCloseButton"
        localization.completionOverlaySuccessMessage = "Localized completionOverlaySuccessMessage"
    }
}
// @EndTag("Check Localization")

