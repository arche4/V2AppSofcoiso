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
@Table(name = "formacion")
@NamedQueries({
    @NamedQuery(name = "Formacion.findAll", query = "SELECT f FROM Formacion f")
    , @NamedQuery(name = "Formacion.findByIdFormacion", query = "SELECT f FROM Formacion f WHERE f.idFormacion = :idFormacion")
    , @NamedQuery(name = "Formacion.findByTipoFormacion", query = "SELECT f FROM Formacion f WHERE f.tipoFormacion = :tipoFormacion")
    , @NamedQuery(name = "Formacion.findByFechaFormacion", query = "SELECT f FROM Formacion f WHERE f.fechaFormacion = :fechaFormacion")
    , @NamedQuery(name = "Formacion.findByTemas", query = "SELECT f FROM Formacion f WHERE f.temas = :temas")
    , @NamedQuery(name = "Formacion.findByNumeroAsistentes", query = "SELECT f FROM Formacion f WHERE f.numeroAsistentes = :numeroAsistentes")})
public class Formacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "id_formacion")
    private String idFormacion;
    @Size(max = 500)
    @Column(name = "tipo_formacion")
    private String tipoFormacion;
    @Size(max = 500)
    @Column(name = "fecha_formacion")
    private String fechaFormacion;
    @Size(max = 500)
    @Column(name = "temas")
    private String temas;
    @Size(max = 500)
    @Column(name = "numero_asistentes")
    private String numeroAsistentes;

    public Formacion() {
    }

    public Formacion(String idFormacion) {
        this.idFormacion = idFormacion;
    }

    public String getIdFormacion() {
        return idFormacion;
    }

    public void setIdFormacion(String idFormacion) {
        this.idFormacion = idFormacion;
    }

    public String getTipoFormacion() {
        return tipoFormacion;
    }

    public void setTipoFormacion(String tipoFormacion) {
        this.tipoFormacion = tipoFormacion;
    }

    public String getFechaFormacion() {
        return fechaFormacion;
    }

    public void setFechaFormacion(String fechaFormacion) {
        this.fechaFormacion = fechaFormacion;
    }

    public String getTemas() {
        return temas;
    }

    public void setTemas(String temas) {
        this.temas = temas;
    }

    public String getNumeroAsistentes() {
        return numeroAsistentes;
    }

    public void setNumeroAsistentes(String numeroAsistentes) {
        this.numeroAsistentes = numeroAsistentes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFormacion != null ? idFormacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Formacion)) {
            return false;
        }
        Formacion other = (Formacion) object;
        if ((this.idFormacion == null && other.idFormacion != null) || (this.idFormacion != null && !this.idFormacion.equals(other.idFormacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.sofcoiso.modelo.Formacion[ idFormacion=" + idFormacion + " ]";
    }
    
}
