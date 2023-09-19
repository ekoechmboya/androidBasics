package com.example.androidbasics

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.androidbasics.broadcasts.AirplaneModeReceiver
import com.example.androidbasics.broadcasts.TestReceiver
import com.example.androidbasics.foregroundServices.RunningService
import com.example.androidbasics.ui.theme.AndroidBasicsTheme
import com.example.androidbasics.uris.UrisActivity
import java.util.jar.Manifest

class MainActivity : ComponentActivity() {

    private val airplaneModeReceiver = AirplaneModeReceiver()
    private val testReceiver = TestReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    0
                    )


        registerReceiver(
            airplaneModeReceiver,
            IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED)
            )
        registerReceiver(
            testReceiver,
            IntentFilter("TEST_ACTION")
            )
        setContent {
            AndroidBasicsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Button(onClick = {
                            Intent(Intent.ACTION_MAIN).also{
                            it.`package` = "com.google.android.youtube"
                            startActivity(it)
                        }
                        }) {
                            Text(text = "Launch Youtube")
                        }
                        Button(onClick = {
                            sendBroadcast(
                                Intent("TEST_ACTION")
                            )
                        }

                        ) {
                            Text(text = "Send Broadcast")
                        }
                        Button(onClick = {
                            Intent(applicationContext, RunningService::class.java).also {
                                it.action = RunningService.Actions.START.toString()
                                startService(it)
                            }
                        }) {
                            Text(text = "Start Run")

                        }
                        Button(onClick = {
                            Intent(applicationContext, RunningService::class.java).also {
                                it.action = RunningService.Actions.STOP.toString()
                                startService(it)
                            }
                        }) {
                            Text(text = "Stop Run")

                        }
                        Button(onClick = {
                            Intent(applicationContext, UrisActivity::class.java).also {
                                startActivity(it)
                            }
                        }) {

                        }
                    }

                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(airplaneModeReceiver)
        unregisterReceiver(testReceiver)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun mainLayout(){
    Column {
        var text = "Add a quote"
        Text(text = "I like Coding. - Enock Koech")
        TextField(modifier = Modifier.width(50.dp), value = text, onValueChange = {text = it})
    }
}

