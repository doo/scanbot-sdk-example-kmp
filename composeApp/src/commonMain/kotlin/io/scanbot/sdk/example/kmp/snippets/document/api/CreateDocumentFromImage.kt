package io.scanbot.sdk.example.kmp.snippets.document.api

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.common.document.configuration.CreateDocumentOptions
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.page.DocumentData
import io.scanbot.sdk.kmp.utils.Result

fun createDocumentFromImage(
    images: List<ImageRef>,
): Result<DocumentData> {
    val configuration = CreateDocumentOptions()
    // Configure other parameters as needed.

    return ScanbotSDK.document.createDocumentFromImages(
        images = images,
        options = configuration,
    )
}
