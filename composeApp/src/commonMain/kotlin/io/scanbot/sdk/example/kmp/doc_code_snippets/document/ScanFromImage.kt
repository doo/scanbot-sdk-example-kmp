package io.scanbot.sdk.example.kmp.doc_code_snippets.document

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.documentscanner.DocumentScannerConfiguration
import io.scanbot.sdk.kmp.documentscanner.DocumentScanningResult
import io.scanbot.sdk.kmp.documentscanner.PartiallyVisibleDocumentConfiguration
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.utils.Result

// @Tag("Scan document from image")
fun scanDocumentFromImage(
    image: ImageRef,
): Result<DocumentScanningResult> {
    val configuration = DocumentScannerConfiguration()
    configuration.partiallyVisibleDocumentConfiguration = PartiallyVisibleDocumentConfiguration(
        allowPartiallyVisibleDocuments = true,
    )
    // Configure other parameters as needed.

    return ScanbotSDK.document.scanFromImage(
        image = image,
        configuration = configuration,
    )
}
// @EndTag("Scan document from image")


// @Tag("Scan document from image with custom config")
fun scanDocumentFromImageWithCustomConfig(
    image: ImageRef,
) {
    // TODO Yurii: since the name is custom config, should we show/change more params (otherwise is the same as the above function)
    val configuration = DocumentScannerConfiguration()
    val result = ScanbotSDK.document.scanFromImage(
        image = image,
        configuration = configuration,
    )

    result.getOrNull()?.use { scanningResult ->
        val detectionResult = scanningResult.detectionResult

        // Check the detection status
        println("Detection status: ${detectionResult.status}")

        // Access the document polygon (corner points)
        val cornerPoints = detectionResult.points
        println("Document corners: $cornerPoints")

        // Access the aspect ratio of the detected document
        println("Aspect ratio: ${detectionResult.aspectRatio}")

        // Access the cropped document image (if available)
        val croppedImage = scanningResult.croppedImage
        if (croppedImage != null) {
            // Display or process the cropped image as needed
        }
    }
}
// @EndTag("Scan document from image with custom config")