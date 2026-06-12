package io.scanbot.sdk.example.kmp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.decodeToImageBitmap
import androidx.compose.ui.layout.ContentScale
import io.scanbot.sdk.example.kmp.ui.common.TopBar
import io.scanbot.sdk.kmp.documentscanner.DocumentStraighteningResult

@Composable
fun StraightenImagePreviewScreen(
    straighteningResultJson: String,
    onPopBackStack: () -> Unit,
) {
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(straighteningResultJson) {
        val documentStraighteningResult =
            DocumentStraighteningResult.fromJson(straighteningResultJson)
        imageBitmap = documentStraighteningResult.straightenedImage?.encode()?.getOrNull()
            ?.decodeToImageBitmap()
    }

    Scaffold(topBar = {
        TopBar(
            title = "Straightened Image",
            showBackButton = true,
            onPopBackStack = onPopBackStack
        )
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {

            if (imageBitmap != null) {
                imageBitmap?.let {
                    Image(
                        bitmap = it,
                        contentDescription = "Straightened image preview",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            if (imageBitmap == null) {
                Text("No straightened image to preview.", color = Color.White)
            }
        }
    }
}