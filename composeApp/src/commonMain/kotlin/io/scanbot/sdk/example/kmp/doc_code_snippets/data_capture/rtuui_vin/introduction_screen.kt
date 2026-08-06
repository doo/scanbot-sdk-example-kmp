package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_vin

// @Tag("VIN Introduction Screen")
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.vin.configuration.VinIntroDefaultImage
import io.scanbot.sdk.kmp.ui_v2.vin.configuration.VinScannerScreenConfiguration

fun vinIntroductionScreenConfiguration(): VinScannerScreenConfiguration {
    return VinScannerScreenConfiguration().apply {
        introScreen.showAutomatically = true
        introScreen.title.text = "How to scan VIN"
        introScreen.image = VinIntroDefaultImage()
        introScreen.explanation.color = ScanbotColor("#000000")
        introScreen.explanation.text =
            "To scan a VIN, align the full code inside the finder and keep the device steady."
        introScreen.doneButton.text = "Start Scanning"
        introScreen.doneButton.background.fillColor = ScanbotColor("#C8193C")
    }
}
// @EndTag("VIN Introduction Screen")

