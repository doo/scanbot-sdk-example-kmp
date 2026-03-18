package io.scanbot.sdk.example.kmp.doc_code_snippets.document

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.imageprocessing.ScanbotBinarizationFilter
import io.scanbot.sdk.kmp.tiffgeneration.CompressionMode
import io.scanbot.sdk.kmp.tiffgeneration.TiffGeneratorParameters
import io.scanbot.sdk.kmp.utils.Result

// @Tag("CreateTiffFromDocument")
fun createTiffFromDocument(
    documentUuid: String, outputUri: String? = null
): io.scanbot.sdk.kmp.utils.Result<String> {

    val tiffParams = TiffGeneratorParameters(
        dpi = 200, compression = TiffGeneratorParameters.defaultCompression
    )

    return ScanbotSDK.tiffGenerator.generateFromDocument(
        documentUuid = documentUuid, tiffGeneratorParameters = tiffParams, outputURI = outputUri
    )
}
// @EndTag("CreateTiffFromDocument")

// @Tag("CreateBinarizedTiffFromDocument")
fun createBinarizedTiffFromDocument(
    documentUuid: String, outputUri: String? = null
): Result<String> {

    val tiffParams = TiffGeneratorParameters(
        binarizationFilter = ScanbotBinarizationFilter(),
        dpi = 200,
        compression = TiffGeneratorParameters.binaryDocumentOptimizedCompression // CCITT_T6
    )

    return ScanbotSDK.tiffGenerator.generateFromDocument(
        documentUuid = documentUuid, tiffGeneratorParameters = tiffParams, outputURI = outputUri
    )
}
// @EndTag("CreateBinarizedTiffFromDocument")

// @Tag("CreateTiffFromImages")
fun createTiffFromImages(
    imageRefs: List<ImageRef>, outputUri: String
): Result<String> {

    val tiffParams = TiffGeneratorParameters(
        compression = CompressionMode.LZW, jpegQuality = 80
    )

    return ScanbotSDK.tiffGenerator.generateFromImages(
        images = imageRefs, tiffGeneratorParameters = tiffParams, outputURI = outputUri
    )
}
// @EndTag("CreateTiffFromImages")
