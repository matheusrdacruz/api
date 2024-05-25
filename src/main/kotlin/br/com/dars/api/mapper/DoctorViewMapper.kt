package br.com.dars.api.mapper

import br.com.dars.api.dto.DoctorView
import br.com.dars.api.entities.Doctor
import org.springframework.stereotype.Component

@Component
class DoctorViewMapper {
    fun map(doctor: Doctor): DoctorView {
        return DoctorView(
            id = doctor.id,
            name = doctor.name,
            email = doctor.email,
            crm = doctor.crm,
            specialty = doctor.specialty,
        )
    }
}