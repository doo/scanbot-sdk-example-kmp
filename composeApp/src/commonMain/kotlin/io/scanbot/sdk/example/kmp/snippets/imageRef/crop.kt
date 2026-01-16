package io.scanbot.sdk.example.kmp.snippets.imageRef

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.geometry.PointF
import io.scanbot.sdk.kmp.image.ImageRef

fun cropImageRef(image: ImageRef) {
    val points = listOf(
        PointF(0.0f, 0.0f),
        PointF(1.0f, 0.0f),
        PointF(1.0f, 1.0f),
        PointF(0.0f, 1.0f)
    )

    ScanbotSDK.imageProcessor.crop(
        image,
        polygon = points
    )
}