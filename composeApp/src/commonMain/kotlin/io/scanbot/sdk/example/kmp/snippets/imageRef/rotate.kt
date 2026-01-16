package io.scanbot.sdk.example.kmp.snippets.imageRef

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.image.ImageRotation

fun rotateImageRef(image: ImageRef) {
    ScanbotSDK.imageProcessor.rotate(
        image,
        ImageRotation.CLOCKWISE_90
    )
}