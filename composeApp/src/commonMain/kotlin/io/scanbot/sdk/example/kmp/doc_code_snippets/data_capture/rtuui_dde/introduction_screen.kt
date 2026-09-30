package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_dde

// @Tag("DDE Introduction Screen")
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.documentdata.configuration.DocumentDataExtractorScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.documentdata.configuration.DocumentDataIntroDefaultImage

fun documentDataIntroductionScreenConfiguration(): DocumentDataExtractorScreenConfiguration {
    return DocumentDataExtractorScreenConfiguration().apply {
        introScreen.showAutomatically = true
        introScreen.title.text = "How to scan a document"
        introScreen.image = DocumentDataIntroDefaultImage()
        introScreen.explanation.color = ScanbotColor("#000000")
        introScreen.explanation.text =
            "Hold your device over the document so all information is visible in the finder."
        introScreen.doneButton.text = "Start Scanning"
        introScreen.doneButton.background.fillColor = ScanbotColor("#C8193C")
    }
}
// @EndTag("DDE Introduction Screen")

