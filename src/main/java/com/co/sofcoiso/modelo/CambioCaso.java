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
@Table(name = "cambio_caso")
@NamedQueries({
    @NamedQuery(name = "CambioCaso.findAll", query = "SELECT c FROM CambioCaso c")
    , @NamedQuery(name = "CambioCaso.findByIdCambioCaso", query = "SELECT c FROM CambioCaso c WHERE c.idCambioCaso = :idCambioCaso")
    , @NamedQuery(name = "CambioCaso.findByCodigoCaso", query = "SELECT c FROM CambioCaso c WHERE c.codigoCaso = :codigoCaso")
    , @NamedQuery(name = "CambioCaso.findByEstadoCaso", query = "SELECT c FROM CambioCaso c WHERE c.estadoCaso = :estadoCaso")
    , @NamedQuery(name = "CambioCaso.findByUsuario", query = "SELECT c FROM CambioCaso c WHERE c.usuario = :usuario")
    , @NamedQuery(name = "CambioCaso.findByFechaActualizacion", query = "SELECT c FROM CambioCaso c WHERE c.fechaActualizacion = :fechaActualizacion")})
public class CambioCaso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cambio_caso")
    private Integer idCambioCaso;
    @Size(max = 20)
    @Column(name = "codigo_caso")
    private String codigoCaso;
    @Size(max = 20)
    @Column(name = "estado_caso")
    private String estadoCaso;
    @Size(max = 20)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 20)
    @Column(name = "fecha_actualizacion")
    private String fechaActualizacion;

    public CambioCaso() {
    }

    public CambioCaso(Integer idCambioCaso) {
        this.idCambioCaso = idCambioCaso;
    }
    
      public CambioCaso(String  casoCodigo,String casoEstado, String usuario,  String ultimaActualizacion) {
        this.codigoCaso = casoCodigo;
        this.estadoCaso = casoEstado;
        this.usuario = usuario;
        this.fechaActualizacion = ultimaActualizacion;
    }

    public Integer getIdCambioCaso() {
        return idCambioCaso;
    }

    public void setIdCambioCaso(Integer idCambioCaso) {
        this.idCambioCaso = idCambioCaso;
    }

    public String getCodigoCaso() {
        return codigoCaso;
    }

    public void setCodigoCaso(String codigoCaso) {
        this.codigoCaso = codigoCaso;
    }

    public String getEstadoCaso() {
        return estadoCaso;
    }

    public void setEstadoCaso(String estadoCaso) {
        this.estadoCaso = estadoCaso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCambioCaso != null ? idCambioCaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CambioCaso)) {
            return false;
        }
        CambioCaso other = (CambioCaso) object;
        if ((this.idCambioCaso == null && other.idCambioCaso != null) || (this.idCambioCaso != null && !this.idCambioCaso.equals(other.idCambioCaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.sofcoiso.modelo.CambioCaso[ idCambioCaso=" + idCambioCaso + " ]";
    }
    
}
