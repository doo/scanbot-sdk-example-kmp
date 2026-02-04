package io.scanbot.sdk.example.kmp.snippets.document.api

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.documentqualityanalyzer.DocumentQualityAnalyzerConfiguration
import io.scanbot.sdk.kmp.documentqualityanalyzer.DocumentQualityAnalyzerResult
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.utils.Result

fun analyzeDocumentQualityOnImage(
    image: ImageRef,
): Result<DocumentQualityAnalyzerResult> {
    val configuration = DocumentQualityAnalyzerConfiguration()
    configuration.detectOrientation = true
    // Configure other parameters as needed.

    return ScanbotSDK.document.analyzeQualityOnImage(
        image = image,
        configuration = configuration,
    )
}
