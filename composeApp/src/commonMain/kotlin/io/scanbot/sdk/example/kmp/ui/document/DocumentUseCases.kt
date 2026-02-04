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
import io.github.ismoy.imagepickerkmp.domain.extensions.loadBytes
import io.github.ismoy.imagepickerkmp.presentation.ui.components.GalleryPickerLauncher
import io.scanbot.sdk.example.kmp.snippets.document.api.analyzeDocumentQualityOnImage
import io.scanbot.sdk.example.kmp.snippets.document.api.createDocumentFromImage
import io.scanbot.sdk.example.kmp.snippets.document.scanner.common_use_cases.startMultiPageScanning
import io.scanbot.sdk.example.kmp.snippets.document.scanner.common_use_cases.startSinglePageFinderScanning
import io.scanbot.sdk.example.kmp.snippets.document.scanner.common_use_cases.startSinglePageScanning
import io.scanbot.sdk.example.kmp.snippets.ocr.performOcrOnImage
import io.scanbot.sdk.example.kmp.ui.common.LicenseInvalidDialog
import io.scanbot.sdk.example.kmp.ui.common.MenuItem
import io.scanbot.sdk.example.kmp.ui.common.SelectImagesFromGallery
import io.scanbot.sdk.example.kmp.ui.common.TopBar
import io.scanbot.sdk.example.kmp.ui.common.isLicenseValid
import io.scanbot.sdk.kmp.image.ImageRef
import kotlinx.coroutines.launch

@Composable
fun DocumentUseCasesScreen(
    onResultPreview: (String) -> Unit,
    onPopBackStack: () -> Unit
) {
    val scope = rememberCoroutineScope()

    var pendingAction by remember { mutableStateOf<Action?>(null) }
    var dialogText by remember { mutableStateOf<String?>(null) }
    var showLicenseError by remember { mutableStateOf(false) }

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
            MenuItem("Single Page Scanning", { startSinglePageScanning(onResultPreview) })
            MenuItem("Single Page Scanning with Finder", { startSinglePageFinderScanning(onResultPreview) })
            MenuItem("Multi Page Scanning with Finder", { startMultiPageScanning(onResultPreview) })

            Spacer(Modifier.height(16.dp))

            MenuItem("Create Document from Images", { pendingAction = Action.CreateDocument })
            MenuItem("Analyze Document Quality", { pendingAction = Action.AnalyzeQuality })
            MenuItem("Perform OCR", { pendingAction = Action.PerformOcr})
        }

        pendingAction?.let { action ->
            SelectImagesFromGallery(
                onImagesSelected = { images ->
                    if (!isLicenseValid()) {
                        showLicenseError = true
                        pendingAction = null
                        return@SelectImagesFromGallery
                    }

                    scope.launch {
                        dialogText = when (action) {

                            Action.CreateDocument ->
                                createDocumentFromImage(images)
                                    .getOrThrow()
                                    .toJson()
                                    .toString()

                            Action.AnalyzeQuality ->
                                analyzeDocumentQualityOnImage(images.first())
                                    .getOrThrow()
                                    .toJson()
                                    .toString()

                            Action.PerformOcr ->
                                performOcrOnImage(images)
                                    .getOrThrow()
                                    .plainText
                        }

                        pendingAction = null
                    }
                },
                onDismiss = {
                    pendingAction = null
                }
            )
        }

        dialogText?.let { text ->
            AlertDialog(
                onDismissRequest = { dialogText = null },
                title = { Text("Result") },
                text = {
                    Text(
                        text = text,
                        fontFamily = FontFamily.Monospace
                    )
                },
                confirmButton = {
                    TextButton(onClick = { dialogText = null }) {
                        Text("Close")
                    }
                }
            )
        }

        if (showLicenseError) {
            LicenseInvalidDialog {
                showLicenseError = false
            }
        }
    }
}

@Composable
private fun GalleryPicker(
    onResult: (List<ImageRef>) -> Unit,
    onDismiss: () -> Unit
) {
    GalleryPickerLauncher(
        allowMultiple = true,
        onPhotosSelected = { photos ->
            val imageRefs = photos.mapNotNull { photo ->
                photo.loadBytes().let(ImageRef::fromEncodedBuffer)
            }

            if (imageRefs.isNotEmpty()) {
                onResult(imageRefs)
            } else {
                onDismiss()
            }
        },
        onDismiss = onDismiss,
        onError = {}
    )
}

private enum class Action() {
    CreateDocument,
    PerformOcr,
    AnalyzeQuality
}
