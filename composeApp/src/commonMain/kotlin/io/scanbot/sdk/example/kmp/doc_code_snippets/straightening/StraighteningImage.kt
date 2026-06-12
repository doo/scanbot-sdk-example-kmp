package io.scanbot.sdk.example.kmp.doc_code_snippets.straightening

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.documentscanner.DocumentStraighteningMode
import io.scanbot.sdk.kmp.documentscanner.DocumentStraighteningParameters
import io.scanbot.sdk.kmp.documentscanner.DocumentStraighteningResult
import io.scanbot.sdk.kmp.geometry.AspectRatio
import io.scanbot.sdk.kmp.image.ImageRef

fun straighteningImage(
    imageRef: ImageRef
): DocumentStraighteningResult? {

    /** Create the default configuration instance */
    val straighteningParameters = DocumentStraighteningParameters(
        straighteningMode = DocumentStraighteningMode.STRAIGHTEN
    )

    /**
     * The straightening parameters can be customized to fit the expected aspect ratio of the document
     * to be straightened. This can help the straightening algorithm to achieve better results.
     */
    straighteningParameters.aspectRatios = listOf(
        AspectRatio(5.0, 7.0),
        AspectRatio(1.0, 1.0),
        AspectRatio(16.0, 9.0),
        AspectRatio(3.0, 4.0),
    )

    return ScanbotSDK.documentEnhancer.straightenImage(
        imageRef,
        straighteningParameters
    ).fold(onSuccess = { result ->
        result
    }, onFailure = { error ->
        print("Failed to straighten image: ${error.message ?: "Unknown error"}")
        null
    })
}