package com.example.weatherupdate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherupdate.api.WeatherModel
import com.example.weatherupdate.ui.theme.WeatherUpdateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                android.graphics.Color.TRANSPARENT
            )
        )

        super.onCreate(savedInstanceState)

        val weatherViewModel = ViewModelProvider(this)[weatherViewModel::class.java]

        setContent {
            WeatherUpdateTheme {
                // A surface container using the 'background' color from the theme
                HomeScreen(weatherViewModel)
//                composeTest()
            }
        }
    }
}
