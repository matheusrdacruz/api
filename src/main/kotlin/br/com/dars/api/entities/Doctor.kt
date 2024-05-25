package br.com.dars.api.entities

import br.com.dars.api.dto.DoctorFormUpdate
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "tb_doctors")
class Doctor (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    val email: String,
    @Column(nullable = false)
    val crm: String,
    @Column(nullable = false)
    var phoneNumber : String,
    @Column(nullable = false)
    val specialty: String,
    @Column(nullable = false)
    var active: Boolean = true,
    @OneToOne
    var address: Address,
) {
    fun updateData(json: DoctorFormUpdate) {
        if (json.name?.isNotBlank() == true) {
            this.name = json.name
        }
        if (json.phoneNumber?.isNotBlank() == true) {
            this.phoneNumber = json.phoneNumber
        }
    }

    fun activate() {
        this.active = true
    }

    fun disable() {
        this.active = false
    }
}