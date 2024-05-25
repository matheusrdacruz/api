package br.com.dars.api.repository

import br.com.dars.api.entities.Address
import org.springframework.data.jpa.repository.JpaRepository

interface AddressRepository: JpaRepository<Address, Long> {
}