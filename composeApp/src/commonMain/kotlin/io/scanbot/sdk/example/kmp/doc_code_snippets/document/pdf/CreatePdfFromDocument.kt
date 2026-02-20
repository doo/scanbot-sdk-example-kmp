package io.scanbot.sdk.example.kmp.doc_code_snippets.document.pdf

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.pdfgeneration.PageDirection
import io.scanbot.sdk.kmp.pdfgeneration.PageFit
import io.scanbot.sdk.kmp.pdfgeneration.PageSize
import io.scanbot.sdk.kmp.pdfgeneration.PdfAttributes
import io.scanbot.sdk.kmp.pdfgeneration.PdfConfiguration
import io.scanbot.sdk.kmp.pdfgeneration.ResamplingMethod
import io.scanbot.sdk.kmp.utils.Result

// @Tag("CreatePdfFromDocument")
fun createPdfFromDocument(
    documentId: String,
    outputUri: String? = null
): Result<String> {

    // Create PDF with default config (empty attributes, CUSTOM page size, AUTO orientation).
    // Customize PdfConfiguration for DPI, page size, OCR, etc.
    val pdfConfig = PdfConfiguration(
        attributes = PdfAttributes(
            author = "",
            title = "",
            subject = "",
            keywords = "",
            creator = ""
        ),
        pageSize = PageSize.A4,
        pageDirection = PageDirection.AUTO,
        dpi = 200,
        jpegQuality = 100,
        pageFit = PageFit.NONE,
        resamplingMethod = ResamplingMethod.NONE
    )

    return ScanbotSDK.pdfGenerator.generateFromDocument(
        documentUuid = documentId,
        pdfConfiguration = pdfConfig,
        performOcr = false,
        outputURI = outputUri,
    )
}
// @EndTag("CreatePdfFromDocument")

// @Tag("CreateSearchablePdfFromDocument")
fun createSearchablePdfFromDocument(
    documentId: String,
    outputUri: String? = null
): Result<String> {
    val pdfConfig = PdfConfiguration(
        attributes = PdfAttributes(
            author = "",
            title = "",
            subject = "",
            keywords = "",
            creator = ""
        ),
        pageSize = PageSize.A4,
        pageDirection = PageDirection.AUTO,
        dpi = 200,
        jpegQuality = 100,
        pageFit = PageFit.NONE,
        resamplingMethod = ResamplingMethod.NONE
    )

    return ScanbotSDK.pdfGenerator.generateFromDocument(
        documentUuid = documentId,
        pdfConfiguration = pdfConfig,
        performOcr = true,  // Enable OCR for searchable PDF
        outputURI = outputUri,
    )
}
// @EndTag("CreateSearchablePdfFromDocument")