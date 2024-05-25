package br.com.dars.api.dto

import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class DoctorForm (
    @field:NotBlank
    val name : String,
    @field:NotBlank
    @field:Email
    val email : String,
    @field:NotBlank
    @field:Pattern(regexp = "\\d{4,6}")
    val crm: String,
    @field:NotBlank
    val phoneNumber : String,
    @field:NotNull
    val specialty : String,
    @field:NotNull
    @field:Valid
    val address : AddressForm,
)