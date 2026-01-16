package io.scanbot.sdk.example.kmp.snippets.document.scanner.visual_changes

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.documentqualityanalyzer.DocumentQuality
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.document.configuration.AcknowledgementMode
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow

fun localizationConfigurationScanning(): DocumentScanningFlow {
    // Create the default configuration object.
    val configuration = DocumentScanningFlow()


    return configuration
}

fun startScanningWithLocalizationConfig() {
    ScanbotSDK.document.startScanner(
        configuration = localizationConfigurationScanning(),
        onResult = {
            it.onSuccess { TODO("Handle scanned document result") }
            it.onFailure { TODO("Handle error") }
        }
    )
}