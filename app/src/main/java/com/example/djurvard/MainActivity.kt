package com.example.djurvard

import com.example.djurvard.ui.screens.PetHomeScreen
import com.example.djurvard.ui.screens.WelcomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.djurvard.ui.theme.DjurvårdTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DjurvårdTheme {

                var visaHusdjursSida by remember {
                    mutableStateOf(false)
                }

                if (visaHusdjursSida) {
                    PetHomeScreen()
                } else {
                    WelcomeScreen(
                        onStartClick = {
                            visaHusdjursSida = true
                        }
                    )
                }
            }
        }
    }
}
