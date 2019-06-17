/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.co.sofcoiso.modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "tipo_caso")
@NamedQueries({
    @NamedQuery(name = "TipoCaso.findAll", query = "SELECT t FROM TipoCaso t")
    , @NamedQuery(name = "TipoCaso.findByCodigoTipoCaso", query = "SELECT t FROM TipoCaso t WHERE t.codigoTipoCaso = :codigoTipoCaso")
    , @NamedQuery(name = "TipoCaso.findByTipoCaso", query = "SELECT t FROM TipoCaso t WHERE t.tipoCaso = :tipoCaso")
    , @NamedQuery(name = "TipoCaso.findByNombreTipoCaso", query = "SELECT t FROM TipoCaso t WHERE t.nombreTipoCaso = :nombreTipoCaso")
    , @NamedQuery(name = "TipoCaso.findByDescripcionTipoCaso", query = "SELECT t FROM TipoCaso t WHERE t.descripcionTipoCaso = :descripcionTipoCaso")})
public class TipoCaso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "codigo_tipo_caso")
    private String codigoTipoCaso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "tipo_caso")
    private String tipoCaso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "nombre_tipo_caso")
    private String nombreTipoCaso;
    @Size(max = 50)
    @Column(name = "descripcion_tipo_caso")
    private String descripcionTipoCaso;
    @ManyToMany(mappedBy = "tipoCasoCollection")
    private Collection<Caso> casoCollection;

    public TipoCaso() {
    }

    public TipoCaso(String codigoTipoCaso) {
        this.codigoTipoCaso = codigoTipoCaso;
    }

    public TipoCaso(String codigoTipoCaso, String tipoCaso, String nombreTipoCaso) {
        this.codigoTipoCaso = codigoTipoCaso;
        this.tipoCaso = tipoCaso;
        this.nombreTipoCaso = nombreTipoCaso;
    }

    public String getCodigoTipoCaso() {
        return codigoTipoCaso;
    }

    public void setCodigoTipoCaso(String codigoTipoCaso) {
        this.codigoTipoCaso = codigoTipoCaso;
    }

    public String getTipoCaso() {
        return tipoCaso;
    }

    public void setTipoCaso(String tipoCaso) {
        this.tipoCaso = tipoCaso;
    }

    public String getNombreTipoCaso() {
        return nombreTipoCaso;
    }

    public void setNombreTipoCaso(String nombreTipoCaso) {
        this.nombreTipoCaso = nombreTipoCaso;
    }

    public String getDescripcionTipoCaso() {
        return descripcionTipoCaso;
    }

    public void setDescripcionTipoCaso(String descripcionTipoCaso) {
        this.descripcionTipoCaso = descripcionTipoCaso;
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
        hash += (codigoTipoCaso != null ? codigoTipoCaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoCaso)) {
            return false;
        }
        TipoCaso other = (TipoCaso) object;
        if ((this.codigoTipoCaso == null && other.codigoTipoCaso != null) || (this.codigoTipoCaso != null && !this.codigoTipoCaso.equals(other.codigoTipoCaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.sofcoiso.modelo.TipoCaso[ codigoTipoCaso=" + codigoTipoCaso + " ]";
    }
    
}
