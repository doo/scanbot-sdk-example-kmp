package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_dde

// @Tag("DDE Localization")
import io.scanbot.sdk.kmp.ui_v2.documentdata.configuration.DocumentDataExtractorScreenConfiguration

fun documentDataLocalizationConfiguration(): DocumentDataExtractorScreenConfiguration {
    return DocumentDataExtractorScreenConfiguration().apply {
        localization.topBarTitle = "Document Data Extractor"
        localization.topUserGuidance = "Localized topUserGuidance"
        localization.cameraPermissionCloseButton = "Localized cameraPermissionCloseButton"
        localization.completionOverlaySuccessMessage = "Localized completionOverlaySuccessMessage"
    }
}
// @EndTag("DDE Localization")

