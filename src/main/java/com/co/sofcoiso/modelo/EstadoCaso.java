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
@Table(name = "estado_caso")
@NamedQueries({
    @NamedQuery(name = "EstadoCaso.findAll", query = "SELECT e FROM EstadoCaso e")
    , @NamedQuery(name = "EstadoCaso.findByCodigoestado", query = "SELECT e FROM EstadoCaso e WHERE e.codigoestado = :codigoestado")
    , @NamedQuery(name = "EstadoCaso.findByNombreEstado", query = "SELECT e FROM EstadoCaso e WHERE e.nombreEstado = :nombreEstado")
    , @NamedQuery(name = "EstadoCaso.findByDescripcionestado", query = "SELECT e FROM EstadoCaso e WHERE e.descripcionestado = :descripcionestado")})
public class EstadoCaso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoestado")
    private Integer codigoestado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nombre_estado")
    private String nombreEstado;
    @Size(max = 50)
    @Column(name = "descripcionestado")
    private String descripcionestado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadoCasoCodigoestado")
    private Collection<Caso> casoCollection;

    public EstadoCaso() {
    }

    public EstadoCaso(Integer codigoestado) {
        this.codigoestado = codigoestado;
    }

    public EstadoCaso(Integer codigoestado, String nombreEstado) {
        this.codigoestado = codigoestado;
        this.nombreEstado = nombreEstado;
    }
    
      public EstadoCaso(Integer codigoestado, String nombreEstado, String Descripcion) {
        this.codigoestado = codigoestado;
        this.nombreEstado = nombreEstado;
        this.descripcionestado = Descripcion;
    }

    public Integer getCodigoestado() {
        return codigoestado;
    }

    public void setCodigoestado(Integer codigoestado) {
        this.codigoestado = codigoestado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getDescripcionestado() {
        return descripcionestado;
    }

    public void setDescripcionestado(String descripcionestado) {
        this.descripcionestado = descripcionestado;
    }

    public Collection<Caso> getCasoCollection() {
        return casoCollection;
    }

    public void setCasoCollection(Collection<Caso> casoCollection) {
        this.casoCollection = casoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoestado != null ? codigoestado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoCaso)) {
            return false;
        }
        EstadoCaso other = (EstadoCaso) object;
        if ((this.codigoestado == null && other.codigoestado != null) || (this.codigoestado != null && !this.codigoestado.equals(other.codigoestado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.sofcoiso.modelo.EstadoCaso[ codigoestado=" + codigoestado + " ]";
    }
    
}
