<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <link rel="stylesheet" href="plugins/datatables/extensions/Select/select.dataTables.min.css">
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
        <script src="${pageContext.servletContext.contextPath}/javaScript/usuario.js" type="text/javascript"></script>

    </head>


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
                        <li class="nav-item">
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
                        <li class="nav-item">
                            <a class="nav-link" name="accion" value="listar" href="${pageContext.servletContext.contextPath}/view/casos.jsp">
                                <i class="material-icons">library_books</i>
                                <p>Casos</p>
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" name="accion" value="listar" href="${pageContext.servletContext.contextPath}/view/tiposCasos.jsp">
                                <i class="material-icons">library_books</i>
                                <p>Tipo de Caso</p>
                            </a>
                        </li>
                        <li class="nav-item active ">
                            <a class="nav-link" name="accion" value="listar" href="${pageContext.servletContext.contextPath}/view/usuario.jsp">
                                <i class="material-icons">location_ons</i>
                                <p>Usuarios</p>
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link"  href="${pageContext.servletContext.contextPath}/view/listadoFormaciones.jsp">
                                <i class="material-icons">notifications</i>
                                <p>Formaciones</p>
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
                            <a class="navbar-brand" href="#pablo">Usuario</a>
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


                        <hr class="line-seprate">
                        <div class="section__content section__content--p30">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-lg-12">

                                        <c:forEach var="usuario" items="${sessionScope.listUsuario}">
                                            <c:choose>
                                                <c:when test="${usuario.getCedulaUsuario() == sessionScope.documento}">
                                                    <form  method="post"  name="editarUsuario" id="editarUsuario" action="">
                                                        <div class="form-row">
                                                            <div class="form-group col-md-6">
                                                                <input  name="cedula" class="form-control"  type="hidden" value="<c:out value='${usuario.getIdUsuario()}' />">
                                                                <input  name="" disabled  class="form-control" type="text" value="<c:out value='${usuario.getIdUsuario()}' />">
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <input name="nombre" class="form-control" type="text" value="<c:out value='${usuario.getUsuarioNombre()}'/>">
                                                            </div>
                                                        </div>
                                                        <div class="form-row">
                                                            <div class="form-group col-md-6">
                                                                <input name="apellido" class="form-control" type="text" value="<c:out value='${usuario.getUsuarioApellido()}'/>">
                                                            </div>
                                                            <div class="form-group col-md-6">
                                                                <select name="rol" id="inputState" 
                                                                        class="form-control">
                                                                    <option value="<c:out value='${usuario.getRolUsuario()}'/>"selected><c:out value='${usuario.getRolUsuario()}'/></option>
                                                                    <option value="Administrador" selected>Administrador</option>
                                                                    <option value="Coordinador" selected>Coordinador</option>
                                                                </select>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <input type="submit" onclick="validacion()" name="eliminar"   class="btn btn-danger" value="Eliminar" />
                                                                <button name="accion" value="modificar" type="submit" class="btn btn-success" onclick="return validar()">Guardar</button>
                                                                <button type="submit" name="accion" value="volver"  class="btn btn-primary" onclick="return volver()">Volver</button>
                                                            </div>
                                                        </div>
                                                    </form>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>



                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>


    </body>

</html>
