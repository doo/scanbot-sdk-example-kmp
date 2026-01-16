package io.scanbot.sdk.example.kmp.ui.document

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import io.github.ismoy.imagepickerkmp.domain.extensions.loadBytes
import io.github.ismoy.imagepickerkmp.presentation.ui.components.GalleryPickerLauncher
import io.scanbot.sdk.example.kmp.snippets.document.scanner.common_use_cases.startMultiPageScanning
import io.scanbot.sdk.example.kmp.snippets.document.scanner.common_use_cases.startSinglePageFinderScanning
import io.scanbot.sdk.example.kmp.snippets.document.scanner.common_use_cases.startSinglePageScanning
import io.scanbot.sdk.example.kmp.ui.components.MenuItem
import io.scanbot.sdk.kmp.ScanbotSDK
import io.scanbot.sdk.kmp.documentqualityanalyzer.DocumentQualityAnalyzerConfiguration
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.ocr.OcrConfiguration
import io.scanbot.sdk.kmp.ocr.OcrEngine
import kotlinx.coroutines.launch

@Composable
fun DocumentUseCasesScreen(
    onResultPreview: (String) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    var pendingAction by remember { mutableStateOf<Action?>(null) }
    var dialogText by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        MenuItem("Single Page Scanning") {
            startSinglePageScanning(onResultPreview)
        }

        MenuItem("Single Page Scanning with Finder") {
            startSinglePageFinderScanning(onResultPreview)
        }

        MenuItem("Multi Page Scanning with Finder") {
            startMultiPageScanning(onResultPreview)
        }

        Spacer(Modifier.height(16.dp))

        MenuItem("Create Document from Images") {
            pendingAction = Action.CreateDocument
        }

        MenuItem("Analyze Document Quality") {
            pendingAction = Action.AnalyzeQuality
        }

        MenuItem("Perform OCR") {
            pendingAction = Action.PerformOcr
        }
    }

    pendingAction?.let { action ->
        GalleryPicker(
            onResult = { images ->
                coroutineScope.launch {
                    dialogText =
                        when (action) {
                            Action.CreateDocument -> {
                                ScanbotSDK.document
                                    .createDocumentFromImages(images, options = null)
                                    .getOrThrow()
                                    .toJson()
                                    .toString()
                            }

                            Action.AnalyzeQuality -> {
                                val configuration = DocumentQualityAnalyzerConfiguration()

                                ScanbotSDK.document
                                    .analyzeQualityOnImage(images.first(), configuration)
                                    .getOrThrow()
                                    .toJson()
                                    .toString()
                            }

                            Action.PerformOcr -> {
                                ScanbotSDK.ocrEngine
                                    .recognizeOnImages(
                                        imageRefs = images,
                                        ocrConfiguration = OcrConfiguration(
                                            recognitionMode = OcrEngine.TESSERACT,
                                            languages = listOf("en")
                                        )
                                    )
                                    .getOrThrow()
                                    .plainText
                            }
                        }
                }
            },
            onDismiss = {}
        )
    }

    dialogText?.let {
        AlertDialog(
            onDismissRequest = { },
            title = { Text("Result") },
            text = {
                Text(
                    text = it,
                    fontFamily = FontFamily.Monospace
                )
            },
            confirmButton = {
                TextButton(onClick = { }) {
                    Text("Close")
                }
            }
        )
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
