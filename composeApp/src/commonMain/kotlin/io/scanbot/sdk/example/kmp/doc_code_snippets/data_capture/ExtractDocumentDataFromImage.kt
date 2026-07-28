package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.documentdata.DocumentDataExtractionResult
import io.scanbot.sdk.kmp.documentdata.DocumentDataExtractorConfiguration
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.utils.Result

// @Tag("Extract document data from image")
fun extractDocumentData(image: ImageRef): Result<DocumentDataExtractionResult> {
    val configuration = DocumentDataExtractorConfiguration()

    return ScanbotSDK.documentDataExtractor.extractFromImage(
        image = image,
        configuration = configuration,
    )
}
// @EndTag("Extract document data from image")
