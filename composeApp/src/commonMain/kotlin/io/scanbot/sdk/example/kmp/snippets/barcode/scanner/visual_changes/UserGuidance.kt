package io.scanbot.sdk.example.kmp.snippets.barcode.scanner.visual_changes

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor

fun rtuUiUserGuidanceConfig(): BarcodeScannerScreenConfiguration {
    // Create the default configuration object.
    val configuration = BarcodeScannerScreenConfiguration()

    // Hide/unhide the user guidance.
    configuration.userGuidance.visible = true

    // Configure the title.
    configuration.userGuidance.title.text = "Move the finder over a barcode"
    configuration.userGuidance.title.color = ScanbotColor("#FFFFFF")

    // Configure the background.
    configuration.userGuidance.background.fillColor = ScanbotColor("#0000007A")

    // Configure other parameters as needed.

    return configuration
}

fun startUserGuidanceScanning() {
    ScanbotSDK.barcode.startScanner(
        configuration = rtuUiUserGuidanceConfig(),
        onResult = {
            it.onSuccess { TODO("Handle scanned result") }
            it.onFailure { TODO("Handle error") }
        }
    )
}
