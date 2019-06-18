
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


