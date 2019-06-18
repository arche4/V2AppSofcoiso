package com.co.sofcoiso.modelo;

import com.co.sofcoiso.modelo.Afp;
import com.co.sofcoiso.modelo.Arl;
import com.co.sofcoiso.modelo.Caso;
import com.co.sofcoiso.modelo.Eps;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-06-16T21:46:13")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, String> apellidoDos;
    public static volatile SingularAttribute<Persona, String> fechaNacimiento;
    public static volatile SingularAttribute<Persona, String> antiguedadEmpresa;
    public static volatile SingularAttribute<Persona, Integer> cedula;
    public static volatile SingularAttribute<Persona, String> fechaClinica;
    public static volatile SingularAttribute<Persona, String> nombre;
    public static volatile SingularAttribute<Persona, String> edad;
    public static volatile SingularAttribute<Persona, Arl> arlCodigoarl;
    public static volatile SingularAttribute<Persona, Eps> epsCodigoeps;
    public static volatile SingularAttribute<Persona, Afp> afpCodigoafp;
    public static volatile CollectionAttribute<Persona, Caso> casoCollection;
    public static volatile SingularAttribute<Persona, String> apellidoUno;
    public static volatile SingularAttribute<Persona, String> genero;
    public static volatile SingularAttribute<Persona, Integer> recomendado;
    public static volatile SingularAttribute<Persona, String> empresa;
    public static volatile SingularAttribute<Persona, String> cargo;

}