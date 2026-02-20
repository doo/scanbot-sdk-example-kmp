package io.scanbot.sdk.example.kmp.doc_code_snippets.document.scanner.visual_changes

// @Tag("Palette")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow

fun paletteConfigurationScanning(): DocumentScanningFlow {
    // Create the default configuration object.
    val configuration = DocumentScanningFlow()

    // Simply alter one color and keep the other default.
    configuration.palette.sbColorPrimary = ScanbotColor("c86e19")

    // ... or set an entirely new palette.
    configuration.palette.sbColorPrimary = ScanbotColor("#C8193C")
    configuration.palette.sbColorPrimaryDisabled = ScanbotColor("#F5F5F5")
    configuration.palette.sbColorNegative = ScanbotColor("#FF3737")
    configuration.palette.sbColorPositive = ScanbotColor("#4EFFB4")
    configuration.palette.sbColorWarning = ScanbotColor("#FFCE5C")
    configuration.palette.sbColorSecondary = ScanbotColor("#FFEDEE")
    configuration.palette.sbColorSecondaryDisabled = ScanbotColor("#F5F5F5")
    configuration.palette.sbColorOnPrimary = ScanbotColor("#FFFFFF")
    configuration.palette.sbColorOnSecondary = ScanbotColor("#C8193C")
    configuration.palette.sbColorSurface = ScanbotColor("#FFFFFF")
    configuration.palette.sbColorOutline = ScanbotColor("#EFEFEF")
    configuration.palette.sbColorOnSurfaceVariant = ScanbotColor("#707070")
    configuration.palette.sbColorOnSurface = ScanbotColor("#000000")
    configuration.palette.sbColorSurfaceLow = ScanbotColor("#00000026")
    configuration.palette.sbColorSurfaceHigh = ScanbotColor("#0000007A")
    configuration.palette.sbColorModalOverlay = ScanbotColor("#000000A3")

    return configuration
}

fun startScanningWithPaletteConfig() {
    ScanbotSDK.document.startScanner(
        configuration = paletteConfigurationScanning(),
        onResult = {
            it.onSuccess { TODO("Handle scanned document result") }
            it.onFailure { TODO("Handle error") }
        }
    )
}
// @EndTag("Palette")