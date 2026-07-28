package io.scanbot.sdk.example.kmp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.scanbot.sdk.example.kmp.doc_code_snippets.analyzeDocumentQualityOnImage
import io.scanbot.sdk.example.kmp.doc_code_snippets.createDocumentFromImages
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ocr.performOcrOnImages
import io.scanbot.sdk.example.kmp.doc_code_snippets.straightening.straighteningImage
import io.scanbot.sdk.example.kmp.ui.common.ErrorDialog
import io.scanbot.sdk.example.kmp.ui.common.Footer
import io.scanbot.sdk.example.kmp.ui.common.GalleryPicker
import io.scanbot.sdk.example.kmp.ui.common.InfoDialog
import io.scanbot.sdk.example.kmp.ui.common.LicenseGuard
import io.scanbot.sdk.example.kmp.ui.common.LicenseInfoDialog
import io.scanbot.sdk.example.kmp.ui.common.MenuItem
import io.scanbot.sdk.example.kmp.ui.common.TopBar
import io.scanbot.sdk.example.kmp.ui.data_capture.DataCaptureUseCases
import io.scanbot.sdk.example.kmp.ui.document.DocumentAction
import io.scanbot.sdk.example.kmp.ui.document.DocumentUseCases
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.page.DocumentData
import kotlinx.coroutines.launch

@Composable
fun UseCasesMenuScreen(
    onResultPreview: (DocumentData) -> Unit,
    onStraightenedImagePreview: (String) -> Unit,
) {
    val scope = rememberCoroutineScope()
    var pendingAction by remember { mutableStateOf<DocumentAction?>(null) }
    var useCaseResult by remember { mutableStateOf<String?>(null) }
    var useCaseError by remember { mutableStateOf<Throwable?>(null) }
    var showLicenseDialog by rememberSaveable { mutableStateOf(false) }
    var showCleanupConfirmation by rememberSaveable { mutableStateOf(false) }
    var cleanupStorageResult by rememberSaveable { mutableStateOf<String?>(null) }

    LicenseGuard { checkLicense ->
        Scaffold(
            topBar = { TopBar(title = "Scanbot SDK KMP Example") },
            bottomBar = { Footer() }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DocumentUseCases(
                    checkLicense = checkLicense,
                    onResultPreview = onResultPreview,
                    onAction = { pendingAction = it },
                    onError = { useCaseError = it }
                )

                Spacer(Modifier.height(24.dp))

                DataCaptureUseCases(
                    checkLicense = checkLicense,
                    onResult = { useCaseResult = it },
                    onError = { useCaseError = it }
                )

                Spacer(Modifier.height(24.dp))

                MenuItem("Clean up Storage") { showCleanupConfirmation = true }
                MenuItem("View License Info") { showLicenseDialog = true }
            }

            pendingAction?.let { action ->
                GalleryPicker(
                    allowMultiple = action == DocumentAction.CreateDocument ||
                        action == DocumentAction.PerformOcr,
                    onImagesSelected = { images ->
                        scope.launch {
                            when (action) {
                                DocumentAction.CreateDocument -> {
                                    createDocumentFromImages(images)?.let(onResultPreview)
                                        ?: run {
                                            useCaseError = Throwable("Failed to create document")
                                        }
                                }

                                DocumentAction.AnalyzeQuality -> images.firstOrNull()?.let {
                                    useCaseResult = analyzeDocumentQualityOnImage(it)
                                } ?: run {
                                    useCaseError = Throwable("No image selected")
                                }

                                DocumentAction.PerformOcr -> {
                                    useCaseResult = performOcrOnImages(images)
                                }

                                DocumentAction.StraightenImage -> {
                                    val image = images.firstOrNull()
                                    if (image == null) {
                                        useCaseError = Throwable("No image selected")
                                    } else {
                                        straighteningImage(image)?.straightenedImage?.uniqueId?.let {
                                            onStraightenedImagePreview(it.toString())
                                        } ?: run {
                                            useCaseResult = "Could not straighten the image"
                                        }
                                    }
                                }
                            }
                            pendingAction = null
                        }
                    },
                    onDismiss = { pendingAction = null }
                )
            }

            useCaseResult?.let { text ->
                InfoDialog("Result", text) { useCaseResult = null }
            }
            useCaseError?.let { error ->
                ErrorDialog(message = error.message) { useCaseError = null }
            }

            if (showCleanupConfirmation) {
                AlertDialog(
                    onDismissRequest = { showCleanupConfirmation = false },
                    title = { Text("Clean up Storage") },
                    text = { Text("Are you sure you want to clean up the storage?") },
                    confirmButton = {
                        TextButton(onClick = {
                            showCleanupConfirmation = false
                            ScanbotSDK.cleanupStorage().fold(
                                onSuccess = {
                                    cleanupStorageResult = "Storage cleaned up successfully."
                                },
                                onFailure = { cleanupStorageResult = it.message }
                            )
                        }) {
                            Text("Clean up", color = MaterialTheme.colorScheme.error)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showCleanupConfirmation = false }) {
                            Text("Cancel")
                        }
                    }
                )
            }

            if (showLicenseDialog) {
                LicenseInfoDialog(onDismiss = { showLicenseDialog = false })
            }

            cleanupStorageResult?.let {
                InfoDialog(
                    title = "Clean up Storage",
                    text = it,
                    onDismiss = { cleanupStorageResult = null }
                )
            }
        }
    }
}
