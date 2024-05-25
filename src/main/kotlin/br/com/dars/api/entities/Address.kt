package br.com.dars.api.entities

import br.com.dars.api.dto.AddressForm
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "tb_addresses")
class Address (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var street: String,
    var city: String,
    var state: String,
    var postalCode: String,
    var neighborhood: String,
    var number: Int,
    var complement: String,
) {
    fun updateData(json: AddressForm) {
        if (json.street.isNotBlank()) {
            this.street = json.street
        }
        if (json.city.isNotBlank()) {
            this.city = json.city
        }
        if (json.state.isNotBlank()) {
            this.state = json.state
        }
        if (json.postalCode.isNotBlank()) {
            this.postalCode = json.postalCode
        }
        if (json.neighborhood.isNotBlank()) {
            this.neighborhood = json.neighborhood
        }
        if (json.number != this.number) {
            this.number = json.number
        }
        if (json.complement.isNotBlank()) {
            this.complement = json.complement
        }
    }
}