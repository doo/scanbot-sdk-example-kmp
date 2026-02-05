package io.scanbot.sdk.example.kmp.snippets.ocr

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.ocr.PerformOcrResult
import io.scanbot.sdk.kmp.utils.Result

suspend fun performOcrOnImage(
    images: List<ImageRef>,
): Result<PerformOcrResult> =
    ScanbotSDK.ocrEngine.recognizeOnImages(
        images = images,
    )