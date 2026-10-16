package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_dde

// @Tag("DDE Action Bar")
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.documentdata.configuration.DocumentDataExtractorScreenConfiguration

fun documentDataActionBarConfiguration(): DocumentDataExtractorScreenConfiguration {
    return DocumentDataExtractorScreenConfiguration().apply {
        actionBar.flashButton.visible = true
        actionBar.flashButton.backgroundColor = ScanbotColor("#C8193C")
        actionBar.flashButton.foregroundColor = ScanbotColor("#FFFFFF")

        actionBar.zoomButton.visible = true
        actionBar.zoomButton.backgroundColor = ScanbotColor("#C8193C")
        actionBar.zoomButton.foregroundColor = ScanbotColor("#FFFFFF")

        actionBar.flipCameraButton.visible = false
    }
}
// @EndTag("DDE Action Bar")

