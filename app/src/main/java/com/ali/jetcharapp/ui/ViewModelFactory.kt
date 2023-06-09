package com.ali.jetcharapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ali.jetcharapp.data.Repository
import com.ali.jetcharapp.ui.screen.detail.DetailViewModel
import com.ali.jetcharapp.ui.screen.home.HomeViewModel

class ViewModelFactory (
    private val repository: Repository
): ViewModelProvider.NewInstanceFactory()
{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel Class: " + modelClass.name)
    }
}