package io.scanbot.sdk.example.kmp.doc_code_snippets.document.scanner.scanning_flow

// @Tag("Crop")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow

fun cropFlowConfig(): DocumentScanningFlow {
    // Create the default configuration object.
    val configuration = DocumentScanningFlow().apply {

        // Disable the rotation feature.
        screens.cropping.bottomBar.rotateButton.visible = false

        // Configure various colors.
        appearance.topBarBackgroundColor = ScanbotColor("#C8193C")
        screens.cropping.topBarConfirmButton.foreground.color = ScanbotColor("#FFFFFF")

        // Customize a UI element's text
        localization.croppingTopBarCancelButtonTitle = "Cancel"
    }

    return configuration
}

fun startScanningWithCropFlow() = ScanbotSDK.document.startScanner(
    configuration = cropFlowConfig(), onResult = {
        it.onSuccess { TODO("Handle scanned document result") }
        it.onFailure { TODO("Handle error") }
    })
// @EndTag("Crop")