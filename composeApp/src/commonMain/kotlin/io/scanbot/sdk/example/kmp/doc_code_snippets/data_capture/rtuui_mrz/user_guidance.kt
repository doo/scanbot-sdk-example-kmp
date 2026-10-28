package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_mrz

// @Tag("MRZ User Guidance")
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.mrz.configuration.MrzScannerScreenConfiguration

fun mrzUserGuidanceConfiguration(): MrzScannerScreenConfiguration {
    return MrzScannerScreenConfiguration().apply {
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
// @EndTag("MRZ User Guidance")

