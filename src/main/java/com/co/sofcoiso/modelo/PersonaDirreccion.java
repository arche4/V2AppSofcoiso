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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author manue
 */
@Entity
@Table(name = "persona_dirreccion")
@NamedQueries({
    @NamedQuery(name = "PersonaDirreccion.findAll", query = "SELECT p FROM PersonaDirreccion p")
    , @NamedQuery(name = "PersonaDirreccion.findByIdDirrecion", query = "SELECT p FROM PersonaDirreccion p WHERE p.idDirrecion = :idDirrecion")
    , @NamedQuery(name = "PersonaDirreccion.findByCedulaPersona", query = "SELECT p FROM PersonaDirreccion p WHERE p.cedulaPersona = :cedulaPersona")
    , @NamedQuery(name = "PersonaDirreccion.findByDirreccion", query = "SELECT p FROM PersonaDirreccion p WHERE p.dirreccion = :dirreccion")
    , @NamedQuery(name = "PersonaDirreccion.findByComuna", query = "SELECT p FROM PersonaDirreccion p WHERE p.comuna = :comuna")})
public class PersonaDirreccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dirrecion")
    private Integer idDirrecion;
    @Column(name = "cedula_persona")
    private Integer cedulaPersona;
    @Size(max = 50)
    @Column(name = "dirreccion")
    private String dirreccion;
    @Size(max = 50)
    @Column(name = "comuna")
    private String comuna;

    public PersonaDirreccion() {
    }

    public PersonaDirreccion(Integer idDirrecion) {
        this.idDirrecion = idDirrecion;
    }
    
     public PersonaDirreccion(Integer cedula, String direccion, String comuna) {
        this.cedulaPersona = cedula;
        this.dirreccion = direccion;
        this.comuna = comuna;
    }

    public Integer getIdDirrecion() {
        return idDirrecion;
    }

    public void setIdDirrecion(Integer idDirrecion) {
        this.idDirrecion = idDirrecion;
    }

    public Integer getCedulaPersona() {
        return cedulaPersona;
    }

    public void setCedulaPersona(Integer cedulaPersona) {
        this.cedulaPersona = cedulaPersona;
    }

    public String getDirreccion() {
        return dirreccion;
    }

    public void setDirreccion(String dirreccion) {
        this.dirreccion = dirreccion;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDirrecion != null ? idDirrecion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaDirreccion)) {
            return false;
        }
        PersonaDirreccion other = (PersonaDirreccion) object;
        if ((this.idDirrecion == null && other.idDirrecion != null) || (this.idDirrecion != null && !this.idDirrecion.equals(other.idDirrecion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.sofcoiso.modelo.PersonaDirreccion[ idDirrecion=" + idDirrecion + " ]";
    }
    
}
