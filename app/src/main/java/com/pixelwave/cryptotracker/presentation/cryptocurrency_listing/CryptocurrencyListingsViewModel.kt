package com.pixelwave.cryptotracker.presentation.cryptocurrency_listing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pixelwave.cryptotracker.domain.repository.CryptocurrencyRepository
import com.pixelwave.cryptotracker.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptocurrencyListingsViewModel @Inject constructor(
    private val repository: CryptocurrencyRepository
) : ViewModel() {

    var state by mutableStateOf(CryptocurrencyListingsState())

    init {
        getCryptocurrencyListings()
    }

    fun onEvent(event: CryptocurrencyListingsEvent) {
        when (event) {
            is CryptocurrencyListingsEvent.Refresh -> {
                getCryptocurrencyListings(fetchFromRemote = true)
            }
        }
    }

    private fun getCryptocurrencyListings(
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository
                .getCryptocurrencyListings(fetchFromRemote)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    cryptocurrencies = listings
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
