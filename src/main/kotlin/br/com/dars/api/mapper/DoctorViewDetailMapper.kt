package br.com.dars.api.mapper

import br.com.dars.api.dto.DoctorViewDetail
import br.com.dars.api.entities.Doctor
import org.springframework.stereotype.Component

@Component
class DoctorViewDetailMapper (
    private val addressViewMapper: AddressViewMapper
) {
    fun map(doctor: Doctor): DoctorViewDetail {
        return DoctorViewDetail(
            id = doctor.id,
            name = doctor.name,
            email = doctor.email,
            crm = doctor.crm,
            phoneNumber = doctor.phoneNumber,
            specialty = doctor.specialty,
            address = addressViewMapper.map(doctor.address)
        )
    }
}