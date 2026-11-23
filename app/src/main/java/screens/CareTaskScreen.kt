package com.example.djurvard.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.heightIn
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import com.example.djurvard.data.model.CareTask
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.djurvard.ui.viewmodel.PetViewModel
import com.example.djurvard.data.model.Pet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun CareTaskScreen(pet: Pet) {

    var taskTitle by remember { mutableStateOf("") }
    var taskCategory by remember { mutableStateOf("") }
    var editingTaskId by remember { mutableStateOf<Int?>(null) }

    val petViewModel: PetViewModel = viewModel()
    val careTasks by petViewModel.careTasks.collectAsState()

    val selectedPetTasks = careTasks.filter { task ->
        task.petId == pet.id
    }
    val completedTasks = selectedPetTasks.count { task ->
        task.isCompleted
    }
    val totalTasks = selectedPetTasks.size

    val progress = if (totalTasks > 0) {
        completedTasks.toFloat() / totalTasks
    } else {
        0f
    }

    Text("Skötseluppgifter för ${pet.name}")

    Text(
        text = "$completedTasks av $totalTasks klara"
    )
    LinearProgressIndicator(
        progress = { progress },
        modifier = Modifier.fillMaxWidth()
    )

    if (selectedPetTasks.isEmpty()) {
        Text(text = "Inga skötseluppgifter ännu.")
    } else {
        LazyColumn(
            modifier = Modifier.heightIn(max = 200.dp)
        ) {
            items(selectedPetTasks) { task ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = task.isCompleted,
                        onCheckedChange = {
                            petViewModel.toggleCareTask(task.id)
                        }
                    )

                    Text(text = task.title)

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = {
                            editingTaskId = task.id
                            taskTitle = task.title
                            taskCategory = task.category
                        }
                    ) {
                        Text(text = "Redigera")
                    }

                    Spacer(modifier = Modifier.width(8.dp))


                    Button(
                        onClick = {
                            petViewModel.deleteCareTask(task.id)
                        }
                    ) {
                        Text(text = "Ta bort")
                    }
                }
            }
        }
    }
    OutlinedTextField(
        value = taskTitle,
        onValueChange = { taskTitle = it },
        label = { Text(text = "Skötseluppgift") }
    )

    OutlinedTextField(
        value = taskCategory,
        onValueChange = { taskCategory = it },
        label = { Text(text = "Kategori") }
    )


    Button(
        onClick = {
            if (taskTitle.isNotBlank() && taskCategory.isNotBlank()) {

                if (editingTaskId != null) {
                    val updatedTask = CareTask(
                        id = editingTaskId!!,
                        petId = pet.id,
                        title = taskTitle,
                        category = taskCategory,
                        isCompleted = selectedPetTasks.first {
                            it.id == editingTaskId
                        }.isCompleted
                    )

                    petViewModel.editCareTask(updatedTask)

                } else {
                    val newTask = CareTask(
                        id = careTasks.size + 1,
                        petId = pet.id,
                        title = taskTitle,
                        category = taskCategory
                    )

                    petViewModel.addCareTask(newTask)
                }

                taskTitle = ""
                taskCategory = ""
                editingTaskId = null
            }
        }
    ) {
        Text(
            text = if (editingTaskId != null) {
                "Spara ändringar"
            } else {
                "Lägg till skötseluppgift"
            }
        )
    }
}



