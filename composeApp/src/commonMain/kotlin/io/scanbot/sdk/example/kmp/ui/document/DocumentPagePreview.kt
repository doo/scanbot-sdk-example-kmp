package io.scanbot.sdk.example.kmp.ui.document

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import io.scanbot.sdk.example.kmp.doc_code_snippets.document.analyzeDocumentQualityOnImage
import io.scanbot.sdk.example.kmp.doc_code_snippets.document.applyFilterToDocumentPage
import io.scanbot.sdk.example.kmp.doc_code_snippets.document.startCroppingScreen
import io.scanbot.sdk.example.kmp.ui.ScanbotRed
import io.scanbot.sdk.example.kmp.ui.common.InfoDialog
import io.scanbot.sdk.example.kmp.ui.common.LicenseGuard
import io.scanbot.sdk.example.kmp.ui.common.TopBar
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.imageprocessing.BinarizationFilterPreset
import io.scanbot.sdk.kmp.imageprocessing.BrightnessFilter
import io.scanbot.sdk.kmp.imageprocessing.ColorDocumentFilter
import io.scanbot.sdk.kmp.imageprocessing.ContrastFilter
import io.scanbot.sdk.kmp.imageprocessing.CustomBinarizationFilter
import io.scanbot.sdk.kmp.imageprocessing.GrayscaleFilter
import io.scanbot.sdk.kmp.imageprocessing.OutputMode
import io.scanbot.sdk.kmp.imageprocessing.ScanbotBinarizationFilter
import io.scanbot.sdk.kmp.imageprocessing.WhiteBlackPointFilter
import io.scanbot.sdk.kmp.page.DocumentData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentPagePreviewScreen(
    documentUuid: String,
    pageUuid: String,
    onPopBackStack: () -> Unit,
) {
    var documentData by remember { mutableStateOf<DocumentData?>(null) }
    val page by remember(documentData) {
        derivedStateOf { documentData?.pages?.find { it.uuid == pageUuid } }
    }

    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var resultDialogMessage by remember { mutableStateOf<String?>(null) }
    var showDeleteConfirmation by remember { mutableStateOf(false) }
    var showFilterSheet by remember { mutableStateOf(false) }

    LaunchedEffect(documentUuid) {
        ScanbotSDK.document.loadDocument(documentUuid).fold(
            onSuccess = { documentData = it },
            onFailure = { println("Load document error: ${it.message}") })
    }

    LaunchedEffect(page) {
        imageBitmap = page?.documentImagePreviewURI?.let { uri ->
            ImageRef.fromPath(uri)?.encode()?.getOrNull()?.decodeToImageBitmap()
        }
        isLoading = false
    }

    LicenseGuard { checkLicense ->
        Scaffold(topBar = {
            TopBar(
                title = "Page Preview", showBackButton = true, onPopBackStack = onPopBackStack
            )
        }, bottomBar = {
            BottomAppBar(
                containerColor = ScanbotRed, contentColor = Color.White
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    TextButton(onClick = {
                        checkLicense {
                            documentData?.let { doc ->
                                startCroppingScreen(
                                    documentUuid = doc.uuid,
                                    pageUuid = pageUuid,
                                    handleResult = { updatedDocument ->
                                        documentData = updatedDocument
                                    },
                                    handleError = { resultDialogMessage = it.message })
                            }
                        }
                    }) {
                        Text(
                            "Crop",
                            color = Color.White,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                    TextButton(onClick = {
                        checkLicense { showFilterSheet = true }
                    }) {
                        Text(
                            "Filter",
                            color = Color.White,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }

                    TextButton(onClick = {
                        checkLicense {
                            page?.let { p ->
                                val imageRef = ImageRef.fromPath(
                                    p.documentImageURI ?: return@let
                                )
                                imageRef?.let {
                                    resultDialogMessage = analyzeDocumentQualityOnImage(it)
                                }
                            }
                        }
                    }) {
                        Text(
                            "Quality",
                            color = Color.White,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }

                    TextButton(onClick = {
                        checkLicense { showDeleteConfirmation = true }
                    }) {
                        Text(
                            "Delete",
                            color = Color.White,
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }) { paddingValues ->
            Box(
                modifier = Modifier.fillMaxSize().background(Color.Black).padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                AnimatedVisibility(
                    visible = isLoading, exit = fadeOut()
                ) {
                    CircularProgressIndicator(color = Color.White)
                }

                AnimatedVisibility(
                    visible = !isLoading && imageBitmap != null, enter = fadeIn()
                ) {
                    imageBitmap?.let {
                        Image(
                            bitmap = it,
                            contentDescription = "Page Preview",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

            if (showDeleteConfirmation) {
                AlertDialog(
                    onDismissRequest = { showDeleteConfirmation = false },
                    title = { Text("Delete Page") },
                    text = { Text("Are you sure you want to delete this page?") },
                    confirmButton = {
                        TextButton(onClick = {
                            showDeleteConfirmation = false
                            documentData?.uuid?.let { docUuid ->
                                page?.uuid?.let { pUuid ->
                                    ScanbotSDK.document.removePages(
                                        documentUuid = docUuid, pageUuids = listOf(pUuid)
                                    ).fold(
                                        onSuccess = { onPopBackStack() },
                                        onFailure = { println("Delete error: ${it.message}") })
                                }
                            }
                        }) {
                            Text("Delete", color = MaterialTheme.colorScheme.error)
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDeleteConfirmation = false }) {
                            Text("Cancel")
                        }
                    })
            }

            if (showFilterSheet) {
                val filters = listOf(
                    "None" to listOf(),
                    "Scanbot Binarization" to listOf(ScanbotBinarizationFilter()),
                    "Scanbot Binarization Antialiased" to listOf(
                        ScanbotBinarizationFilter(
                            outputMode = OutputMode.ANTIALIASED
                        )
                    ),
                    "Color Document" to listOf(ColorDocumentFilter()),
                    "Custom Binarization" to listOf(CustomBinarizationFilter(preset = BinarizationFilterPreset.PRESET_1)),
                    "Brightness" to listOf(BrightnessFilter(brightness = 0.4)),
                    "Contrast" to listOf(ContrastFilter(contrast = 125.0)),
                    "Grayscale" to listOf(GrayscaleFilter()),
                    "White Black Point" to listOf(
                        WhiteBlackPointFilter(
                            blackPoint = 0.5, whitePoint = 0.5
                        )
                    ),
                )

                ModalBottomSheet(onDismissRequest = { showFilterSheet = false }) {
                    LazyColumn {
                        items(filters) { (label, filterList) ->
                            TextButton(
                                onClick = {
                                    showFilterSheet = false
                                    documentData?.uuid?.let { docUuid ->
                                        page?.uuid?.let { pUuid ->
                                            applyFilterToDocumentPage(
                                                documentUuid = docUuid,
                                                pageUuid = pUuid,
                                                filters = filterList
                                            ).fold(
                                                onSuccess = { documentData = it },
                                                onFailure = { resultDialogMessage = it.message })
                                        }
                                    }
                                }, modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                            ) {
                                Text(label, style = MaterialTheme.typography.bodyLarge)
                            }
                        }
                    }
                }
            }

            resultDialogMessage?.let {
                InfoDialog("Result", it) { resultDialogMessage = null }
            }
        }
    }
}