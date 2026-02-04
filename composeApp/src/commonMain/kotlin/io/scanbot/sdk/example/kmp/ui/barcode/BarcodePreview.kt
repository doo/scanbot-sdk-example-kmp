package io.scanbot.sdk.example.kmp.ui.barcode

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.scanbot.sdk.example.kmp.ui.common.GenericDocumentView
import io.scanbot.sdk.example.kmp.ui.common.TopBar
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerUiItem
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerUiResult

@Composable
fun BarcodePreviewScreen(
    resultJson: String,
    onPopBackStack: () -> Unit,
) {
    val parsedResult = remember(resultJson) {
        BarcodeScannerUiResult.fromJson(resultJson)
    }

    Scaffold(
        topBar = {
            TopBar(title = "Barcodes preview", showBackButton = true, onPopBackStack)
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            itemsIndexed(parsedResult.items) { index, item ->
                Text(
                    text = "Barcode №${index + 1}",
                    modifier = Modifier.padding(bottom = 8.dp),
                    fontWeight = FontWeight.Bold
                )

                BarcodeCard(item)

                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun BarcodeCard(
    item: BarcodeScannerUiItem
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            item.barcode.sourceImage
                ?.encode()
                ?.getOrNull()
                ?.decodeToImageBitmap()
                ?.let { bitmap ->
                    Image(
                        bitmap = bitmap,
                        contentDescription = "Barcode image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                }

            Text(
                text = item.barcode.format.name,
                color = Color.Black
            )

            Text(
                text = buildString {
                    append(item.barcode.text)
                    if (item.barcode.upcEanExtension.isNotBlank()) {
                        append(" ${item.barcode.upcEanExtension}")
                    }
                },
                color = Color.Black
            )

            item.barcode.extractedDocument?.let { doc ->
                Spacer(Modifier.height(8.dp))
                GenericDocumentView(doc)
            }
        }
    }
}

