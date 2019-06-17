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
@Table(name = "afp ")
@NamedQueries({
    @NamedQuery(name = "Afp.findAll", query = "SELECT a FROM Afp a")
    , @NamedQuery(name = "Afp.findByCodigoafp", query = "SELECT a FROM Afp a WHERE a.codigoafp = :codigoafp")
    , @NamedQuery(name = "Afp.findByNombre", query = "SELECT a FROM Afp a WHERE a.nombre = :nombre")})
public class Afp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "codigoafp")
    private String codigoafp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "afpCodigoafp")
    private Collection<Persona> personaCollection;

    public Afp() {
    }

    public Afp(String codigoafp) {
        this.codigoafp = codigoafp;
    }

    public Afp(String codigoafp, String nombre) {
        this.codigoafp = codigoafp;
        this.nombre = nombre;
    }

    public String getCodigoafp() {
        return codigoafp;
    }

    public void setCodigoafp(String codigoafp) {
        this.codigoafp = codigoafp;
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
        hash += (codigoafp != null ? codigoafp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Afp)) {
            return false;
        }
        Afp other = (Afp) object;
        if ((this.codigoafp == null && other.codigoafp != null) || (this.codigoafp != null && !this.codigoafp.equals(other.codigoafp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.sofcoiso.modelo.Afp[ codigoafp=" + codigoafp + " ]";
    }
    
}
