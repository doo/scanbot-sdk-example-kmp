package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_dde

// @Tag("DDE User Guidance")
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.documentdata.configuration.DocumentDataExtractorScreenConfiguration

fun documentDataUserGuidanceConfiguration(): DocumentDataExtractorScreenConfiguration {
    return DocumentDataExtractorScreenConfiguration().apply {
        topUserGuidance.visible = true
        topUserGuidance.title.text = "Scan your Identity Document"
        topUserGuidance.title.color = ScanbotColor("#FFFFFF")
        topUserGuidance.background.fillColor = ScanbotColor("#7A000000")

        scanStatusUserGuidance.title.text = "Scan document"
        scanStatusUserGuidance.title.color = ScanbotColor("#FFFFFF")
        scanStatusUserGuidance.background.fillColor = ScanbotColor("#7A000000")
    }
}
// @EndTag("DDE User Guidance")

