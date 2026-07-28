package io.scanbot.sdk.example.kmp.ui.data_capture

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ocr.performOcrOnImages
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ready_to_use_ui.startCheckScanner
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ready_to_use_ui.startCreditCardScanner
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ready_to_use_ui.startDocumentDataExtractor
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ready_to_use_ui.startMrzScanner
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ready_to_use_ui.startTextPatternScanner
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ready_to_use_ui.startVinScanner
import io.scanbot.sdk.example.kmp.ui.common.GalleryPicker
import io.scanbot.sdk.example.kmp.ui.common.MenuItem
import io.scanbot.sdk.example.kmp.ui.common.MenuSection
import kotlinx.coroutines.launch

@Composable
fun DataCaptureUseCases(
    runWithValidLicense: (action: () -> Unit) -> Unit,
    onResult: (String) -> Unit,
    onError: (Throwable) -> Unit,
) {
    val scope = rememberCoroutineScope()
    var showOcrImagePicker by remember { mutableStateOf(false) }

    MenuSection("Data Capture Use Cases") {
        MenuItem("VIN Scanner") {
            runWithValidLicense {
                startVinScanner(
                    onResultHandler = { onResult(it.toString()) },
                    onErrorHandler = onError
                )
            }
        }
        MenuItem("Check Scanner") {
            runWithValidLicense {
                startCheckScanner(
                    onResultHandler = {
                        onResult(it.toString())
                        it.close()
                    },
                    onErrorHandler = onError
                )
            }
        }
        MenuItem("MRZ Scanner") {
            runWithValidLicense {
                startMrzScanner(
                    onResultHandler = {
                        onResult(it.toString())
                        it.close()
                    },
                    onErrorHandler = onError
                )
            }
        }
        MenuItem("Document Data Extractor") {
            runWithValidLicense {
                startDocumentDataExtractor(
                    onResultHandler = {
                        onResult(it.toString())
                        it.close()
                    },
                    onErrorHandler = onError
                )
            }
        }
        MenuItem("Text Pattern Scanner") {
            runWithValidLicense {
                startTextPatternScanner(
                    onResultHandler = { onResult(it.toString()) },
                    onErrorHandler = onError
                )
            }
        }
        MenuItem("Credit Card Scanner") {
            runWithValidLicense {
                startCreditCardScanner(
                    onResultHandler = {
                        onResult(it.toString())
                        it.close()
                    },
                    onErrorHandler = onError
                )
            }
        }
        MenuItem("Perform OCR") {
            runWithValidLicense {
                showOcrImagePicker = true
            }
        }
    }

    if (showOcrImagePicker) {
        GalleryPicker(
            allowMultiple = true,
            onImagesSelected = { images ->
                scope.launch {
                    onResult(performOcrOnImages(images))
                    showOcrImagePicker = false
                }
            },
            onDismiss = { showOcrImagePicker = false },
        )
    }
}
