package com.example.djurvard.ui.screens

import androidx.compose.foundation.layout.offset
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.Box
import com.example.djurvard.R
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun WelcomeScreen() {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.djur_grupp),
            contentDescription = "Olika husdjur",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )

        // Välkomsttexten placeras ovanför hundens huvud
        Text(
            text = "Välkommen till djur appen",
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(x = 30.dp)
                .padding(top = 100.dp),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

// Startknappen placeras i mitten
        Button(
            onClick = { },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = "Start")
        }
    }
}