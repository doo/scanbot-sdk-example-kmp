package io.scanbot.sdk.example.kmp.snippets.imageRef

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.imageprocessing.ParametricFilter

fun applyFiltersOnImageRef(image: ImageRef) {
    ScanbotSDK.imageProcessor.applyFilters(
        image,
        listOf(ParametricFilter.contrastFilter())
    )
}