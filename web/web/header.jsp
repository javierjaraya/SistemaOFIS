<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sistema Ofis</title>    
        <link href="/SistemaOFIS/img/favicon2.png" rel="icon" />
        <!-- CSS Part Start-->     
        <link rel="stylesheet" type="text/css" href="/SistemaOFIS/css/estilos.css" />     
        <link rel="stylesheet" type="text/css" href="/SistemaOFIS/css/notificaciones.css" />
        <link rel="stylesheet" type="text/css" href="/SistemaOFIS/complementos/menuDespegable/estilo-menu.css" />        
        <link rel="stylesheet" type="text/css" href="/SistemaOFIS/complementos/bootcomplete/dist/bootcomplete.css" media="screen" />
        <!-- CSS Part End-->
        <!-- JS Part Start-->
        <script type="text/javascript" src="/SistemaOFIS/js/jquery-2.2.3.min.js"></script>
        <script type="text/javascript" charset="utf8" src="/SistemaOFIS/complementos/datatables/jquery.dataTables.js"></script>
        <script type="text/javascript" src="/SistemaOFIS/complementos/jquery-easyui-1.4.2/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="/SistemaOFIS/complementos/menuDespegable/js-menu.js"></script>
        <script type="text/javascript" src="/SistemaOFIS/complementos/bootcomplete/dist/jquery.bootcomplete.js"></script>
        <!-- JS Part End-->
        <!-- Bootstrap 3.3.6 -->
        <link rel="stylesheet" type="text/css" href="/SistemaOFIS/complementos/bootstrap/css/bootstrap.css" />
        <script src="/SistemaOFIS/complementos/bootstrap/js/bootstrap.min.js"></script>        
        <!-- Usabilidad -->
        <script src="/SistemaOFIS/js/notificaciones.js"></script>
        <script src="/SistemaOFIS/js/validarut.js"></script>            
    </head>
    <body background="/SistemaOFIS/img/fondoflor1.jpg">
        <div class="container" style="background: #fff; margin-top: 20px; border-radius: 5px 5px 0px 0px;">
            <!-- HEADER -->
            <div class="row" style="padding: 10px;">
                <div class="col-md-1">
                    <a href="#"><img src="/SistemaOFIS/img/log.png" title="Ofis" /></a>
                </div>
                 <div class="col-md-5">
                    <h3 href="#" style="padding-top: 10px"><strong>OFIS Venta de artículos de oficina </strong></h3>
                </div>
                <div class="col-md-6">
                    <div style="text-align: right">
                        <h8>Bienvenido/a</h8>
                        <a href="cerrarSesion">Cerrar Session</a>
                    </div>
                </div>  
            </div>
             
            <div class="row" style="padding-left: 10px; padding-right: 10px;">                
                <input type="hidden" value="<%= session.getAttribute("idPerfil")%>" name="idPerfilMenu" id="idPerfilMenu">
                <div id="menu"></div>
            </div> 

            <!--cuerpo-->
            <div class="row">
                <div class="container">
                   
                    <div class="col-md-12">
                        <div class="row">
                            <!--Middle Part Start-->
                            <div id="content" style="padding-top: 0">    
