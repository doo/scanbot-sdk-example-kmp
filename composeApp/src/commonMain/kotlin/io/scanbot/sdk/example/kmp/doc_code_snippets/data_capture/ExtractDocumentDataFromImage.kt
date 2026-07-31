package io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.documentdata.DocumentDataExtractionResult
import io.scanbot.sdk.kmp.documentdata.DocumentDataExtractorCommonConfiguration
import io.scanbot.sdk.kmp.documentdata.DocumentDataExtractorConfiguration
import io.scanbot.sdk.kmp.genericdocument.document.DeHealthInsuranceCardFront
import io.scanbot.sdk.kmp.genericdocument.document.DeIdCardBack
import io.scanbot.sdk.kmp.genericdocument.document.DeIdCardFront
import io.scanbot.sdk.kmp.genericdocument.document.DePassport
import io.scanbot.sdk.kmp.genericdocument.document.DeResidencePermitBack
import io.scanbot.sdk.kmp.genericdocument.document.DeResidencePermitFront
import io.scanbot.sdk.kmp.genericdocument.document.EuropeanDriverLicenseBack
import io.scanbot.sdk.kmp.genericdocument.document.EuropeanDriverLicenseFront
import io.scanbot.sdk.kmp.genericdocument.document.EuropeanHealthInsuranceCard
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.utils.Result

// @Tag("Extract document data from image")
fun extractDocumentData(image: ImageRef): Result<DocumentDataExtractionResult> {
    val commonConfig = DocumentDataExtractorCommonConfiguration(
        acceptedDocumentTypes = listOf(
            DeIdCardFront.DOCUMENT_TYPE,
            DeIdCardBack.DOCUMENT_TYPE,
            DeHealthInsuranceCardFront.DOCUMENT_TYPE,
            DePassport.DOCUMENT_TYPE,
            DeResidencePermitFront.DOCUMENT_TYPE,
            DeResidencePermitBack.DOCUMENT_TYPE,
            EuropeanHealthInsuranceCard.DOCUMENT_TYPE,
            EuropeanDriverLicenseFront.DOCUMENT_TYPE,
            EuropeanDriverLicenseBack.DOCUMENT_TYPE,
        )
    )

    val configuration = DocumentDataExtractorConfiguration(
        configurations = listOf(commonConfig)
    )
    // Configure other parameters as needed.

    return ScanbotSDK.documentDataExtractor.extractFromImage(
        image = image,
        configuration = configuration,
    )
}
// @EndTag("Extract document data from image")
