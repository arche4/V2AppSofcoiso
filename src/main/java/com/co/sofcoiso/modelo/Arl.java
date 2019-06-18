/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author manue
 */
@Entity
@Table(name = "arl ")
@NamedQueries({
    @NamedQuery(name = "Arl.findAll", query = "SELECT a FROM Arl a")
    , @NamedQuery(name = "Arl.findByCodigoarl", query = "SELECT a FROM Arl a WHERE a.codigoarl = :codigoarl")
    , @NamedQuery(name = "Arl.findByNombre", query = "SELECT a FROM Arl a WHERE a.nombre = :nombre")})
public class Arl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "codigoarl")
    private String codigoarl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "arlCodigoarl")
    private Collection<Persona> personaCollection;

    public Arl() {
    }

    public Arl(String codigoarl) {
        this.codigoarl = codigoarl;
    }

    public Arl(String codigoarl, String nombre) {
        this.codigoarl = codigoarl;
        this.nombre = nombre;
    }

    public String getCodigoarl() {
        return codigoarl;
    }

    public void setCodigoarl(String codigoarl) {
        this.codigoarl = codigoarl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<Persona> getPersonaCollection() {
        return personaCollection;
    }

    public void setPersonaCollection(Collection<Persona> personaCollection) {
        this.personaCollection = personaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoarl != null ? codigoarl.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arl)) {
            return false;
        }
        Arl other = (Arl) object;
        if ((this.codigoarl == null && other.codigoarl != null) || (this.codigoarl != null && !this.codigoarl.equals(other.codigoarl))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.sofcoiso.modelo.Arl[ codigoarl=" + codigoarl + " ]";
    }
    
}
