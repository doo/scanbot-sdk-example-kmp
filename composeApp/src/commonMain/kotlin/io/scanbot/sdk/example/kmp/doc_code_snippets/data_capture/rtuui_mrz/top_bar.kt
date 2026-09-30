package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_mrz

// @Tag("MRZ Top Bar")
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.common.configuration.StatusBarMode
import io.scanbot.sdk.kmp.ui_v2.common.configuration.TopBarMode
import io.scanbot.sdk.kmp.ui_v2.mrz.configuration.MrzScannerScreenConfiguration

fun mrzTopBarConfiguration(): MrzScannerScreenConfiguration {
    return MrzScannerScreenConfiguration().apply {
        topBar.mode = TopBarMode.SOLID
        topBar.backgroundColor = ScanbotColor("#C8193C")
        topBar.statusBarMode = StatusBarMode.LIGHT
        topBar.cancelButton.text = "Cancel"
        topBar.cancelButton.foreground.color = ScanbotColor("#FFFFFF")
    }
}
// @EndTag("MRZ Top Bar")

