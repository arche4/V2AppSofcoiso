function validarTipo(){

    var codigo = document.getElementById('codigo').value;
    var tipo = document.getElementById('tipo').value;
    var nombreTipo = document.getElementById('nombreTipo').value;
    var Descripcion = document.getElementById('Descripcion').value;
   

    if (codigo == "" && nombreTipo == "" && Descripcion == "" && tipo == "") {
        toastr.error("No ha ingresado codigo del estado ", "Aviso!");
        toastr.error("No ha ingresado nombre del estado", "Aviso!");
        toastr.error("No ha ingresado Descripcion", "Aviso!");
        return false;
    }
    if ($.trim(codigo) == "") {
        toastr.error("No ha ingresado codigo del tipo", "Aviso!");
        return false;
    }
    if ($.trim(nombreTipo) == "") {
        toastr.error("No ha ingresado nombre del Tipo de caso", "Aviso!");
        return false;
    }
    if ($.trim(tipo) == "") {
        toastr.error("No ha ingresado el tipo de caso", "Aviso!");
        return false;
    }
    if ($.trim(Descripcion) == "") {
        toastr.error("No ha ingresado Descripcion", "Aviso!");
        return false;
    }
    

    if (!codigo == "" && !nombreTipo == "" && !Descripcion == "" && !tipo == "") {

        document.getElementById("estado").action = "/sofCoiso/tipoCasoServlet";
        document.getElementById("estado").submit();
        toastr.success("Estado Guardado Correctamente");
        return true;
    }
}
