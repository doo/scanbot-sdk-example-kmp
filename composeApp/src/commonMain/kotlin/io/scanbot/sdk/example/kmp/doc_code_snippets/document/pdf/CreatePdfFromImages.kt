package io.scanbot.sdk.example.kmp.doc_code_snippets.document.pdf

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.pdfgeneration.PdfConfiguration
import io.scanbot.sdk.kmp.utils.Result

// @Tag("CreatePdfFromImages")
fun createPdfFromImages(
    imageRefs: List<ImageRef>,
    outputUri: String
): Result<String> {

    val pdfConfig = PdfConfiguration.default()

    return ScanbotSDK.pdfGenerator.generateFromImages(
        imageRefs,
        outputURI = outputUri,
        pdfConfiguration = pdfConfig,
    )
}
// @EndTag("CreatePdfFromImages")

// @Tag("CreateSearchablePdfFromImages")
fun createSearchablePdfFromImages(
    imageRefs: List<ImageRef>,
    outputUri: String
): Result<String> {
    val pdfConfig = PdfConfiguration.default()

    return ScanbotSDK.pdfGenerator.generateFromImages(
        imageRefs,
        outputURI = outputUri,
        pdfConfiguration = pdfConfig,
        performOcr = true,  // Enable OCR for searchable PDF
    )
}
// @EndTag("CreateSearchablePdfFromImages")
