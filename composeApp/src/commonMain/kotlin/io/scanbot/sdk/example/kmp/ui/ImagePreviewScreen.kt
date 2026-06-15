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
import io.scanbot.sdk.kmp.image.ImageRef
import io.scanbot.sdk.kmp.utils.UUID

@Composable
fun ImagePreviewScreen(
    imageUuid: String,
    onPopBackStack: () -> Unit,
) {
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(imageUuid) {
        val imageRef = ImageRef(UUID.fromString(imageUuid))
        imageBitmap = imageRef.encode().getOrNull()
            ?.decodeToImageBitmap()
    }

    Scaffold(topBar = {
        TopBar(
            title = "Image Preview",
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
            imageBitmap?.let {
                Image(
                    bitmap = it,
                    contentDescription = "image preview",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            } ?: run {
                Text("No image to preview.", color = Color.White)
            }
        }
    }
}