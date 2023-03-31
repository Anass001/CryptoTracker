package com.pixelwave.cryptotracker.presentation.cryptocurrency_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pixelwave.cryptotracker.domain.repository.CryptocurrencyRepository
import com.pixelwave.cryptotracker.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptocurrencyInfoViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: CryptocurrencyRepository
) : ViewModel() {

    var state by mutableStateOf(CryptocurrencyInfoState())

    init {
        val symbol = savedStateHandle.get<String>("symbol")
        getCryptocurrencyInfo(symbol)
    }

    private fun getCryptocurrencyInfo(symbol: String?) {
        viewModelScope.launch {
            if (symbol == null) return@launch
            repository
                .getHistoricalData(symbol = symbol)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { info ->
                                state = state.copy(
                                    data = info,
                                    isLoading = false,
                                )
                            }
                        }
                        is Resource.Error -> Unit //TODO
                        is Resource.Loading -> {
                            state = state.copy(isLoading = true)
                        }
                    }
                }
        }
    }
}