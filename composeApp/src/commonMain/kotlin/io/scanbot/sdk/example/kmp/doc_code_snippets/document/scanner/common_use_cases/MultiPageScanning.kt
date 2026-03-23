package io.scanbot.sdk.example.kmp.doc_code_snippets.document.scanner.common_use_cases

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

// @Tag("Multi Page")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.page.DocumentData
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow

fun rtuUiMultiPageScanningUseCase(): DocumentScanningFlow {
    // Create the default configuration object.
    val configuration = DocumentScanningFlow().apply {

        // Enable the multiple page behavior
        outputSettings.pagesScanLimit = 0

        // Enable/Disable Auto Snapping behavior
        screens.camera.cameraConfiguration.autoSnappingEnabled = true

        // Hide/Reveal the auto snapping enable/disable button
        screens.camera.bottomBar.autoSnappingModeButton.visible = true
        screens.camera.bottomBar.manualSnappingModeButton.visible = true

        // Set colors
        palette.sbColorPrimary = ScanbotColor("#C8193CFF")
        palette.sbColorOnPrimary = ScanbotColor("#ffffff")

        // Configure the hint texts for different scenarios
        screens.camera.userGuidance.statesTitles.tooDark = "Need more lighting to detect a document"
        screens.camera.userGuidance.statesTitles.tooSmall = "Document too small"
        screens.camera.userGuidance.statesTitles.noDocumentFound = "Could not detect a document"

        // Enable/Disable the review screen.
        screens.review.enabled = true

        // Configure bottom bar (further properties like title, icon and  background can also be set for these buttons)
        screens.review.bottomBar.addButton.visible = true
        screens.review.bottomBar.retakeButton.visible = true
        screens.review.bottomBar.cropButton.visible = true
        screens.review.bottomBar.rotateButton.visible = true
        screens.review.bottomBar.deleteButton.visible = true

        // Configure `more` popup on review screen
        screens.review.morePopup.reorderPages.icon.visible = true
        screens.review.morePopup.deleteAll.icon.visible = true
        screens.review.morePopup.deleteAll.title.text = "Delete all pages"

        // Configure reorder pages screen
        screens.reorderPages.topBarTitle.text = "Reorder Pages"
        screens.reorderPages.guidance.title.text = "Reorder Pages"

        // Configure cropping screen
        screens.cropping.topBarTitle.text = "Cropping Screen"
        screens.cropping.bottomBar.resetButton.visible = true
        screens.cropping.bottomBar.rotateButton.visible = true
        screens.cropping.bottomBar.detectButton.visible = true
    }

    return configuration
}

fun startMultiPageScanning(
    onResultHandler: (DocumentData) -> Unit, onErrorHandler: (error: Throwable) -> Unit
) {
    ScanbotSDK.document.startScanner(
        configuration = rtuUiMultiPageScanningUseCase(), onResult = { result ->
            result.onSuccess {
                onResultHandler(it)
            }.onFailure {
                onErrorHandler(it)
            }
        })
}
// @EndTag("Multi Page")