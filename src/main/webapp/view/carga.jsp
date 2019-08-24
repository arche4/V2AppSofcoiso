<%-- 
    Document   : CargaMasivaVolks
    Created on : 05-feb-2019, 10:44:47
    Author     : fadia.badavid
--%>

<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Reportería autoatención</title>
           <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/bootstrap/css/bootstrap.min.css">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <!-- Ionicons -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <!-- Theme style -->
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/dist/css/AdminLTE.min.css">
        <!-- Page style -->
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/dist/css/home.css">
        <!-- DataTables -->
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/plugins/datatables/dataTables.bootstrap.css">
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/plugins/datatables/extensions/Select/select.dataTables.min.css ">
        <link href="https://cdn.jsdelivr.net/npm/alertifyjs@1.11.0/build/css/alertify.min.css" rel="stylesheet"/>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.7/css/bootstrap-dialog.min.css" rel="stylesheet"/>
        <script src="${pageContext.servletContext.contextPath}/javaScript/cargarArchivos.js" type="text/javascript"></script>

        <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
              page. However, you can choose any other skin. Make sure you
              apply the skin class to the body tag so the changes take effect.
        -->
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/dist/css/skins/skin-blue.min.css">

        <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <link rel="shortcut icon" type="image/x-icon"  href="img/favicon.ico">

        <!-- daterange picker -->
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/plugins/daterangepicker/daterangepicker.css">
    </head>
    <body>


    <body class="hold-transition skin-blue sidebar-mini">
        <div class="wrapper">

            <!-- Main Header -->
            <header class="main-header">
                <!-- Logo -->
                <a class="logo">
                    <!-- mini logo for sidebar mini 50x50 pixels -->
                    <span class="logo-mini"><img src="img/logo-blanco.png" style="width:100%;max-height: 80px;max-width: 305px"/></span>
                    <!-- logo for regular state and mobile devices -->
                    <span class="logo-lg"><img src="img/logo-blanco.png" style="width:100%;max-height: 80px;max-width: 305px"/></span>
                </a>

                <!-- Header Navbar -->
                <nav class="navbar navbar-static-top" role="navigation">
                    <!-- Navbar Right Menu -->
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <!-- User Account Menu -->
                            <li class="dropdown user user-menu">
                                <!-- Menu Toggle Button-->
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <!-- The user image in the navbar-->
                                    <img src="img/usuario.png" class="user-image" alt="User Image">
                                    <!-- hidden-xs hides the username on small devices so only the image appears. -->
                                    <span class="hidden-xs" id="usuario">${sessionScope.user}</span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- The user image in the menu -->
                                    <li class="user-header">
                                        <img src="img/usuario.png" class="img-circle" alt="User Image">
                                        <p>
                                            ${sessionScope.user}
                                        </p>
                                    </li>
                                    <!-- Menu Footer-->
                                    <li class="user-footer">
                                        <div class="pull-right">
                                            <form action = "AdminServletLogout" method = "post">
                                                <button class="btn btn-default btn-flat" type="submit">Cerrar sesi&oacute;n</button>
                                            </form>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </nav>
            </header>




            <!-- Left side column. contains the logo and sidebar -->
            <aside class="main-sidebar">

                <!-- sidebar: style can be found in sidebar.less -->
                <section class="sidebar">

                    
                    <!-- Sidebar Menu -->
                    <ul class="sidebar-menu">
                        <li class="header"> <h3 class="text-center" style="margin: 0">Men&uacute;</h3></li>
                        <!-- Optionally, you can add icons to the links -->
                        
                        <li class="active"><a href="CargaMasivaVolks.jsp"><i class="fa fa-link"></i> <span>Carga Masiva</span></a></li>
                        
                        <li class=""><a href="home.jsp"><i class="fa fa-link"></i> <span>Consultas</span></a></li>
                        

                    </ul>
                    <!-- /.sidebar-menu -->
                </section>
                <!-- /.sidebar -->
            </aside>

            <!-- Content Wrapper. Contains page content -->
            <div class="content-wrapper">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        Carga Masiva                    
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="#"><i class="fa fa-home"></i>Inicio</a></li>
                        <li class="active">Carga Masiva</li>
                    </ol>
                </section>

                <!-- Main content -->


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


                <!-- /.content -->
            </div>
            <!-- /.content-wrapper -->

            <!-- Main Footer -->
            <footer class="main-footer" style="margin: auto">
                <!-- Default to the left -->
                <strong>Copyright &copy; 2017 <a href="http://www.grupokonecta.com/" style="color: #002852">Konecta</a>.</strong> Todos los derechos reservados.
            </footer>

            <!-- Control Sidebar -->
            <!-- /.control-sidebar -->
            <!-- Add the sidebar's background. This div must be placed
                 immediately after the control sidebar -->
        </div>
        <!-- ./wrapper -->

        <!-- Modales -->

        <div class="modal" id="modalInf">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">x</span>
                        </button>
                        <h4 class="modal-title">Error</h4>
                    </div>
                    <div class="modal-body" id="modInf">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" id="btnErr" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal" id="modalSkt">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">x</span>
                        </button>
                        <h4 class="modal-title">Error</h4>
                    </div>
                    <div class="modal-body">
                        <p>En estos momentos se presenta un error de conexi&oacute;n,
                            por favor intentar nuevamente m&aacute;s tarde.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" id="btnErr" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal" id="modalEBD">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">x</span>
                        </button>
                        <h4 class="modal-title">Error</h4>
                    </div>
                    <div class="modal-body">
                        <p>Error insertando o actualizando la informaci&oacute;n de la base de datos.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" id="btnErr" data-dismiss="modal">Cerrar</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="loadIc"></div>

<!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 2.2.3 -->
<script src="plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="plugins/datatables/jquery.dataTables.min.js"></script>
<script src="plugins/datatables/dataTables.bootstrap.min.js"></script>
<script src="plugins/datatables/extensions/Select/dataTables.select.min.js"></script>

<!-- AdminLTE App -->
<script src="dist/js/app.min.js"></script>
<!-- Funcionalidad js -->
<script src="dist/js/CargaMasivaVolks.js"></script>
<!-- Optionally, you can add Slimscroll and FastClick plugins.
     Both of these plugins are recommended to enhance the
     user experience. Slimscroll is required when using the
     fixed layout. -->        
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<script src="plugins/daterangepicker/daterangepicker.js"></script>


<!-- Download to excel --> 
<script type="text/javascript" language="javascript" src="https://nightly.datatables.net/buttons/js/dataTables.buttons.min.js"></script>        
<script type="text/javascript" language="javascript" src="https://nightly.datatables.net/buttons/js/buttons.jqueryui.min.js"></script>

<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.flash.min.js"></script>

<script type="text/javascript" language="javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script type="text/javascript" language="javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/pdfmake.min.js"></script>
<script type="text/javascript" language="javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.32/vfs_fonts.js"></script>
<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.html5.min.js"></script>
<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.print.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/alertifyjs@1.11.0/build/alertify.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.7/js/bootstrap-dialog.min.js"></script>

    </body>
</html>
