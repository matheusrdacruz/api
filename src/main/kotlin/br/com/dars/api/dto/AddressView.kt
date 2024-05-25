package br.com.dars.api.dto


data class AddressView (
    val id: Long,
    val street: String,
    val city: String,
    val state: String,
    val postalCode: String,
    val neighborhood: String,
    val number: Int,
    val complement: String,
)