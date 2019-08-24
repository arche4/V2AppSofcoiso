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
@Table(name = "comuna_medellin")
@NamedQueries({
    @NamedQuery(name = "ComunaMedellin.findAll", query = "SELECT c FROM ComunaMedellin c")
    , @NamedQuery(name = "ComunaMedellin.findByIdComuna", query = "SELECT c FROM ComunaMedellin c WHERE c.idComuna = :idComuna")
    , @NamedQuery(name = "ComunaMedellin.findByCodigocomuna", query = "SELECT c FROM ComunaMedellin c WHERE c.codigocomuna = :codigocomuna")
    , @NamedQuery(name = "ComunaMedellin.findByComunaNombre", query = "SELECT c FROM ComunaMedellin c WHERE c.comunaNombre = :comunaNombre")})
public class ComunaMedellin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_comuna")
    private Integer idComuna;
    @Column(name = "codigocomuna")
    private Integer codigocomuna;
    @Size(max = 30)
    @Column(name = "comuna_nombre")
    private String comunaNombre;

    public ComunaMedellin() {
    }

    public ComunaMedellin(Integer idComuna) {
        this.idComuna = idComuna;
    }

    public Integer getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(Integer idComuna) {
        this.idComuna = idComuna;
    }

    public Integer getCodigocomuna() {
        return codigocomuna;
    }

    public void setCodigocomuna(Integer codigocomuna) {
        this.codigocomuna = codigocomuna;
    }

    public String getComunaNombre() {
        return comunaNombre;
    }

    public void setComunaNombre(String comunaNombre) {
        this.comunaNombre = comunaNombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComuna != null ? idComuna.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ComunaMedellin)) {
            return false;
        }
        ComunaMedellin other = (ComunaMedellin) object;
        if ((this.idComuna == null && other.idComuna != null) || (this.idComuna != null && !this.idComuna.equals(other.idComuna))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.sofcoiso.modelo.ComunaMedellin[ idComuna=" + idComuna + " ]";
    }
    
}
