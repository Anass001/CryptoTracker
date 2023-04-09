package com.pixelwave.cryptotracker.presentation.cryptocurrency_listing

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pixelwave.cryptotracker.domain.repository.CryptocurrencyRepository
import com.pixelwave.cryptotracker.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptocurrencyListingsViewModel @Inject constructor(
    private val repository: CryptocurrencyRepository
) : ViewModel() {

    var state by mutableStateOf(CryptocurrencyListingsState())
    var searchJob: Job? = null

    init {
        getCryptocurrencyListings()
    }

    fun onEvent(event: CryptocurrencyListingsEvent) {
        when (event) {
            is CryptocurrencyListingsEvent.Refresh -> {
                getCryptocurrencyListings(fetchFromRemote = true)
            }
            is CryptocurrencyListingsEvent.SearchQueryChanged -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getCryptocurrencyListings()
                }
            }
        }
    }

    private fun getCryptocurrencyListings(
        fetchFromRemote: Boolean = false,
        searchQuery: String = state.searchQuery.lowercase()
    ) {
        viewModelScope.launch {
            repository
                .getCryptocurrencyListings(fetchFromRemote, searchQuery)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            delay(1000L)
                            result.data?.let { listings ->
                                state = state.copy(
                                    cryptocurrencies = listings
                                )
                            }
                        }
                        is Resource.Error -> Unit //TODO
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }
}
