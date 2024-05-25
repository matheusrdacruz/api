package br.com.dars.api.mapper

import br.com.dars.api.dto.DoctorForm
import br.com.dars.api.entities.Doctor
import org.springframework.stereotype.Component

@Component
class DoctorFormMapper (
    private val addressFormMapper: AddressFormMapper
) {
    fun map(doctorForm: DoctorForm): Doctor {
        return Doctor(
            name = doctorForm.name,
            email = doctorForm.email,
            crm = doctorForm.crm,
            phoneNumber = doctorForm.phoneNumber,
            specialty = doctorForm.specialty,
            address = addressFormMapper.map(doctorForm.address),
        )
    }
}