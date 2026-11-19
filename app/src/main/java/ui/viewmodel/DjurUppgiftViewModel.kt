package com.example.djurvard.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.djurvard.data.model.DjurUppgift
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DjurUppgiftViewModel : ViewModel() {

    private val _uppgifter = MutableStateFlow<List<DjurUppgift>>(emptyList())

    val uppgifter: StateFlow<List<DjurUppgift>> = _uppgifter.asStateFlow()

    fun laggTillUppgift(uppgift: DjurUppgift) {
        _uppgifter.value = _uppgifter.value + uppgift
    }

    fun andraStatus(uppgiftId: Int) {
        _uppgifter.value = _uppgifter.value.map { uppgift ->
            if (uppgift.id == uppgiftId) {
                uppgift.copy(klar = !uppgift.klar)
            } else {
                uppgift
            }
        }
    }

    fun taBortUppgift(uppgiftId: Int) {
        _uppgifter.value = _uppgifter.value.filterNot { uppgift ->
            uppgift.id == uppgiftId
        }
    }
}