package io.scanbot.sdk.example.kmp.ui.document

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import io.scanbot.sdk.example.kmp.doc_code_snippets.createDocumentFromImages
import io.scanbot.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startMultiPageScanning
import io.scanbot.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startSinglePageFinderScanning
import io.scanbot.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startSinglePageScanning
import io.scanbot.sdk.example.kmp.ui.common.GalleryPicker
import io.scanbot.sdk.example.kmp.ui.common.MenuItem
import io.scanbot.sdk.example.kmp.ui.common.MenuSection
import io.scanbot.sdk.kmp.page.DocumentData
import kotlinx.coroutines.launch

@Composable
fun DocumentUseCases(
    runWithValidLicense: (action: () -> Unit) -> Unit,
    onResultPreview: (DocumentData) -> Unit,
    onError: (Throwable) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val pendingDocumentCreation = remember { mutableStateOf(false) }

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
                pendingDocumentCreation.value = true
            }
        }
    }

    if (pendingDocumentCreation.value) {
        GalleryPicker(
            allowMultiple = true,
            onImagesSelected = { images ->
                scope.launch {
                    createDocumentFromImages(images)?.let(onResultPreview)
                        ?: onError(Throwable("Failed to create document"))
                    pendingDocumentCreation.value = false
                }
            },
            onDismiss = { pendingDocumentCreation.value = false },
        )
    }
}
