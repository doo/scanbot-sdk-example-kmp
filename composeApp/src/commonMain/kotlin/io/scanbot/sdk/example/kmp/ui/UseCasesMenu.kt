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
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ocr.performOcrOnImages
import io.scanbot.sdk.example.kmp.doc_code_snippets.straightening.straighteningImage
import io.scanbot.sdk.example.kmp.ui.common.ErrorDialog
import io.scanbot.sdk.example.kmp.ui.common.Footer
import io.scanbot.sdk.example.kmp.ui.common.GalleryPicker
import io.scanbot.sdk.example.kmp.ui.common.InfoDialog
import io.scanbot.sdk.example.kmp.ui.common.LicenseGuard
import io.scanbot.sdk.example.kmp.ui.common.LicenseInfoDialog
import io.scanbot.sdk.example.kmp.ui.common.MenuItem
import io.scanbot.sdk.example.kmp.ui.common.MenuSection
import io.scanbot.sdk.example.kmp.ui.common.TopBar
import io.scanbot.sdk.example.kmp.navigation.DataCaptureResultNavigator
import io.scanbot.sdk.example.kmp.ui.data_capture.DataCaptureUseCases
import io.scanbot.sdk.example.kmp.ui.document.DocumentUseCases
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.page.DocumentData
import kotlinx.coroutines.launch

@Composable
fun UseCasesMenuScreen(
    onResultPreview: (DocumentData) -> Unit,
    onImagePreview: (String) -> Unit,
    dataCaptureResultNavigator: DataCaptureResultNavigator,
) {
    var useCaseResult by rememberSaveable { mutableStateOf<String?>(null) }
    var useCaseError by rememberSaveable { mutableStateOf<String?>(null) }
    var showLicenseDialog by rememberSaveable { mutableStateOf(false) }
    var showCleanupConfirmation by rememberSaveable { mutableStateOf(false) }
    var cleanupStorageResult by rememberSaveable { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()
    val pendingMiscellaneousAction = remember { mutableStateOf<MiscellaneousImageAction?>(null) }

    LicenseGuard { runWithValidLicense ->
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
                    runWithValidLicense = runWithValidLicense,
                    onResultPreview = onResultPreview,
                    onError = { useCaseError = it.message },
                )

                Spacer(Modifier.height(24.dp))

                DataCaptureUseCases(
                    runWithValidLicense = runWithValidLicense,
                    resultNavigator = dataCaptureResultNavigator,
                    onError = { useCaseError = it.message },
                )

                Spacer(Modifier.height(24.dp))

                MenuSection("MISCELLANEOUS") {
                    MenuItem("Analyze Document Quality") {
                        runWithValidLicense {
                            pendingMiscellaneousAction.value = MiscellaneousImageAction.AnalyzeQuality
                        }
                    }
                    MenuItem("Perform OCR") {
                        runWithValidLicense {
                            pendingMiscellaneousAction.value = MiscellaneousImageAction.Ocr
                        }
                    }
                    MenuItem("Straighten Document") {
                        runWithValidLicense {
                            pendingMiscellaneousAction.value = MiscellaneousImageAction.StraightenImage
                        }
                    }
                    MenuItem("View License Info") { showLicenseDialog = true }
                    MenuItem("Clean up Storage") { showCleanupConfirmation = true }
                }
            }

            pendingMiscellaneousAction.value?.let { action ->
                GalleryPicker(
                    allowMultiple = action == MiscellaneousImageAction.Ocr,
                    onImagesSelected = { images ->
                        scope.launch {
                            when (action) {
                                MiscellaneousImageAction.AnalyzeQuality -> {
                                    images.firstOrNull()?.let { image ->
                                        useCaseResult = analyzeDocumentQualityOnImage(image)
                                    } ?: run {
                                        useCaseError = "No image selected"
                                    }
                                }

                                MiscellaneousImageAction.Ocr -> {
                                    useCaseResult = performOcrOnImages(images)
                                }

                                MiscellaneousImageAction.StraightenImage -> {
                                    val image = images.firstOrNull()
                                    if (image == null) {
                                        useCaseError = "No image selected"
                                    } else {
                                        straighteningImage(image)?.straightenedImage?.uniqueId?.let {
                                            onImagePreview(it.toString())
                                        } ?: run {
                                            useCaseResult = "Could not straighten the image"
                                        }
                                    }
                                }
                            }
                            pendingMiscellaneousAction.value = null
                        }
                    },
                    onDismiss = { pendingMiscellaneousAction.value = null },
                )
            }

            useCaseResult?.let { text ->
                InfoDialog("Result", text) { useCaseResult = null }
            }
            useCaseError?.let { message ->
                ErrorDialog(message = message) { useCaseError = null }
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

private enum class MiscellaneousImageAction {
    AnalyzeQuality,
    Ocr,
    StraightenImage,
}
