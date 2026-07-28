package io.scanbot.sdk.example.kmp.ui.data_capture

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ready_to_use_ui.startCheckScanner
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ready_to_use_ui.startCreditCardScanner
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ready_to_use_ui.startDocumentDataExtractor
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ready_to_use_ui.startMrzScanner
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ready_to_use_ui.startTextPatternScanner
import io.scanbot.sdk.example.kmp.doc_code_snippets.data_capture.ready_to_use_ui.startVinScanner
import io.scanbot.sdk.example.kmp.ui.common.MenuItem

@Composable
fun DataCaptureUseCases(
    checkLicense: (() -> Unit) -> Unit,
    onResult: (String) -> Unit,
    onError: (Throwable) -> Unit,
) {
    Text("Data Capture Use Cases", style = MaterialTheme.typography.titleMedium)
    Spacer(Modifier.height(8.dp))

    MenuItem("VIN Scanner") {
        checkLicense {
            startVinScanner(
                onResultHandler = { onResult(it.toString()) },
                onErrorHandler = onError
            )
        }
    }
    MenuItem("Check Scanner") {
        checkLicense {
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
        checkLicense {
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
        checkLicense {
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
        checkLicense {
            startTextPatternScanner(
                onResultHandler = { onResult(it.toString()) },
                onErrorHandler = onError
            )
        }
    }
    MenuItem("Credit Card Scanner") {
        checkLicense {
            startCreditCardScanner(
                onResultHandler = {
                    onResult(it.toString())
                    it.close()
                },
                onErrorHandler = onError
            )
        }
    }
}
