package br.com.dars.api.repository

import br.com.dars.api.entities.Doctor
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface DoctorRepository : JpaRepository<Doctor, Long> {
    fun findAllByActiveTrue(pageable: Pageable): Page<Doctor>
}