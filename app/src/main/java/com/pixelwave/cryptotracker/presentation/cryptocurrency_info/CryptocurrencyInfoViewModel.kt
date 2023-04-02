package com.pixelwave.cryptotracker.presentation.cryptocurrency_info

import android.util.Log
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
    var symbol: String? = null

    init {
        symbol = savedStateHandle.get<String>("symbol")
        getCryptocurrencyInfo(symbol)
        getCryptoCurrencyListing(symbol)
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
                        is Resource.Error -> {
                            state = state.copy(
                                isLoading = false,
                                error = result.message,
                            )
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }

    private fun getCryptoCurrencyListing(symbol: String?) {
        viewModelScope.launch {
            if (symbol == null) return@launch
            repository
                .getCryptocurrencyListing(symbol = symbol)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { info ->
                                state = state.copy(
                                    cryptocurrencyListing = info,
                                    isLoading = false,
                                )
                            }
                        }
                        is Resource.Error -> {
                            state = state.copy(
                                isLoading = false,
                                error = result.message,
                            )
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }

    fun onToggleFavorite() {
        viewModelScope.launch {
            symbol?.let { symbol ->
                state =
                    state.copy(
                        cryptocurrencyListing = state.cryptocurrencyListing?.isFavorite?.let { isFavorite ->
                            state.cryptocurrencyListing?.copy(
                                isFavorite = !isFavorite
                            )
                        }
                    )
                state.cryptocurrencyListing?.isFavorite?.let { isFavorite ->
                    repository.updateFavorite(
                        symbol = symbol,
                        isFavorite = isFavorite
                    )
                }
            } ?: return@launch
        }
    }
}