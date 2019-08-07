
function validar() {

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


function cambiarEstado() {
    document.getElementById("cambiarEstado").action = "/sofCoiso/CasoServlet";
    document.getElementById("cambiarEstado").submit();
    toastr.success("persona Guardado Correctamente");
    return true;
}

function asignar() {
    document.getElementById("cambiarUsuario").action = "/sofCoiso/CasoServlet";
    document.getElementById("cambiarUsuario").submit();
    toastr.success("Usuario Asignado  Correctamente");
    return true;

}

function citar() {
    document.getElementById("citar").action = "/sofCoiso/CasoServlet";
    document.getElementById("citar").submit();
    toastr.success("Usuario Asignado  Correctamente");
    return true;

}
function comment() {
    document.getElementById("comment").action = "/sofCoiso/CasoServlet";
    document.getElementById("comment").submit();
    toastr.success("persona Guardado Correctamente");
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

       $("body").on("click", "#btnEdtar", function () {
        var btnEdtar = $(this).val();
        $.ajax({
            async: false,
            type: "GET",
            url: "/sofCoiso/ConsultarModalServlet",
            data: 'btnEdtar=' + btnEdtar,
            success: function (data) {
                var json_obj = $.parseJSON(data);
                $('#modInf').html(' <div class="form-row">  <div class="form-group col-md-6">'
                        + '<label for="cedula">Codigo caso</label>'
                        + '<p></p>'
                        + '<span>' + json_obj.codigo[0] + '</span>'
                        + '<input name="codigoEdit" id="codigoEdit" type="hidden" class="form-control" value=' + json_obj.codigo[0] + '> </div>'
                        + '<div class="form-group col-md-6">'
                        + '<label for="nombre">Creado por</label>'
                        + '<span>' + json_obj.creado[0] + '</span>'
                        + '<input name="creadoEdit" id="creadoEdit" type="hidden" class="form-control" value=' + json_obj.creado[0] + '> </div> </div>'
                        + '<div class="form-row"> <div class="form-group col-md-6">'
                        + '<label for="apellido">Fecha Afectacion</label>'
                        + '<input name="fechaAfectacion" id="fehcaFormacionFor" class="form-control" value=' + json_obj.fehcaFormacion[0] + '> </div>'
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
    
    $(document).ready(function () {
    var alt = $(document).height();
    $('.modal-dialog').css('top', alt * 0.3);
    $(window).resize(function () {
        var alt2 = $(document).height();
        $('.modal-dialog').css('top', alt2 * 0.3);
    });

       $("body").on("click", "#citaConsulta", function () {
        var citaConsulta = $(this).val();
        $.ajax({
            async: false,
            type: "GET",
            url: "/sofCoiso/ConsultarModalServlet",
            data: 'citaConsulta=' + citaConsulta,
            success: function (data) {
                var json_obj = $.parseJSON(data);
                 $(this).removeData('modalCitasInf');
                $('#infCitas').html(' <div class="form-row">  <div class="form-group col-md-6">'
                        + '<label for="cedula">Codigo caso</label>'
                        
                        + '<input name="codigoEdit" id="codigoEdit"  class="form-control" value=' + json_obj.getFechaCita[0] + '> </div> </div>'
                        + '<div class="form-row">  <div class="form-group col-md-6">'
                        + '<label for="nombre">Estado</label>'
                        + '<input name="creadoEdit" id="creadoEdit"  class="form-control" value=' + json_obj.getEstado[0] + '> </div> </div>'
                        )

                $('#modalCitasInf').modal('show');
            }
        });
    });
});
});
window.addEventListener("load", function () {
    // store tabs variable
    var myTabs = document.querySelectorAll("ul.nav-tabs > li");
    function myTabClicks(tabClickEvent) {
        for (var i = 0; i < myTabs.length; i++) {
            myTabs[i].classList.remove("active");
        }
        var clickedTab = tabClickEvent.currentTarget;
        clickedTab.classList.add("active");
        tabClickEvent.preventDefault();
        var myContentPanes = document.querySelectorAll(".tab-pane");
        for (i = 0; i < myContentPanes.length; i++) {
            myContentPanes[i].classList.remove("active");
        }
        var anchorReference = tabClickEvent.target;
        var activePaneId = anchorReference.getAttribute("href");
        var activePane = document.querySelector(activePaneId);
        activePane.classList.add("active");
    }
    for (i = 0; i < myTabs.length; i++) {
        myTabs[i].addEventListener("click", myTabClicks)
    }
});
