/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$body = $("body");

$(document).ready(function () {
    var alt = $(document).height();
    $('.modal-dialog').css('top', alt * 0.3);
    $(window).resize(function () {
        var alt2 = $(document).height();
        $('.modal-dialog').css('top', alt2 * 0.3);
    });

    //DECLARACION DE LAS VARIABLES A UTILIZAR


    var tCons;
    var fecha_i;
    var fecha_f;


    $("#btnDescargar").click(function () {
        var url = 'C:\Users\manue\Documents\NetBeansProjects\sofCoiso\target\sofCoiso-1.0-SNAPSHOT\Personas.xls'
           location.href  = $(this).attr("urldescarga");
    })

    //EL BOTON CONSULTAR EJECUTA  
    $("body").on("click", "#botCons", function () {

        event.stopPropagation();
        event.preventDefault();


        tCons = $('#selectConsulta option:selected').text();
        fecha_i = ($('#rangTime').val()).substring(0, 16);
        fecha_f = ($('#rangTime').val()).substring(19, 41);

        $.when($.ajax({

            type: "GET",
            url: "/sofCoiso/ReportServlet",
            data: 'tCons=' + tCons + '&fecha_i=' + fecha_i + '&fecha_f=' + fecha_f,
            success: function (data) {
                console.log(data);
            }
        })).done(function (data) {

            $("#btnDescargar").attr("urlDescarga",tCons+".xls");
            $("#btnDescargar").prop("disabled", false);
            $body.removeClass("loading");

            $('#miTabla').html(data);
            var idTabla = $('#miTabla table').attr("id");
            $("#" + idTabla).DataTable({
                "language": {
                    "lengthMenu": "Mostrar _MENU_ registros por p&aacute;gina",
                    "zeroRecords": "No se encontraron datos ",
                    "info": "Mostrando p&aacute;gina _PAGE_ de _PAGES_",
                    "infoEmpty": "No hay registros disponibles",
                    "infoFiltered": "(Filtrados de un total de _MAX_ registros)",
                    "search": "Buscar registro:",
                    "pagingType": {
                        "next": "Siguiente",
                        "previous": "Anterior"
                    },
                    "loadingRecords": "Cargando..."
                },
                "scrollX": true,
                responsive: true,
                "dom": "<'row'<'col-sm-6'l><'col-sm-6'f>>" +
                        "<'row'<'col-sm-12'tr>>" +
                        "<'row'<'col-sm-5'i><'col-sm-7'p>>" +
                        "<'row'<'col-sm-5 myText'><'col-sm-7'>>" +
                        "<'row'<'col-sm-5'B><'col-sm-7'>>"
            });

            $('.myText').append("<p><strong>Opciones para la descarga:</strong></p>");
        });
    });
});



//ANIMACI�N DE LOADING PARA LAS CONSULTAS
$(document).ajaxStart(function () {
    $body.addClass("loading");
});
$(document).ajaxStop(function () {
    $body.removeClass("loading");
});




