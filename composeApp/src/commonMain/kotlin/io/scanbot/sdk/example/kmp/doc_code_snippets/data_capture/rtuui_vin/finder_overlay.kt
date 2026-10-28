package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_vin

// @Tag("VIN Finder Overlay")
import io.scanbot.sdk.kmp.ui_v2.common.configuration.FinderCorneredStyle
import io.scanbot.sdk.kmp.ui_v2.vin.configuration.VinScannerScreenConfiguration

fun vinFinderOverlayConfiguration(): VinScannerScreenConfiguration {
    return VinScannerScreenConfiguration().apply {
        viewFinder.style = FinderCorneredStyle(
            cornerRadius = 8.0,
            strokeWidth = 2.0
        )
    }
}
// @EndTag("VIN Finder Overlay")

