
package com.example.djurvard.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
@OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PetHomeScreen() {

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "Djurvård")
                    }
                )
            }
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Text(
                    text = "Mina husdjur" )

                Text(
                    text = "Här kommer dina husdjur att visas." )
            }
        }
    }