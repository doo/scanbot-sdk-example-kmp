package io.scanbot.sdk.example.kmp.snippets.document.api.pdf

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.ocr.OcrConfiguration
import io.scanbot.sdk.kmp.ocr.OcrEngine
import io.scanbot.sdk.kmp.pdfgeneration.PageDirection
import io.scanbot.sdk.kmp.pdfgeneration.PageSize
import io.scanbot.sdk.kmp.pdfgeneration.PdfConfiguration

fun createPdfFromDocument(
    documentId: String,
    outputUri: String?
): Result<String> {

    val pdfConfig = PdfConfiguration(
        pageSize = PageSize.A4,
        pageDirection = PageDirection.PORTRAIT
    )

    val ocrConfig = OcrConfiguration(
        recognitionMode = OcrEngine.SCANBOT_OCR
    )

    return ScanbotSDK.pdfGenerator.generateFromDocument(
        documentID = documentId,
        outputURI = outputUri,
        pdfConfiguration = pdfConfig,
        ocrConfiguration = ocrConfig
    )
}
