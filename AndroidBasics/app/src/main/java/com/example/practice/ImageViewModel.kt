package com.example.practice

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ImageViewModel: ViewModel() {
    var imageUri:Uri? by mutableStateOf(Uri.parse("https://static.javatpoint.com/computer/images/what-is-the-url.png"))
    private set

    fun updateUri(uri: Uri?){
        Log.d("ImageViewModel", "image## $uri")
        imageUri = uri
    }
}
