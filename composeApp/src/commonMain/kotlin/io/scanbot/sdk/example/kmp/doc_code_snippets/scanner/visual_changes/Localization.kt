package io.scanbot.sdk.example.kmp.doc_code_snippets.scanner.visual_changes

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("Localization")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow

fun localizationConfigurationScanning(): DocumentScanningFlow {
    // Create the default configuration object.
    val configuration = DocumentScanningFlow().apply {
        localization.cameraTopBarTitle = "document.camera.title"
        localization.reviewScreenSubmitButtonTitle = "review.submit.title"
        localization.cameraUserGuidanceNoDocumentFound = "camera.userGuidance.noDocumentFound"
        localization.cameraUserGuidanceTooDark = "camera.userGuidance.tooDark"
    }

    return configuration
}

fun startScanningWithLocalizationConfig() {
    ScanbotSDK.document.startScanner(
        configuration = localizationConfigurationScanning(), onResult = {
            it.onSuccess { TODO("Handle scanned document result") }
            it.onFailure { TODO("Handle error") }
        })
}
// @EndTag("Localization")