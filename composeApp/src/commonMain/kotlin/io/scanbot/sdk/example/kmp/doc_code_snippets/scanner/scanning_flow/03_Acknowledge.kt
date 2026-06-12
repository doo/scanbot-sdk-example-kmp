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
        /**
         * Set the acknowledgment mode
         * Modes:
         *  - UNACCEPTABLE_QUALITY: The acknowledgment screen will only be shown when the quality of a scanned page is unacceptable.
         *                          The quality threshold is determined by the document quality analyzer parameters.
         *  - ALWAYS: The acknowledgment screen will always be shown after each snap, regardless of the scanned page's quality.
         *  - NONE: The acknowledgment screen will be disabled, in effect never shown.
         */
        screens.camera.acknowledgement.acknowledgementMode =
            AcknowledgementMode.UNACCEPTABLE_QUALITY
        /** Set the background color for the acknowledgment screen. */
        screens.camera.acknowledgement.backgroundColor = ScanbotColor("#EFEFEF")
        /**
         * You can also configure the buttons in the bottom bar of the acknowledgment screen.
         * e.g To force the user to retake, if the captured document is not OK.
         */
        screens.camera.acknowledgement.bottomBar.retakeButton.visible = false
        /** Hide the titles of the buttons. */
        screens.camera.acknowledgement.bottomBar.acceptWhenAcceptableButton.title.visible = false
        screens.camera.acknowledgement.bottomBar.proceedAnywayButton.unacceptableQuality.title.visible =
            false
        screens.camera.acknowledgement.bottomBar.proceedAnywayButton.documentNotFound.title.visible =
            false
        screens.camera.acknowledgement.bottomBar.proceedAnywayButton.uncertainQuality.title.visible =
            false
        screens.camera.acknowledgement.bottomBar.retakeButton.title.visible = false
        /** Configure the acknowledgment screen's hint message which is shown. */
        screens.camera.acknowledgement.documentNotFoundWarning.title.text = "No document found";
        screens.camera.acknowledgement.unacceptableQualityWarning.title.text =
            "Document quality is unacceptable";
        screens.camera.acknowledgement.uncertainQualityWarning.title.text =
            "Document quality is unacceptable";
    }

    return configuration
}

fun startScanningWithAcknowledgeFlow() = ScanbotSDK.document.startScanner(
    configuration = acknowledgeFlowConfig(), onResult = { result ->
        result.onSuccess { TODO("Handle scanned document result") }
        result.onFailure { TODO("Handle error") }
    })
// @EndTag("Acknowledge")