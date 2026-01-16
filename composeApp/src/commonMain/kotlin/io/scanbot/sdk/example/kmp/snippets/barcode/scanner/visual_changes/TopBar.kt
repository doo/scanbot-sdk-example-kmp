package io.scanbot.sdk.example.kmp.snippets.barcode.scanner.visual_changes

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.common.configuration.StatusBarMode
import io.scanbot.sdk.kmp.ui_v2.common.configuration.TopBarMode

fun rtuUiTopBarConfiguration(): BarcodeScannerScreenConfiguration {
    // Create the default configuration object.
    val configuration = BarcodeScannerScreenConfiguration()

    // Configure the top bar.

    // Set the top bar mode.
    configuration.topBar.mode = TopBarMode.GRADIENT

    // Set the background color which will be used as a gradient.
    configuration.topBar.backgroundColor = ScanbotColor("#C8193C")

    // Configure the status bar look. If visible - select DARK or LIGHT according to your app's theme color.
    configuration.topBar.statusBarMode = StatusBarMode.HIDDEN

    // Configure the Cancel button.
    configuration.topBar.cancelButton.text = "Cancel"
    configuration.topBar.cancelButton.foreground.color = ScanbotColor("#FFFFFF")

    // Configure other parameters as needed.

    return configuration
}

fun startTopBarScanning() {
    ScanbotSDK.barcode.startScanner(
        configuration = rtuUiTopBarConfiguration(),
        onResult = {
            it.onSuccess { TODO("Handle scanned result") }
            it.onFailure { TODO("Handle error") }
        }
    )
}
