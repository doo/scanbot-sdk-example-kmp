package io.scanbot.sdk.example.kmp.doc_code_snippets.document

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.common.document.configuration.ModifyPageOptions
import io.scanbot.sdk.kmp.documentscanner.DocumentScannerConfiguration
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.image.ImageRotation
import io.scanbot.sdk.kmp.imageprocessing.BrightnessFilter
import io.scanbot.sdk.kmp.imageprocessing.OutputMode
import io.scanbot.sdk.kmp.imageprocessing.ParametricFilter
import io.scanbot.sdk.kmp.imageprocessing.ScanbotBinarizationFilter
import io.scanbot.sdk.kmp.page.DocumentData
import io.scanbot.sdk.kmp.utils.Result

// @Tag("Apply filter to document page")
fun applyFilterToDocumentPage(
    documentUuid: String,
    pageUuid: String,
    filters: List<ParametricFilter> = listOf(
        ScanbotBinarizationFilter(outputMode = OutputMode.ANTIALIASED),
        BrightnessFilter(brightness = 0.4)
    )
): Result<DocumentData> {

    return ScanbotSDK.document.modifyPage(
        documentUuid = documentUuid,
        pageUuid = pageUuid,
        options = ModifyPageOptions(
            filters = filters
        )
    )
}
// @EndTag("Apply filter to document page")

// @Tag("Apply filter to image with chained processing steps")
fun processRawImage(image: ImageRef): ImageRef? {
    // Step 1: Detect document polygon
    val detectionResult = ScanbotSDK.document.scanFromImage(
        image = image,
        configuration = DocumentScannerConfiguration()
    ).getOrNull()?.detectionResult

    val polygon = detectionResult?.pointsNormalized ?: return null

    // Step 2: Chain processing steps
    return image.let { ScanbotSDK.imageProcessor.crop(it, polygon) }.getOrNull()
        ?.let { ScanbotSDK.imageProcessor.rotate(it, ImageRotation.CLOCKWISE_90) }?.getOrNull()
        ?.let { ScanbotSDK.imageProcessor.resize(it, 700) }?.getOrNull()?.let { img ->
            val filters = listOf(
                ScanbotBinarizationFilter(outputMode = OutputMode.ANTIALIASED),
                BrightnessFilter(brightness = 0.4)
            )
            ScanbotSDK.imageProcessor.applyFilters(img, filters).getOrNull()
        }
}
// @EndTag("Apply filter to image with chained processing steps")