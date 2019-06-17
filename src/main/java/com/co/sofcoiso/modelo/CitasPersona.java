/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author manue
 */
@Entity
@Table(name = "citas_persona")
@NamedQueries({
    @NamedQuery(name = "CitasPersona.findAll", query = "SELECT c FROM CitasPersona c")
    , @NamedQuery(name = "CitasPersona.findByIdCitas", query = "SELECT c FROM CitasPersona c WHERE c.idCitas = :idCitas")
    , @NamedQuery(name = "CitasPersona.findByFechaCita", query = "SELECT c FROM CitasPersona c WHERE c.fechaCita = :fechaCita")
    , @NamedQuery(name = "CitasPersona.findByObservacion", query = "SELECT c FROM CitasPersona c WHERE c.observacion = :observacion")
    , @NamedQuery(name = "CitasPersona.findByPersonasCedula", query = "SELECT c FROM CitasPersona c WHERE c.personasCedula = :personasCedula")})
public class CitasPersona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "id_citas")
    private String idCitas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "fecha_cita")
    private String fechaCita;
    @Size(max = 500)
    @Column(name = "observacion")
    private String observacion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "personas_cedula")
    private String personasCedula;

    public CitasPersona() {
    }

    public CitasPersona(String idCitas) {
        this.idCitas = idCitas;
    }

    public CitasPersona(String idCitas, String fechaCita, String personasCedula) {
        this.idCitas = idCitas;
        this.fechaCita = fechaCita;
        this.personasCedula = personasCedula;
    }

    public String getIdCitas() {
        return idCitas;
    }

    public void setIdCitas(String idCitas) {
        this.idCitas = idCitas;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getPersonasCedula() {
        return personasCedula;
    }

    public void setPersonasCedula(String personasCedula) {
        this.personasCedula = personasCedula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCitas != null ? idCitas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CitasPersona)) {
            return false;
        }
        CitasPersona other = (CitasPersona) object;
        if ((this.idCitas == null && other.idCitas != null) || (this.idCitas != null && !this.idCitas.equals(other.idCitas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.sofcoiso.modelo.CitasPersona[ idCitas=" + idCitas + " ]";
    }
    
}
