package io.scanbot.sdk.example.kmp.ui.document

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import io.scanbot.sdk.example.kmp.doc_code_snippets.document.analyzeDocumentQualityOnImage
import io.scanbot.sdk.example.kmp.doc_code_snippets.document.createDocumentFromImages
import io.scanbot.sdk.example.kmp.doc_code_snippets.document.scanner.common_use_cases.startMultiPageScanning
import io.scanbot.sdk.example.kmp.doc_code_snippets.document.scanner.common_use_cases.startSinglePageFinderScanning
import io.scanbot.sdk.example.kmp.doc_code_snippets.document.scanner.common_use_cases.startSinglePageScanning
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ocr.performOcrOnImages
import io.scanbot.sdk.example.kmp.ui.common.ErrorDialog
import io.scanbot.sdk.example.kmp.ui.common.LicenseGuard
import io.scanbot.sdk.example.kmp.ui.common.MenuItem
import io.scanbot.sdk.example.kmp.ui.common.GalleryPicker
import io.scanbot.sdk.example.kmp.ui.common.InfoDialog
import io.scanbot.sdk.example.kmp.ui.common.TopBar
import io.scanbot.sdk.kmp.page.DocumentData
import kotlinx.coroutines.launch

@Composable
fun DocumentUseCasesScreen(
    onResultPreview: (DocumentData) -> Unit,
    onPopBackStack: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var pendingAction by remember { mutableStateOf<Action?>(null) }
    var useCaseResult by remember { mutableStateOf<String?>(null) }
    var useCaseError by remember { mutableStateOf<Throwable?>(null) }

    LicenseGuard { checkLicense ->
        Scaffold(
            topBar = {
                TopBar(
                    title = "Document Use cases",
                    showBackButton = true,
                    onPopBackStack = onPopBackStack
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MenuItem("Single Page Scanning", {
                    checkLicense { startSinglePageScanning(onResultPreview, { useCaseError = it }) }
                })
                MenuItem("Single Page Scanning with Finder", {
                    checkLicense { startSinglePageFinderScanning(onResultPreview, { useCaseError = it }) }
                })
                MenuItem("Multi Page Scanning with Finder", {
                    checkLicense { startMultiPageScanning(onResultPreview, { useCaseError = it }) }
                })

                Spacer(Modifier.height(16.dp))

                MenuItem("Create Document from Images", {
                    checkLicense { pendingAction = Action.CreateDocument }
                })
                MenuItem("Analyze Document Quality", {
                    checkLicense { pendingAction = Action.AnalyzeQuality }
                })
                MenuItem("Perform OCR", {
                    checkLicense { pendingAction = Action.PerformOcr }
                })
            }

            pendingAction?.let { action ->
                GalleryPicker(
                    allowMultiple = action == Action.CreateDocument || action == Action.PerformOcr,
                    onImagesSelected = { images ->
                        scope.launch {
                            when (action) {
                                Action.CreateDocument -> {
                                    createDocumentFromImages(images)?.let {
                                        onResultPreview(it)
                                    } ?: run { useCaseError = Throwable("Failed to create document") }
                                }
                                Action.AnalyzeQuality -> images.firstOrNull()?.let {
                                    useCaseResult = analyzeDocumentQualityOnImage(it)
                                } ?: run { useCaseError = Throwable("No image selected") }
                                Action.PerformOcr -> useCaseResult = performOcrOnImages(images)
                            }
                            pendingAction = null
                        }
                    },
                    onDismiss = { pendingAction = null }
                )
            }

            useCaseResult?.let { text ->
                InfoDialog("Result", text) { useCaseResult = null}
            }

            useCaseError?.let { error ->
                ErrorDialog(message = error.message) { useCaseError = null}
            }
        }
    }
}

private enum class Action() {
    CreateDocument,
    PerformOcr,
    AnalyzeQuality
}
