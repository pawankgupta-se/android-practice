package com.example.practice

import android.Manifest
import android.content.ContentUris
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import coil.compose.AsyncImage
import com.example.practice.receivers.AirPlaneModeReceiver
import com.example.practice.receivers.TestReceiver
import com.example.practice.ui.theme.PracticeTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.util.Calendar

@Suppress("UNCHECKED_CAST")
class MainActivity : ComponentActivity() {
    //private val viewModel by viewModels<ContactViewModel>()

    //  private val imageViewModel by viewModels<ImageViewModel>()
    private lateinit var workManager: WorkManager
    private var airPlaneModeReceiver = AirPlaneModeReceiver()
    private var testReceiver = TestReceiver()
    private val viewModel by viewModels<ContactViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return ContactViewModel(2) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * content provider usage
         */
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_MEDIA_IMAGES), 0)

        val projection = arrayOf(MediaStore.Images.Media._ID, MediaStore.Images.Media.DISPLAY_NAME)
        val selection = "${MediaStore.Images.Media.DATE_TAKEN} >= ?"
        val milliYesterday = Calendar.getInstance().apply { Calendar.DAY_OF_YEAR
            add(Calendar.DAY_OF_YEAR, -1)
        }.timeInMillis
        val selectionArgs = arrayOf(milliYesterday.toString())
        val sortOrder = "${MediaStore.Images.Media.DATE_TAKEN} DESC"
        contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, selection, selectionArgs, sortOrder)?.use {
            cursor ->
            val idColumn = cursor.getColumnIndex(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)

            val images = mutableListOf<Image>()
            Log.d("TAG", "Image Query")
            while (cursor.moveToNext()){
                Log.d("TAG", cursor.getLong(idColumn).toString())
                images.add(Image(cursor.getLong(idColumn), cursor.getString(nameColumn), ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, cursor.getLong(idColumn))))
            }
            viewModel.updateImages(images)
        }
        //readIntent(intent)
        readIntentForWorker(intent)
        workManager = WorkManager.getInstance(applicationContext)
        registerReceiver(airPlaneModeReceiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
        registerReceiver(testReceiver, IntentFilter("TEST_ACTION"))
        Log.d(TAG, "onCreate()")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 100)
        }
        setContent {
            PracticeTheme {

                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = viewModel.backgroundColor) {
                    val workResult = viewModel.workId?.let { id ->
                        workManager.getWorkInfoByIdLiveData(id).observeAsState().value
                    }

                    LaunchedEffect(key1 = workResult?.outputData){
                        if(workResult?.outputData !=null){
                            val filePath = workResult.outputData.getString(PhotoCompressionWorker.KEY_RESULT_PATH)
                            filePath?.let {
                                val bitmap = BitmapFactory.decodeFile(it)
                                viewModel.updateCompressedBitmap(bitmap)
                            }
                        }
                    }


                    Column {
                        viewModel.imageUri?.let {
                            Text(text = "Uncompressed Image:")
                            AsyncImage(model = it, contentDescription = "")
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        viewModel.compressedBitmap?.let {
                            Text(text = "Compressed Image:")
                            Image(bitmap = it.asImageBitmap(), contentDescription = "")
                        }
                        Button(onClick = {
                            val intent = Intent(this@MainActivity, RunningService::class.java).also {
                                it.action = RunningService.Actions.START.toString()
                            }
                            startService(intent)
                        }) {
                            Text(text = "Start Service")
                        }

                        Button(onClick = {
                            val intent = Intent(this@MainActivity, RunningService::class.java).also {
                                it.action = RunningService.Actions.STOP.toString()
                            }
                            startService(intent)
                        }) {
                            Text(text = "Stop Service")
                        }

                        Button(onClick = {
                            viewModel.changeBackgroundColor()
                        }) {
                            Text(text = "Change Background")
                        }

                        Button(onClick = {
                            //Explicit Intent - 1
                            /*      Intent(this@MainActivity, SecondActivity::class.java).also {
                                      startActivity(it)
                                  }*/

                            // Explicit Intent - 2
                            /*    Intent(Intent.ACTION_MAIN).also {
                                    it.`package` = "com.google.android.youtube"
                                    startActivity(it)
                                }*/

                            // Implicit Intent

                            val intent = Intent(Intent.ACTION_SEND).apply {
                                type = "text/plain"
                                putExtra(Intent.EXTRA_EMAIL, arrayOf("pawan@gamil.com", "uttam@gmail.com"))
                                putExtra(Intent.EXTRA_SUBJECT, "Notification for  rent!")
                                putExtra(Intent.EXTRA_TEXT, "Hi All, rent has to be paid by 10th of each month.")
                            }

                            if (intent.resolveActivity(packageManager) != null) {
                                startActivity(intent)
                            }

                        }) {
                            Text(text = "Start Activity")
                        }


                        Divider()

                        LazyColumn(modifier = Modifier){
                            items(viewModel.images){
                                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                                    AsyncImage(model = it.uri, contentDescription = "${it.id}${it.name}")
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        Log.d(TAG, "onNewIntent()")
        super.onNewIntent(intent)
        //readIntent(intent)
        readIntentForWorker(intent)
    }

    private fun readIntent(intent: Intent?) {
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
        } else {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM)
        }
        Log.d(TAG, "$uri")
        viewModel.updateUri(uri)
    }

    private fun readIntentForWorker(intent: Intent?) {
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
        } else {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM)
        } ?: return

        viewModel.updateUri(uri)

        val request = OneTimeWorkRequestBuilder<PhotoCompressionWorker>()
            .setInputData(
                workDataOf(
                    PhotoCompressionWorker.KEY_CONTENT_URI to uri.toString(),
                    PhotoCompressionWorker.KEY_COMPRESSION_THRESHOLD to 1024 * 20L
                )
            )
            .setConstraints(
                Constraints(
                    requiresStorageNotLow = true
                )
            )
            .build()
        viewModel.updateWorkId(request.id)
        workManager.enqueue(request)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
        unregisterReceiver(airPlaneModeReceiver)
        unregisterReceiver(testReceiver)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart()")
    }

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }
}

data class Image(
    val id: Long,
    val name: String,
    val uri: Uri
)
@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PracticeTheme {
        Greeting("Android")
    }
}
