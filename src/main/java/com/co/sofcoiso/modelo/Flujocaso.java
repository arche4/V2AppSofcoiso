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
@Table(name = "flujocaso")
@NamedQueries({
    @NamedQuery(name = "Flujocaso.findAll", query = "SELECT f FROM Flujocaso f")
    , @NamedQuery(name = "Flujocaso.findByIdFlujocaso", query = "SELECT f FROM Flujocaso f WHERE f.idFlujocaso = :idFlujocaso")
    , @NamedQuery(name = "Flujocaso.findByCodigocaso", query = "SELECT f FROM Flujocaso f WHERE f.codigocaso = :codigocaso")
    , @NamedQuery(name = "Flujocaso.findByEstadocaso", query = "SELECT f FROM Flujocaso f WHERE f.estadocaso = :estadocaso")
    , @NamedQuery(name = "Flujocaso.findByUsuario", query = "SELECT f FROM Flujocaso f WHERE f.usuario = :usuario")
    , @NamedQuery(name = "Flujocaso.findByFechaCreacion", query = "SELECT f FROM Flujocaso f WHERE f.fechaCreacion = :fechaCreacion")
    , @NamedQuery(name = "Flujocaso.findByFechaActualizacion", query = "SELECT f FROM Flujocaso f WHERE f.fechaActualizacion = :fechaActualizacion")})
public class Flujocaso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_flujocaso")
    private Integer idFlujocaso;
    @Column(name = "codigocaso")
    private Integer codigocaso;
    @Size(max = 20)
    @Column(name = "estadocaso")
    private String estadocaso;
    @Size(max = 20)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 20)
    @Column(name = "fecha_creacion")
    private String fechaCreacion;
    @Size(max = 20)
    @Column(name = "fecha_actualizacion")
    private String fechaActualizacion;

    public Flujocaso() {
    }

    public Flujocaso(Integer idFlujocaso) {
        this.idFlujocaso = idFlujocaso;
    }

    public Flujocaso(Integer caso, String Usuario, String estadoCaso
            , String fecha_actualizada) {
       
        this.codigocaso = caso;
        this.usuario = Usuario;
        this.estadocaso = estadoCaso;
        this.fechaActualizacion =  fecha_actualizada;
    }
     public Flujocaso(Integer caso,String estadoCaso,String Usuario,
             String fecha_creacion, String fecha_actualizada) {
       
        this.codigocaso = caso;
        this.estadocaso = estadoCaso;
        this.usuario = Usuario;
        this.fechaCreacion = fecha_creacion;
        this.fechaActualizacion =  fecha_actualizada;
    }
    public Integer getIdFlujocaso() {
        return idFlujocaso;
    }

    public void setIdFlujocaso(Integer idFlujocaso) {
        this.idFlujocaso = idFlujocaso;
    }

    public Integer getCodigocaso() {
        return codigocaso;
    }

    public void setCodigocaso(Integer codigocaso) {
        this.codigocaso = codigocaso;
    }

    public String getEstadocaso() {
        return estadocaso;
    }

    public void setEstadocaso(String estadocaso) {
        this.estadocaso = estadocaso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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
        hash += (idFlujocaso != null ? idFlujocaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flujocaso)) {
            return false;
        }
        Flujocaso other = (Flujocaso) object;
        if ((this.idFlujocaso == null && other.idFlujocaso != null) || (this.idFlujocaso != null && !this.idFlujocaso.equals(other.idFlujocaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.sofcoiso.modelo.Flujocaso[ idFlujocaso=" + idFlujocaso + " ]";
    }
    
}
