package io.scanbot.sdk.example.kmp.doc_code_snippets.document.scanner.common_use_cases

// @Tag("Single Page Finder")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.page.DocumentData
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow

fun rtuUiSinglePageScanningFinderUseCase(): DocumentScanningFlow {
    // Create the default configuration object.
    val configuration = DocumentScanningFlow().apply {

        // Disable the multiple page behavior
        outputSettings.pagesScanLimit = 1

        // Enable view finder
        screens.camera.viewFinder.visible = true
        // configuration.screens.camera.viewFinder.aspectRatio = AspectRatio(width: 3, height: 4);

        // Enable/Disable the review screen.
        screens.review.enabled = false

        // Enable/Disable Auto Snapping behavior
        screens.camera.cameraConfiguration.autoSnappingEnabled = true

        // Hide the auto snapping enable/disable button
        screens.camera.bottomBar.autoSnappingModeButton.visible = false
        screens.camera.bottomBar.manualSnappingModeButton.visible = false

        // Set colors
        palette.sbColorPrimary = ScanbotColor("#C8193CFF")
        palette.sbColorOnPrimary = ScanbotColor("#ffffff")

        // Configure the hint texts for different scenarios
        screens.camera.userGuidance.statesTitles.tooDark = "Need more lighting to detect a document"
        screens.camera.userGuidance.statesTitles.tooSmall = "Document too small"
        screens.camera.userGuidance.statesTitles.noDocumentFound = "Could not detect a document"

    }
    return configuration;
}

fun startSinglePageFinderScanning(
    onResultHandler: (DocumentData) -> Unit, onErrorHandler: (error: Throwable) -> Unit
) {
    ScanbotSDK.document.startScanner(
        configuration = rtuUiSinglePageScanningFinderUseCase(), onResult = { result ->
            result.onSuccess {
                onResultHandler(it)
            }.onFailure {
                onErrorHandler(it)
            }
        })
}
// @EndTag("SinglePageScanningFinder")