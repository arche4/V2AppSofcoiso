<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <link rel="apple-touch-icon" sizes="76x76" href="../assets/img/apple-icon.png">
        <link rel="icon" type="image/png" href="../assets/img/favicon.png">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
        <!--     Fonts and icons     -->
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
        <!-- CSS Files -->
        <link href="${pageContext.servletContext.contextPath}/dist/assets/css/material-dashboard.css?v=2.1.1" rel="stylesheet" />
        <!-- CSS Just for demo purpose, don't include it in your project -->
        <link href="${pageContext.servletContext.contextPath}/dist/assets/demo/demo.css" rel="stylesheet" />


        <!-- Data table -->


        <title>Software de Gestión Coiso</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" rel="stylesheet" >
        <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script src="${pageContext.servletContext.contextPath}/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <link href="${pageContext.servletContext.contextPath}/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.servletContext.contextPath}/javaScript/crearPersona.js" type="text/javascript"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
        <link href="${pageContext.servletContext.contextPath}/css/bootstrap-iso.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.servletContext.contextPath}/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <link href="${pageContext.servletContext.contextPath}/css/bootstrap-datepicker3.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
    <body class="">
        <div class="wrapper ">
            <div class="sidebar" data-color="purple" data-background-color="white" data-image="../assets/img/sidebar-1.jpg">
                <!--
                  Tip 1: You can change the color of the sidebar using: data-color="purple | azure | green | orange | danger"
          
                  Tip 2: you can also add an image using data-image tag
                -->
                <div class="logo">
                    <a href="http://www.creative-tim.com" class="simple-text logo-normal">
                        Menu
                    </a>
                </div>
                <div class="sidebar-wrapper">
                    <ul class="nav">
                        <li class="nav-item active  ">
                            <a class="nav-link" name="accion" value="ListarDashboard"  href="${pageContext.servletContext.contextPath}/view/menu.jsp">
                                <i class="material-icons">dashboard</i>
                                <p>Dashboard</p>
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" name="accion" value="listarPersonas" href="${pageContext.servletContext.contextPath}/view/registroPersonas.jsp">
                                <i class="material-icons">person</i>
                                <p>Personas</p>
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" name="accion" value="listar" href="${pageContext.servletContext.contextPath}/view/casos.jsp">
                                <i class="material-icons">person</i>
                                <p>Casos</p>
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" name="accion" value="listar" href="${pageContext.servletContext.contextPath}/view/listadoCitas.jsp">
                                <i class="material-icons">library_books</i>
                                <p>Citas</p>
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" name="accion" value="listar" href="${pageContext.servletContext.contextPath}/view/asistente.jsp">
                                <i class="material-icons">bubble_chart</i>
                                <p>Asistentes</p>
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" name="accion" value="listar" href="${pageContext.servletContext.contextPath}/view/usuario.jsp">
                                <i class="material-icons">location_ons</i>
                                <p>Empresa</p>
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" name="accion" value="listar" href="${pageContext.servletContext.contextPath}/view/usuario.jsp">
                                <i class="material-icons">location_ons</i>
                                <p>Usuarios</p>
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" name="accion" value="listar" href="${pageContext.servletContext.contextPath}/view/empresa.jsp">
                                <i class="material-icons">location_ons</i>
                                <p>Empresa</p>
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" name="accion" value="listar" href="${pageContext.servletContext.contextPath}/view/usuario.jsp">
                                <i class="material-icons">location_ons</i>
                                <p>Cargo</p>
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link"  href="${pageContext.servletContext.contextPath}/view/listadoFormaciones.jsp">
                                <i class="material-icons">notifications</i>
                                <p>Formaciones</p>
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" href="./rtl.html">
                                <i class="material-icons">language</i>
                                <p>RTL Support</p>
                            </a>
                        </li>
                        <li class="nav-item active-pro ">
                            <a class="nav-link" href="./upgrade.html">
                                <i class="material-icons">unarchive</i>
                                <p>Upgrade to PRO</p>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="main-panel">
                <!-- Navbar -->
                <nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top ">
                    <div class="container-fluid">
                        <div class="navbar-wrapper">
                            <a class="navbar-brand" href="#pablo">Personas</a>
                        </div>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="navbar-toggler-icon icon-bar"></span>
                            <span class="navbar-toggler-icon icon-bar"></span>
                            <span class="navbar-toggler-icon icon-bar"></span>
                        </button>
                        <div class="collapse navbar-collapse justify-content-end">

                            <ul class="navbar-nav">
                                <li class="nav-item">
                                    <a class="nav-link" href="#pablo">
                                        <i class="material-icons">dashboard</i>
                                        <p class="d-lg-none d-md-block">
                                            Stats
                                        </p>
                                    </a>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link" href="http://example.com" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="material-icons">notifications</i>
                                        <span class="notification">5</span>
                                        <p class="d-lg-none d-md-block">
                                            Some Actions
                                        </p>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
                                        <a class="dropdown-item" href="#">Mike John responded to your email</a>
                                        <a class="dropdown-item" href="#">You have 5 new tasks</a>
                                        <a class="dropdown-item" href="#">You're now friend with Andrew</a>
                                        <a class="dropdown-item" href="#">Another Notification</a>
                                        <a class="dropdown-item" href="#">Another One</a>
                                    </div>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link" href="#pablo" id="navbarDropdownProfile" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="material-icons">person</i>
                                        <p class="d-lg-none d-md-block">
                                            Account
                                        </p>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownProfile">
                                        <a class="dropdown-item" href="#">Profile</a>
                                        <a class="dropdown-item" href="#">Settings</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="#">Log out</a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
                <!-- End Navbar -->
                <div class="content">
                    <div class="container-fluid">

                        <div class="col">
                            <button name="accion" value="crear" type="button" class="btn btn-info" data-toggle="modal" data-target="#crearPersona">
                                Crear Persona
                            </button>
                        </div>                        <hr class="line-seprate">
                        <div class="section__content section__content--p30">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <div class="table-responsive table--no-card m-b-30">
                                            <table id="table_id"  class="table table-borderless table-striped table-earning">
                                                <thead>
                                                    <tr>
                                                        <th scope="col">Accion</th>
                                                        <th scope="col">Cedula</th>
                                                        <th scope="col">Nombre</th>
                                                        <th scope="col">Apellidos</th>
                                                        <th scope="col">Genero</th>
                                                        <th scope="col">Nacimiento</th>
                                                        <th scope="col">Edad</th>
                                                        <th scope="col">Telefono</th>
                                                        <th scope="col">Empresa</th>
                                                        <th scope="col">Antiguedad</th>
                                                        <th scope="col">Cargo</th>
                                                        <th scope="col">Fecha Clinica</th>
                                                        <th scope="col">Eps</th>
                                                        <th scope="col">Arl</th>
                                                        <th scope="col">Afp</th>
                                                        <th scope="col">Recomendado</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="personas" items="${sessionScope.Persona}" varStatus="myIndex">
                                                        <tr>
                                                            <td>
                                                                <form  method="post" action="${pageContext.servletContext.contextPath}/PersonaServlet">
                                                                    <button name="ver" value="${personas.getCedula()}" type="submit" class="btn btn-primary">Ver</button>
                                                                </form>
                                                            </td> 
                                                            <td><c:out value="${personas.getCedula()}"/></td>
                                                            <td><c:out value="${personas.getNombre()}"/></td>
                                                            <td><c:out value="${personas.getApellidoUno()}"/></td>
                                                            <td><c:out value="${personas.getGenero()}"/></td>
                                                            <td><c:out value="${personas.getFechaNacimiento()}"/></td>
                                                            <td><c:out value="${personas.getEdad()}"/></td>
                                                            <td><c:out value="${personas.getTelefono()}"/></td>
                                                            <td><c:out value="${personas.getEmpresa()}"/></td>
                                                            <td><c:out value="${personas.getAntiguedadEmpresa()}"/></td>
                                                            <td><c:out value="${personas.getCargo()}"/></td>
                                                            <td><c:out value="${personas.getFechaClinica()}"/></td>
                                                            <td><c:out value="${personas.getAfpCodigoafp().getNombre()}"/></td>
                                                            <td><c:out value="${personas.getArlCodigoarl().getNombre()}"/></td>
                                                            <td><c:out value="${personas.getEpsCodigoeps().getNombre()}"/></td>
                                                            <td><c:out value="${personas.getRecomendado()}"/></td>

                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <script>
                            $.noConflict();
                            jQuery(document).ready(function ($) {
                                $('#table_id').DataTable();
                            });
                        </script>
                        <div class="modal fade" id="crearPersona">
                            <div class="modal-dialog">
                                <div class="modal-content" style="padding:60px 50px;">
                                    <!-- Modal Header -->
                                    <div class="modal-header">
                                        <h4 class="modal-title">Crear Persona</h4>
                                        <button type="button" class="close" data-dismiss="modal">×</button>
                                    </div>
                                    <h4>Datos Personales</h4>
                                    <form method="post" name="persona" id="persona" action="">
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <input type="text" class="form-control" id="cedula" name="cedula"  placeholder="Cedula">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="primerApellido" name="primerApellido"  placeholder="Primer Apellido">
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control" id="segundoApellido" name="segundoApellido"  placeholder="Segundo Apellido">
                                        </div>
                                        <div class="form-group">
                                            <div class="col col-md-3">
                                                <label class=" form-control-label">Genero</label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="genero" id="femenino" value="femenino">
                                                <label class="form-check-label" for="inlineRadio1">F</label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <input class="form-check-input" type="radio" name="genero" id="Masculino" value="Masculino">
                                                <label class="form-check-label" for="inlineRadio2">M</label>
                                            </div>

                                        </div>
                                        <div class="form-group">
                                            <label for="cedula">Fecha  Nacimiento</label>
                                            <input type="date" class="form-control" id="cumpleaños" name="cumpleaños" placeholder="MM/DD/YYY" id="example-month-input">
                                        </div>

                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <input type="text" class="form-control" name="telefono" id="telefono" placeholder="Telefono">
                                            </div>
                                        </div>
                                        <h4>Datos Empresa Y Salud</h4>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <select name="eps" id="eps" class="form-control-sm form-control">
                                                    <option value="">EPS</option>
                                                    <c:forEach var="eps" items="${sessionScope.EPS}">
                                                        <option value="${eps.getCodigoeps()}"><c:out value="${eps.getNombre()}"/></option>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="form-group col-md-6">
                                                <select name="arl" id="arl"  class="form-control-sm form-control">
                                                    <option value="">ARL</option>
                                                    <c:forEach var="arl" items="${sessionScope.ARL}">
                                                        <option value="${arl.getCodigoarl()}"><c:out value="${arl.getNombre()}"/></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <select name="afp" id="afp"  class="form-control-sm form-control">
                                                    <option value="">AFP</option>
                                                    <c:forEach var="afp" items="${sessionScope.AFP}">
                                                        <option value="${afp.getCodigoafp()}"><c:out value="${afp.getNombre()}"/></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group col-md-6">
                                                <input type="text" class="form-control" id="empresa" name="empresa" placeholder="Empresa">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <input type="text" class="form-control" id="cargo" name="cargo" placeholder="cargo">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <input type="number" class="form-control" name="anosExperiencia" id="anosExperiencia" placeholder="Años Experiencia">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="cedula">Fecha de Clinica</label>
                                            <input type="date" class="form-control" id="FechaClinica" name="FechaClinica" placeholder="MM/DD/YYY" id="example-month-input">
                                        </div>
                                        <div class="form-group">
                                            <input type="recomendado" class="form-control" name="recomendado" id="recomendado" placeholder="Recomendado">
                                        </div>
                                        <div class="modal-footer">
                                            <button name="accion" value="crear" type="submit" class="btn btn-success" onclick="return validar()">Guardar</button>

                                            <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                                        </div>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function () {
                $('#myTable').DataTable();
            });

        </script>

    </body>
</html>
