<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <link rel="apple-touch-icon" sizes="76x76" href="${pageContext.servletContext.contextPath}/images/logo_S.png">
        <link rel="icon" type="image/png" href="${pageContext.servletContext.contextPath}/images/logo_S.png">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
        <!--     Fonts and icons     -->
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
        <!-- CSS Files -->
        <link href="${pageContext.servletContext.contextPath}/dist/assets/css/material-dashboard.css?v=2.1.1" rel="stylesheet" />
        <!-- CSS Just for demo purpose, don't include it in your project -->
        <link href="${pageContext.servletContext.contextPath}/dist/assets/demo/demo.css" rel="stylesheet" />
        <title>Software de Gestión Coiso</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" rel="stylesheet" >
        <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <link href="${pageContext.servletContext.contextPath}/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
        <link href="${pageContext.servletContext.contextPath}/css/bootstrap-iso.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.servletContext.contextPath}/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <link href="${pageContext.servletContext.contextPath}/css/bootstrap-datepicker3.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.servletContext.contextPath}/plugins/jQuery/jquery-2.2.3.min.js" type="text/javascript"></script>
        <script src="${pageContext.servletContext.contextPath}/javaScript/Otros.js" type="text/javascript"></script>
    </head>


    <body class="">
        <div class="wrapper ">
            <div class="sidebar" data-color="purple" data-background-color="white" data-image="../assets/img/sidebar-1.jpg">
                <!--
                  Tip 1: You can change the color of the sidebar using: data-color="purple | azure | green | orange | danger"
          
                  Tip 2: you can also add an image using data-image tag
                -->
                <div class="logo">
                    <a href="${pageContext.servletContext.contextPath}/view/menu.jsp" class="simple-text logo-normal">
                        Menu
                    </a>
                </div>
                <div class="sidebar-wrapper">
                    <ul class="nav">
                        <li class="nav-item   ">
                            <a class="nav-link" name="accion" value="ListarDashboard"  href="${pageContext.servletContext.contextPath}/view/menu.jsp">
                                <i class="material-icons">dashboard</i>
                                <p>Dashboard</p>
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" name="accion" value="listarEmpreC" href="${pageContext.servletContext.contextPath}/view/registroPersonas.jsp">
                                <i class="material-icons">person</i>
                                <p>Personas</p>
                            </a>
                        </li>
                        <li class="nav-item  ">
                            <a class="nav-link" name="accion" value="listar" href="${pageContext.servletContext.contextPath}/view/formacion.jsp">
                                <i class="material-icons">list_alt</i>
                                <p>Fomarcion</p>
                            </a>
                        </li>
                        <c:choose>
                            <c:when test="${sessionScope.USUARIO.getRol() == sessionScope.rol}">  
                                <li class="nav-item ">
                                    <a class="nav-link" name="accion" value="listar" href="${pageContext.servletContext.contextPath}/view/usuario.jsp">
                                        <i class="material-icons">person_pin</i>
                                        <p>Usuarios</p>
                                    </a>
                                </li>

                                <li class="nav-item ">
                                    <a class="nav-link" name="accion" value="listar" href="${pageContext.servletContext.contextPath}/view/estadoCasos.jsp">
                                        <i class="material-icons">autorenew</i>
                                        <p>Estado Casos</p>
                                    </a>
                                </li>
                                <li class="nav-item active ">
                                    <a class="nav-link" name="accion" value="listar" href="${pageContext.servletContext.contextPath}/view/tiposCasos.jsp">
                                        <i class="material-icons">calendar_today</i>
                                        <p>Tipo de caso</p>
                                    </a>
                                </li>
                            </c:when> 
                        </c:choose>
                        <li class="nav-item ">
                            <a class="nav-link" name="accion" value="listar" href="${pageContext.servletContext.contextPath}/reportes.jsp">
                                <i class="material-icons">assessment</i>
                                <p>Reportes</p>
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
                            <a class="navbar-brand" href="#pablo">Tipos de Casos</a>
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
                                    <a class="nav-link" href="#pablo" id="navbarDropdownProfile" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        <i class="material-icons">person</i>
                                        <p class="d-lg-none d-md-block">
                                            Account
                                        </p>
                                    </a>
                                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownProfile">
                                        <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/view/perfil.jsp">Profile</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="${pageContext.servletContext.contextPath}/index.jsp">Log out</a>
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
                            <button name="accion" value="crear" type="button" class="btn btn-info" data-toggle="modal" data-target="#crearTipo" style="background: #1c2165;">
                                Crear Tipo
                            </button>
                        </div>                        
                        <hr class="line-seprate">
                        <div class="section__content section__content--p30">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-lg-12">
                                        <div class="table-responsive table--no-card m-b-30">
                                            <table id="table_id"  class="table table-borderless table-striped table-earning">
                                                <thead>
                                                    <tr>
                                                        <th scope="col">Codigo</th>
                                                        <th scope="col">Tipo</th>
                                                        <th scope="col">Nombre</th>
                                                        <th scope="col">Descripcion</th>
                                                        <th scope="col">Ver</th>
                                                           <!-- <c:choose>
                                                                <c:when test="${sessionScope.USUARIO.getRol() == sessionScope.rol}">  
                                                                <th scope="col">Eliminar</th>
                                                                </c:when> 
                                                            </c:choose>-->
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="Tipo" items="${sessionScope.Tipo}" varStatus="myIndex">
                                                        <tr>
                                                            <td><c:out value="${Tipo.getCodigoTipoCaso()}"/></td>
                                                            <td><c:out value="${Tipo.getTipoCaso()}"/></td>
                                                            <td><c:out value="${Tipo.getNombreTipoCaso()}"/></td>
                                                            <td><c:out value="${Tipo.getDescripcionTipoCaso()}"/></td>
                                                            <td> <button type="button" href="#modalInf" id ="estadoCasoConsulta" 
                                                                         name="estadoCasoConsulta" class="btn btn-link" value="${estado.getCodigoestado()}"><i class="material-icons">remove_red_eye</i> </button>
                                                            </td>
                                                            <!-- d<c:choose>
                                                                <c:when test="${sessionScope.USUARIO.getRol() == sessionScope.rol}">  
                                                                    <td><button type="button" href="#modalDelete" id ="btnElimiar" 
                                                                                name="btnElimiar" class="btn btn-link" value="${estado.getCodigoestado()}"><i class="material-icons">highlight_off</i></button>   </td>
                                                                    </c:when> 
                                                                </c:choose> -->
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
                        <div class="modal fade" id="crearTipo">
                            <div class="modal-dialog">
                                <div class="modal-content" style="padding:40px 50px;">
                                    <!-- Modal Header -->
                                    <div class="modal-header">
                                        <h4 class="modal-title">Crear Tipo</h4>
                                        <button type="button" class="close" data-dismiss="modal">×</button>
                                    </div>
                                    <form method="post" name="estado" id="estado" action="">
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <input type="text" class="form-control" id="codigo" name="codigo"  placeholder="Codigo Tipo de Caso">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <select name="tipo" id="tipo" class="form-control">
                                                    <option value="Emfermedad General" selected>Emfermedad General</option>
                                                    <option value="Emfermedad Laboral" selected>Emfermedad Laboral</option>
                                                    <option value="Accidente Laboral" selected>Accidente laboral</option>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <input type="text" class="form-control" id="nombreTipo" name="nombreTipo" placeholder="Nombre">
                                        </div>
                                        <div class="form-row">
                                            <textarea type="text" class="form-control" id="Descripcion" name="Descripcion"  placeholder="Descripcion"></textarea>
                                        </div>
                                        <div class="modal-footer">
                                            <button name="crear" value="crear" type="submit" class="btn btn-success" onclick="return validarTipo()">Crear</button>
                                            <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                                        </div>
                                    </form>
                                    <!-- Modal footer -->

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="modal" id="modalInf">
            <div class="modal-dialog">
                <div class="modal-content" style="padding:40px 50px;">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Estado</h4>
                        <button type="button" class="close" data-dismiss="modal">×</button>
                    </div>
                    <form method="post" name="personaEdit" id="persona" action="">

                        <div class="modal-body" id="modInf">
                        </div>
                        <div class="modal-footer">
                            <button  type="submit" class="btn btn-primary btn-block" name="btnModificar" value="btnModificar" id="btnModificar">
                                Guardar
                            </button>

                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="modal" id="modalDelete">
            <div class="modal-dialog modal-confirm">
                <div class="modal-content">
                    <div class="modal-header">
                        <div class="icon-box">
                            <i class="material-icons"></i>
                        </div>				
                        <h4 class="modal-title">¿Estás seguro?</h4>	
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    </div>
                    <div class="modal-body">
                        <p>¿Realmente quieres borrar estos registros? Este proceso no se puede deshacer.</p>
                    </div>
                    <form method="post" name="usuario" id="usuario"action="${pageContext.servletContext.contextPath}/EstadoCasoServlet">
                        <div class="modal-body" id="inputUsuario">
                        </div>
                        <div class="modal-footer">
                            <button name="eliminar" value="eliminar" id="eliminar" type="submit" class="btn btn-danger">Eliminar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function () {
                $('#myTable').DataTable();
            });

        </script>
        <script src="${pageContext.servletContext.contextPath}/bootstrap/js/bootstrap.min.js"></script>
        <!-- DataTables -->
        <script src="${pageContext.servletContext.contextPath}/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <link href="${pageContext.servletContext.contextPath}/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <!-- AdminLTE App -->
        <!-- Funcionalidad js -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <link href="${pageContext.servletContext.contextPath}/css/bootstrap-datepicker3.css" rel="stylesheet" type="text/css"/>

        <script src="${pageContext.servletContext.contextPath}/javaScript/app.min.js" type="text/javascript"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <link href="${pageContext.servletContext.contextPath}/css/bootstrap-datepicker3.css" rel="stylesheet" type="text/css"/>


    </body>

</html>
