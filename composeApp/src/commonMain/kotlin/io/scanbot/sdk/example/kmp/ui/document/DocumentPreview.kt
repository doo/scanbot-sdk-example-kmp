package io.scanbot.sdk.example.kmp.ui.document

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import io.scanbot.sdk.example.kmp.doc_code_snippets.addPages
import io.scanbot.sdk.example.kmp.doc_code_snippets.createBinarizedTiffFromDocument
import io.scanbot.sdk.example.kmp.doc_code_snippets.createPdfFromDocument
import io.scanbot.sdk.example.kmp.doc_code_snippets.createSearchablePdfFromDocument
import io.scanbot.sdk.example.kmp.doc_code_snippets.createTiffFromDocument
import io.scanbot.sdk.example.kmp.doc_code_snippets.removeAllPagesFromDocument
import io.scanbot.sdk.example.kmp.ui.ScanbotRed
import io.scanbot.sdk.example.kmp.ui.common.InfoDialog
import io.scanbot.sdk.example.kmp.ui.common.LicenseGuard
import io.scanbot.sdk.example.kmp.ui.common.TopBar
import io.scanbot.sdk.example.kmp.ui.common.rememberImagePickerLauncher
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.page.DocumentData
import io.scanbot.sdk.kmp.page.PageData
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentPreviewScreen(
    documentUuid: String,
    navigateToPagePreview: (documentUuid: String, pageUuid: String) -> Unit,
    onPopBackStack: () -> Unit,
) {
    var documentData by remember { mutableStateOf<DocumentData?>(null) }
    var resultDialogMessage by remember { mutableStateOf<String?>(null) }
    var showExportSheet by remember { mutableStateOf(false) }
    var showDeleteAllConfirmation by remember { mutableStateOf(false) }

    val pickImagesForAddPages = rememberImagePickerLauncher(
        allowMultiple = true,
        onImagesSelected = { images ->
            documentData?.uuid?.let { uuid ->
                addPages(documentUuid = uuid, images = images)
                    .onSuccess { updatedDoc -> documentData = updatedDoc }
                    .onFailure { error ->
                        resultDialogMessage = "Add pages failed: ${error.message}"
                    }
            }
        },
        onError = { error ->
            resultDialogMessage = "Add pages failed: ${error.message}"
        },
    )

    LaunchedEffect(documentUuid) {
        ScanbotSDK.document.loadDocument(documentUuid).fold(
            onSuccess = { documentData = it },
            onFailure = { println("Load document error: ${it.message}") }
        )
    }

    LicenseGuard { runWithValidLicense ->
        Scaffold(topBar = {
            TopBar(title = "Documents preview", showBackButton = true, onPopBackStack)
        }, bottomBar = {
            BottomAppBar(
                containerColor = ScanbotRed, contentColor = Color.White
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(onClick = {
                        runWithValidLicense {
                            documentData?.let {
                                ScanbotSDK.document.startScanner(
                                    configuration = DocumentScanningFlow(documentUuid = it.uuid),
                                    onResult = { result ->
                                        // Keep the current document if scanning failed.
                                        result.onSuccess { updatedDocument -> documentData = updatedDocument }
                                            .onFailure { error ->
                                                resultDialogMessage = "Scanning failed: ${error.message}"
                                            }
                                    })
                            }
                        }
                    }) {
                        Text(
                            "Continue\nScanning",
                            color = Color.White,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }

                    TextButton(
                        onClick = {
                            runWithValidLicense { pickImagesForAddPages() }
                        }
                    ) {
                        Text(
                            "Add Page",
                            color = Color.White,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }

                    TextButton(
                        onClick = {
                            runWithValidLicense { showExportSheet = true }
                        }
                    ) {
                        Text(
                            "Export",
                            color = Color.White,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }

                    TextButton(onClick = {
                        runWithValidLicense { showDeleteAllConfirmation = true }
                    }) {
                        Text(
                            "Delete All",
                            color = Color.White,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }) { paddingValues ->
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 100.dp),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(paddingValues)
            ) {
                documentData?.let {
                    items(it.pages) { page ->
                        PagePreviewItem(page = page, onClick = {
                            navigateToPagePreview(it.uuid, page.uuid)
                        })
                    }
                }
            }

            if (showExportSheet) {
                ModalBottomSheet(onDismissRequest = { showExportSheet = false }) {
                    ExportBottomSheetContent(onExportPdf = { withOcr ->
                        showExportSheet = false
                        val uuid = documentData?.uuid ?: return@ExportBottomSheetContent
                        val type = if (withOcr) "Searchable PDF File" else "PDF File"

                        val result = if (withOcr) {
                            createSearchablePdfFromDocument(documentId = uuid)
                        } else {
                            createPdfFromDocument(documentId = uuid)
                        }
                        result.onSuccess { path -> resultDialogMessage = "$type created: $path" }
                            .onFailure { error -> resultDialogMessage = "$type export failed: ${error.message}" }
                    }, onExportTiff = { binarized ->
                        showExportSheet = false
                        val uuid = documentData?.uuid ?: return@ExportBottomSheetContent
                        val type = if (binarized) "Binarized TIFF File" else "TIFF File"

                        val result = if (binarized) {
                            createBinarizedTiffFromDocument(documentUuid = uuid)
                        } else {
                            createTiffFromDocument(documentUuid = uuid)
                        }
                        result.onSuccess { path -> resultDialogMessage = "$type created: $path" }
                            .onFailure { error -> resultDialogMessage = "$type export failed: ${error.message}" }
                    }, onCancel = { showExportSheet = false })
                }
            }

            if (showDeleteAllConfirmation) {
                AlertDialog(
                    onDismissRequest = { showDeleteAllConfirmation = false },
                    title = { Text("Delete All Pages") },
                    text = { Text("Are you sure you want to delete all pages?") },
                    confirmButton = {
                        TextButton(onClick = {
                            showDeleteAllConfirmation = false
                            documentData?.let {
                                removeAllPagesFromDocument(documentUuid = it.uuid)
                                    .onSuccess { updatedDocument -> documentData = updatedDocument }
                                    .onFailure { error ->
                                        resultDialogMessage = "Delete all pages failed: ${error.message}"
                                    }
                            }
                        }) {
                            Text("Delete", color = MaterialTheme.colorScheme.error)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDeleteAllConfirmation = false }) {
                            Text("Cancel")
                        }
                    })
            }

            resultDialogMessage?.let {
                InfoDialog("Result", it) { resultDialogMessage = null }
            }
        }
    }
}

@Composable
fun PagePreviewItem(page: PageData, onClick: () -> Unit) {
    var imageBitmap by remember(page) { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(page) {
        withContext(Dispatchers.Default) {
            imageBitmap = (page.documentImagePreviewURI ?: page.originalImageURI).let { uri ->
                ImageRef.fromPath(uri)?.encode()?.getOrNull()?.decodeToImageBitmap()
            }
        }
    }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(0.7f)
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        imageBitmap?.let {
            Image(
                bitmap = it,
                contentDescription = "Page Preview",
                contentScale = ContentScale.Inside,
                modifier = Modifier.fillMaxSize()
            )
        } ?: Box(Modifier.fillMaxSize())
    }
}

@Composable
fun ExportBottomSheetContent(
    onExportPdf: (withOcr: Boolean) -> Unit,
    onExportTiff: (binarized: Boolean) -> Unit,
    onCancel: () -> Unit
) {
    Column(modifier = Modifier.padding(bottom = 32.dp)) {
        ExportItem(Icons.Default.PictureAsPdf, "Save as PDF") { onExportPdf(false) }
        ExportItem(Icons.Default.PictureAsPdf, "Save as PDF with OCR") { onExportPdf(true) }
        ExportItem(Icons.Default.Image, "Save as TIFF (1-bit B&W)") { onExportTiff(true) }
        ExportItem(Icons.Default.Image, "Save as TIFF (color)") { onExportTiff(false) }
        ExportItem(Icons.Default.Cancel, "Cancel") { onCancel() }
    }
}

@Composable
fun ExportItem(icon: ImageVector, text: String, onClick: () -> Unit) {
    ListItem(
        leadingContent = { Icon(icon, contentDescription = null) },
        headlineContent = { Text(text) },
        modifier = Modifier.clickable(onClick = onClick)
    )
}