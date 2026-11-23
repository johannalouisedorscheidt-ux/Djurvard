package com.example.djurvard.ui.viewmodel

import com.example.djurvard.data.model.CareTask
import androidx.lifecycle.ViewModel
import com.example.djurvard.data.model.Pet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PetViewModel : ViewModel() {

    // Lista som håller reda på alla husdjur
    private val _pets = MutableStateFlow<List<Pet>>(emptyList())

    // Gör listan tillgänglig för andra delar av appen
    val pets: StateFlow<List<Pet>> = _pets.asStateFlow()

    // Lista som håller reda på alla skötseluppgifter
    private val _careTasks = MutableStateFlow<List<CareTask>>(emptyList())

    // Håller reda på nästa lediga ID för skötseluppgifter
    private var nextCareTaskId = 1

    // Gör skötseluppgifterna tillgängliga för andra delar av appen
    val careTasks: StateFlow<List<CareTask>> = _careTasks.asStateFlow()

    // Lägger till ett nytt husdjur
    fun addPet(pet: Pet) {
        _pets.value = _pets.value + pet
    }

    // Lägger till en ny skötseluppgift
    fun addCareTask(task: CareTask) {
        val newTask = task.copy(id = nextCareTaskId)
        _careTasks.value = _careTasks.value + newTask
        nextCareTaskId++
    }


    // Ändrar om en skötseluppgift är klar
    fun toggleCareTask(taskId: Int) {
        _careTasks.value = _careTasks.value.map { task ->
            if (task.id == taskId) {
                task.copy(isCompleted = !task.isCompleted)
            } else {
                task
            }
        }
    }

    // Redigerar en skötseluppgift
    fun editCareTask(updatedTask: CareTask) {
        _careTasks.value = _careTasks.value.map { task ->
            if (task.id == updatedTask.id) {
                updatedTask
            } else {
                task
            }
        }
    }

    // Tar bort en skötseluppgift
    fun deleteCareTask(taskId: Int) {
        _careTasks.value = _careTasks.value.filterNot { task ->
            task.id == taskId
        }
    }


    // Redigera djur med hjälp av unika Id:n
    fun editPet(updatedPet: Pet) {
        _pets.value = _pets.value.map { pet ->
            if (pet.id == updatedPet.id) {
                updatedPet
            } else {
                pet
            }
        }
    }

    // Tar bort husdjur i listan
    fun deletePet(petId: Int) {
        _pets.value = _pets.value.filterNot { pet ->
            pet.id == petId
        }
    }
}