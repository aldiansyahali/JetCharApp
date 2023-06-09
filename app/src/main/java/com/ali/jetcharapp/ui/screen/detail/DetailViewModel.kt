package com.ali.jetcharapp.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.jetcharapp.data.Repository
import com.ali.jetcharapp.model.OrderCharacter
import com.ali.jetcharapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderCharacter>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderCharacter>> get() = _uiState

    fun getCharacterId(characterId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderById(characterId))
        }
    }
}