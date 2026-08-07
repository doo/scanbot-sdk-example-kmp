package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_check

// @Tag("Check Action Bar")
import io.scanbot.sdk.kmp.ui_v2.check.configuration.CheckScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor

fun checkActionBarConfiguration(): CheckScannerScreenConfiguration {
    return CheckScannerScreenConfiguration().apply {
        actionBar.flashButton.visible = true
        actionBar.flashButton.backgroundColor = ScanbotColor("#C8193C")
        actionBar.flashButton.foregroundColor = ScanbotColor("#FFFFFF")

        actionBar.zoomButton.visible = true
        actionBar.zoomButton.backgroundColor = ScanbotColor("#C8193C")
        actionBar.zoomButton.foregroundColor = ScanbotColor("#FFFFFF")

        actionBar.flipCameraButton.visible = false
    }
}
// @EndTag("Check Action Bar")

