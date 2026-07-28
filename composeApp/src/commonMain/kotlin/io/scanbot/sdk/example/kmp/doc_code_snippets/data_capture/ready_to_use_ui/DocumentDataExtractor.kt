package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ready_to_use_ui

// @Tag("Document Data Extractor")
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.common.configuration.FinderCorneredStyle
import io.scanbot.sdk.kmp.ui_v2.common.configuration.StatusBarMode
import io.scanbot.sdk.kmp.ui_v2.common.configuration.TopBarMode
import io.scanbot.sdk.kmp.ui_v2.documentdata.configuration.DocumentDataExtractorScreenConfiguration
import io.scanbot.sdk.kmp.ui_v2.documentdata.configuration.DocumentDataExtractorUiResult
import io.scanbot.sdk.kmp.ui_v2.documentdata.configuration.DocumentDataIntroDefaultImage

fun rtuUiDocumentDataExtractorUseCase(): DocumentDataExtractorScreenConfiguration {
    return DocumentDataExtractorScreenConfiguration().apply {
        palette.sbColorPrimary = ScanbotColor("#C8193C")
        palette.sbColorOnPrimary = ScanbotColor("#FFFFFF")

        localization.topBarTitle = "Document Data Extractor"
        localization.topUserGuidance = "Scan your identity document"
        localization.completionOverlaySuccessMessage = "Document scanned"

        introScreen.showAutomatically = true
        introScreen.title.text = "How to scan a document"
        introScreen.image = DocumentDataIntroDefaultImage()
        introScreen.explanation.text =
            "Hold your device over the document and align all information with the finder."
        introScreen.doneButton.text = "Start Scanning"
        introScreen.doneButton.background.fillColor = ScanbotColor("#C8193C")

        topBar.mode = TopBarMode.GRADIENT
        topBar.statusBarMode = StatusBarMode.LIGHT
        topBar.cancelButton.text = "Cancel"

        topUserGuidance.visible = true
        topUserGuidance.title.text = "Scan your identity document"
        scanStatusUserGuidance.title.text = "Scanning document..."

        viewFinder.style = FinderCorneredStyle(
            cornerRadius = 8.0,
            strokeWidth = 3.0
        )

        actionBar.flashButton.visible = true
        actionBar.flashButton.backgroundColor = ScanbotColor("#C8193C")
        actionBar.flashButton.foregroundColor = ScanbotColor("#FFFFFF")
        actionBar.zoomButton.visible = true
        actionBar.flipCameraButton.visible = false

        scannerConfiguration.returnCrops = true
    }
}

fun startDocumentDataExtractor(
    onResultHandler: (DocumentDataExtractorUiResult) -> Unit,
    onErrorHandler: (Throwable) -> Unit
) {
    ScanbotSDK.documentDataExtractor.startExtractorScreen(
        configuration = rtuUiDocumentDataExtractorUseCase(),
        onResult = { result ->
            result.onSuccess(onResultHandler).onFailure(onErrorHandler)
        }
    )
}
// @EndTag("Document Data Extractor")
