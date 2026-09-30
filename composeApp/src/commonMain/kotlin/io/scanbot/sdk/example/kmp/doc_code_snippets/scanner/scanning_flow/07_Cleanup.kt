package io.scanbot.sdk.example.kmp.doc_code_snippets.scanner.scanning_flow

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("Cleanup")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow

fun cleanupFlowConfig(): DocumentScanningFlow {
    // Create the default configuration object.
    val configuration = DocumentScanningFlow().apply {

        // Reveal the 'Clean up' button in the review screen's toolbar. It is hidden by default.
        screens.review.toolbar.documentCleanupButton.barButton.visible = true

        // Configure the toolbar buttons on the cleanup screen. They are enabled by default.
        screens.cleanup.toolbar.undoButton.visible = true
        screens.cleanup.toolbar.redoButton.visible = true

        // Configure various colors.
        appearance.topBarBackgroundColor = ScanbotColor("#C8193C")
        screens.cleanup.topBarConfirmButton.foreground.color = ScanbotColor("#FFFFFF")

        // Customize a UI element's text.
        localization.documentCleanupTopBarCancelButtonTitle = "Cancel"
    }

    return configuration
}

fun startScanningWithCleanupFlow() = ScanbotSDK.document.startScanner(
    configuration = cleanupFlowConfig(), onResult = {
        it.onSuccess { TODO("Handle scanned document result") }
        it.onFailure { TODO("Handle error") }
    })
// @EndTag("Cleanup")
