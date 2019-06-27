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
@Table(name = "acciones_caso")
@NamedQueries({
    @NamedQuery(name = "AccionesCaso.findAll", query = "SELECT a FROM AccionesCaso a")
    , @NamedQuery(name = "AccionesCaso.findByCodigoCaso", query = "SELECT a FROM AccionesCaso a WHERE a.codigoCaso = :codigoCaso")
    , @NamedQuery(name = "AccionesCaso.findByUsuario", query = "SELECT a FROM AccionesCaso a WHERE a.usuario = :usuario")
    , @NamedQuery(name = "AccionesCaso.findByArchivo", query = "SELECT a FROM AccionesCaso a WHERE a.archivo = :archivo")
    , @NamedQuery(name = "AccionesCaso.findByCometario", query = "SELECT a FROM AccionesCaso a WHERE a.cometario = :cometario")})
public class AccionesCaso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigoCaso")
    private Integer codigoCaso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usuario")
    private String usuario;
    @Size(max = 100)
    @Column(name = "archivo")
    private String archivo;
    @Size(max = 1000)
    @Column(name = "cometario")
    private String cometario;

    public AccionesCaso() {
    }

    public AccionesCaso(Integer codigoCaso) {
        this.codigoCaso = codigoCaso;
    }

    public AccionesCaso(Integer codigoCaso, String usuario) {
        this.codigoCaso = codigoCaso;
        this.usuario = usuario;
    }
    
    public AccionesCaso(Integer casoCodigocaso,  String usuario, String comentarios, String archivo) {
        this.codigoCaso = casoCodigocaso;
        this.usuario = usuario;
        this.cometario = comentarios;
        this.archivo = archivo;
       
    }
    
    public Integer getCodigoCaso() {
        return codigoCaso;
    }

    public void setCodigoCaso(Integer codigoCaso) {
        this.codigoCaso = codigoCaso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getCometario() {
        return cometario;
    }

    public void setCometario(String cometario) {
        this.cometario = cometario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoCaso != null ? codigoCaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccionesCaso)) {
            return false;
        }
        AccionesCaso other = (AccionesCaso) object;
        if ((this.codigoCaso == null && other.codigoCaso != null) || (this.codigoCaso != null && !this.codigoCaso.equals(other.codigoCaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.sofcoiso.modelo.AccionesCaso[ codigoCaso=" + codigoCaso + " ]";
    }
    
}
