package com.example.hiltsample.viewModel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.hiltsample.data.model.Cryptocurrency
import com.example.hiltsample.data.model.CryptocurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val cryptocurrencyRepository: CryptocurrencyRepository
) : ViewModel() {

    private val _cryptocurrencies = mutableStateOf<List<Cryptocurrency>>(emptyList())
    val cryptocurrencies: State<List<Cryptocurrency>> = _cryptocurrencies

    init {
        loadCryptocurrencies()
    }

    private fun loadCryptocurrencies() {
        _cryptocurrencies.value = cryptocurrencyRepository.getCryptoCurrency()
    }
}