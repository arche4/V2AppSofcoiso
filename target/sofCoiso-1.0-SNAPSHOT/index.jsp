<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Sofcoiso</title>
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
        <link rel="stylesheet" href="dist/css/AdminLTE.min.css">
        <link rel="stylesheet" href="dist/css/login.css">
        <link rel="stylesheet" href="dist/css/skins/skin-blue.min.css">
        <link rel="shortcut icon" type="images/logo_S.png"  href="img/logo_S.png">
    </head>

    <body>
        <div class="wrapper">
            <div class="row" style="height: 100%; overflow: auto; overflow-x: hidden">
                <div class="col-xs-7 contLe" style="padding-top: 17em;">
                    <div id="formLog">
                        <div class="row display-table">
                            <div class="col-xs-6 display-cell" style="border-right: #002852 solid 3px; height: 100%">
                                <img src="images/logo.png" id="minLog">
                            </div>
                            <div class="col-xs-6 display-cell">
                                <h2 id="textApp">SofCoiso</h2>
                            </div>
                        </div>
                        <span class="centrado" style="color:red;"> <c:out value="${sessionScope.MENSAJE}"/></span>

                        <div class="row" id="formSect">
                            <form id="frmLogin" method="post" 
                                  action="${pageContext.servletContext.contextPath}/usuarioLogin">
                                <div class="form-group">
                                    <label>Usuario</label>
                                    <input class="form-control" type="text" id="fmUser" name="txtid" placeholder="Usuario">
                                </div>
                                <div class="form-group">
                                    <label>Contraseña</label>
                                    <input class="form-control" type="password" id="fmPass" name="txtclave" placeholder="Clave">
                                </div>

                                <button class="btn btn-primary" id="btnLog" type="submit">Iniciar Sesion</button>
                            </form>
                        </div>
                    </div>
                </div>   
                <div class="col-xs-5 contRi">
                    <img src="images/Reporting.png" id="iconLog">
                </div>
            </div>
        </div>

    </body>

    
</html>
<!-- end document-->