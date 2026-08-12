package io.scanbot.sdk.example.kmp.ui.data_capture

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.image_recognizers.extractDocumentData
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.image_recognizers.recognizeCheckOnImage
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.image_recognizers.recognizeCreditCardOnImage
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.image_recognizers.recognizeMrzDocumentOnImage
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtu_data_detectors.startRtuVinScanner
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtu_data_detectors.startRtuCheckScanner
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtu_data_detectors.startRtuCreditCardScanner
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtu_data_detectors.startRtuDocumentDataExtractor
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtu_data_detectors.startRtuMrzScanner
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.rtu_data_detectors.startRtuTextPatternScanner
import io.scanbot.sdk.example.kmp.navigation.DataCaptureResultNavigator
import io.scanbot.sdk.example.kmp.ui.common.MenuItem
import io.scanbot.sdk.example.kmp.ui.common.MenuSection
import io.scanbot.sdk.example.kmp.ui.common.rememberImagePickerLauncher
import kotlinx.coroutines.launch

@Composable
fun DataCaptureUseCases(
    runWithValidLicense: (action: () -> Unit) -> Unit,
    resultNavigator: DataCaptureResultNavigator,
    onError: (Throwable) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val pendingImageAction = remember { mutableStateOf<DataCaptureImageAction?>(null) }

    val pickImage = rememberImagePickerLauncher(
        allowMultiple = false,
        onImagesSelected = { images ->
            val action = pendingImageAction.value ?: return@rememberImagePickerLauncher
            pendingImageAction.value = null
            val image = images.firstOrNull()
            if (image == null) {
                onError(Throwable("No image selected"))
                return@rememberImagePickerLauncher
            }
            scope.launch {
                when (action) {
                    DataCaptureImageAction.Mrz -> recognizeMrzDocumentOnImage(image)
                        .onSuccess(resultNavigator::showMrzResult).onFailure(onError)

                    DataCaptureImageAction.Check -> recognizeCheckOnImage(image)
                        .onSuccess(resultNavigator::showCheckResult).onFailure(onError)

                    DataCaptureImageAction.DocumentData -> extractDocumentData(image)
                        .onSuccess(resultNavigator::showDocumentDataResult).onFailure(onError)

                    DataCaptureImageAction.CreditCard -> recognizeCreditCardOnImage(image)
                        .onSuccess(resultNavigator::showCreditCardResult).onFailure(onError)
                }
            }
        },
        onError = { error ->
            pendingImageAction.value = null
            onError(error)
        },
        onDismiss = { pendingImageAction.value = null },
    )

    fun triggerImageAction(action: DataCaptureImageAction) {
        pendingImageAction.value = action
        pickImage()
    }

    MenuSection("Data Capture Use Cases") {
        MenuItem("VIN Scanner") {
            runWithValidLicense {
                startRtuVinScanner(
                    onResultHandler = resultNavigator::showVinResult,
                    onErrorHandler = onError
                )
            }
        }
        MenuItem("Check Scanner") {
            runWithValidLicense {
                startRtuCheckScanner(
                    onResultHandler = resultNavigator::showCheckResult,
                    onErrorHandler = onError
                )
            }
        }
        MenuItem("MRZ Scanner") {
            runWithValidLicense {
                startRtuMrzScanner(
                    onResultHandler = resultNavigator::showMrzResult,
                    onErrorHandler = onError
                )
            }
        }
        MenuItem("Document Data Extractor") {
            runWithValidLicense {
                startRtuDocumentDataExtractor(
                    onResultHandler = resultNavigator::showDocumentDataResult,
                    onErrorHandler = onError
                )
            }
        }
        MenuItem("Text Pattern Scanner") {
            runWithValidLicense {
                startRtuTextPatternScanner(
                    onResultHandler = resultNavigator::showTextPatternResult,
                    onErrorHandler = onError
                )
            }
        }
        MenuItem("Credit Card Scanner") {
            runWithValidLicense {
                startRtuCreditCardScanner(
                    onResultHandler = resultNavigator::showCreditCardResult,
                    onErrorHandler = onError
                )
            }
        }
        MenuItem("Scan MRZ from Image") {
            runWithValidLicense { triggerImageAction(DataCaptureImageAction.Mrz) }
        }
        MenuItem("Scan Check from Image") {
            runWithValidLicense { triggerImageAction(DataCaptureImageAction.Check) }
        }
        MenuItem("Extract Document Data from Image") {
            runWithValidLicense { triggerImageAction(DataCaptureImageAction.DocumentData) }
        }
        MenuItem("Scan Credit Card from Image") {
            runWithValidLicense { triggerImageAction(DataCaptureImageAction.CreditCard) }
        }
    }
}

private enum class DataCaptureImageAction {
    Mrz,
    Check,
    DocumentData,
    CreditCard,
}