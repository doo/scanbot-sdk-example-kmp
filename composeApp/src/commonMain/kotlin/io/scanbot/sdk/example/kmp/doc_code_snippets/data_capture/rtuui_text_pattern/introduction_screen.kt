package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_text_pattern

// @Tag("Text Pattern Introduction Screen")
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.textpattern.configuration.TextPatternIntroGeneralField
import io.scanbot.sdk.kmp.ui_v2.textpattern.configuration.TextPatternScannerScreenConfiguration

fun textPatternIntroductionScreenConfiguration(): TextPatternScannerScreenConfiguration {
    return TextPatternScannerScreenConfiguration().apply {
        introScreen.showAutomatically = true
        introScreen.title.text = "Text Pattern Scanner"
        introScreen.image = TextPatternIntroGeneralField()
        introScreen.explanation.color = ScanbotColor("#000000")
        introScreen.explanation.text =
            "To scan a single line of text, align the text inside the finder until it is recognized."
        introScreen.doneButton.text = "Start Scanning"
        introScreen.doneButton.background.fillColor = ScanbotColor("#C8193C")
    }
}
// @EndTag("Text Pattern Introduction Screen")

