package br.com.dars.api.mapper

import br.com.dars.api.dto.AddressForm
import br.com.dars.api.entities.Address
import org.springframework.stereotype.Component

@Component
class AddressFormMapper {
    fun map(addressForm: AddressForm): Address {
        return Address(
            street = addressForm.street,
            city = addressForm.city,
            state = addressForm.state,
            number = addressForm.number,
            neighborhood = addressForm.neighborhood,
            complement = addressForm.complement,
            postalCode = addressForm.postalCode,
        )
    }
}