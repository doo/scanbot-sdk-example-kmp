package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ocr

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.page.DocumentData
import io.scanbot.sdk.kmp.utils.Result

// @Tag("Perform OCR on images")
suspend fun performOcrOnImages(images: List<ImageRef>): String {
    // Run the OCR engine recognize call
    val result = ScanbotSDK.ocrEngine.recognizeOnImages(images = images)

    // Handle Success and Failure to return a result to show in the UI
    return result.fold(onSuccess = { ocrResult ->
        // Return the plain text from the OCR result
        ocrResult.plainText
    }, onFailure = { error ->
        // Return a descriptive error message
        "OCR recognition failed: ${error.message ?: "Unknown error"}"
    })
}
// @EndTag("Perform OCR on images")


// @Tag("Perform OCR on document")
suspend fun performOcrOnDocument(document: DocumentData): String {
    // Run the OCR engine recognize call
    val result = ScanbotSDK.ocrEngine.recognizeOnDocument(document.uuid)

    // Handle Success and Failure to return a result to show in the UI
    return result.fold(onSuccess = { ocrResult ->
        // Return the plain text from the OCR result
        ocrResult.plainText
    }, onFailure = { error ->
        // Return a descriptive error message
        "OCR recognition failed: ${error.message ?: "Unknown error"}"
    })
}
// @EndTag("Perform OCR on document")