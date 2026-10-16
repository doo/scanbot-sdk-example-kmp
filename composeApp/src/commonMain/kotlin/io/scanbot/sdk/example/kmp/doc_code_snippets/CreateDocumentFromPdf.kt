package io.scanbot.sdk.example.kmp.doc_code_snippets

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.common.document.configuration.CreateDocumentOptions
import io.scanbot.sdk.kmp.imageprocessing.ColorDocumentFilter
import io.scanbot.sdk.kmp.page.DocumentData
import io.scanbot.sdk.kmp.utils.Result

// @Tag("Create document from PDF")
fun createDocumentFromPdf(
    pdfFileUri: String,
): Result<DocumentData> {
    val options = CreateDocumentOptions(
        filters = listOf(ColorDocumentFilter()),
        // Configure other parameters as needed.
    )

    return ScanbotSDK.document.createDocumentFromPdf(
        pdfFileUri = pdfFileUri,
        options = options,
    )
}
// @EndTag("Create document from PDF")
