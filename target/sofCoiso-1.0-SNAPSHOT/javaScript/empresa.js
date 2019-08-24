function validarEmpresa() {
    var nit = document.getElementById('nit').value;
    var nombre = document.getElementById('nombre').value;
    var sector = document.getElementById('sector').value;
    var actividadEconomica = document.getElementById('actividadEconomica').value;
    var antiguedad = document.getElementById('antiguedad').value;

    if (nit == "" && nombre == "" && sector == "" && actividadEconomica == "" && añosAntiguedad == "") {
        toastr.error("No ha ingresado Nit Empresa", "Aviso!");
        toastr.error("No ha ingresado Nombre", "Aviso!");
        toastr.error("No ha ingresado Sector", "Aviso!");
        toastr.error("No ha ingresado Actividad Economica", "Aviso!");
        toastr.error("No ha ingresado Años Antiguedad Clave", "Aviso!");
        return false;
    }
    if ($.trim(nit) == "") {
        toastr.error("No ha ingresado NIT de la Empresa", "Aviso!");
        return false;
    }
    if ($.trim(nombre) == "") {
        toastr.error("No ha ingresado Nombre", "Aviso!");
        return false;
    }
    if ($.trim(sector) == "") {
        toastr.error("No ha ingresado Sector", "Aviso!");
        return false;
    }
    if ($.trim(actividadEconomica) == "") {
        toastr.error("No ha ingresado Actividad Economica", "Aviso!");
        return false;
    }
    if ($.trim(antiguedad) == "") {
        toastr.error("No ha ingresado Confirmar Años Antiguedad", "Aviso!");
        return false;
    }
    if (!nit == "" && !nombre == "" && !sector == "" && !actividadEconomica == "" && !antiguedad == "") {

        document.getElementById("crearEmpresa").action = "${pageContext.servletContext.contextPath}/EmpresaServlet";
        document.getElementById("crearEmpresa").submit();
        toastr.success("Empresa Guardado Correctamente");
        return true;
    }

}

function myFunction() {
    document.getElementById("verEmpresa").action = "${pageContext.servletContext.contextPath}/EmpresaServlet";
    document.getElementById("verEmpresa").submit();
    return true;
}

$.noConflict();
jQuery(document).ready(function ($) {
    $('.AllDataTables').DataTable();
});
