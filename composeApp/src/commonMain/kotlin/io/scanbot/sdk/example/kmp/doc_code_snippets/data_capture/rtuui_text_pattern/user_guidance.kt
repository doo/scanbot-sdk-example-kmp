package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_text_pattern

// @Tag("Text Pattern User Guidance")
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.textpattern.configuration.TextPatternScannerScreenConfiguration

fun textPatternUserGuidanceConfiguration(): TextPatternScannerScreenConfiguration {
    return TextPatternScannerScreenConfiguration().apply {
        topUserGuidance.visible = true
        topUserGuidance.title.text = "Customized title"
        topUserGuidance.title.color = ScanbotColor("#000000")
        topUserGuidance.background.fillColor = ScanbotColor("#C8193C")

        finderViewUserGuidance.visible = true
        finderViewUserGuidance.title.text = "Customized title"
        finderViewUserGuidance.title.color = ScanbotColor("#000000")
        finderViewUserGuidance.background.fillColor = ScanbotColor("#C8193C")
    }
}
// @EndTag("Text Pattern User Guidance")

