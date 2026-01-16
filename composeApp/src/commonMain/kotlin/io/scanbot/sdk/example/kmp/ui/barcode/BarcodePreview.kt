package io.scanbot.sdk.example.kmp.ui.barcode

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
import io.scanbot.sdk.kmp.ui_v2.barcode.configuration.BarcodeScannerUiResult

@Composable
fun BarcodePreviewScreen(
    resultJson: String,
) {
    val parsedBarcode = remember(resultJson) {
        BarcodeScannerUiResult.fromJson(resultJson)
    }

    val scrollState = rememberScrollState()
    val imageBitmaps = remember(parsedBarcode) {
        parsedBarcode.items
            .mapNotNull { it.barcode.sourceImage }
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
        imageBitmaps.forEach { bitmap ->
            Image(
                bitmap = bitmap,
                contentDescription = "Barcode Image",
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        Spacer(Modifier.height(12.dp))

        Text("Raw JSON:")
        Text(
            text = resultJson,
            modifier = Modifier.padding(10.dp),
        )
    }
}