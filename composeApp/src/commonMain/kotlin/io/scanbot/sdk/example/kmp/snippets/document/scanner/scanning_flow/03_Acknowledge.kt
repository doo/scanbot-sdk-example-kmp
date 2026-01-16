package io.scanbot.sdk.example.kmp.snippets.document.scanner.scanning_flow

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