package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_vin

// @Tag("VIN Action Bar")
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.vin.configuration.VinScannerScreenConfiguration

fun vinActionBarConfiguration(): VinScannerScreenConfiguration {
    return VinScannerScreenConfiguration().apply {
        actionBar.flashButton.visible = true
        actionBar.flashButton.backgroundColor = ScanbotColor("#C8193C")
        actionBar.flashButton.foregroundColor = ScanbotColor("#FFFFFF")

        actionBar.zoomButton.visible = true
        actionBar.zoomButton.backgroundColor = ScanbotColor("#C8193C")
        actionBar.zoomButton.foregroundColor = ScanbotColor("#FFFFFF")

        actionBar.flipCameraButton.visible = false
    }
}
// @EndTag("VIN Action Bar")

