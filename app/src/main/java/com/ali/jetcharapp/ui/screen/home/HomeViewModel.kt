package com.ali.jetcharapp.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.jetcharapp.data.Repository
import com.ali.jetcharapp.model.OrderCharacter
import com.ali.jetcharapp.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel (private val repository: Repository): ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<OrderCharacter>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderCharacter>>> get() = _uiState

    fun getCharacter() {
        viewModelScope.launch {
            repository.getCharacter()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { characters ->
                    _uiState.value = UiState.Success(characters)
                }
        }
    }
}