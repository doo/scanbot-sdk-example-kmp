package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_mrz

// @Tag("MRZ Finder Overlay")
import io.scanbot.sdk.kmp.ui_v2.common.configuration.FinderCorneredStyle
import io.scanbot.sdk.kmp.ui_v2.mrz.configuration.MrzScannerScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.mrz.configuration.ThreeLineMrzFinderLayoutPreset

fun mrzFinderOverlayConfiguration(): MrzScannerScreenConfiguration {
    return MrzScannerScreenConfiguration().apply {
        mrzExampleOverlay = ThreeLineMrzFinderLayoutPreset(
            mrzTextLine1 = "I<USA2342353464<<<<<<<<<<<<<<<",
            mrzTextLine2 = "9602300M2904076USA<<<<<<<<<<<2",
            mrzTextLine3 = "SMITH<<JACK<<<<<<<<<<<<<<<<<<<"
        )
        viewFinder.style = FinderCorneredStyle(
            cornerRadius = 8.0,
            strokeWidth = 2.0
        )
    }
}
// @EndTag("MRZ Finder Overlay")

