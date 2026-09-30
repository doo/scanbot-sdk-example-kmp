package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_check

// @Tag("Check Introduction Screen")
import io.scanbot.sdk.kmp.ui_v2.check.configuration.CheckIntroDefaultImage
import io.scanbot.sdk.kmp.ui_v2.check.configuration.CheckScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor

fun checkIntroductionScreenConfiguration(): CheckScannerScreenConfiguration {
    return CheckScannerScreenConfiguration().apply {
        introScreen.showAutomatically = true
        introScreen.title.text = "Check Scanner"
        introScreen.image = CheckIntroDefaultImage()
        introScreen.explanation.color = ScanbotColor("#000000")
        introScreen.explanation.text =
            "Quickly and securely scan your checks by holding your device over the check."
        introScreen.doneButton.text = "Start Scanning"
        introScreen.doneButton.background.fillColor = ScanbotColor("#C8193C")
    }
}
// @EndTag("Check Introduction Screen")

