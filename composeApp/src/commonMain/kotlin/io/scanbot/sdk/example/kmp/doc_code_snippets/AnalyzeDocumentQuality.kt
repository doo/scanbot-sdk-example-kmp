package io.scanbot.sdk.example.kmp.doc_code_snippets

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.documentqualityanalyzer.DocumentQualityAnalyzerConfiguration
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.page.DocumentData

// @Tag("Analyze document quality on image")
fun analyzeDocumentQualityOnImage(image: ImageRef): String {
    val configuration = DocumentQualityAnalyzerConfiguration().apply {
        detectOrientation = true
    }

    // Run the analysis and transform the Result into a displayable string
    return ScanbotSDK.document.analyzeQualityOnImage(image, configuration)
        .fold(onSuccess = { result ->
            // Handle Success: return a summary of the quality and orientation
            "Quality: ${result.quality}\nOrientation: ${result.orientation}"
        }, onFailure = { error ->
            // Handle Failure: return a descriptive error message
            "Analysis failed: ${error.message ?: "Unknown error"}"
        })
}
// @EndTag("Analyze document quality on image")

// @Tag("Analyze quality of document pages")
fun analyzeDocumentPagesQuality(document: DocumentData) {
    document.pages.forEach { page ->
        // Create an ImageRef from the original image URI path
        val imageRef = ImageRef.fromPath(page.documentImageURI ?: page.originalImageURI)

        imageRef?.use { image ->
            // Run the quality check using the KMP Document module
            val result = ScanbotSDK.document.analyzeQualityOnImage(
                image = image,
                configuration = DocumentQualityAnalyzerConfiguration()
            )

            // Handle the result safely
            result.onSuccess { qualityResult ->
                val qualityScore = qualityResult.quality
                println("Page ${page.uuid} quality score: $qualityScore")
            }.onFailure { exception ->
                println("Failed to analyze page ${page.uuid}: ${exception.message}")
            }
        }
            ?: println("Could not load image from path: ${page.documentImageURI ?: page.originalImageURI}")
    }
}
// @EndTag("Analyze quality of document pages")