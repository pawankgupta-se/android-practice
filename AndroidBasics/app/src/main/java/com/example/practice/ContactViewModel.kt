package com.example.practice

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.*

class ContactViewModel(val param: Int) : ViewModel() {

//    init {
//        viewModelScope.launch {
//            combine7(flowOf("A"), flowOf("B"), flowOf("C"), flowOf("D"), flowOf("E"), flowOf("F"), flowOf("G"))
//            {a, b, c, d, e, f, g -> Test(a, b, c, d, e, f, g)}.map {
//                Log.d("@@@value -> ",it.a )
//            }.collect()
//        }
//
//    }
    var backgroundColor by mutableStateOf(Color.White)
        private set

    var imageUri: Uri? by mutableStateOf(null)
        private set

    var compressedBitmap : Bitmap? by mutableStateOf(null)
        private set

    var workId: UUID? by mutableStateOf(null)
        private set

    var images by mutableStateOf(emptyList<Image>())
        private set

    fun updateUri(uri: Uri?){
        imageUri = uri
    }

    fun changeBackgroundColor(){
        backgroundColor = Color.LightGray
    }

    fun updateCompressedBitmap(bitmap: Bitmap?){
        compressedBitmap = bitmap
    }

    fun updateWorkId(uuid: UUID?){
        workId = uuid
    }

    fun updateImages(images: List<Image>){
        this.images = images
    }
}

data class Test(val a:String,
                val b:String,
                val c:String,
                val d:String,
                val e:String,
                val f:String,
                val g:String)
