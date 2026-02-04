package io.scanbot.sdk.example.kmp.snippets.document.api.pdf

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.pdfgeneration.PageDirection
import io.scanbot.sdk.kmp.pdfgeneration.PageSize
import io.scanbot.sdk.kmp.pdfgeneration.PdfConfiguration
import io.scanbot.sdk.kmp.utils.Result

fun createPdfFromDocument(
    documentId: String,
    outputUri: String?
): Result<String> {

    val pdfConfig = PdfConfiguration(
        pageSize = PageSize.A4,
        pageDirection = PageDirection.PORTRAIT
    )

    return ScanbotSDK.pdfGenerator.generateFromDocument(
        documentUuid = documentId,
        pdfConfiguration = pdfConfig,
        performOcr = false,
        outputURI = outputUri,
    )
}
