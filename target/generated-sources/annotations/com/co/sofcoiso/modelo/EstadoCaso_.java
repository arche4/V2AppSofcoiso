package com.co.sofcoiso.modelo;

import com.co.sofcoiso.modelo.Caso;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-08-11T19:21:51")
@StaticMetamodel(EstadoCaso.class)
public class EstadoCaso_ { 

    public static volatile SingularAttribute<EstadoCaso, Integer> codigoestado;
    public static volatile CollectionAttribute<EstadoCaso, Caso> casoCollection;
    public static volatile SingularAttribute<EstadoCaso, String> nombreEstado;
    public static volatile SingularAttribute<EstadoCaso, String> descripcionestado;

}