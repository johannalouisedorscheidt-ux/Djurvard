package com.example.djurvard.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.djurvard.data.model.Pet
import com.example.djurvard.ui.viewmodel.PetViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PetHomeScreen() {

    var petName by remember {
        mutableStateOf("")
    }

    var animalType by remember {
        mutableStateOf("")
    }

    val petViewModel: PetViewModel = viewModel()

    val pets by petViewModel.pets.collectAsState()

    var errorMessage by remember {
        mutableStateOf("")
    }

    var selectedPet by remember {
        mutableStateOf<Pet?>(null)
    }


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
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            if (selectedPet == null) {

                Text(text = "Mina husdjur")

                OutlinedTextField(
                    value = petName,
                    onValueChange = { petName = it },
                    label = {
                        Text(text = "Husdjurets namn")
                    }
                )

                OutlinedTextField(
                    value = animalType,
                    onValueChange = { animalType = it },
                    label = {
                        Text(text = "Djurtyp")
                    }
                )

                Button(
                    onClick = {
                        if (petName.isNotBlank() && animalType.isNotBlank()) {

                            val newPet = Pet(
                                id = pets.size + 1,
                                name = petName,
                                animalType = animalType
                            )

                            petViewModel.addPet(newPet)
                            petName = ""
                            animalType = ""
                            errorMessage = ""

                        } else {
                            errorMessage =
                                "Var vänlig att fyll i både namn och djurtyp på husdjuret"
                        }
                    }
                ) {
                    Text(text = "Lägg till husdjur")
                }

                if (errorMessage.isNotEmpty()) {
                    Text(text = errorMessage)
                }

                pets.forEach { pet ->
                    Text(
                        text = "${pet.name} - ${pet.animalType}",
                        modifier = Modifier.clickable {
                            selectedPet = pet
                            petName = pet.name
                            animalType = pet.animalType
                        }
                    )
                }

            } else {

                selectedPet?.let { pet ->

                    Button(onClick = {
                        selectedPet = null
                        petName = ""
                        animalType = ""
                    }) {
                        Text(text = "Tillbaka")
                    }

                    CareTaskScreen(pet = pet)
                }
            }
        }
    }
}