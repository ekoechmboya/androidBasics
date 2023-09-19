package com.example.androidbasics.uris

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androidbasics.uris.ui.theme.AndroidBasicsTheme
import java.io.File
import java.io.FileOutputStream

class UrisActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uri = Uri.parse("android.resource://$packageName/drawable/download")
        val picBytes = contentResolver.openInputStream(uri).use {
            it!!.readBytes()
        }
        println("download size: ${picBytes?.size}")
        val file = File(filesDir, "download.jpg")
        FileOutputStream(file).use {
            it.write(picBytes)
        }
        setContent {
            AndroidBasicsTheme {

            }
        }
    }
}

