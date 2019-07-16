function validar() {
    var cedula = document.getElementById('cedula').value;
    var nombre = document.getElementById('nombre').value;
    var apellido = document.getElementById('apellido').value;
    var clave = document.getElementById('clave').value;
    var comfirmarClave = document.getElementById('comfirmarClave').value;

    if (nombre == "" && cedula == "" && apellido == "" && clave == "" && comfirmarClave == "") {
        toastr.error("No ha ingresado Cedula", "Aviso!");
        toastr.error("No ha ingresado Nombre", "Aviso!");
        toastr.error("No ha ingresado Apellido", "Aviso!");
        toastr.error("No ha ingresado Clave", "Aviso!");
        toastr.error("No ha ingresado Confirmar Clave", "Aviso!");
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
    if ($.trim(apellido) == "") {
        toastr.error("No ha ingresado Apellido", "Aviso!");
        return false;
    }
    if ($.trim(clave) == "") {
        toastr.error("No ha ingresado Clave", "Aviso!");
        return false;
    }
    if ($.trim(comfirmarClave) == "") {
        toastr.error("No ha ingresado Confirmar Clave", "Aviso!");
        return false;
    }
    if (!nombre == "" && !cedula == "" && !apellido == "" && !clave == "" && !comfirmarClave == "") {
        if (clave != comfirmarClave) {
            toastr.error("Las contraseñas deben de coincidir");

            return false;
        } else {
            document.getElementById("usuario").action = "/sofCoiso/UsuarioServlet";
            document.getElementById("usuario").submit();
            toastr.success("Usuario Guardado Correctamente");
            return true;
        }

    }
}
var botCons;
$body = $("body");

$("body").on("click", "#botCons", function () {
    $.ajax({
        type: "GET",
        url: "/sofCoiso/ReportServlet",
        data: 'botCons=' + botCons,
        success: function (data) {
            if (data === null && data === undefined) {
                $('#modInf').html('<p>Se ha presentado un error en la busqueda '
                        + ' de los paises. Por favor contacte a un administrador.</p>');
                $('#modalInf').modal('show');
            } else {
                var listItems = "<option value='" + 9999 + "'>--Seleccione un Pais--</option>";
                listItems += data;
            }
            $("#selectPais").html(listItems);
            $("#selectPais").prop('disabled', false);
        }
    });

});


function ValidarFormacion()
{

    var idFormacion = document.getElementById('idFormacion').value;
    var tipoFormacion = document.getElementById('tipoFormacion').value;
    var tema = document.getElementById('tema').value;
    var nAsistentes = document.getElementById('nAsistentes').value;

    if (idFormacion == "" && tipoFormacion == "" && tema == "" && nAsistentes == "") {
        toastr.error("No ha ingresado id Formacion", "Aviso!");
        toastr.error("No ha ingresado Tipo Formacion", "Aviso!");
        toastr.error("No ha ingresado Tema", "Aviso!");
        toastr.error("No ha ingresado N° Asistentes", "Aviso!");
        return false;
    }
    if ($.trim(idFormacion) == "") {
        toastr.error("No ha ingresado id Formacion", "Aviso!");
        return false;
    }
    if ($.trim(tipoFormacion) == "") {
        toastr.error("No ha ingresado Tipo Formacion", "Aviso!");
        return false;
    }
    if ($.trim(tema) == "") {
        toastr.error("No ha ingresado Tema", "Aviso!");
        return false;
    }
    if ($.trim(nAsistentes) == "") {
        toastr.error("No ha ingresado N° Asistentes", "Aviso!");
        return false;
    }

    if (!tipoFormacion == "" && !idFormacion == "" && !tema == "" && !nAsistentes == "") {

        document.getElementById("formacion").action = "/sofCoiso/FormacionServlet";
        document.getElementById("formacion").submit();
        toastr.success("Formacion Guardado Correctamente");
        return true;
    }
}