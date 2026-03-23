package io.scanbot.sdk.example.kmp.doc_code_snippets.document.scanner.scanning_flow

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("Reorder")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow

fun reorderFlowConfig(): DocumentScanningFlow {
    // Create the default configuration object.
    val configuration = DocumentScanningFlow().apply {

        // Hide the guidance view.
        screens.reorderPages.guidance.visible = false

        // Set the title for the reorder screen.
        screens.reorderPages.topBarTitle.text = "Reorder Pages Screen"

        // Set the title for the guidance.
        screens.reorderPages.guidance.title.text = "Reorder"

        // Set the color for the page number text.
        screens.reorderPages.pageTextStyle.color = ScanbotColor("#000000")
    }

    return configuration
}

fun startScanningWithReorderFlow() = ScanbotSDK.document.startScanner(
    configuration = reorderFlowConfig(), onResult = {
        it.onSuccess { TODO("Handle scanned document result") }
        it.onFailure { TODO("Handle error") }
    })
// @EndTag("Reorder")