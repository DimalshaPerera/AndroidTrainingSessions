package com.example.hilt.data.repository

import com.example.hilt.data.models.Cryptocurrency

interface CryptocurrencyRepository {
    fun getCryptoCurrency(): List<Cryptocurrency>
}