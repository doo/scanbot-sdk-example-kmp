package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_vin

// @Tag("VIN Top Bar")
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.common.configuration.StatusBarMode
import io.scanbot.sdk.kmp.ui_v2.common.configuration.TopBarMode
import io.scanbot.sdk.kmp.ui_v2.vin.configuration.VinScannerScreenConfiguration

fun vinTopBarConfiguration(): VinScannerScreenConfiguration {
    return VinScannerScreenConfiguration().apply {
        topBar.mode = TopBarMode.SOLID
        topBar.backgroundColor = ScanbotColor("#C8193C")
        topBar.statusBarMode = StatusBarMode.LIGHT
        topBar.cancelButton.text = "Cancel"
        topBar.cancelButton.foreground.color = ScanbotColor("#FFFFFF")
    }
}
// @EndTag("VIN Top Bar")

