package br.com.dars.api.controller

import br.com.dars.api.dto.DoctorForm
import br.com.dars.api.dto.DoctorFormUpdate
import br.com.dars.api.dto.DoctorView
import br.com.dars.api.dto.DoctorViewDetail
import br.com.dars.api.service.DoctorService
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("api/doctors")
class DoctorController (
    private val doctorService: DoctorService
){

    @GetMapping
    fun listDoctors(
        @PageableDefault(size = 10, sort = ["name"]) pageable: Pageable
    ) : ResponseEntity<Page<DoctorView>> {
        return ResponseEntity.ok(doctorService.findAll(pageable))
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) : ResponseEntity<DoctorView> {
        return ResponseEntity.ok(doctorService.findDoctorViewById(id))
    }


    @PostMapping
    fun create(
        @RequestBody @Valid json: DoctorForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<DoctorViewDetail> {
        val doctorViewDetail = doctorService.create(json)
        val uri = uriBuilder.path("/api/doctors/${doctorViewDetail.id}").build().toUri()
        return ResponseEntity.created(uri).body(doctorViewDetail)
    }

    @PutMapping
    fun update(@RequestBody json: DoctorFormUpdate): ResponseEntity<DoctorViewDetail> {
        return ResponseEntity.ok(doctorService.update(json))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Any> {
        doctorService.disable(id)
        return ResponseEntity.noContent().build()
    }
}