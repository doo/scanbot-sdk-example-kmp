package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_check

// @Tag("Check User Guidance")
import io.scanbot.sdk.kmp.ui_v2.check.configuration.CheckScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor

fun checkUserGuidanceConfiguration(): CheckScannerScreenConfiguration {
    return CheckScannerScreenConfiguration().apply {
        topUserGuidance.visible = true
        topUserGuidance.title.text = "Customized title"
        topUserGuidance.title.color = ScanbotColor("#000000")
        topUserGuidance.background.fillColor = ScanbotColor("#C8193C")

        scanStatusUserGuidance.title.text = "Customized title"
        scanStatusUserGuidance.title.color = ScanbotColor("#000000")
        scanStatusUserGuidance.background.fillColor = ScanbotColor("#C8193C")
    }
}
// @EndTag("Check User Guidance")

