package com.jesusrojo.home.presentation.viewmodel

import androidx.lifecycle.*
import com.jesusrojo.data.mapper.UiDataEntityToUiDataMapper
import com.jesusrojo.data.model.RemoteState
import com.jesusrojo.data.model.UiData
import com.jesusrojo.data.repository.UiDatasRepository
import com.jesusrojo.data.model.UiState

import com.jesusrojo.home.di.hilt.IoDispatcher
import com.jesusrojo.common.util.DebugHelp
import com.jesusrojo.common.util.PrefsHelp
import com.jesusrojo.common.util.exhaustive


import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val repository: UiDatasRepository,
    private val mapper: UiDataEntityToUiDataMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val prefsHelp: PrefsHelp
) : ViewModel() {

    private val vmScope = viewModelScope
    private var fetchDatasJob: Job? = null

    private val _uiState = MutableLiveData<UiState<List<UiData>>>()
    val uiState: LiveData<UiState<List<UiData>>> = _uiState

    private fun updateUiLoading() {
        _uiState.value = UiState.Loading(null) //on Ui Thread
        // if we use post value, the addOnScrollListener will not work,
        // because it checks if the progress visibility,
        // and will have delay for being on background thread(postValue)
    }

    private fun updateUiSuccess(uiState: UiState<List<UiData>>) = _uiState.postValue(uiState)

    private fun updateUiError(message: String) = _uiState.postValue(UiState.Error(message))

    override fun onCleared() {
        fetchDatasJob?.cancel()
        deleteAllDB()
        super.onCleared()
    }

    init {
        fetchDatas()
    }

    private suspend fun shouldUpdate() = prefsHelp.getShouldUpdate()

    private fun fetchDatas() {
        DebugHelp.l("fetchDatas")
        updateUiLoading()
        fetchDatasJob?.cancel()
        fetchDatasJob = vmScope.launch(ioDispatcher) {
            try {
                val shouldUpdate = shouldUpdate()
                val remoteState = repository.fetchDatas(shouldUpdate)
                handleRemoteState(remoteState)
            } catch (e: Exception) {
                updateUiError("Error $e")
            }
        }
    }

    private fun handleRemoteState(remoteState: RemoteState<List<UiData>>) {
        when (remoteState) {
            is RemoteState.Success -> {
                val uiDatas: List<UiData>? = remoteState.data
                val msg: String? = remoteState.message
                if (uiDatas != null) {
                    updateUiSuccess(UiState.Success(uiDatas, msg!!))
                } else {
                    updateUiError("Error ${remoteState.message}")
                }
            }
            is RemoteState.Error -> updateUiError("Error ${remoteState.message}")
        }.exhaustive
    }

    fun deleteAllDB() {
        DebugHelp.l("deleteAllDB")
        vmScope.launch(ioDispatcher) {
            try {
                repository.deleteAllDB()//todo does it works?
                updateUiError("All deleted ok")
                DebugHelp.l("all deleted ok")
            } catch (e: Exception) {
                updateUiError("Error $e")
            }
        }
    }
}