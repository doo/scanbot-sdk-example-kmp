package io.scanbot.sdk.example.kmp.snippets.document.api.pdf

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.pdfgeneration.PageDirection
import io.scanbot.sdk.kmp.pdfgeneration.PageSize
import io.scanbot.sdk.kmp.pdfgeneration.PdfConfiguration

fun createPdfFromImages(
    imageRefs: List<ImageRef>,
    outputUri: String
): Result<String> {

    val pdfConfig = PdfConfiguration(
        pageSize = PageSize.A4,
        pageDirection = PageDirection.PORTRAIT
    )

    return ScanbotSDK.pdfGenerator.generateFromImages(
        imageRefs,
        outputURI = outputUri,
        pdfConfiguration = pdfConfig,
    )
}
