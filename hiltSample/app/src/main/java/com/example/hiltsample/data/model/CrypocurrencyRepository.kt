package com.example.hiltsample.data.model

interface CryptocurrencyRepository {
    fun getCryptoCurrency(): List<Cryptocurrency>
}