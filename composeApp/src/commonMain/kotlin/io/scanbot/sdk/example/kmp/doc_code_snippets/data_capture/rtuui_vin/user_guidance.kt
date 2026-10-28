package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_vin

// @Tag("VIN User Guidance")
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.vin.configuration.VinScannerScreenConfiguration

fun vinUserGuidanceConfiguration(): VinScannerScreenConfiguration {
    return VinScannerScreenConfiguration().apply {
        topUserGuidance.visible = true
        topUserGuidance.title.text = "Locate the VIN you are looking for"
        topUserGuidance.title.color = ScanbotColor("#000000")
        topUserGuidance.background.fillColor = ScanbotColor("#C8193C")

        finderViewUserGuidance.title.text = "Scanning for VIN..."
        finderViewUserGuidance.title.color = ScanbotColor("#000000")
        finderViewUserGuidance.background.fillColor = ScanbotColor("#C8193C")
    }
}
// @EndTag("VIN User Guidance")

