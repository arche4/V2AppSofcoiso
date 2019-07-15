/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
$body = $("body");

$(document).ready(function(){
    var alt = $(document).height();
    $('.modal-dialog').css('top',alt*0.3);
    $(window).resize(function(){
        var alt2 = $(document).height();
        $('.modal-dialog').css('top',alt2*0.3);
    });
    
    //DECLARACION DE LAS VARIABLES A UTILIZAR
 
   
    var tCons;



    
    //EL BOTON CONSULTAR EJECUTA  
    $("body").on("click","#botCons",function(){        
        
        event.stopPropagation();
        event.preventDefault();
        
       
        tCons = $('#selectConsulta option:selected').text();
    
      
        $.when($.ajax({
                
            type: "GET",
            url:    "/sofCoiso/ReportServlet",
            data: 'tCons=' + tCons,                
            success: function(data){
                console.log(data);
            }
        })).done(function(data){
           
            $("#btnDescargar").attr("urlDescarga"+"Personas.xls");
            $("#btnDescargar").prop("disabled",false);
            $body.removeClass("loading");
            
            $('#miTabla').html(data);
            var idTabla = $('#miTabla table').attr("id");
            $("#"+idTabla).DataTable({
                "language": {
                    "lengthMenu":   "Mostrar _MENU_ registros por p&aacute;gina",
                    "zeroRecords":  "No se encontraron datos de IVR",
                    "info":                "Mostrando p&aacute;gina _PAGE_ de _PAGES_",
                    "infoEmpty":      "No hay registros disponibles",
                    "infoFiltered":    "(Filtrados de un total de _MAX_ registros)",
                    "search":           "Buscar registro:",
                    "pagingType": {
                        "next":          "Siguiente",
                        "previous":   "Anterior"
                    },
                    "loadingRecords" : "Cargando..."
                },
                "scrollX": true,
                responsive:true,
                "dom": "<'row'<'col-sm-6'l><'col-sm-6'f>>" +
            "<'row'<'col-sm-12'tr>>" +
            "<'row'<'col-sm-5'i><'col-sm-7'p>>"+
            "<'row'<'col-sm-5 myText'><'col-sm-7'>>"+
            "<'row'<'col-sm-5'B><'col-sm-7'>>"
            });

            $('.myText').append( "<p><strong>Opciones para la descarga:</strong></p>" );
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




