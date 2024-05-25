package br.com.dars.api.exceptions.dto

import java.time.LocalDateTime

data class ErrorView(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val message: String?,
    val error: String,
    val path: String,
)
