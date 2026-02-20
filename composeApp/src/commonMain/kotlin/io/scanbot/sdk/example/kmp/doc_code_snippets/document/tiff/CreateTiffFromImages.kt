package io.scanbot.sdk.example.kmp.doc_code_snippets.document.tiff

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.tiffgeneration.CompressionMode
import io.scanbot.sdk.kmp.tiffgeneration.TiffGeneratorParameters
import io.scanbot.sdk.kmp.utils.Result

// @Tag("CreateTiffFromImages")
fun createTiffFromImages(
    imageRefs: List<ImageRef>,
    outputUri: String
): Result<String> {

    val tiffParams = TiffGeneratorParameters(
        compression = CompressionMode.LZW,
        jpegQuality = 80
    )

    return ScanbotSDK.tiffGenerator.generateFromImages(
        images = imageRefs,
        tiffGeneratorParameters = tiffParams,
        outputURI = outputUri
    )
}
// @EndTag("CreateTiffFromImages")
