function openModal() {
    $('#modalInf').modal();
}
function validar() {
    var cedula = document.getElementById('cedula').value;
    var nombre = document.getElementById('nombre').value;
    var primerApellido = document.getElementById('primerApellido').value;
    var eps = document.getElementById('eps').value;
    var arl = document.getElementById('arl').value;
    var afp = document.getElementById('afp').value;
    var FechaClinica = document.getElementById('FechaClinica').value;
    if (nombre == "" && cedula == "" && primerApellido == ""
            && FechaClinica == "" && eps == "" && arl == "" && afp == "") {
        toastr.error("No ha ingresado Cedula", "Aviso!");
        toastr.error("No ha ingresado Nombre", "Aviso!");
        toastr.error("No ha ingresado Primer Apellido", "Aviso!");
        toastr.error("No ha ingresado Eps", "Aviso!");
        toastr.error("No ha ingresado Arl", "Aviso!");
        toastr.error("No ha ingresado Afp", "Aviso!");
        toastr.error("No ha ingresado FechaClinica", "Aviso!");
        return false;
    }
    if ($.trim(cedula) == "") {
        toastr.error("No ha ingresado Cedula", "Aviso!");
        return false;
    }
    if ($.trim(nombre) == "") {
        toastr.error("No ha ingresado Nombre", "Aviso!");
        return false;
    }
    if ($.trim(primerApellido) == "") {
        toastr.error("No ha ingresado Primer Apellido", "Aviso!");
        return false;
    }
    if ($.trim(eps) == "") {
        toastr.error("No ha ingresado Eps", "Aviso!");
        return false;
    }
    if ($.trim(arl) == "") {
        toastr.error("No ha ingresado Arl", "Aviso!");
        return false;
    }
    if ($.trim(afp) == "") {
        toastr.error("No ha ingresado Afp", "Aviso!");
        return false;
    }
    if ($.trim(FechaClinica) == "") {
        toastr.error("No ha ingresado FechaClinica", "Aviso!");
        return false;
    }
    if (!nombre == "" && !cedula == "" && !primerApellido == "" && !eps == "" && !arl == "" && !afp == "" &&
            !FechaClinica == "") {
        document.getElementById("persona").action = "/sofCoiso/PersonaServlet";
        document.getElementById("persona").submit();
        toastr.success("persona Guardado Correctamente");
        return true;
    }
}
function validarCaso() {

    var persona = document.getElementById('persona').value;
    var tipo = document.getElementById('Tipo').value;
    if (persona == "" && tipo == "") {
        toastr.error("No ha ingresado Persona", "Aviso!");
        toastr.error("No ha ingresado tipo", "Aviso!");

        return false;
    }
    if ($.trim(persona) == "") {
        toastr.error("No ha ingresado persona", "Aviso!");
        return false;
    }
    if ($.trim(tipo) == "") {
        toastr.error("No ha ingresado tipo", "Aviso!");
        return false;
    }

    if (!persona == "" && !tipo == "") {
        document.getElementById("Caso").action = "/sofCoiso/CasoServlet";
        document.getElementById("Caso").submit();
        toastr.success("persona Guardado Correctamente");
        return true;
    }
}

$body = $("body");

