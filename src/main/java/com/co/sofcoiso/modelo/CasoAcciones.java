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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author manue
 */
@Entity
@Table(name = "caso_acciones")
@NamedQueries({
    @NamedQuery(name = "CasoAcciones.findAll", query = "SELECT c FROM CasoAcciones c")
    , @NamedQuery(name = "CasoAcciones.findByComentarios", query = "SELECT c FROM CasoAcciones c WHERE c.comentarios = :comentarios")
    , @NamedQuery(name = "CasoAcciones.findByCasoCodigocaso", query = "SELECT c FROM CasoAcciones c WHERE c.casoCodigocaso = :casoCodigocaso")
    , @NamedQuery(name = "CasoAcciones.findByUsuario", query = "SELECT c FROM CasoAcciones c WHERE c.usuario = :usuario")})
public class CasoAcciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Lob
    @Column(name = "archivo")
    private byte[] archivo;
    @Size(max = 1000)
    @Column(name = "comentarios")
    private String comentarios;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "caso_codigocaso")
    private Integer casoCodigocaso;
    @Size(max = 20)
    @Column(name = "usuario")
    private String usuario;
    @JoinColumn(name = "caso_codigocaso", referencedColumnName = "codigocaso", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Caso caso;

    public CasoAcciones() {
    }

    public CasoAcciones(Integer casoCodigocaso) {
        this.casoCodigocaso = casoCodigocaso;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Integer getCasoCodigocaso() {
        return casoCodigocaso;
    }

    public void setCasoCodigocaso(Integer casoCodigocaso) {
        this.casoCodigocaso = casoCodigocaso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Caso getCaso() {
        return caso;
    }

    public void setCaso(Caso caso) {
        this.caso = caso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (casoCodigocaso != null ? casoCodigocaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CasoAcciones)) {
            return false;
        }
        CasoAcciones other = (CasoAcciones) object;
        if ((this.casoCodigocaso == null && other.casoCodigocaso != null) || (this.casoCodigocaso != null && !this.casoCodigocaso.equals(other.casoCodigocaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.sofcoiso.modelo.CasoAcciones[ casoCodigocaso=" + casoCodigocaso + " ]";
    }
    
}
