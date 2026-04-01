package io.scanbot.sdk.example.kmp.doc_code_snippets.detailed_setup_guide

import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.common.InvalidImageRefError
import io.scanbot.sdk.kmp.common.InvalidLicenseError
import io.scanbot.sdk.kmp.common.IoError
import io.scanbot.sdk.kmp.common.OperationCanceledError
import io.scanbot.sdk.kmp.common.OutOfMemoryError
import io.scanbot.sdk.kmp.common.TimeoutError
import io.scanbot.sdk.kmp.common.document.configuration.CreateDocumentOptions
import io.scanbot.sdk.kmp.documentscanner.DocumentScannerConfiguration
import io.scanbot.sdk.kmp.documentscanner.DocumentScanningResult
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.pdfgeneration.PdfConfiguration
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow
import io.scanbot.sdk.kmp.utils.Result

/*
    NOTE: this snippet of code is to be used only as a part of the website documentation.
    This code is not intended for any use outside of the support of documentation by Scanbot SDK GmbH employees.
*/

class ResultApiSnippets {

    // @Tag("Handle result with when expression")
    fun processResultWithSwitch(result: Result<DocumentScanningResult>) = when (result) {
        is Result.Success -> {
            val documentResult = result.value
            // Handle successful result
            val detectionStatus = documentResult.detectionResult.status
            val croppedImage = documentResult.croppedImage
        }

        is Result.Failure -> {
            val exception = result.exception
            // Handle error
            println("Error scanning document: ${exception.message}")
        }
    }
    // @EndTag("Handle result with when expression")

    fun startDocumentScanner() {
        // @Tag("Start document scanner with result handling")
        ScanbotSDK.document.startScanner(
            configuration = DocumentScanningFlow(),
            onResult = { result ->
                when (result) {
                    is Result.Success -> {
                        val documentData = result.value
                        // documentData is of type DocumentData
                        val documentUuid = documentData.uuid
                        val pageCount = documentData.pages.size
                    }

                    is Result.Failure -> {
                        println("UI error: ${result.exception.message}")
                    }
                }
            }
        )
        // @EndTag("Start document scanner with result handling")
    }

    // @Tag("Handle specific error types")
    fun handleSpecificErrors(result: Result<DocumentScanningResult>) {
        result.onFailure { exception ->
            when (exception) {
                is InvalidLicenseError -> {
                    // Handle license error - show license activation UI
                    println("License is invalid or expired")
                }

                is InvalidImageRefError -> {
                    // Handle invalid image - prompt user to capture again
                    println("Image is corrupted or invalid")
                }

                is OperationCanceledError -> {
                    // Handle cancellation - user might have cancelled
                    println("Operation was cancelled")
                }

                is TimeoutError -> {
                    // Handle timeout - retry or inform user
                    println("Operation timed out, please try again")
                }

                is OutOfMemoryError -> {
                    // Handle memory error - reduce image quality or clear cache
                    println("Not enough memory to process image")
                }

                is IoError -> {
                    // Handle I/O error - check storage permissions
                    println("File operation failed: ${exception.message}")
                }

                else -> {
                    // Handle other errors
                    println("Error: ${exception.message}")
                }
            }
        }
    }
    // @EndTag("Handle specific error types")

    fun proceedWithGetters(image: ImageRef, config: DocumentScannerConfiguration) {
        // @Tag("Handle Result with getters")
        val result = ScanbotSDK.document.scanFromImage(image, config)

        // Get value or null if unsuccessful
        val nullableResult = result.getOrNull()

        // Get value or throw exception if unsuccessful
        val nonNullableResult = result.getOrThrow()
        // @EndTag("Handle Result with getters")
    }

    // @Tag("Handle Result with chain API")
    fun processResultWithChainApi(result: Result<DocumentScanningResult>) {
        // Handle success and failure in a chained way
        result.onSuccess { documentResult ->
            // Handle successful document scanning result
            val status = documentResult.detectionResult.status
        }.onFailure { exception ->
            // Handle error during document scanning
            println("Error scanning document: ${exception.message}")
        }

        // Chained transformations
        val detectionStatus = result.map { documentResult ->
            // Transform the successful result to the status
            documentResult.detectionResult.status
        }.getOrNull()
    }
    // @EndTag("Handle Result with chain API")

    // @Tag("Handle Result with fold function")
    fun processResultWithFold(result: Result<DocumentScanningResult>) {
        val message = result.fold(
            onSuccess = { documentResult ->
                "Detection status: ${documentResult.detectionResult.status}"
            },
            onFailure = { exception ->
                "Error: ${exception.message}"
            }
        )
    }
    // @EndTag("Handle Result with fold function")

    // @Tag("Chaining multiple operations")
    fun scanAndCreatePdf(
        image: ImageRef,
        docOptions: CreateDocumentOptions,
        pdfConfig: PdfConfiguration,
        outputUri: String
    ): Result<String> {
        // We use mapCatching to safely chain the document creation and PDF generation.
        // Returns Result.Failure if either createDocumentFromImages or generateFromDocument fails.
        return ScanbotSDK.document
            .createDocumentFromImages(listOf(image), docOptions)
            .mapCatching { docData ->
                // Use the uuid from the successfully created DocumentData
                ScanbotSDK.pdfGenerator.generateFromDocument(
                    documentUuid = docData.uuid,
                    pdfConfiguration = pdfConfig,
                    performOcr = true,
                    outputURI = outputUri
                ).getOrThrow()
            }
    }
    // @EndTag("Chaining multiple operations")
}
