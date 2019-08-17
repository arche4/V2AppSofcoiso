package com.co.sofcoiso.modelo;

import com.co.sofcoiso.modelo.EstadoCaso;
import com.co.sofcoiso.modelo.Persona;
import com.co.sofcoiso.modelo.TipoCaso;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-17T13:10:59")
@StaticMetamodel(Caso.class)
public class Caso_ { 

    public static volatile SingularAttribute<Caso, String> descripcionCaso;
    public static volatile SingularAttribute<Caso, String> creado;
    public static volatile SingularAttribute<Caso, EstadoCaso> estadoCasoCodigoestado;
    public static volatile SingularAttribute<Caso, String> parteAfectada;
    public static volatile SingularAttribute<Caso, String> asignado;
    public static volatile SingularAttribute<Caso, Integer> codigocaso;
    public static volatile SingularAttribute<Caso, String> pcl;
    public static volatile SingularAttribute<Caso, String> tiempoIncapacidad;
    public static volatile SingularAttribute<Caso, String> fechaInicioAfectacion;
    public static volatile SingularAttribute<Caso, Persona> personaCedula;
    public static volatile SingularAttribute<Caso, TipoCaso> tipoCasoCodigoTipoCaso;

}