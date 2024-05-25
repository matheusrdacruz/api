package br.com.dars.api.service

import br.com.dars.api.dto.AddressForm
import br.com.dars.api.entities.Address
import br.com.dars.api.mapper.AddressFormMapper
import br.com.dars.api.repository.AddressRepository
import org.springframework.stereotype.Service

@Service
class AddressService(
    private val addressRepository: AddressRepository,
    private val addressFormMapper: AddressFormMapper
) {

    fun save(address: AddressForm): Address {
        return addressRepository.save(addressFormMapper.map(address))
    }

    fun update(json: AddressForm?, address: Address) {
        if (json != null) {
            val addressEntity: Address = this.findById(address.id!!)
            addressEntity.updateData(json)
            addressRepository.save(addressEntity)
        }
    }

    private fun findById(id: Long): Address {
        return addressRepository.findById(id)
            .orElseThrow { RuntimeException("Endereço não encontrado") }
    }
}