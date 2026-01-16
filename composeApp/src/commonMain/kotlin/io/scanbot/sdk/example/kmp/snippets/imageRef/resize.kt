package io.scanbot.sdk.example.kmp.snippets.imageRef

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.image.ImageRef

fun resizeImageRef(
    image: ImageRef
) {
    ScanbotSDK.imageProcessor.resize(
        image,
        maxSize = 100
    )
}