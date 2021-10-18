package com.jesusrojo.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jesusrojo.common.util.PrefsHelp
import com.jesusrojo.data.mapper.UiDataEntityToUiDataMapper
import com.jesusrojo.data.repository.UiDatasRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class ItemsViewModelFactory @Inject constructor(
    private val repository: UiDatasRepository,
    private val mapper: UiDataEntityToUiDataMapper,
    private val ioDispatcher: CoroutineDispatcher,
    private val prefsHelp: PrefsHelp
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ItemsViewModel::class.java)) {
            return ItemsViewModel(repository,
                mapper, ioDispatcher, prefsHelp) as T
        }
        throw IllegalArgumentException("Unknown class name in ViewModelFactory")
    }
}