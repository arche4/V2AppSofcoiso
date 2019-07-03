package com.co.sofcoiso.modelo;

import com.co.sofcoiso.modelo.Afp;
import com.co.sofcoiso.modelo.Arl;
import com.co.sofcoiso.modelo.Caso;
import com.co.sofcoiso.modelo.Eps;
import com.co.sofcoiso.modelo.Persona;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-07-01T21:03:25")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, String> apellidoDos;
    public static volatile SingularAttribute<Persona, String> fechaNacimiento;
    public static volatile SingularAttribute<Persona, String> antiguedadEmpresa;
    public static volatile SingularAttribute<Persona, Integer> cedula;
    public static volatile SingularAttribute<Persona, Afp> codigoafp;
    public static volatile ListAttribute<Persona, Persona> bodyTable;
    public static volatile SingularAttribute<Persona, String> eps;
    public static volatile SingularAttribute<Persona, String> afp;
    public static volatile SingularAttribute<Persona, String> fechaClinica;
    public static volatile SingularAttribute<Persona, String> nombre;
    public static volatile SingularAttribute<Persona, String> edad;
    public static volatile CollectionAttribute<Persona, Caso> casoCollection;
    public static volatile SingularAttribute<Persona, String> apellidoUno;
    public static volatile SingularAttribute<Persona, Arl> codigoarl;
    public static volatile SingularAttribute<Persona, String> genero;
    public static volatile SingularAttribute<Persona, Integer> recomendado;
    public static volatile SingularAttribute<Persona, List> header;
    public static volatile SingularAttribute<Persona, String> arl;
    public static volatile SingularAttribute<Persona, Eps> codigoeps;
    public static volatile SingularAttribute<Persona, String> empresa;
    public static volatile SingularAttribute<Persona, String> cargo;
    public static volatile SingularAttribute<Persona, String> telefono;

}