package io.scanbot.sdk.example.kmp.ui.document

import androidx.compose.runtime.Composable
import io.scanbot.sdk.example.kmp.doc_code_snippets.createDocumentFromImages
import io.scanbot.sdk.example.kmp.doc_code_snippets.createDocumentFromPdf
import io.scanbot.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startMultiPageScanning
import io.scanbot.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startSinglePageFinderScanning
import io.scanbot.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startSinglePageScanning
import io.scanbot.sdk.example.kmp.ui.common.MenuItem
import io.scanbot.sdk.example.kmp.ui.common.MenuSection
import io.scanbot.sdk.example.kmp.ui.common.rememberImagePickerLauncher
import io.scanbot.sdk.example.kmp.ui.common.rememberPdfPickerLauncher
import io.scanbot.sdk.kmp.page.DocumentData

@Composable
fun DocumentUseCases(
    runWithValidLicense: (action: () -> Unit) -> Unit,
    onResultPreview: (DocumentData) -> Unit,
    onError: (Throwable) -> Unit,
) {
    val pickImagesForNewDocument = rememberImagePickerLauncher(
        allowMultiple = true,
        onImagesSelected = { images ->
            createDocumentFromImages(images)?.let(onResultPreview)
                ?: onError(Throwable("Failed to create document"))
        },
        onError = onError,
    )

    val pickPdfForNewDocument = rememberPdfPickerLauncher(
        onPdfSelected = { pdfUri ->
            createDocumentFromPdf(pdfUri)
                .onSuccess(onResultPreview)
                .onFailure(onError)
        },
        onError = onError,
    )

    MenuSection("Document Use Cases") {
        MenuItem("Single Page Scanning") {
            runWithValidLicense {
                startSinglePageScanning(onResultPreview, onError)
            }
        }
        MenuItem("Single Page Scanning with Finder") {
            runWithValidLicense {
                startSinglePageFinderScanning(onResultPreview, onError)
            }
        }
        MenuItem("Multi Page Scanning with Finder") {
            runWithValidLicense {
                startMultiPageScanning(onResultPreview, onError)
            }
        }
        MenuItem("Create Document from Images") {
            runWithValidLicense {
                pickImagesForNewDocument()
            }
        }
        MenuItem("Create Document from PDF") {
            runWithValidLicense {
                pickPdfForNewDocument()
            }
        }
    }
}
