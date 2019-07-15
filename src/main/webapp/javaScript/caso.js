
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

}
function comment() {
    document.getElementById("comment").action = "/sofCoiso/CasoServlet";
    document.getElementById("comment").submit();
    toastr.success("persona Guardado Correctamente");
    return true;
}