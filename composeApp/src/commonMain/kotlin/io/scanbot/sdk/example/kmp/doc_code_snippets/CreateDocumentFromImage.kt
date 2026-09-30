package io.scanbot.sdk.example.kmp.doc_code_snippets

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.common.document.configuration.CreateDocumentOptions
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.page.DocumentData

fun createDocumentFromImages(images: List<ImageRef>): DocumentData? {
// @Tag("Create document from images")
    val options = CreateDocumentOptions()
    // Configure other parameters (e.g., documentImageSizeLimit) as needed.

    // Run the document creation and unwrap the Result
    return ScanbotSDK.document.createDocumentFromImages(
        images = images, options = options
    ).fold(onSuccess = { documentData ->
        // Return the created document
        documentData
    }, onFailure = { error ->
        // Print a descriptive error message
        println("Failed to create document: ${error.message ?: "Unknown error"}")
        null
    })
// @EndTag("Create document from images")
}
