package io.scanbot.sdk.example.kmp.doc_code_snippets.document.scanner.scanning_flow

// @Tag("Reorder")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow

fun reorderFlowConfig(): DocumentScanningFlow {
    // Create the default configuration object.
    val configuration = DocumentScanningFlow()

    // Retrieve the instance of the reorder pages configuration from the main configuration object.
    val reorderScreenConfiguration = configuration.screens.reorderPages

    // Hide the guidance view.
    reorderScreenConfiguration.guidance.visible = false

    // Set the title for the reorder screen.
    reorderScreenConfiguration.topBarTitle.text = "Reorder Pages Screen"

    // Set the title for the guidance.
    reorderScreenConfiguration.guidance.title.text = "Reorder"

    // Set the color for the page number text.
    reorderScreenConfiguration.pageTextStyle.color = ScanbotColor("#000000")

    return configuration
}

fun startScanningWithReorderFlow() =
    ScanbotSDK.document.startScanner(
        configuration = reorderFlowConfig(),
        onResult = {
            it.onSuccess { TODO("Handle scanned document result") }
            it.onFailure { TODO("Handle error") }
        }
    )
// @EndTag("Reorder")