package br.com.dars.api.dto

data class DoctorViewDetail (
    val id: Long?,
    val name : String,
    val phoneNumber : String,
    val email : String,
    val crm: String,
    val specialty : String,
    val address : AddressView
)