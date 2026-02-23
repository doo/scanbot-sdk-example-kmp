package io.scanbot.sdk.example.kmp.doc_code_snippets.document

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.common.document.configuration.CreateDocumentOptions
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.utils.Result

fun createDocumentFromImages(images: List<ImageRef>): String {
// @Tag("Create document from images")
    val configuration = CreateDocumentOptions()
    // Configure other parameters (e.g., documentImageSizeLimit) as needed.

    // Run the document creation and transform the Result into a displayable string
    return ScanbotSDK.document.createDocumentFromImages(
        images = images,
        options = configuration
    ).fold(
        onSuccess = { documentData ->
            // Return the JSON string representation of the created document
            documentData.toJsonString()
        },
        onFailure = { error ->
            // Return a descriptive error message
            "Failed to create document: ${error.message ?: "Unknown error"}"
        }
    )
// @EndTag("Create document from images")
}
