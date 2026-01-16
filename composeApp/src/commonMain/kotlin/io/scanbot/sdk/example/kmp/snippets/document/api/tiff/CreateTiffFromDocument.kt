package io.scanbot.sdk.example.kmp.snippets.document.api.tiff

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.tiffgeneration.CompressionMode
import io.scanbot.sdk.kmp.tiffgeneration.TiffGeneratorParameters

fun createTiffFromDocument(
    documentId: String,
    outputUri: String?
): Result<String> {

    val tiffParams = TiffGeneratorParameters(
        compression = CompressionMode.LZW,
        jpegQuality = 80
    )

    return ScanbotSDK.tiffGenerator.generateFromDocument(
        documentID = documentId,
        tiffGeneratorParameters = tiffParams,
        outputURI = outputUri
    )
}
