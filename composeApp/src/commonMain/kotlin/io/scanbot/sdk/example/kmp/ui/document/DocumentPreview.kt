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
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import io.scanbot.sdk.example.kmp.ui.ScanbotRed
import io.scanbot.sdk.example.kmp.ui.common.LicenseInvalidDialog
import io.scanbot.sdk.example.kmp.ui.common.SelectImagesFromGallery
import io.scanbot.sdk.example.kmp.ui.common.TopBar
import io.scanbot.sdk.example.kmp.ui.common.isLicenseValid
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.imageprocessing.ScanbotBinarizationFilter
import io.scanbot.sdk.kmp.page.DocumentData
import io.scanbot.sdk.kmp.page.PageData
import io.scanbot.sdk.kmp.pdfgeneration.PdfConfiguration
import io.scanbot.sdk.kmp.tiffgeneration.TiffGeneratorParameters
import io.scanbot.sdk.kmp.ui_v2.document.configuration.DocumentScanningFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentPreviewScreen(
    resultJson: String,
    onPopBackStack: () -> Unit
) {
    var documentData by remember(resultJson) { mutableStateOf(DocumentData.fromJson(resultJson)) }
    var resultDialogMessage by remember { mutableStateOf<String?>(null) }
    var showExportSheet by remember { mutableStateOf(false) }
    var showImagePicker by remember { mutableStateOf(false) }
    var showLicenseError by remember { mutableStateOf(false) }

    fun checkLicense(action: () -> Unit) {
        if (isLicenseValid()) {
            action()
        } else {
            showLicenseError = true
        }
    }

    Scaffold(
        topBar = {
            TopBar(title = "Documents preview", showBackButton = true, onPopBackStack)
        },
        bottomBar = {
            BottomAppBar(
                containerColor = ScanbotRed,
                contentColor = Color.White
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(onClick = {
                        checkLicense {
                            continueScanning(
                                documentUuid = documentData.uuid,
                                onDocumentUpdated = { documentData = it }
                            )
                        }
                    }) {
                        Text("Continue\nScanning", color = Color.White, style = MaterialTheme.typography.labelMedium)
                    }

                    TextButton(onClick = { checkLicense { showImagePicker = true } }) {
                        Text("Add Page", color = Color.White, style = MaterialTheme.typography.labelMedium)
                    }

                    TextButton(onClick = { checkLicense { showExportSheet = true } }) {
                        Text("Export", color = Color.White, style = MaterialTheme.typography.labelMedium)
                    }

                    TextButton(onClick = {
                        checkLicense {
                            deleteAllPages(
                                documentUuid = documentData.uuid,
                                onDocumentUpdated = { documentData = it }
                            )
                        }
                    }) {
                        Text("Delete All", color = Color.White, style = MaterialTheme.typography.labelMedium)
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 100.dp),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White)
        ) {
            items(documentData.pages) { page ->
                PagePreviewItem(page = page)
            }
        }

        if (showImagePicker) {
            SelectImagesFromGallery(
                allowMultiple = true,
                onImagesSelected = { images ->
                    showImagePicker = false
                    addPagesFromGallery(
                        documentUuid = documentData.uuid,
                        images = images,
                        onDocumentUpdated = { documentData = it }
                    )
                },
                onDismiss = { showImagePicker = false }
            )
        }

        if (showExportSheet) {
            ModalBottomSheet(onDismissRequest = { showExportSheet = false }) {
                ExportBottomSheetContent(
                    onExportPdf = { withOcr ->
                        showExportSheet = false
                        exportPdf(
                            documentUuid = documentData.uuid,
                            withOcr = withOcr,
                            onExported = { path ->
                                resultDialogMessage = "Result\nPdf File created: $path"
                            }
                        )
                    },
                    onExportTiff = { binarized ->
                        showExportSheet = false
                        exportTiff(
                            documentUuid = documentData.uuid,
                            binarized = binarized,
                            onExported = { path ->
                                resultDialogMessage = "Result\nTiff File created: $path"
                            }
                        )
                    },
                    onCancel = { showExportSheet = false }
                )
            }
        }

        if (resultDialogMessage != null) {
            AlertDialog(
                onDismissRequest = { resultDialogMessage = null },
                title = { Text("Result") },
                text = { Text(text = resultDialogMessage ?: "", fontFamily = FontFamily.Monospace) },
                confirmButton = {
                    TextButton(onClick = { resultDialogMessage = null }) { Text("Close") }
                }
            )
        }

        if (showLicenseError) {
            LicenseInvalidDialog { showLicenseError = false }
        }
    }
}

@Composable
fun PagePreviewItem(page: PageData) {
    var imageBitmap by remember(page) { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(page) {
        withContext(Dispatchers.Default) {
            page.documentImagePreviewURI?.let { uri ->
                ImageRef.fromPath(uri)?.encode()?.getOrNull()?.decodeToImageBitmap()
            }?.let {
                imageBitmap = it
            }
        }
    }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .aspectRatio(0.7f),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        if (imageBitmap != null) {
            Image(
                bitmap = imageBitmap!!,
                contentDescription = "Page Preview",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
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

private fun continueScanning(
    documentUuid: String,
    onDocumentUpdated: (DocumentData) -> Unit
) {
    ScanbotSDK.document.startScanner(
        configuration = DocumentScanningFlow(documentUuid = documentUuid),
        onResult = { result ->
            result.getOrNull()?.let(onDocumentUpdated)
        }
    )
}

private fun deleteAllPages(
    documentUuid: String,
    onDocumentUpdated: (DocumentData) -> Unit
) {
    onDocumentUpdated(
        ScanbotSDK.document.removeAllPages(documentUuid).getOrThrow()
    )
}

private fun addPagesFromGallery(
    documentUuid: String,
    images: List<ImageRef>,
    onDocumentUpdated: (DocumentData) -> Unit
) {
    ScanbotSDK.document.addPages(
        documentUuid = documentUuid,
        images = images
    ).getOrNull()?.let(onDocumentUpdated)
}

private fun exportPdf(
    documentUuid: String,
    withOcr: Boolean,
    onExported: (path: String) -> Unit
) {
    ScanbotSDK.pdfGenerator.generateFromDocument(
        documentUuid = documentUuid,
        pdfConfiguration = PdfConfiguration(),
        performOcr = withOcr
    ).getOrNull()?.let(onExported)
}

private fun exportTiff(
    documentUuid: String,
    binarized: Boolean,
    onExported: (path: String) -> Unit
) {
    val params = if (binarized) {
        TiffGeneratorParameters(
            binarizationFilter = ScanbotBinarizationFilter(),
            dpi = 300
        )
    } else {
        TiffGeneratorParameters()
    }

    ScanbotSDK.tiffGenerator.generateFromDocument(
        documentUuid = documentUuid,
        tiffGeneratorParameters = params
    ).getOrNull()?.let(onExported)
}
