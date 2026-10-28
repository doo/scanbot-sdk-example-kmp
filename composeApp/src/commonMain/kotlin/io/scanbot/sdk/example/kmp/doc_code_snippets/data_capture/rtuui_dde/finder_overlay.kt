package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_dde

// @Tag("DDE Finder Overlay")
import io.scanbot.sdk.kmp.ui_v2.common.configuration.FinderCorneredStyle
import io.scanbot.sdk.kmp.ui_v2.documentdata.configuration.DocumentDataExtractorScreenConfiguration

fun documentDataFinderOverlayConfiguration(): DocumentDataExtractorScreenConfiguration {
    return DocumentDataExtractorScreenConfiguration().apply {
        viewFinder.style = FinderCorneredStyle(
            strokeWidth = 3.0
        )
    }
}
// @EndTag("DDE Finder Overlay")

