function ValidarFormacion(){

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

$body = $("body");

$(document).ready(function () {
    var alt = $(document).height();
    $('.modal-dialog').css('top', alt * 0.3);
    $(window).resize(function () {
        var alt2 = $(document).height();
        $('.modal-dialog').css('top', alt2 * 0.3);
    });
    $("body").on("click", "#formacionConsulta", function () {
        var formacionConsulta = $(this).val();
        $.ajax({
            async: false,
            type: "GET",
            url: "/sofCoiso/ConsultarModalServlet",
            data: 'formacionConsulta=' + formacionConsulta,
            success: function (data) {
                var json_obj = $.parseJSON(data);
                $(this).removeData('modalInf');
                $('#modInf').html(' <div class="form-row">  <div class="form-group col-md-6">'
                        + '<label for="cedula">Codigo</label>'
                        + '<p></p>'
                        + '<span>' + json_obj.codigo[0] + '</span>'
                        + '<input name="codigoForm" id="codigoForm" type="hidden" class="form-control" value=' + json_obj.codigo[0] + '> </div>'
                        + '<div class="form-group col-md-6">'
                        + '<label for="nombre">Tipo formación</label>'
                        + '<input name="TipoFor" id="TipoFor" class="form-control" value=' + json_obj.Tipo[0] + '> </div> </div>'
                        + '<div class="form-row"> <div class="form-group col-md-6">'
                        + '<label for="apellido">Fecha Formación</label>'
                        + '<input type="date" name="fehcaFormacionFor" id="fehcaFormacionFor" class="form-control" value=' + json_obj.fehcaFormacion[0] + '> </div>'
                        + '<div class="form-group col-md-6">'
                        + '<label for="rol">Tema</label>'
                        + '<input name="temaForm" id="temaForm" class="form-control" value=' + json_obj.tema[0] + '> </div> </div>'
                        + '<div class="form-row"> <div class="form-group col-md-6"> '
                        + '<label for="Clave">N° Asistentes</label>'
                        + '<input name="cantidadAsistentes" id="cantidadAsistentes" class="form-control" value=' + json_obj.cantidadAsistentes[0] + '> </div> </div>'
                        )

                $('#modalInf').modal('show');
            }
        });
    });
    var codigoForm;
    var TipoFor;
    var fehcaFormacionFor;
    var temaForm;
    var cantidadAsistentes;
    $("body").on("click", "#btnModificar", function () {
        codigoForm = $('#codigoForm').val();
        TipoFor = $('#TipoFor').val();
        fehcaFormacionFor = $('#fehcaFormacionFor').val();
        temaForm = $('#temaForm').val();
        cantidadAsistentes = $('#cantidadAsistentes').val();
        btnModificar = $(this).val();
        toastr.success("Usuario Guardado Correctamente");
        $.ajax({
            async: false,
            type: "GET",
            url: "/sofCoiso/FormacionServlet",
            data: 'codigoForm=' + codigoForm + '&TipoFor=' + TipoFor + '&fehcaFormacionFor=' + fehcaFormacionFor + '&temaForm=' + temaForm + '&cantidadAsistentes=' + cantidadAsistentes + '&btnModificar=' + btnModificar,
            success: function (data) {
                toastr.success("Formacion Guardado Correctamente");
                return true;
            }
        });
    });

    $("body").on("click", "#btnElimiar", function () {
        var btnElimiar = this.value
        $('#inputUsuario').html('<input name="codigoForm" type="hidden"  id="codigoForm" class="form-control" value=' + btnElimiar + '>')
        $('#modalDelete').modal('show');
    });
});

