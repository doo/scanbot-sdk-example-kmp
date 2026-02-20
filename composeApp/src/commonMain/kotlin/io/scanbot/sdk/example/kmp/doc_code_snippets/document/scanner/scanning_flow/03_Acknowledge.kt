package io.scanbot.sdk.example.kmp.doc_code_snippets.document.scanner.scanning_flow

// @Tag("Acknowledge")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow

fun acknowledgeFlowConfig(): DocumentScanningFlow {
    // Create the default configuration object.
    val configuration = DocumentScanningFlow()

    return configuration
}

fun startScanningWithAcknowledgeFlow() =
    ScanbotSDK.document.startScanner(
        configuration = acknowledgeFlowConfig(),
        onResult = { result ->
            result.onSuccess { TODO("Handle scanned document result") }
            result.onFailure { TODO("Handle error") }
        }
    )
// @EndTag("Acknowledge")