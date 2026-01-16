package io.scanbot.sdk.example.kmp.ui.document

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.unit.dp
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.page.DocumentData

@Composable
fun DocumentPreviewScreen(
    resultJson: String,
) {
    val parsedDocument = remember(resultJson) {
        DocumentData.fromJson(resultJson)
    }

    val scrollState = rememberScrollState()

    val imageBitmaps = remember(parsedDocument) {
        parsedDocument.pages
            .mapNotNull { it.documentImagePreviewURI }
            .mapNotNull { path -> ImageRef.fromPath(path) }
            .mapNotNull { ref -> ref.encode()?.decodeToImageBitmap() }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(scrollState)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        imageBitmaps.forEach {
            Image(
                bitmap = it,
                contentDescription = "Document Image",
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Spacer(Modifier.height(12.dp))
        Text(
            text = resultJson,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}