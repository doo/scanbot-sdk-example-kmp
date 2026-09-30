package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_mrz

// @Tag("MRZ Introduction Screen")
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.mrz.configuration.MrzIntroDefaultImage
import io.scanbot.sdk.kmp.ui_v2.mrz.configuration.MrzScannerScreenConfiguration

fun mrzIntroductionScreenConfiguration(): MrzScannerScreenConfiguration {
    return MrzScannerScreenConfiguration().apply {
        introScreen.showAutomatically = true
        introScreen.title.text = "MRZ Scanner"
        introScreen.image = MrzIntroDefaultImage()
        introScreen.explanation.color = ScanbotColor("#000000")
        introScreen.explanation.text =
            "Hold the camera over the document and align the machine-readable zone."
        introScreen.doneButton.text = "Start Scanning"
        introScreen.doneButton.background.fillColor = ScanbotColor("#C8193C")
    }
}
// @EndTag("MRZ Introduction Screen")

