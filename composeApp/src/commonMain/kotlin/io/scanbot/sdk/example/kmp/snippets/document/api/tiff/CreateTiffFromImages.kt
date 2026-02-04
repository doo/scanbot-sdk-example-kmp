package io.scanbot.sdk.example.kmp.snippets.document.api.tiff

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.tiffgeneration.CompressionMode
import io.scanbot.sdk.kmp.tiffgeneration.TiffGeneratorParameters
import io.scanbot.sdk.kmp.utils.Result

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
