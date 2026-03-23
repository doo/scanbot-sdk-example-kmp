package io.scanbot.sdk.example.kmp.doc_code_snippets.document.scanner.scanning_flow

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("Review Screen")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow

fun reviewFlowConfig(): DocumentScanningFlow {
    // Create the default configuration object.
    val configuration = DocumentScanningFlow().apply {

        // Configure the review screen.
        screens.review.apply {
            enabled = true
            zoomButton.visible = false
            bottomBar.addButton.visible = false
            bottomBar.retakeButton.visible = true
            bottomBar.retakeButton.title.color = ScanbotColor("#000000")
        }

        // Configure the reorder pages screen.
        screens.reorderPages.apply {
            guidance.visible = false
            topBarTitle.text = "Reorder Pages Screen"
        }

        // Configure the cropping screen.
        screens.cropping.bottomBar.resetButton.visible = false
    }

    return configuration

}

fun startScanningWithReviewFlow() = ScanbotSDK.document.startScanner(
    configuration = reviewFlowConfig(), onResult = {
        it.onSuccess { TODO("Handle scanned document result") }
        it.onFailure { TODO("Handle error") }
    })
// @EndTag("Review Screen")