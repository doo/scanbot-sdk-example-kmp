package io.scanbot.sdk.example.kmp.doc_code_snippets.document.scanner.visual_changes

// @Tag("Localization")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow

fun localizationConfigurationScanning(): DocumentScanningFlow {
    // Create the default configuration object.
    val configuration = DocumentScanningFlow()
    configuration.localization.cameraTopBarTitle = "document.camera.title"
    configuration.localization.reviewScreenSubmitButtonTitle = "review.submit.title"
    configuration.localization.cameraUserGuidanceNoDocumentFound = "camera.userGuidance.noDocumentFound"
    configuration.localization.cameraUserGuidanceTooDark = "camera.userGuidance.tooDark"

    return configuration
}

fun startScanningWithLocalizationConfig() {
    ScanbotSDK.document.startScanner(
        configuration = localizationConfigurationScanning(),
        onResult = {
            it.onSuccess { TODO("Handle scanned document result") }
            it.onFailure { TODO("Handle error") }
        }
    )
}
// @EndTag("Localization")