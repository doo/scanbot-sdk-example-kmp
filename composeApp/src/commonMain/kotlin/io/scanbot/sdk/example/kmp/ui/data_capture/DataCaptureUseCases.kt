package io.scanbot.sdk.example.kmp.ui.data_capture

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.image_recognizers.extractDocumentData
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.image_recognizers.recognizeCheckOnImage
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.image_recognizers.recognizeCreditCardOnImage
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.image_recognizers.recognizeMrzDocumentOnImage
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_check.startCheckScanner
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_credit_card.startCreditCardScanner
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_dde.startDocumentDataExtractor
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_mrz.startMrzScanner
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_text_pattern.startTextPatternScanner
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtuui_vin.startVinScanner
import io.scanbot.sdk.example.kmp.navigation.DataCaptureResultNavigator
import io.scanbot.sdk.example.kmp.ui.common.GalleryPicker
import io.scanbot.sdk.example.kmp.ui.common.MenuItem
import io.scanbot.sdk.example.kmp.ui.common.MenuSection
import kotlinx.coroutines.launch

@Composable
fun DataCaptureUseCases(
    runWithValidLicense: (action: () -> Unit) -> Unit,
    resultNavigator: DataCaptureResultNavigator,
    onError: (Throwable) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val pendingImageAction = remember { mutableStateOf<DataCaptureImageAction?>(null) }

    MenuSection("Data Capture Use Cases") {
        MenuItem("VIN Scanner") {
            runWithValidLicense {
                startVinScanner(
                    onResultHandler = resultNavigator::showVinResult,
                    onErrorHandler = onError
                )
            }
        }
        MenuItem("Check Scanner") {
            runWithValidLicense {
                startCheckScanner(
                    onResultHandler = resultNavigator::showCheckResult,
                    onErrorHandler = onError
                )
            }
        }
        MenuItem("MRZ Scanner") {
            runWithValidLicense {
                startMrzScanner(
                    onResultHandler = resultNavigator::showMrzResult,
                    onErrorHandler = onError
                )
            }
        }
        MenuItem("Document Data Extractor") {
            runWithValidLicense {
                startDocumentDataExtractor(
                    onResultHandler = resultNavigator::showDocumentDataResult,
                    onErrorHandler = onError
                )
            }
        }
        MenuItem("Text Pattern Scanner") {
            runWithValidLicense {
                startTextPatternScanner(
                    onResultHandler = resultNavigator::showTextPatternResult,
                    onErrorHandler = onError
                )
            }
        }
        MenuItem("Credit Card Scanner") {
            runWithValidLicense {
                startCreditCardScanner(
                    onResultHandler = resultNavigator::showCreditCardResult,
                    onErrorHandler = onError
                )
            }
        }
        MenuItem("Scan MRZ from Image") {
            runWithValidLicense {
                pendingImageAction.value = DataCaptureImageAction.Mrz
            }
        }
        MenuItem("Scan Check from Image") {
            runWithValidLicense {
                pendingImageAction.value = DataCaptureImageAction.Check
            }
        }
        MenuItem("Extract Document Data from Image") {
            runWithValidLicense {
                pendingImageAction.value = DataCaptureImageAction.DocumentData
            }
        }
        MenuItem("Scan Credit Card from Image") {
            runWithValidLicense {
                pendingImageAction.value = DataCaptureImageAction.CreditCard
            }
        }
    }

    pendingImageAction.value?.let { action ->
        GalleryPicker(
            allowMultiple = false,
            onImagesSelected = { images ->
                scope.launch {
                    when (action) {
                        DataCaptureImageAction.Mrz -> images.firstOrNull()?.let { image ->
                            recognizeMrzDocumentOnImage(image)
                                .onSuccess(resultNavigator::showMrzResult)
                                .onFailure(onError)
                        } ?: onError(Throwable("No image selected"))

                        DataCaptureImageAction.Check -> images.firstOrNull()?.let { image ->
                            recognizeCheckOnImage(image)
                                .onSuccess(resultNavigator::showCheckResult)
                                .onFailure(onError)
                        } ?: onError(Throwable("No image selected"))

                        DataCaptureImageAction.DocumentData -> images.firstOrNull()?.let { image ->
                            extractDocumentData(image)
                                .onSuccess(resultNavigator::showDocumentDataResult)
                                .onFailure(onError)
                        } ?: onError(Throwable("No image selected"))

                        DataCaptureImageAction.CreditCard -> images.firstOrNull()?.let { image ->
                            recognizeCreditCardOnImage(image)
                                .onSuccess(resultNavigator::showCreditCardResult)
                                .onFailure(onError)
                        } ?: onError(Throwable("No image selected"))
                    }
                    pendingImageAction.value = null
                }
            },
            onDismiss = { pendingImageAction.value = null },
        )
    }
}

private enum class DataCaptureImageAction {
    Mrz,
    Check,
    DocumentData,
    CreditCard,

}
