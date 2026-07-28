package io.scanbot.sdk.example.kmp.ui.document

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import io.scanbot.sdk.example.kmp.doc_code_snippets.analyzeDocumentQualityOnImage
import io.scanbot.sdk.example.kmp.doc_code_snippets.createDocumentFromImages
import io.scanbot.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startMultiPageScanning
import io.scanbot.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startSinglePageFinderScanning
import io.scanbot.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startSinglePageScanning
import io.scanbot.sdk.example.kmp.doc_code_snippets.straightening.straighteningImage
import io.scanbot.sdk.example.kmp.ui.common.GalleryPicker
import io.scanbot.sdk.example.kmp.ui.common.MenuItem
import io.scanbot.sdk.example.kmp.ui.common.MenuSection
import io.scanbot.sdk.kmp.page.DocumentData
import kotlinx.coroutines.launch

@Composable
fun DocumentUseCases(
    runWithValidLicense: (action: () -> Unit) -> Unit,
    onResultPreview: (DocumentData) -> Unit,
    onImagePreview: (String) -> Unit,
    onResult: (String) -> Unit,
    onError: (Throwable) -> Unit,
) {
    val scope = rememberCoroutineScope()
    var pendingAction by remember { mutableStateOf<DocumentAction?>(null) }

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
                pendingAction = DocumentAction.CreateDocument
            }
        }
        MenuItem("Analyze Document Quality") {
            runWithValidLicense {
                pendingAction = DocumentAction.AnalyzeQuality
            }
        }
        MenuItem("Straighten Document") {
            runWithValidLicense {
                pendingAction = DocumentAction.StraightenImage
            }
        }
    }

    pendingAction?.let { action ->
        GalleryPicker(
            allowMultiple = action == DocumentAction.CreateDocument,
            onImagesSelected = { images ->
                scope.launch {
                    when (action) {
                        DocumentAction.CreateDocument -> {
                            createDocumentFromImages(images)?.let(onResultPreview)
                                ?: onError(Throwable("Failed to create document"))
                        }

                        DocumentAction.AnalyzeQuality -> {
                            images.firstOrNull()?.let { image ->
                                onResult(analyzeDocumentQualityOnImage(image))
                            } ?: onError(Throwable("No image selected"))
                        }

                        DocumentAction.StraightenImage -> {
                            val image = images.firstOrNull()
                            if (image == null) {
                                onError(Throwable("No image selected"))
                            } else {
                                straighteningImage(image)?.straightenedImage?.uniqueId?.let {
                                    onImagePreview(it.toString())
                                } ?: onResult("Could not straighten the image")
                            }
                        }
                    }
                    pendingAction = null
                }
            },
            onDismiss = { pendingAction = null },
        )
    }
}

private enum class DocumentAction {
    CreateDocument,
    AnalyzeQuality,
    StraightenImage,
}
