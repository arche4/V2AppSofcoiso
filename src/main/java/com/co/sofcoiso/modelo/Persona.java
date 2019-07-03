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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "persona")
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")
    , @NamedQuery(name = "Persona.findByCedula", query = "SELECT p FROM Persona p WHERE p.cedula = :cedula")
    , @NamedQuery(name = "Persona.findByNombre", query = "SELECT p FROM Persona p WHERE p.nombre = :nombre")
    , @NamedQuery(name = "Persona.findByApellidoUno", query = "SELECT p FROM Persona p WHERE p.apellidoUno = :apellidoUno")
    , @NamedQuery(name = "Persona.findByApellidoDos", query = "SELECT p FROM Persona p WHERE p.apellidoDos = :apellidoDos")
    , @NamedQuery(name = "Persona.findByGenero", query = "SELECT p FROM Persona p WHERE p.genero = :genero")
    , @NamedQuery(name = "Persona.findByFechaNacimiento", query = "SELECT p FROM Persona p WHERE p.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Persona.findByEdad", query = "SELECT p FROM Persona p WHERE p.edad = :edad")
    , @NamedQuery(name = "Persona.findByEmpresa", query = "SELECT p FROM Persona p WHERE p.empresa = :empresa")
    , @NamedQuery(name = "Persona.findByAntiguedadEmpresa", query = "SELECT p FROM Persona p WHERE p.antiguedadEmpresa = :antiguedadEmpresa")
    , @NamedQuery(name = "Persona.findByCargo", query = "SELECT p FROM Persona p WHERE p.cargo = :cargo")
    , @NamedQuery(name = "Persona.findByFechaClinica", query = "SELECT p FROM Persona p WHERE p.fechaClinica = :fechaClinica")
    , @NamedQuery(name = "Persona.findByRecomendado", query = "SELECT p FROM Persona p WHERE p.recomendado = :recomendado")
    , @NamedQuery(name = "Persona.findByTelefono", query = "SELECT p FROM Persona p WHERE p.telefono = :telefono")})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cedula")
    private Integer cedula;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "apellido_uno")
    private String apellidoUno;
    @Size(max = 15)
    @Column(name = "apellido_dos")
    private String apellidoDos;
    @Size(max = 2)
    @Column(name = "genero")
    private String genero;
    @Size(max = 20)
    @Column(name = "fecha_nacimiento")
    private String fechaNacimiento;
    @Size(max = 2)
    @Column(name = "edad")
    private String edad;
    @Size(max = 50)
    @Column(name = "empresa")
    private String empresa;
    @Size(max = 2)
    @Column(name = "antiguedad_empresa")
    private String antiguedadEmpresa;
    @Size(max = 50)
    @Column(name = "cargo")
    private String cargo;
    @Size(max = 20)
    @Column(name = "fecha_clinica")
    private String fechaClinica;
    @Column(name = "recomendado")
    private Integer recomendado;
    @Size(max = 10)
    @Column(name = "telefono")
    private String telefono;
    @JoinColumn(name = "codigoafp", referencedColumnName = "codigoafp")
    @ManyToOne(optional = false)
    private Afp codigoafp;
    @JoinColumn(name = "codigoarl", referencedColumnName = "codigoarl")
    @ManyToOne(optional = false)
    private Arl codigoarl;
    @JoinColumn(name = "codigoeps", referencedColumnName = "codigoeps")
    @ManyToOne(optional = false)
    private Eps codigoeps;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personaCedula")
    private Collection<Caso> casoCollection;

    public Persona() {
    }

    public Persona(Integer cedula) {
        this.cedula = cedula;
    }

    public Persona(Integer cedula, String nombre, String apellidoUno) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellidoUno = apellidoUno;
    }

    public Persona(String cedula, String nombre, String apellidoUno, String apellidoDos, String genero,
            String fechaCumpleaños, String edad, String empresa, Eps eps, Arl arl, Afp codigoafp, String fechaClinica,
            String antiguedad, String recomendado, String telefono, String cargo) {

        this.cedula = Integer.parseInt(cedula);
        this.nombre = nombre;
        this.apellidoUno = apellidoUno;
        this.apellidoDos = apellidoDos;
        this.genero = genero;
        this.fechaNacimiento = fechaCumpleaños;
        this.edad = edad;
        this.empresa = empresa;
        this.antiguedadEmpresa = antiguedad;
        this.telefono = telefono;
        this.codigoeps = eps;
        this.codigoarl = arl;
        this.codigoafp = codigoafp;
        this.cargo = cargo;
        this.fechaClinica = fechaClinica;
        this.recomendado = Integer.parseInt(recomendado);
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoUno() {
        return apellidoUno;
    }

    public void setApellidoUno(String apellidoUno) {
        this.apellidoUno = apellidoUno;
    }

    public String getApellidoDos() {
        return apellidoDos;
    }

    public void setApellidoDos(String apellidoDos) {
        this.apellidoDos = apellidoDos;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getAntiguedadEmpresa() {
        return antiguedadEmpresa;
    }

    public void setAntiguedadEmpresa(String antiguedadEmpresa) {
        this.antiguedadEmpresa = antiguedadEmpresa;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getFechaClinica() {
        return fechaClinica;
    }

    public void setFechaClinica(String fechaClinica) {
        this.fechaClinica = fechaClinica;
    }

    public Integer getRecomendado() {
        return recomendado;
    }

    public void setRecomendado(Integer recomendado) {
        this.recomendado = recomendado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Afp getCodigoafp() {
        return codigoafp;
    }

    public void setCodigoafp(Afp codigoafp) {
        this.codigoafp = codigoafp;
    }

    public Arl getCodigoarl() {
        return codigoarl;
    }

    public void setCodigoarl(Arl codigoarl) {
        this.codigoarl = codigoarl;
    }

    public Eps getCodigoeps() {
        return codigoeps;
    }

    public void setCodigoeps(Eps codigoeps) {
        this.codigoeps = codigoeps;
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
        hash += (cedula != null ? cedula.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.cedula == null && other.cedula != null) || (this.cedula != null && !this.cedula.equals(other.cedula))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.co.sofcoiso.modelo.Persona[ cedula=" + cedula + " ]";
    }

}
