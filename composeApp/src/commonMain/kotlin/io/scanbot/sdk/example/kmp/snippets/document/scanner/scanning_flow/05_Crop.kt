package io.scanbot.sdk.example.kmp.snippets.document.scanner.scanning_flow

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow

fun cropFlowConfig(): DocumentScanningFlow {
    // Create the default configuration object.
    val configuration = DocumentScanningFlow()

    // Retrieve the instance of the crop configuration from the main configuration object.
    val cropScreenConfiguration = configuration.screens.cropping

    // Disable the rotation feature.
    cropScreenConfiguration.bottomBar.rotateButton.visible = false

    // Configure various colors.
    configuration.appearance.topBarBackgroundColor = ScanbotColor("#C8193C")
    cropScreenConfiguration.topBarConfirmButton.foreground.color = ScanbotColor("#FFFFFF")

    // Customize a UI element's text
    configuration.localization.croppingTopBarCancelButtonTitle = "Cancel"

    return configuration
}

fun startScanningWithCropFlow() =
    ScanbotSDK.document.startScanner(
        configuration = cropFlowConfig(),
        onResult = {
            it.onSuccess { TODO("Handle scanned document result") }
            it.onFailure { TODO("Handle error") }
        }
    )