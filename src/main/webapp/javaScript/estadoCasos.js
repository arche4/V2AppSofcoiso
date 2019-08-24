function validarEstado(){

    var codigo = document.getElementById('codigo').value;
    var nombreEstado = document.getElementById('nombreEstado').value;
    var Descripcion = document.getElementById('Descripcion').value;
   

    if (codigo == "" && nombreEstado == "" && Descripcion == "") {
        toastr.error("No ha ingresado codigo del estado ", "Aviso!");
        toastr.error("No ha ingresado nombre del estado", "Aviso!");
        toastr.error("No ha ingresado Descripcion", "Aviso!");
        return false;
    }
    if ($.trim(codigo) == "") {
        toastr.error("No ha ingresado codigo del estado", "Aviso!");
        return false;
    }
    if ($.trim(nombreEstado) == "") {
        toastr.error("No ha ingresado nombre del estado", "Aviso!");
        return false;
    }
    if ($.trim(Descripcion) == "") {
        toastr.error("No ha ingresado Descripcion", "Aviso!");
        return false;
    }
    

    if (!codigo == "" && !nombreEstado == "" && !Descripcion == "") {

        document.getElementById("estado").action = "/sofCoiso/EstadoCasoServlet";
        document.getElementById("estado").submit();
        toastr.success("Estado Guardado Correctamente");
        return true;
    }
}

function openCity(evt, cityName) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(cityName).style.display = "block";
  evt.currentTarget.className += " active";
}

$body = $("body");

$(document).ready(function () {
    var alt = $(document).height();
    $('.modal-dialog').css('top', alt * 0.3);
    $(window).resize(function () {
        var alt2 = $(document).height();
        $('.modal-dialog').css('top', alt2 * 0.3);
    });
    $("body").on("click", "#estadoCasoConsulta", function () {
        var estadoCasoConsulta = $(this).val();
        $.ajax({
            async: false,
            type: "GET",
            url: "/sofCoiso/ConsultarModalServlet",
            data: 'estadoCasoConsulta=' + estadoCasoConsulta,
            success: function (data) {
                var json_obj = $.parseJSON(data);
                $(this).removeData('modalInf');
                $('#modInf').html(' <div class="form-row">  <div class="form-group col-md-6">'
                        + '<label for="cedula">Codigo</label>'
                        + '<p></p>'
                        + '<span>' + json_obj.codigo[0] + '</span>'
                        + '<input name="codigoForm" id="codigoForm" type="hidden" class="form-control" value=' + json_obj.codigo[0] + '> </div>'
                        + '<div class="form-group col-md-6">'
                        + '<label for="nombre">nombre</label>'
                        + '<input name="nombreEst" id="nombreEst" class="form-control" value=' + json_obj.nombre[0] + '> </div> </div>'
                        + '<div class="form-row"> '
                        + '<label for="Descripcion">Descripcion</label>'
                        + '<textarea name="DescripcionEsta" id="DescripcionEsta" class="form-control" rows="10" cols="50" value="' + json_obj.Descripcion[0] + '">'+ json_obj.Descripcion[0]+'</textarea>   </div>'
                       
                        )

                $('#modalInf').modal('show');
            }
        });
    });
    var codigoForm;
    var nombreEst;
    var DescripcionEsta;
    $("body").on("click", "#btnModificar", function () {
        codigoForm = $('#codigoForm').val();
        nombreEst = $('#nombreEst').val();
        DescripcionEsta = $('#DescripcionEsta').val();
        btnModificar = $(this).val();
         toastr.success("Usuario Guardado Correctamente");
        $.ajax({
            async: false,
            type: "GET",
            url: "/sofCoiso/EstadoCasoServlet",
            data: 'codigoForm=' + codigoForm + '&nombreEst=' + nombreEst + '&DescripcionEsta=' + DescripcionEsta + '&btnModificar=' + btnModificar,
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

