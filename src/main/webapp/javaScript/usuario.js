
function validarUsuario() {
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
function deleteUser() {
    var cedulaUser = document.getElementById('cedulaUser').value;
    document.getElementById("eliminarUsuario").action = "/sofCoiso/UsuarioServlet";
    document.getElementById("eliminarUsuario").submit();
    toastr.success("Usuario Guardado Correctamente");
    return true;

}


$body = $("body");

$(document).ready(function () {
    var alt = $(document).height();
    $('.modal-dialog').css('top', alt * 0.3);
    $(window).resize(function () {
        var alt2 = $(document).height();
        $('.modal-dialog').css('top', alt2 * 0.3);
    });
    $("body").on("click", "#usuarioConsulta", function () {
        var usuarioConsulta = $(this).val();
        $.ajax({
            async: false,
            type: "GET",
            url: "/sofCoiso/ConsultarModalServlet",
            data: 'usuarioConsulta=' + usuarioConsulta,
            success: function (data) {
                var json_obj = $.parseJSON(data);
                $(this).removeData('modalInf');
                $('#modInf').html(' <div class="form-row">  <div class="form-group col-md-6">'
                        + '<label for="cedula">Cedula</label>'
                        + '<p></p>'
                        + '<span>' + json_obj.Cedula[0] + '</span>'
                        + '<input name="cedulaUser" id="cedulaUser" type="hidden" class="form-control" value=' + json_obj.Cedula[0] + '> </div>'
                        + '<div class="form-group col-md-6">'
                        + '<label for="nombre">Nombre</label>'
                        + '<input name="nomUser" id="nomUser" class="form-control" value=' + json_obj.Nombre[0] + '> </div> </div>'
                        + '<div class="form-row"> <div class="form-group col-md-6">'
                        + '<label for="apellido">Apellido</label>'
                        + '<input name="apellidoUser" id="apellidoUser" class="form-control" value=' + json_obj.apellido[0] + '> </div>'
                        + '<div class="form-group col-md-6">'
                        + '<label for="rol">Rol</label>'
                        + '<input name="rolUser" id="rolUser" class="form-control" value=' + json_obj.rol[0] + '> </div> </div>'
                        + '<div class="form-row"> <div class="form-group col-md-6"> '
                        + '<label for="Clave">Clave</label>'
                        + '<input type="password" name="ClaveUser" id="ClaveUser" class="form-control" value=' + json_obj.clave[0] + '> </div> </div>'

                        )

                $('#modalInf').modal('show');
            }
        });
    });
    var cedulaUser;
    var nomUser;
    var apellidoUser;
    var rolUser;
    var ClaveUser;
    $("body").on("click", "#btnModificar", function () {
        cedulaUser = $('#cedulaUser').val();
        nomUser = $('#nomUser').val();
        apellidoUser = $('#apellidoUser').val();
        rolUser = $('#rolUser').val();
        ClaveUser = $('#ClaveUser').val();
        btnModificar = $(this).val();
         toastr.success("Usuario Guardado Correctamente");
        $.ajax({
            async: false,
            type: "GET",
            url: "/sofCoiso/UsuarioServlet",
            data: 'cedulaUser=' + cedulaUser + '&nomUser=' + nomUser + '&apellidoUser=' + apellidoUser + '&rolUser=' + rolUser + '&ClaveUser=' + ClaveUser + '&btnModificar=' + btnModificar,
            success: function (data) {
                toastr.success("Formacion Guardado Correctamente");
                return true;
            }
        });
    });

    $("body").on("click", "#btnElimiar", function () {
        var btnElimiar = this.value
        $('#inputUsuario').html('<input name="cedulaUser" type="hidden"  id="cedulaUser" class="form-control" value=' + btnElimiar + '>')
        $('#modalDelete').modal('show');
    });


});

