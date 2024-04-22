package com.yambrosio.bankingapp.data.model

import androidx.compose.ui.graphics.Brush

data class Card(
    val cardType: String,
    val cardNumber: String,
    val cardName: String,
    val balance: Double,
    val color: Brush
)
