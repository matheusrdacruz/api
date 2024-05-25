package br.com.dars.api.mapper

import br.com.dars.api.dto.AddressView
import br.com.dars.api.entities.Address
import org.springframework.stereotype.Component

@Component
class AddressViewMapper {
    fun map(address: Address): AddressView {
        return AddressView(
            id = address.id!!,
            street = address.street,
            city = address.city,
            state = address.state,
            number = address.number,
            neighborhood = address.neighborhood,
            complement = address.complement,
            postalCode = address.postalCode,
        )
    }
}