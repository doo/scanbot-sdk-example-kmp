package io.scanbot.sdk.example.kmp.doc_code_snippets.document.scanner.scanning_flow

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow

fun reviewFlowConfig(): DocumentScanningFlow {

// Create the default configuration object.
    val configuration = DocumentScanningFlow()

// Configure the review screen.
    val reviewScreen = configuration.screens.review
    reviewScreen.apply {
        enabled = true
        zoomButton.visible = false
        bottomBar.addButton.visible = false
        bottomBar.retakeButton.visible = true
        bottomBar.retakeButton.title.color = ScanbotColor("000000")
    }

// Configure the reorder pages screen.
    val reorderPagesScreen = configuration.screens.reorderPages
    reorderPagesScreen.apply {
        guidance.visible = false
        topBarTitle.text = "Reorder Pages Screen"
    }

// Configure the cropping screen.
    configuration.screens.cropping
        .bottomBar
        .resetButton
        .visible = false

    return configuration

}

fun startScanningWithReviewFlow() =
    ScanbotSDK.document.startScanner(
        configuration = reviewFlowConfig(),
        onResult = {
            it.onSuccess { TODO("Handle scanned document result") }
            it.onFailure { TODO("Handle error") }
        }
    )