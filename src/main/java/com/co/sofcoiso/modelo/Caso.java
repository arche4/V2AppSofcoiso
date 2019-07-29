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
import javax.persistence.ManyToOne;
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
@Table(name = "caso")
@NamedQueries({
    @NamedQuery(name = "Caso.findAll", query = "SELECT c FROM Caso c")
    , @NamedQuery(name = "Caso.findByCodigocaso", query = "SELECT c FROM Caso c WHERE c.codigocaso = :codigocaso")
    , @NamedQuery(name = "Caso.findByDescripcionCaso", query = "SELECT c FROM Caso c WHERE c.descripcionCaso = :descripcionCaso")
    , @NamedQuery(name = "Caso.findByFechaInicioAfectacion", query = "SELECT c FROM Caso c WHERE c.fechaInicioAfectacion = :fechaInicioAfectacion")
    , @NamedQuery(name = "Caso.findByPcl", query = "SELECT c FROM Caso c WHERE c.pcl = :pcl")
    , @NamedQuery(name = "Caso.findByParteAfectada", query = "SELECT c FROM Caso c WHERE c.parteAfectada = :parteAfectada")
    , @NamedQuery(name = "Caso.findByTiempoIncapacidad", query = "SELECT c FROM Caso c WHERE c.tiempoIncapacidad = :tiempoIncapacidad")
    , @NamedQuery(name = "Caso.findByCreado", query = "SELECT c FROM Caso c WHERE c.creado = :creado")
    , @NamedQuery(name = "Caso.findByAsignado", query = "SELECT c FROM Caso c WHERE c.asignado = :asignado")})
public class Caso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codigocaso")
    private Integer codigocaso;
    @Size(max = 5000)
    @Column(name = "descripcion_caso")
    private String descripcionCaso;
    @Size(max = 20)
    @Column(name = "fecha_inicio_afectacion")
    private String fechaInicioAfectacion;
    @Size(max = 100)
    @Column(name = "pcl")
    private String pcl;
    @Size(max = 100)
    @Column(name = "parte_afectada")
    private String parteAfectada;
    @Size(max = 15)
    @Column(name = "tiempo_incapacidad")
    private String tiempoIncapacidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "creado")
    private String creado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "asignado")
    private String asignado;
    @JoinColumn(name = "estado_caso_codigoestado", referencedColumnName = "codigoestado")
    @ManyToOne(optional = false)
    private EstadoCaso estadoCasoCodigoestado;
    @JoinColumn(name = "persona_cedula", referencedColumnName = "cedula")
    @ManyToOne(optional = false)
    private Persona personaCedula;
    @JoinColumn(name = "tipo_caso_codigo_tipo_caso", referencedColumnName = "codigo_tipo_caso")
    @ManyToOne(optional = false)
    private TipoCaso tipoCasoCodigoTipoCaso;

    public Caso() {
    }

    public Caso(Integer codigocaso) {
        this.codigocaso = codigocaso;
    }

    public Caso(Integer codigocaso, String creado, String asignado) {
        this.codigocaso = codigocaso;
        this.creado = creado;
        this.asignado = asignado;
    }

    public Caso(Integer codigoCaso, String textarea, String fechaAfectacion, String pcl,
            String parteAfectada, String tiempoInca, String creado,
            Persona per,
            String asignado,
            EstadoCaso estadocaso, TipoCaso tipo) {
        this.codigocaso = codigoCaso;
        this.descripcionCaso = textarea;
        this.fechaInicioAfectacion = fechaAfectacion;
        this.pcl = pcl;
        this.parteAfectada = parteAfectada;
        this.tiempoIncapacidad = tiempoInca;
        this.creado = creado;
        this.personaCedula = per;
        this.asignado = asignado;
        this.estadoCasoCodigoestado = estadocaso;
        this.tipoCasoCodigoTipoCaso = tipo;
    }
    public Integer getCodigocaso() {
        return codigocaso;
    }

    public void setCodigocaso(Integer codigocaso) {
        this.codigocaso = codigocaso;
    }

    public String getDescripcionCaso() {
        return descripcionCaso;
    }

    public void setDescripcionCaso(String descripcionCaso) {
        this.descripcionCaso = descripcionCaso;
    }

    public String getFechaInicioAfectacion() {
        return fechaInicioAfectacion;
    }

    public void setFechaInicioAfectacion(String fechaInicioAfectacion) {
        this.fechaInicioAfectacion = fechaInicioAfectacion;
    }

    public String getPcl() {
        return pcl;
    }

    public void setPcl(String pcl) {
        this.pcl = pcl;
    }

    public String getParteAfectada() {
        return parteAfectada;
    }

    public void setParteAfectada(String parteAfectada) {
        this.parteAfectada = parteAfectada;
    }

    public String getTiempoIncapacidad() {
        return tiempoIncapacidad;
    }

    public void setTiempoIncapacidad(String tiempoIncapacidad) {
        this.tiempoIncapacidad = tiempoIncapacidad;
    }

    public String getCreado() {
        return creado;
    }

    public void setCreado(String creado) {
        this.creado = creado;
    }

    public String getAsignado() {
        return asignado;
    }

    public void setAsignado(String asignado) {
        this.asignado = asignado;
    }

    public EstadoCaso getEstadoCasoCodigoestado() {
        return estadoCasoCodigoestado;
    }

    public void setEstadoCasoCodigoestado(EstadoCaso estadoCasoCodigoestado) {
        this.estadoCasoCodigoestado = estadoCasoCodigoestado;
    }

    public Persona getPersonaCedula() {
        return personaCedula;
    }

    public void setPersonaCedula(Persona personaCedula) {
        this.personaCedula = personaCedula;
    }

    public TipoCaso getTipoCasoCodigoTipoCaso() {
        return tipoCasoCodigoTipoCaso;
    }

    public void setTipoCasoCodigoTipoCaso(TipoCaso tipoCasoCodigoTipoCaso) {
        this.tipoCasoCodigoTipoCaso = tipoCasoCodigoTipoCaso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigocaso != null ? codigocaso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Caso)) {
            return false;
        }
        Caso other = (Caso) object;
        if ((this.codigocaso == null && other.codigocaso != null) || (this.codigocaso != null && !this.codigocaso.equals(other.codigocaso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.sofcoiso.modelo.Caso[ codigocaso=" + codigocaso + " ]";
    }
    
}
