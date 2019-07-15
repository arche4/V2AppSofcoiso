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
@Table(name = "acciones_caso")
@NamedQueries({
    @NamedQuery(name = "AccionesCaso.findAll", query = "SELECT a FROM AccionesCaso a")
    , @NamedQuery(name = "AccionesCaso.findByIdCasoAcciones", query = "SELECT a FROM AccionesCaso a WHERE a.idCasoAcciones = :idCasoAcciones")
    , @NamedQuery(name = "AccionesCaso.findByCodigo", query = "SELECT a FROM AccionesCaso a WHERE a.codigo = :codigo")
    , @NamedQuery(name = "AccionesCaso.findByUsuario", query = "SELECT a FROM AccionesCaso a WHERE a.usuario = :usuario")
    , @NamedQuery(name = "AccionesCaso.findByCometario", query = "SELECT a FROM AccionesCaso a WHERE a.cometario = :cometario")
    , @NamedQuery(name = "AccionesCaso.findByArchivo", query = "SELECT a FROM AccionesCaso a WHERE a.archivo = :archivo")
    , @NamedQuery(name = "AccionesCaso.findByFechaActualizada", query = "SELECT a FROM AccionesCaso a WHERE a.fechaActualizada = :fechaActualizada")})
public class AccionesCaso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_caso_acciones")
    private Integer idCasoAcciones;
    @Column(name = "codigo")
    private Integer codigo;
    @Size(max = 50)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 1000)
    @Column(name = "cometario")
    private String cometario;
    @Size(max = 50)
    @Column(name = "archivo")
    private String archivo;
    @Size(max = 30)
    @Column(name = "fecha_actualizada")
    private String fechaActualizada;

    public AccionesCaso() {
    }

    public AccionesCaso(Integer idCasoAcciones) {
        this.idCasoAcciones = idCasoAcciones;
    }

    public AccionesCaso(Integer codigo, String usuario, String comentarios, String archivos, String fecha) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.cometario = comentarios;
        this.archivo = archivos;
        this.fechaActualizada = fecha;
    }

    public Integer getIdCasoAcciones() {
        return idCasoAcciones;
    }

    public void setIdCasoAcciones(Integer idCasoAcciones) {
        this.idCasoAcciones = idCasoAcciones;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCometario() {
        return cometario;
    }

    public void setCometario(String cometario) {
        this.cometario = cometario;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getFechaActualizada() {
        return fechaActualizada;
    }

    public void setFechaActualizada(String fechaActualizada) {
        this.fechaActualizada = fechaActualizada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCasoAcciones != null ? idCasoAcciones.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccionesCaso)) {
            return false;
        }
        AccionesCaso other = (AccionesCaso) object;
        if ((this.idCasoAcciones == null && other.idCasoAcciones != null) || (this.idCasoAcciones != null && !this.idCasoAcciones.equals(other.idCasoAcciones))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.sofcoiso.modelo.AccionesCaso[ idCasoAcciones=" + idCasoAcciones + " ]";
    }

}
