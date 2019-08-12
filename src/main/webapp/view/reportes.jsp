<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
         <link rel="apple-touch-icon" sizes="76x76" href="${pageContext.servletContext.contextPath}/images/logo_S.png">
        <link rel="icon" type="image/png" href="${pageContext.servletContext.contextPath}/images/logo_S.png">
        <title>Reportes</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
        <!-- CSS Files -->
        <link href="${pageContext.servletContext.contextPath}/dist/assets/css/material-dashboard.css?v=2.1.1" rel="stylesheet" />
        <!-- CSS Just for demo purpose, don't include it in your project -->
        <link href="${pageContext.servletContext.contextPath}/dist/assets/demo/demo.css" rel="stylesheet" />
        <!-- Bootstrap 3.3.6 -->
        <!-- Ionicons -->

        <!-- Theme style -->
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/dist/css/AdminLTE.min.css">
        <!-- DataTables -->

        <!-- Page style -->
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/dist/css/home.css">

        <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

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
                         <li class="nav-item ">
                            <a class="nav-link" name="accion" value="listar" href="${pageContext.servletContext.contextPath}/view/tiposCasos.jsp">
                                <i class="material-icons">autorenew</i>
                                <p>Tipo Casos</p>
                            </a>
                        </li>
                          </c:when> 
                        </c:choose>
                        <li class="nav-item active">
                            <a class="nav-link" name="accion" value="listar" href="${pageContext.servletContext.contextPath}/view/reportes.jsp">
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
                            <a class="navbar-brand" href="#pablo">Reportes</a>
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
                    <section class="content">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="box box-primary">
                                    <div class="box-body">
                                        <div class="row">
                                                <div class="orm-group col-md-6">
                                                    <label>Tipo de consulta</label>
                                                    <select name="selectConsulta" id="selectConsulta" class="form-control" >
                                                    <option value="Personas" selected>Personas</option>
                                                    <option value="Citas" selected>Citas</option>
                                                    <option value="Formaciones" selected>Formaciones</option>
                                                    <option value="Formaciones" selected>Caso</option>
                                                </select>
                                                </div>
                                           <div class="orm-group col-md-6">
                                                <label>Fechas: Inicio - Fin</label>
                                                <div class="input-group">
                                                    <div class="input-group-addon">
                                                        <i class="fa fa-clock-o"></i>
                                                    </div>
                                                    <input type="text" class="form-control pull-right" id="rangTime">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="box-footer">
                                        <div class="row">
                                            <div class="col-xs-3" style="float:right">
                                                <button type="submit" class="btn btn-primary btn-block" id="botCons">
                                                    Consultar
                                                </button>
                                                <button type="button" urlDescarga="" class="btn btn-primary btn-block" id="btnDescargar" disabled>
                                                    Descargar
                                                </button>
                                            </div>                   
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="box box-primary">
                                    <div class="box-header with-border">
                                        <h3 class="box-title">Tabla de resultados</h3>
                                    </div>
                                    <div class="box-body">
                                        <div class="table-responsive table--no-card m-b-30">
                                            <div class="tabla1" id="miTabla">
                                                <table id="table_id"  class="table table-borderless table-striped table-earning">
                                                </table>
                                            </div>                                    
                                            <div class="box-footer">                                   
                                                <div class="col-xs-9"></div>
                                                <div class="col-xs-3 panel-desc">                                            
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Your Page Content Here -->                 
                    </section>
                </div>
            </div>
        </div>
        <div class="loadIc"></div>

        <!-- jQuery 2.2.3 -->
        <!-- Bootstrap 3.3.6 -->
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
        <script src="${pageContext.servletContext.contextPath}/javaScript/home.js" type="text/javascript"></script>
        <!-- Optionally, you can add Slimscroll and FastClick plugins.
             Both of these plugins are recommended to enhance the
             user experience. Slimscroll is required when using the
             fixed layout. -->        
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <link href="${pageContext.servletContext.contextPath}/css/bootstrap-datepicker3.css" rel="stylesheet" type="text/css"/>




        <script>
            $('#rangTime').daterangepicker({
                timePicker: true,
                timePickerIncrement: 1,
                locale: {
                    format: 'YYYY-MM-DD HH:mm'
                }
            });
        </script>

    </body>

</html>
