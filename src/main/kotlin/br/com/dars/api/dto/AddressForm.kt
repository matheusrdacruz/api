package br.com.dars.api.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class AddressForm (
    @field:NotBlank
    val street: String,
    @field:NotBlank
    val city: String,
    @field:NotBlank
    val state: String,
    @field:NotBlank
    @field:Pattern(regexp = "\\d{8}")
    val postalCode: String,
    @field:NotBlank
    val neighborhood: String,
    val number: Int,
    val complement: String,
)