$(document).ready(function () {
    var alt = $(document).height();
    $('.modal-dialog').css('top', alt * 0.3);
    $(window).resize(function () {
        var alt2 = $(document).height();
        $('.modal-dialog').css('top', alt2 * 0.3);
    });
    
    
    $("body").on("click", "#selectConsulta", function () {
        var selectConsulta = $(this).val();
        $.ajax({
            async: false,
            type: "GET",
            url: "/sofCoiso/ConsultarModalServlet",
            data: 'selectConsulta=' + selectConsulta,
            success: function (data) {
                var json_obj = $.parseJSON(data);
                $(this).removeData('modalInf');

                $('#modInf').html('<div class="form-row"><div class="form-group col-md-6">'
                        + '<label for="cedula">Cedula</label>'
                        + '<input name="cedulaPerson" id="cedulaPerson" class="form-control" value=' + json_obj.Cedula[0] + '> </div>'
                        + '<div class="form-group col-md-6">'
                        + '<label for="nombre">Nombre</label>'
                        + '<input name="nomPerson" id="nomPerson" class="form-control" value=' + json_obj.Nombre[0] + '> </div> </div>'
                        + '<div class="form-row"> <div class="form-group col-md-6">'
                        + '<label for="apellidouno">Primer Apellido</label>'
                        + '<input name="ApellidoUnoPeson" id="ApellidoUnoPeson" class="form-control" value=' + json_obj.ApellidoUno[0] + '> </div>'
                        + '<div class="form-group col-md-6">'
                        + '<label for="apellidodso">Segundo Apellido</label>'
                        + '<input name="ApellidodosPeson" id="ApellidodosPeson" class="form-control" value=' + json_obj.ApellidoDos[0] + '> </div> </div>'
                        + '<div class="form-row"> <div class="form-group col-md-6"> '
                        + '<label for="Genero">Genero</label>'
                        + '<input name="generoPerson" id="generoPerson" class="form-control" value=' + json_obj.Genero[0] + '> </div> </div>'
                        + ' <div class="form-row">  <div class="form-group col-md-6">'
                        + '<label for="Edad">Edad</label>'
                        + '<input name="EdadPerson" id="EdadPerson" class="form-control" value=' + json_obj.Edad[0] + '> </div>'
                        + '<div class="form-group col-md-6"> '
                        + '<label for="Nacimiento">Fecha Nacimiento</label>'
                        + '<input name="FechaNacimientoPerson" id="FechaNacimientoPerson" class="form-control" value=' + json_obj.FechaNacimiento[0] + '> </div> </div>'
                        + '<p></p> <h4>Contacto</h4>'
                        + ' <div class="form-row"> <div class="form-group col-md-6">'
                        + '<label for="Telefono">Telefono</label>'
                        + '<input name="TelefonoPerson" id="TelefonoPerson" class="form-control" value=' + json_obj.Telefono[0] + '> </div> </div>'
                        + '<p></p> <h4>Datos Empresa Y Salud</h4>'
                        + '<div class="form-row"> <div class="form-group col-md-6"> '
                        + '<label for="empresa">Empresa</label>'
                        + '<input name="empresaPerson" id="empresaPerson" class="form-control" value=' + json_obj.empresa[0] + '> </div>'
                        + '<div class="form-group col-md-6">'
                        + '<label for="cargo">Cargo</label>'
                        + '<input name="cargoPerson" id="cargoPerson" class="form-control" value=' + json_obj.cargo[0] + '> </div>'
                        + ' <div class="form-group col-md-6"> '
                        + '<label for="experiencia">Años de Antiguedad</label>'
                        + '<input name="ExperienciaPerson" id="ExperienciaPerson" class="form-control" value=' + json_obj.Experiencia[0] + '> </div>'
                        + '<div class="form-group col-md-6">'
                        + '<label for="clinica">Fecha de la Clinica</label>'
                        + '<input name="fechaClinicaPerson" id="fechaClinicaPerson" class="form-control" value=' + json_obj.fechaClinica[0] + '> </div>'
                        + '<div class="form-group col-md-6"> '
                        + '<label for="recomendado">Recomendado por</label>'
                        + '<input name="RecomendadoPerson" id="RecomendadoPerson" class="form-control" value=' + json_obj.Recomendado[0] + '> </div> </div>'

                        )

                $('#modalInf').modal('show');
            }
        });
    });
    
    var cedulaPerson;
    var nomPerson;
    var ApellidoUnoPeson;
    var fecha_f;
    var tCons;
    
    
      $("body").on("click", "#btnModificar", function () {
        cedulaPerson = $('#cedulaPerson').val();
       
       
    $.ajax({
        async: false,
        type: "GET",
        url: "/sofCoiso/PersonaServlet",
        data: 'cedulaPerson=' + cedulaPerson,
        success: function (data) {
            toastr.success("persona Guardado Correctamente");
        }
    });
});
});






//ANIMACIÓN DE LOADING PARA LAS CONSULTAS
$(document).ajaxStart(function () {
    $body.addClass("loading");
});
$(document).ajaxStop(function () {
    $body.removeClass("loading");
}); 