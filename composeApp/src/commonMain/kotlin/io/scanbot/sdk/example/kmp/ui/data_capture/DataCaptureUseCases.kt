package io.scanbot.sdk.example.kmp.ui.data_capture

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.extractDocumentData
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ocr.performOcrOnImages
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.scanCheck
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.scanCreditCard
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.scanMrzFrom
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
    var pendingImageAction by remember { mutableStateOf<DataCaptureImageAction?>(null) }

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
                pendingImageAction = DataCaptureImageAction.Ocr
            }
        }
        MenuItem("Scan MRZ from Image") {
            runWithValidLicense {
                pendingImageAction = DataCaptureImageAction.Mrz
            }
        }
        MenuItem("Scan Check from Image") {
            runWithValidLicense {
                pendingImageAction = DataCaptureImageAction.Check
            }
        }
        MenuItem("Extract Document Data from Image") {
            runWithValidLicense {
                pendingImageAction = DataCaptureImageAction.DocumentData
            }
        }
        MenuItem("Scan Credit Card from Image") {
            runWithValidLicense {
                pendingImageAction = DataCaptureImageAction.CreditCard
            }
        }
    }

    pendingImageAction?.let { action ->
        GalleryPicker(
            allowMultiple = action == DataCaptureImageAction.Ocr,
            onImagesSelected = { images ->
                scope.launch {
                    when (action) {
                        DataCaptureImageAction.Ocr -> onResult(performOcrOnImages(images))
                        DataCaptureImageAction.Mrz -> images.firstOrNull()?.let { image ->
                            scanMrzFrom(image)
                                .onSuccess {
                                    onResult(it.toString())
                                    it.close()
                                }
                                .onFailure(onError)
                        } ?: onError(Throwable("No image selected"))

                        DataCaptureImageAction.Check -> images.firstOrNull()?.let { image ->
                            scanCheck(image)
                                .onSuccess {
                                    onResult(it.toString())
                                    it.close()
                                }
                                .onFailure(onError)
                        } ?: onError(Throwable("No image selected"))

                        DataCaptureImageAction.DocumentData -> images.firstOrNull()?.let { image ->
                            extractDocumentData(image)
                                .onSuccess {
                                    onResult(it.toString())
                                    it.close()
                                }
                                .onFailure(onError)
                        } ?: onError(Throwable("No image selected"))

                        DataCaptureImageAction.CreditCard -> images.firstOrNull()?.let { image ->
                            scanCreditCard(image)
                                .onSuccess {
                                    onResult(it.toString())
                                    it.close()
                                }
                                .onFailure(onError)
                        } ?: onError(Throwable("No image selected"))
                    }
                    pendingImageAction = null
                }
            },
            onDismiss = { pendingImageAction = null },
        )
    }
}

private enum class DataCaptureImageAction {
    Ocr,
    Mrz,
    Check,
    DocumentData,
    CreditCard,
}
