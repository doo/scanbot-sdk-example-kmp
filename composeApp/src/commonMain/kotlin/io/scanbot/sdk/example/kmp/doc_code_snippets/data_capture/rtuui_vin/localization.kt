package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_vin

// @Tag("VIN Localization")
import io.scanbot.sdk.kmp.ui_v2.vin.configuration.VinScannerScreenConfiguration

fun vinLocalizationConfiguration(): VinScannerScreenConfiguration {
    return VinScannerScreenConfiguration().apply {
        localization.topBarTitle = "VIN Scanner"
        localization.topUserGuidance = "Localized topUserGuidance"
        localization.cameraPermissionCloseButton = "Localized cameraPermissionCloseButton"
        localization.completionOverlaySuccessMessage = "Localized completionOverlaySuccessMessage"
    }
}
// @EndTag("VIN Localization")

