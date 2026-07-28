package io.scanbot.sdk.example.kmp.ui.document

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.scanbot.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startMultiPageScanning
import io.scanbot.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startSinglePageFinderScanning
import io.scanbot.sdk.example.kmp.doc_code_snippets.scanner.common_use_cases.startSinglePageScanning
import io.scanbot.sdk.example.kmp.ui.common.MenuItem
import io.scanbot.sdk.kmp.page.DocumentData

@Composable
fun DocumentUseCases(
    checkLicense: (() -> Unit) -> Unit,
    onResultPreview: (DocumentData) -> Unit,
    onAction: (DocumentAction) -> Unit,
    onError: (Throwable) -> Unit,
) {
    Text("Document Use Cases", style = MaterialTheme.typography.titleMedium)
    Spacer(Modifier.height(8.dp))

    MenuItem("Single Page Scanning") {
        checkLicense { startSinglePageScanning(onResultPreview, onError) }
    }
    MenuItem("Single Page Scanning with Finder") {
        checkLicense { startSinglePageFinderScanning(onResultPreview, onError) }
    }
    MenuItem("Multi Page Scanning with Finder") {
        checkLicense { startMultiPageScanning(onResultPreview, onError) }
    }
    MenuItem("Create Document from Images") {
        checkLicense { onAction(DocumentAction.CreateDocument) }
    }
    MenuItem("Analyze Document Quality") {
        checkLicense { onAction(DocumentAction.AnalyzeQuality) }
    }
    MenuItem("Perform OCR") {
        checkLicense { onAction(DocumentAction.PerformOcr) }
    }
    MenuItem("Straighten Document") {
        checkLicense { onAction(DocumentAction.StraightenImage) }
    }
}

enum class DocumentAction {
    CreateDocument,
    PerformOcr,
    AnalyzeQuality,
    StraightenImage,
}
