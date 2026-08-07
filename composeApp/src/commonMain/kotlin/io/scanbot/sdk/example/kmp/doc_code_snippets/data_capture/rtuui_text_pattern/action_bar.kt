package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_text_pattern

// @Tag("Text Pattern Action Bar")
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.textpattern.configuration.TextPatternScannerScreenConfiguration

fun textPatternActionBarConfiguration(): TextPatternScannerScreenConfiguration {
    return TextPatternScannerScreenConfiguration().apply {
        actionBar.flashButton.visible = true
        actionBar.flashButton.backgroundColor = ScanbotColor("#C8193C")
        actionBar.flashButton.foregroundColor = ScanbotColor("#FFFFFF")

        actionBar.zoomButton.visible = true
        actionBar.zoomButton.backgroundColor = ScanbotColor("#C8193C")
        actionBar.zoomButton.foregroundColor = ScanbotColor("#FFFFFF")

        actionBar.flipCameraButton.visible = false
    }
}
// @EndTag("Text Pattern Action Bar")

