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
        <script src="${pageContext.servletContext.contextPath}/javaScript/caso.js" type="text/javascript"></script>
        <!-- Data table -->
        <title>Software de Gestión Coiso</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
        <link href="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css" rel="stylesheet" >
        <script src="//cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
        <script src="${pageContext.servletContext.contextPath}/js/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script src="${pageContext.servletContext.contextPath}/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <link href="${pageContext.servletContext.contextPath}/css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
        <link href="${pageContext.servletContext.contextPath}/css/bootstrap-iso.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.servletContext.contextPath}/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <link href="${pageContext.servletContext.contextPath}/css/bootstrap-datepicker3.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.servletContext.contextPath}/javaScript/css/detalleCaso.css" rel="stylesheet" type="text/css"/>

        <script src="https://blueimp.github.io/JavaScript-Templates/js/tmpl.min.js"></script>
        <script src="https://blueimp.github.io/JavaScript-Load-Image/js/load-image.all.min.js"></script>
        <script src="https://blueimp.github.io/JavaScript-Canvas-to-Blob/js/canvas-to-blob.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <script src="https://blueimp.github.io/Gallery/js/jquery.blueimp-gallery.min.js"></script>

        <link href="${pageContext.servletContext.contextPath}/js/jquery.fileupload.css" rel="stylesheet" type="text/css"/>
        <script src="${pageContext.servletContext.contextPath}/js/vendor/jquery.ui.widget.js" type="text/javascript"></script>
        <script src="${pageContext.servletContext.contextPath}/js/main.js" type="text/javascript"></script>
        <script src="${pageContext.servletContext.contextPath}/js/jquery.fileupload.js" type="text/javascript"></script>
        <script src="${pageContext.servletContext.contextPath}/js/jquery.fileupload-validate.js" type="text/javascript"></script>
        <script src="${pageContext.servletContext.contextPath}/js/jquery.fileupload-ui.js" type="text/javascript"></script>
        <link href="${pageContext.servletContext.contextPath}/js/jquery.fileupload-ui.css" rel="stylesheet" type="text/css"/>
    </head>

    <body class="">
        <div class="wrapper ">
            <div class="sidebar" data-color="purple" data-background-color="white" data-image="../assets/img/sidebar-1.jpg">
                <div class="logo">
                    <a href="${pageContext.servletContext.contextPath}/view/menu.jsp" class="simple-text logo-normal">
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
                            <a class="nav-link" name="accion" value="listarEmpreC" href="${pageContext.servletContext.contextPath}/view/registroPersonas.jsp">
                                <i class="material-icons">person</i>
                                <p>Personas</p>
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" name="accion" value="listar" href="${pageContext.servletContext.contextPath}/view/casos.jsp">
                                <i class="material-icons">receipt</i>
                                <p>Caso</p>
                            </a>
                        </li>
                        <li class="nav-item ">
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
                                    </a>t
                                </li>
                          
                        <li class="nav-item ">
                            <a class="nav-link" name="accion" value="listar" href="${pageContext.servletContext.contextPath}/view/estadoCasos.jsp">
                                <i class="material-icons">autorenew</i>
                                <p>Estado Casos</p>
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" name="accion" value="listar" href="${pageContext.servletContext.contextPath}/view/citas.jsp">
                                <i class="material-icons">calendar_today</i>
                                <p>Citas</p>
                            </a>
                        </li>
                          </c:when> 
                        </c:choose>
                         <li class="nav-item ">
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
                            <a class="navbar-brand" href="#pablo">Detalle del Caso</a>
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
                <div class="content" style="background: white;">
                    <div class="col">
                        <c:forEach var="caso" items="${sessionScope.Caso}">
                            <c:choose>
                                <c:when test="${caso.getCodigocaso() == sessionScope.codigoCaso}">
                                    <h1>
                                        <span>
                                            CASO: <c:out value='${caso.getCodigocaso()}'/> - <c:out value='${caso.getTipoCasoCodigoTipoCaso().getNombreTipoCaso()}' />
                                        </span></h1>
                                    <h3>
                                        <span class="overlay-icon aui-icon aui-icon-small aui-iconfont-edit">
                                            <c:out value='${caso.getPersonaCedula().getNombre()}'/>  <c:out value='${caso.getPersonaCedula().getApellidoUno()}' />
                                        </span></h3>
                                    </c:when>    
                                </c:choose>         
                            </c:forEach>

                        <button name="accion" value="CamEstado" type="button" class="btn btn-outline-secondary" data-toggle="modal" data-target="#cambiarEstado">
                            Cambiar Estado
                        </button>
                        <button name="accion" value="Asignar" type="button" class="btn btn-outline-secondary" data-toggle="modal" data-target="#Asignar">
                            Asignar
                        </button>
                        <button name="accion" value="Asignar" type="button" class="btn btn-outline-secondary" data-toggle="modal" data-target="#Asignar">
                            Citar 
                        </button>
                        <button name="accion" value="Asignar" type="button" class="btn btn-outline-secondary" data-toggle="modal" data-target="#Cargar">
                            Cargar Archivos 
                        </button>
                        <div class="issue-body-content">
                            <div class="aui-group issue-body">
                                <div class="aui-item issue-main-column" style="
                                     box-sizing: border-box;
                                     display: table-cell;
                                     margin: 0;
                                     vertical-align: top;">
                                    <hr style="color: #0056b2;" />
                                    <c:forEach var="caso" items="${sessionScope.Caso}">
                                        <c:choose> 
                                            <c:when test="${caso.getCodigocaso() == sessionScope.codigoCaso}">
                                                <div id="details-module" class="module toggle-wrap">
                                                    <div id="details-module_heading" class="mod-header">
                                                        <ul class="ops"></ul>
                                                        <h3 class="toggle-title">Detalles</h3>
                                                    </div>
                                                    <div class="mod-content">
                                                        <ul id="issuedetails" class="property-list two-cols">
                                                            <li class="item">
                                                                <div class="wrap">
                                                                    <strong class="name">Tipo:</strong>
                                                                    <span id="type-val" class="value">
                                                                        <c:out value='${caso.getTipoCasoCodigoTipoCaso().getNombreTipoCaso()}' />
                                                                    </span>
                                                                </div>
                                                            </li>                    
                                                            <li class="item item-right" style="clear: right; float: right;">
                                                                <div class="wrap">
                                                                    <strong class="name">Estado:</strong>
                                                                    <span id="status-val" class="value">
                                                                        <c:out value='${caso.getEstadoCasoCodigoestado().getNombreEstado()}' />
                                                                    </span>
                                                                </div>
                                                            </li>
                                                            <li class="item new">
                                                                <div class="wrap">
                                                                    <strong class="name">PCL</strong>
                                                                    <span>
                                                                        <c:out value='${caso.getPcl()}' />
                                                                    </span>
                                                                </div>
                                                            </li> 
                                                            <li class="item item-right" style="clear: right; float: right;">
                                                                <div class="wrap">
                                                                    <strong class="name">Tiempo Incapacidad</strong>
                                                                    <span id="status-val" class="value">
                                                                        <c:out value='${caso.getTiempoIncapacidad()}' />
                                                                    </span>
                                                                </div>
                                                            </li>
                                                            <li class="item new">
                                                                <div class="wrap">
                                                                    <strong class="name">Parte Afectada</strong>
                                                                    <span>
                                                                        <c:out value='${caso.getParteAfectada()}' />
                                                                    </span>
                                                                </div>
                                                            </li>    
                                                            <li class="item item-right" style="clear: right; float: right;">
                                                                <div class="wrap">
                                                                    <strong class="name">Fecha Incio de Afectacion</strong>
                                                                    <span id="status-val" class="value">
                                                                        <c:out value='${caso.getFechaInicioAfectacion()}'/>
                                                                    </span>
                                                                </div>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                                <div id="descriptionmodule" class="module toggle-wrap"><div id="descriptionmodule_heading" class="mod-header"><ul class="ops"></ul>
                                                        <h3 class="toggle-title">Descripción</h3>
                                                    </div>
                                                    <div class="mod-content">
                                                        <div id="description-val" class="field-ignore-highlight editable-field inactive" title="Haga clic para editar">
                                                            <div class="user-content-block je-not-empty">
                                                                <p> <c:out value='${caso.getDescripcionCaso()}'/></p>
                                                            </div>
                                                            <span class="overlay-icon aui-icon aui-icon-small aui-iconfont-edit"></span></div>
                                                    </div>
                                                </div>
                                            </c:when>
                                        </c:choose>         
                                    </c:forEach>
                                    <div id="dnd-metadata" class="module toggle-wrap">
                                        <div id="dnd-metadata_heading" class="mod-header">
                                            <ul class="ops"></ul><h3 class="toggle-title">Adjuntos</h3>

                                        </div>
                                    </div>
                                    <div id="dnd-metadata" class="module toggle-wrap">
                                        <div id="dnd-metadata_heading" class="mod-header">
                                            <ul class="ops"></ul><h3 class="toggle-title">Comentarios</h3>
                                            
                                        </div>
                                    </div>
                                    <div class="my-3 p-3 bg-white rounded shadow-sm">
                                        <h6 class="border-bottom border-gray pb-2 mb-0">Comentarios</h6>
                                        <c:forEach var="listAccionesCaso" items="${sessionScope.listAccionesCaso}">
                                            <c:choose> 
                                                <c:when test="${listAccionesCaso.getCodigo() == sessionScope.codigoCaso}">
                                                    <div class="media text-muted pt-3">
                                                        <svg class="bd-placeholder-img mr-2 rounded" width="32" height="32" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 32x32"><title>Placeholder</title><rect width="100%" height="100%" fill="#007bff"></rect><text x="50%" y="50%" fill="#007bff" dy=".3em">32x32</text></svg>
                                                        <p class="media-body pb-3 mb-0 small lh-125 border-bottom border-gray">
                                                            <strong class="d-block text-gray-dark"> @<c:out value='${listAccionesCaso.getUsuario()}'/> añadio un comentario - <c:out value='${listAccionesCaso.getFechaActualizada()}'/></strong> 
                                                            <c:out value='${listAccionesCaso.getCometario()}'/>
                                                            
                                              
                                                        </p>
                                                    </div>
                                                </c:when>
                                            </c:choose>  
                                        </c:forEach>
                                        <button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
                                            Comentar
                                        </button>
                                        <div class="collapse" id="collapseExample">
                                            <div class="card card-body">
                                                <c:forEach var="Caso" items="${sessionScope.Caso}">
                                                    <c:choose>
                                                        <c:when test="${Caso.getCodigocaso() == sessionScope.codigoCaso}">
                                                            <form method="post" name="comment" id="caso" action="">
                                                                <div class="form-group">
                                                                    <div class="form-group col-md-6">
                                                                        <input  name="codigoC" class="form-control"  type="hidden" value="<c:out value='${Caso.getCodigocaso()}' />">
                                                                        <input  name="usuarioComenta" class="form-control"  type="hidden" value="<c:out value="${sessionScope.USUARIO.getNombreUsuario()}"/> <c:out value="${sessionScope.USUARIO.getApellidoUsuario()}"/>">

                                                                    </div>
                                                                </div>
                                                                <div class="form-group"> 
                                                                    <label for="comentario">Deje aquí sus comentarios</label>
                                                                    <textarea class="form-control" rows="5" name="comentarios" id="comentarios"></textarea>
                                                                </div>
                                                                    <button name="comment" value="comment" type="submit" class="btn btn-outline-secondary" onclick="return comment()">Comentar</button>
                                                            
                                                            </form>
                                                        </c:when>
                                                    </c:choose>
                                                </c:forEach>

                                            </div>
                                        </div>
                                       
                                    </div>
                                </div>             
                                <div id="viewissuesidebar" class="aui-item issue-side-column" style="width: 35%;padding-left: 30px;
                                     box-sizing: border-box; display: table-cell;margin: 0;vertical-align: top; ">
                                    <c:forEach var="caso" items="${sessionScope.Caso}">
                                        <c:choose>
                                            <c:when test="${caso.getCodigocaso() == sessionScope.codigoCaso}">
                                                <div id="peoplemodule" class="module toggle-wrap">
                                                    <div id="peoplemodule_heading" class="mod-header">
                                                        <ul class="ops"></ul>
                                                        <h3 class="toggle-title">Personas</h3>
                                                    </div>
                                                    <div class="mod-content">
                                                        <ul class="item-details" id="peopledetails">
                                                            <li class="people-details">
                                                                <dl>
                                                                    <dt>Responsable:</dt>
                                                                    <dd>
                                                                        <span id="assignee-val" class="view-issue-field">
                                                                            <c:out value='${caso.getAsignado()}' />
                                                                        </span>
                                                                        </span>
                                                                    </dd>
                                                                </dl>                                            
                                                                <dl>
                                                                    <dt>Informador:</dt>
                                                                    <dd>
                                                                        <span id="reporter-val" class="view-issue-field">
                                                                            <c:out value='${caso.getCreado()}' />
                                                                        </span>
                                                                    </dd>
                                                                </dl>  
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </c:when>
                                        </c:choose>         
                                    </c:forEach>
                                    <div id="datesmodule" class="module toggle-wrap">
                                        <div id="datesmodule_heading" class="mod-header">
                                            <h3 class="toggle-title">Fechas</h3>
                                        </div>
                                        <c:forEach var="flujoList" items="${sessionScope.flujoList}">
                                            <c:choose>
                                                <c:when test="${flujoList.getCodigocaso() == sessionScope.codigoCaso}">
                                                    <div class="mod-content">
                                                        <ul class="item-details">
                                                            <li>
                                                                <dl class="dates">
                                                                    <dt>Creada:</dt>
                                                                    <c:out value='${flujoList.getFechaCreacion()}' />
                                                                </dl>
                                                                <dl class="dates">
                                                                    <dt>Actualizada:</dt>
                                                                    <c:out value='${flujoList.getFechaActualizacion()}' />
                                                                </dl>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </c:when>
                                        </c:choose>         
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div>                 
                </div>
            </div>
            <div class="row">
                <div class="col-md-8">                            
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <h3 class="box-title">Carga Masiva de Información</h3>
                        </div>
                        <form enctype="multipart/form-data" id="formuploadajax"  method="post">
                            <div class="box-body">
                                <div class="col-xs-8">  
                                    <div class="form-group">                                          
                                        <label for="fileCarga">Adjunte el Archivo</label>
                                        <input type="file" class="form-control-file" id="archivo1" name="fileCarga"/>                                               

                                    </div>
                                </div>
                            </div>
                            <div class="box-footer">
                                <div class="row">
                                    <div class="col-xs-4">                                           
                                        <input type="submit" class="btn btn-success btn-block" value="Cargar Archivo"/> 
                                    </div>
                                </div>
                            </div> 
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script>
            $(document).ready(function () {
                $('#myTable').DataTable();
            });

        </script>

        <div class="modal fade" id="cambiarEstado">
            <div class="modal-dialog">
                <div class="modal-content" style="padding:60px 50px;">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Caso: </h4>
                        <button type="button" class="close" data-dismiss="modal">×</button>
                    </div>
                    <c:forEach var="Caso" items="${sessionScope.Caso}">
                        <c:choose>
                            <c:when test="${Caso.getCodigocaso() == sessionScope.codigoCaso}">
                                <form method="post" name="cambiarEstado" id="Caso" action="">
                                    <div class="form-group">
                                        <input  name="codigoCaso" class="form-control"  type="hidden" value="<c:out value='${Caso.getCodigocaso()}' />">
                                        <input  name="usuario" class="form-control"  type="hidden" value="<c:out value="${sessionScope.USUARIO.getNombreUsuario()}"/> <c:out value="${sessionScope.USUARIO.getApellidoUsuario()}"/>">
                                        <input  name="codigoCaso" class="form-control"  type="hidden" value="<c:out value='${Caso.getCodigocaso()}' />">
                                    </div>
                                    <div class="form-group">
                                        <select name="Estado" id="Estado" class="form-control-sm form-control">
                                            <option value="">Estado</option>
                                            <c:forEach var="Estado" items="${sessionScope.Estado}">
                                                <option value="${Estado.getCodigoestado()}"><c:out value="${Estado.getNombreEstado()}"/></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <label class="file">
                                        <input type="file" id="file" name="file" aria-label="File browser example">
                                        <span class="file-custom"></span>
                                    </label>
                                    <div class="form-group"> 
                                        <textarea class="form-control" rows="5" id="comentarios" name="comentarios" value="comentarios"></textarea>
                                    </div>
                                    <button name="cambiarEstado" value="cambiarEstado" type="submit" class="btn btn-success" onclick="return cambiarEstado()">Cambiar Estado</button>
                                    <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                                </form>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="modal fade" id="Cargar">
            <div class="modal-dialog">
                <div class="modal-content" style="padding:60px 50px;">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Caso: </h4>
                        <button type="button" class="close" data-dismiss="modal">×</button>
                    </div>
                    <form id="fileupload" action="https://jquery-file-upload.appspot.com/" method="POST" enctype="multipart/form-data">
                        <!-- Redirect browsers with JavaScript disabled to the origin page -->
                        <noscript><input type="hidden" name="redirect" value="https://blueimp.github.io/jQuery-File-Upload/"></noscript>
                        <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
                        <div class="row fileupload-buttonbar">
                            <div class="col-lg-7">
                                <!-- The fileinput-button span is used to style the file input field as button -->
                                <span class="btn btn-success fileinput-button">
                                    <i class="glyphicon glyphicon-plus"></i>
                                    <span>Add files...</span>
                                    <input type="file" name="files[]" multiple>
                                </span>
                                <button type="submit" class="btn btn-primary start">
                                    <i class="glyphicon glyphicon-upload"></i>
                                    <span>Start upload</span>
                                </button>
                                <button type="reset" class="btn btn-warning cancel">
                                    <i class="glyphicon glyphicon-ban-circle"></i>
                                    <span>Cancel upload</span>
                                </button>
                                <button type="button" class="btn btn-danger delete">
                                    <i class="glyphicon glyphicon-trash"></i>
                                    <span>Delete</span>
                                </button>
                                <input type="checkbox" class="toggle">
                                <!-- The global file processing state -->
                                <span class="fileupload-process"></span>
                            </div>
                            <!-- The global progress state -->
                            <div class="col-lg-5 fileupload-progress fade">
                                <!-- The global progress bar -->
                                <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
                                    <div class="progress-bar progress-bar-success" style="width:0%;"></div>
                                </div>
                                <!-- The extended global progress state -->
                                <div class="progress-extended">&nbsp;</div>
                            </div>
                        </div>
                        <!-- The table listing the files available for upload/download -->
                        <table role="presentation" class="table table-striped"><tbody class="files"></tbody></table>
                    </form>
                </div>
            </div>
        </div>
        <div class="modal fade" id="Asignar">
            <div class="modal-dialog">
                <div class="modal-content" style="padding:60px 50px;">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Asignar</h4>
                        <button type="button" class="close" data-dismiss="modal">×</button>
                    </div>
                    <c:forEach var="Caso" items="${sessionScope.Caso}">
                        <c:choose>
                            <c:when test="${Caso.getCodigocaso() == sessionScope.codigoCaso}">
                                <form method="post" name="usuario" id="Caso" action="">
                                    <div class="form-group">
                                        <div class="form-group col-md-6">
                                            <input  name="codigoCaso" class="form-control"  type="hidden" value="<c:out value='${Caso.getCodigocaso()}' />">
                                            <input  name="" disabled  class="form-control" type="text" value="<c:out value='${Caso.getCodigocaso()}' />">

                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="usuario">Asignar</label>
                                        <select name="usuario" id="persona" class="form-control-sm form-control">
                                            <option value="">Asignar</option>
                                            <c:forEach var="Usuario" items="${sessionScope.Usuario}">
                                                <option value="${Usuario.getCedulaUsuario()}"><c:out value="${Usuario.getNombreUsuario()}"/> <c:out value="${Usuario.getApellidoUsuario()}"/></option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="form-group"> 
                                        <label for="comentario">Deje aquí sus comentarios</label>
                                        <textarea class="form-control" rows="5" name="comentarios" id="comentarios"></textarea>
                                    </div>
                                    <div class="modal-footer">
                                        <button name="accion" value="asignar" type="submit" class="btn btn-success" onclick="return asignar()">Asignar</button>
                                        <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                                    </div>
                                </form>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </div>
            </div>
        </div>
        <script id="template-upload" type="text/x-tmpl">
            {% for (var i=0, file; file=o.files[i]; i++) { %}
            <tr class="template-upload fade">
            <td>
            <span class="preview"></span>
            </td>
            <td>
            {% if (window.innerWidth > 480 || !o.options.loadImageFileTypes.test(file.type)) { %}
            <p class="name">{%=file.name%}</p>
            {% } %}
            <strong class="error text-danger"></strong>
            </td>
            <td>
            <p class="size">Processing...</p>
            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
            </td>
            <td>
            {% if (!i && !o.options.autoUpload) { %}
            <button class="btn btn-primary start" disabled>
            <i class="glyphicon glyphicon-upload"></i>
            <span>Start</span>
            </button>
            {% } %}
            {% if (!i) { %}
            <button class="btn btn-warning cancel">
            <i class="glyphicon glyphicon-ban-circle"></i>
            <span>Cancel</span>
            </button>
            {% } %}
            </td>
            </tr>
            {% } %}
        </script>
        <!-- The template to display files available for download -->
        <script id="template-download" type="text/x-tmpl">
            {% for (var i=0, file; file=o.files[i]; i++) { %}
            <tr class="template-download fade">
            <td>
            <span class="preview">
            {% if (file.thumbnailUrl) { %}
            <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
            {% } %}
            </span>
            </td>
            <td>
            {% if (window.innerWidth > 480 || !file.thumbnailUrl) { %}
            <p class="name">
            {% if (file.url) { %}
            <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
            {% } else { %}
            <span>{%=file.name%}</span>
            {% } %}
            </p>
            {% } %}
            {% if (file.error) { %}
            <div><span class="label label-danger">Error</span> {%=file.error%}</div>
            {% } %}
            </td>
            <td>
            <span class="size">{%=o.formatFileSize(file.size)%}</span>
            </td>
            <td>
            {% if (file.deleteUrl) { %}
            <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
            <i class="glyphicon glyphicon-trash"></i>
            <span>Delete</span>
            </button>
            <input type="checkbox" name="delete" value="1" class="toggle">
            {% } else { %}
            <button class="btn btn-warning cancel">
            <i class="glyphicon glyphicon-ban-circle"></i>
            <span>Cancel</span>
            </button>
            {% } %}
            </td>
            </tr>
            {% } %}
        </script>
    </body>
</html>
