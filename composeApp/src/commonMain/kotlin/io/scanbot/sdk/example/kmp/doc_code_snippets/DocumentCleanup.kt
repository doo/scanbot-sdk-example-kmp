package io.scanbot.sdk.example.kmp.doc_code_snippets

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.page.DocumentData
import io.scanbot.sdk.kmp.ui_v2.common.ScanbotColor
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentCleanupStandaloneConfiguration

// @Tag("Start document cleanup screen for document page")
fun startDocumentCleanupScreen(
    documentUuid: String,
    pageUuid: String,
    handleResult: (DocumentData) -> Unit,
    handleError: (Throwable) -> Unit,
) {
    val configuration = DocumentCleanupStandaloneConfiguration(
        documentUuid = documentUuid,
        pageUuid = pageUuid,
    ).apply {

        // If true, the cleanup tool will not allow erasing text. But it takes some time to process OCR on the image initially.
        cleanup.engineConfiguration.keepText = false

        // The maximum number of undo/redo operations that can be performed. Make it smaller to save up memory.
        cleanup.engineConfiguration.maxUndoRedoStackSize = 4

        // Downscales the stroke area to this value in pixels (width x height) to speed up the cleanup process. The smaller the value the faster but the quality will be lower too.
        cleanup.engineConfiguration.maxCleanupResolution = 1_200_000

        // Customize the top bar.
        cleanup.topBarBackButton.text = "Cancel"
        cleanup.topBarConfirmButton.text = "Done"
        cleanup.topBarTitle.text = "Clean up the page"

        // Background color of the cleanup screen.
        cleanup.backgroundColor = ScanbotColor("#222222")

        // Configure the toolbar buttons (undo / redo / reset).
        cleanup.toolbar.undoButton.title.text = "Undo"
        cleanup.toolbar.redoButton.title.text = "Redo"
        cleanup.toolbar.resetButton.title.text = "Reset"

        cleanup.toolbar.strokeSizeSlider.visible = true
        cleanup.toolbar.strokeSizeSlider.minStrokeSize = 1
        cleanup.toolbar.strokeSizeSlider.maxStrokeSize = 50
        cleanup.toolbar.strokeSizeSlider.title.text = "Brush size"

        // Optional: show an introduction screen the first time the user opens cleanup.
        cleanup.introduction.showAutomatically = true

        // Customize the alert dialogs shown for Reset and for cancelling with unsaved changes.
        cleanup.resetAllEditsAlertDialog.title.text = "Reset all edits?"
        cleanup.resetAllEditsAlertDialog.subtitle.text = "This will revert all cleanup operations on this page."

        cleanup.discardChangesAlertDialog.title.text = "Discard changes?"
        cleanup.discardChangesAlertDialog.subtitle.text = "Your cleanup edits on this page will be lost."
    }

    ScanbotSDK.documentEnhancer.startDocumentCleanupScreen(
        configuration = configuration,
        onResult = { result ->
            result.onSuccess { handleResult(it) }
            result.onFailure { handleError(it) }
        },
        onCanceled = { }
    )
}
// @EndTag("Start document cleanup screen for document page")
