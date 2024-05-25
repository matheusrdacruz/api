package br.com.dars.api.dto

import jakarta.validation.constraints.NotNull

data class DoctorFormUpdate (
    @field:NotNull
    val id: Long,
    val name : String?,
    val phoneNumber : String?,
    val address : AddressForm?,
)