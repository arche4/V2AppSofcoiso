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
                console.log(json_obj)
                $('#modInf').html('<div class="form-row"><div class="form-group col-md-6">'
                        + '<label for="cedula">Cedula</label>'
                        + '<p></p>'
                        + '<span>' + json_obj.Cedula[0] + '</span>'
                        + '<input name="cedulaPerson" id="cedulaPerson" type="hidden" class="form-control" value=' + json_obj.Cedula[0] + '> </div>'
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
                        + '<input type="date" name="FechaNacimientoPerson" id="FechaNacimientoPerson" class="form-control" value=' + json_obj.FechaNacimiento[0] + '> </div> </div>'
                        + '<p></p> <h4>Contacto</h4>'
                        + ' <div class="form-row"> <div class="form-group col-md-6">'
                        + '<label for="Telefono">Telefono</label>'
                        + '<input name="TelefonoPerson" id="TelefonoPerson" class="form-control" value=' + json_obj.Telefono[0] + '> </div>'
                        + '<div class="form-group col-md-6">'
                        + '<label for="comuna">Comuna</label>'
                        + '<input name="comunaPerson" id="comunaPerson" class="form-control" value=' + json_obj.Comuna + '> </div> </div>'
                        + '<div class="form-group">'
                        + '<label for="Direccion">Direccion</label>'
                        + '<input name="direccionPerson" id="direccionPerson" class="form-control" value=' + json_obj.Direccion + '> </div>'
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
                        + '<input type="date" name="fechaClinicaPerson" id="fechaClinicaPerson" class="form-control" value=' + json_obj.fechaClinica[0] + '> </div>'
                        + '<div class="form-group col-md-6"> '
                        + '<label for="recomendado">Recomendado por</label>'
                        + '<input name="RecomendadoPerson" id="RecomendadoPerson" class="form-control" value=' + json_obj.Recomendado[0] + '> </div> </div>'

                        )

                $('#modalInf').modal('show');
            }
        });
    });
    $("body").on("click", "#casoCrear", function () {
        var casoCrear = $(this).val();
        $.ajax({
            async: false,
            type: "GET",
            url: "/sofCoiso/ConsultarModalServlet",
            data: 'casoCrear=' + casoCrear,
            success: function (data) {
                var json_obj = $.parseJSON(data);
                console.log(json_obj)
                $('#crearCasoInf').html('  <div class="form-row">  <div class="form-group col-md-6">'
                        + '<input name="cedulaCaso" id="cedulaCaso" type="hidden" class="form-control" value=' + json_obj.Cedula[0] + '> </div> </div>'
                        )
                $('#crearCaso').modal('show');
            }
        });
    });



    var cedulaCaso;
    $("body").on("click", "#btnCrearCaso", function () {
        var btnCrearCaso = $(this).val();
        cedulaCaso = $('#cedulaCaso').val();
        var Tipo = $('#Tipo').val();
        var creado = $('#creado').val();
        var fechaAfectacion = $('#fechaAfectacion').val();
        var parteAfectada = $('#parteAfectada').val();
        var tiempoInca = $('#tiempoInca').val();
        var pcl = $('#pcl').val();
        var textarea = $('#textarea').val();
        toastr.success("Caso Guardado Correctamente");
        $.ajax({
            async: false,
            type: "GET",
            url: "/sofCoiso/CasoServlet",
            data: 'cedulaCaso=' + cedulaCaso + '&Tipo=' + Tipo + '&creado=' + creado + '&fechaAfectacion=' + fechaAfectacion + '&parteAfectada=' + parteAfectada +
                    '&tiempoInca=' + tiempoInca + '&pcl=' + pcl + '&textarea=' + textarea + '&btnCrearCaso=' + btnCrearCaso,
            success: function (data) {
                toastr.success("Caso Guardado Correctamente");
            }
        });
    });



    $("body").on("click", "#btnModificar", function () {
        var btnModificar = $('#btnModificar').val();
        var cedulaPerson = $('#cedulaPerson').val();
        var nomPerson = $('#nomPerson').val();
        var ApellidoUnoPeson = $('#ApellidoUnoPeson').val();
        var ApellidodosPeson = $('#ApellidodosPeson').val();
        var generoPerson = $('#generoPerson').val();
        var EdadPerson = $('#EdadPerson').val();
        var FechaNacimientoPerson = $('#FechaNacimientoPerson').val();
        var TelefonoPerson = $('#TelefonoPerson').val();
        var comunaPerson = $('#comunaPerson').val();
        var direccionPerson = $('#direccionPerson').val();
        var empresaPerson = $('#empresaPerson').val();
        var cargoPerson = $('#cargoPerson').val();
        var ExperienciaPerson = $('#ExperienciaPerson').val();
        var fechaClinicaPerson = $('#fechaClinicaPerson').val();
        var RecomendadoPerson = $('#RecomendadoPerson').val();
        
        toastr.success("Persona Guardado Correctamente");
        $.ajax({
            async: false,
            type: "GET",
            url: "/sofCoiso/PersonaServlet",
            data: 'cedulaPerson=' + cedulaPerson + '&nomPerson=' + nomPerson + '&ApellidoUnoPeson=' + ApellidoUnoPeson + '&ApellidodosPeson=' + ApellidodosPeson + '&generoPerson=' + generoPerson +
                    '&EdadPerson=' + EdadPerson + '&FechaNacimientoPerson=' + FechaNacimientoPerson + '&TelefonoPerson=' + TelefonoPerson + '&comunaPerson=' + comunaPerson +
                    '&direccionPerson=' + direccionPerson + '&empresaPerson=' + empresaPerson + '&cargoPerson=' + cargoPerson + '&ExperienciaPerson=' + ExperienciaPerson
                    + '&fechaClinicaPerson=' + fechaClinicaPerson + '&RecomendadoPerson=' + RecomendadoPerson + '&btnModificar=' + btnModificar,
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