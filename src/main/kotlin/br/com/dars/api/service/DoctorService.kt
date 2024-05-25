package br.com.dars.api.service

import br.com.dars.api.dto.DoctorForm
import br.com.dars.api.dto.DoctorFormUpdate
import br.com.dars.api.dto.DoctorView
import br.com.dars.api.dto.DoctorViewDetail
import br.com.dars.api.entities.Doctor
import br.com.dars.api.exceptions.NotFoundException
import br.com.dars.api.mapper.DoctorFormMapper
import br.com.dars.api.mapper.DoctorViewDetailMapper
import br.com.dars.api.mapper.DoctorViewMapper
import br.com.dars.api.repository.DoctorRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class DoctorService(
    private val addressService: AddressService,
    private val doctorFormMapper: DoctorFormMapper,
    private val doctorRepository: DoctorRepository,
    private val doctorViewMapper: DoctorViewMapper,
    private val doctorViewDetailMapper: DoctorViewDetailMapper,
    ) {

    fun findAll(pageable: Pageable): Page<DoctorView> {
        val doctors = doctorRepository.findAllByActiveTrue(pageable)
        return doctors.map { doctorViewMapper.map(it) }
    }

    fun findDoctorViewById(id: Long): DoctorView {
        val doctor = this.findById(id)
        return doctorViewMapper.map(doctor)
    }

    fun create(doctorForm: DoctorForm): DoctorViewDetail {
        val address = addressService.save(doctorForm.address)
        val doctor = doctorFormMapper.map(doctorForm)
        doctor.address = address
        doctorRepository.save(doctor)
        return doctorViewDetailMapper.map(doctor)
    }

    fun update(json: DoctorFormUpdate): DoctorViewDetail {
        val doctor = this.findById(json.id)
        addressService.update(json.address, doctor.address)
        doctor.updateData(json)
        doctorRepository.save(doctor)
        return doctorViewDetailMapper.map(doctor)
    }

    private fun findById(id: Long): Doctor {
        return doctorRepository.findById(id).orElseThrow{ NotFoundException("Doctor not found.") }
    }

    fun activate(id: Long) {
        val doctor = this.findById(id)
        doctor.activate()
        this.doctorRepository.save(doctor)
    }

    fun disable(id: Long) {
        val doctor = this.findById(id)
        doctor.disable()
        this.doctorRepository.save(doctor)
    }
}