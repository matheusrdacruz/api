package br.com.dars.api.dto

data class DoctorView (
    val id: Long?,
    val name : String,
    val email : String,
    val crm: String,
    val specialty : String,
)