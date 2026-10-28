package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_text_pattern

// @Tag("Text Pattern Palette")
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.textpattern.configuration.TextPatternScannerScreenConfiguration

fun textPatternPaletteConfiguration(): TextPatternScannerScreenConfiguration {
    return TextPatternScannerScreenConfiguration().apply {
        palette.sbColorPrimary = ScanbotColor("#C8193C")
        palette.sbColorPrimaryDisabled = ScanbotColor("#F5F5F5")
        palette.sbColorNegative = ScanbotColor("#FF3737")
        palette.sbColorPositive = ScanbotColor("#4EFFB4")
        palette.sbColorWarning = ScanbotColor("#FFCE5C")
        palette.sbColorSecondary = ScanbotColor("#FFEDEE")
        palette.sbColorSecondaryDisabled = ScanbotColor("#F5F5F5")
        palette.sbColorOnPrimary = ScanbotColor("#FFFFFF")
        palette.sbColorOnSecondary = ScanbotColor("#C8193C")
        palette.sbColorSurface = ScanbotColor("#FFFFFF")
        palette.sbColorOutline = ScanbotColor("#EFEFEF")
        palette.sbColorOnSurfaceVariant = ScanbotColor("#707070")
        palette.sbColorOnSurface = ScanbotColor("#000000")
        palette.sbColorSurfaceLow = ScanbotColor("#00000026")
        palette.sbColorSurfaceHigh = ScanbotColor("#0000007A")
        palette.sbColorModalOverlay = ScanbotColor("#000000A3")
    }
}
// @EndTag("Text Pattern Palette")

