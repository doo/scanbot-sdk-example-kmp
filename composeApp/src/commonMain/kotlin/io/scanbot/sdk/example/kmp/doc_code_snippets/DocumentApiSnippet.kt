package io.scanbot.sdk.example.kmp.doc_code_snippets

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.common.document.configuration.AddPageOptions
import io.scanbot.sdk.kmp.common.document.configuration.CreateDocumentOptions
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.page.DocumentData
import io.scanbot.sdk.kmp.utils.Result

// @Tag("Storing and retrieving a DocumentData")
fun createScannedDocumentFromImages(
    images: List<ImageRef>,
    options: CreateDocumentOptions? = null
): Result<DocumentData> {
    // Create a new document from the specified images.
    // You can use CreateDocumentOptions to control image size, filters, etc.
    return ScanbotSDK.document.createDocumentFromImages(
        images = images,
        options = options
    )
}

fun createScannedDocumentFromSingleImage(
    image: ImageRef,
    options: CreateDocumentOptions? = null
): Result<DocumentData> {
    // Convenience wrapper for creating a document from a single image.
    return ScanbotSDK.document.createDocumentFromImages(
        images = listOf(image),
        options = options
    )
}

fun loadExistingDocument(
    documentUuid: String
): Result<DocumentData> {
    // Load an existing document by its UUID.
    return ScanbotSDK.document.loadDocument(documentUuid)
}

fun cloneExistingDocument(
    documentUuid: String,
): Result<DocumentData> {
    // Clone an existing document by its UUID and return the cloned copy.
    return ScanbotSDK.document.cloneDocument(documentUuid)
}

fun accessPageImageUris(documentData: DocumentData) {
    // get an array of original image URLs from scanned document.
    val originalImageUris = documentData.pages.map { page -> page.originalImageURI }

    // get an array of document image (processed, rotated, cropped and filtered) URLs from scanned document.
    val documentImageUris = documentData.pages.map { page -> page.documentImageURI }

    // get an array of screen-sized preview image URLs from scanned document.
    val previewImageUris = documentData.pages.map { page -> page.documentImagePreviewURI }

    // Use the URIs as needed in your app.
}

fun reorderPagesInDocument(
    documentUuid: String
): Result<DocumentData> {
    // Move the last page to the first position in the document.
    return ScanbotSDK.document.loadDocument(documentUuid)
        .mapCatching { documentData ->
            val sourceIndex = documentData.pages.size - 1
            val destinationIndex = 0

            ScanbotSDK.document.movePage(
                documentUuid = documentUuid,
                fromIndex = sourceIndex,
                toIndex = destinationIndex
            ).getOrThrow()
        }
}

fun removeAllPagesFromDocument(
    documentUuid: String
): Result<DocumentData> {
    // Remove all pages from the document but keep the document entry itself.
    return ScanbotSDK.document.removeAllPages(documentUuid)
}

fun deleteDocumentPermanently(
    documentUuid: String
): Result<Unit> {
    // Delete the document including its metadata and associated files.
    return ScanbotSDK.document.deleteDocument(documentUuid)
}

fun deleteAllDocuments(): Result<Unit> {
    // Remove all stored documents.
    return ScanbotSDK.document.deleteAllDocuments()
}
// @EndTag("Storing and retrieving a DocumentData")


// @Tag("Adding pages to an existing document")
fun addPages(
    documentUuid: String,
    images: List<ImageRef>,
    options: AddPageOptions? = null
): Result<DocumentData> {
    // Add new pages to an existing document from the specified images.
    return ScanbotSDK.document.addPages(
        documentUuid = documentUuid,
        images = images,
        options = options
    )
}
// @EndTag("Adding pages to an existing document")