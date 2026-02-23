package io.scanbot.sdk.example.kmp.doc_code_snippets.document

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.common.sdk.configuration.SdkConfiguration
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow

@Composable
fun DocumentScannerExample() {
    // @Tag("Document Scanner")
    // Initialize the SDK:
    LaunchedEffect(Unit) {
        ScanbotSDK.initialize(
            SdkConfiguration(
                licenseKey = "" // optional: add your license key here
            )
        )
    }

    Button(onClick = {
        // Create the configuration:
        val config = DocumentScanningFlow().apply {
            // TODO: configure as needed
        }

        // Launch the document scanner:
        ScanbotSDK.document.startScanner(
            configuration = config,
            onResult = { result ->
                result.onSuccess { scanResult ->
                    // Document Scanner result callback:
                    // Get the first scanned page from the result object...
                    val firstPage = scanResult.pages.firstOrNull()
                    // ... and process the result as needed, for example, print to console:
                    println("Scanned page count: ${scanResult.pages.size}")
                }.onFailure { error ->
                    // Optional failure handling to understand why scanner result is not provided
                    println("Scanning failed: ${error.message}")
                }
            },
            onCanceled = {
                // Optional: handle user cancellation
            }
        )
    }) {
        Text("Start Document Scanner")
    }
    // @EndTag("Document Scanner")
}


fun handlingResult() {
    // Create the configuration:
    val config = DocumentScanningFlow().apply {
        // TODO: configure as needed
    }
    // @Tag("Handling the result")
    ScanbotSDK.document.startScanner(
        configuration = config,
        onResult = { result ->
            result.onSuccess { scanResult ->
                // The unique identifier of the scanned document
                val documentId = scanResult.uuid

                // The creation timestamp of the document
                val timestamp = scanResult.creationTimestamp

                // URI to the generated PDF of the document (if configured)
                val pdfUri = scanResult.pdfURI

                // URI to the generated TIFF of the document (if configured)
                val tiffUri = scanResult.tiffURI

                // Iterate over scanned pages
                scanResult.pages.forEach { page ->
                    // Handle each scanned page (PageData)
                    println("Page ID: ${page.uuid}")
                }
            }.onFailure { error ->
                // Handle failure — error is a Throwable
                println("Scanning failed: ${error.message}")
            }
        },
        onCanceled = {
            // Handle cancellation if needed
        }
    )
    // @EndTag("Handling the result")
}