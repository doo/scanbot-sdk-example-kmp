package io.scanbot.sdk.example.kmp.doc_code_snippets.scanner.scanning_flow

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("Acknowledge")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.documentqualityanalyzer.DocumentQuality
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.document.configuration.AcknowledgementMode
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow

fun acknowledgeFlowConfig(): DocumentScanningFlow {
    // Create the default configuration object.
    val configuration = DocumentScanningFlow().apply {
        screens.camera.acknowledgement.apply {
            // Set the acknowledgment mode
            // Modes:
            // - `ALWAYS`: Runs the quality analyzer on the captured document and always displays the acknowledgment screen.
            // - `BAD_QUALITY`: Runs the quality analyzer and displays the acknowledgment screen only if the quality is poor.
            // - `NONE`: Skips the quality check entirely.
            acknowledgementMode = AcknowledgementMode.ALWAYS

            // Set the minimum acceptable document quality.
            // Options: excellent, good, reasonable, poor, veryPoor, or noDocument.
            minimumQuality = DocumentQuality.GOOD

            // Set the background color for the acknowledgment screen.
            backgroundColor = ScanbotColor("#EFEFEF")

            // You can also configure the buttons in the bottom bar of the acknowledgment screen.
            // e.g. To force the user to retake, if the captured document is not OK.
            bottomBar.acceptWhenNotOkButton.visible = false

            // Hide the titles of the buttons.
            bottomBar.acceptWhenNotOkButton.title.visible = false
            bottomBar.acceptWhenOkButton.title.visible = false
            bottomBar.retakeButton.title.visible = false

            // Configure the acknowledgment screen's hint message which is shown if the least acceptable quality is not met.
            badImageHint.visible = true
        }
    }
    return configuration
}

fun startScanningWithAcknowledgeFlow() = ScanbotSDK.document.startScanner(
    configuration = acknowledgeFlowConfig(), onResult = { result ->
        result.onSuccess { TODO("Handle scanned document result") }
        result.onFailure { TODO("Handle error") }
    })
// @EndTag("Acknowledge")