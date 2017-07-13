<%-- 
    Document   : login
    Created on : 13-07-2017, 3:57:56
    Author     : 
--%>

<%@ include file="headerVisitante.jsp" %>  

<div class="cajaFormulario" style=" padding: 1%; align-content: center;">
    <h4 class="TextoTituloFormulario" style="border: green 1px solid; border-radius: 15px; text-align: center ; padding: 1%;"><strong>FORMULARIO DE SOLICITUD DE REGISTRO</strong></h4><br>
    <div style="padding: 3%; align-content: center; border: green 1px solid; border-radius: 15px;">
        <h7 class="TextoTituloFormulario" >COMPLETA TUS DATOS PERSONALES</h7><i style="float: right">(*) Campos Obligatorios</i>
        <hr style="border: green 1px solid;"> <br>
        <div id="alert"></div>
        <form id="fmusuario" class="form-horizontal" method="post" >    
            <div class="form-group">
                <label class="col-sm-3 control-label" for="runUsuario">Rut(*)</label>
                <div class="col-sm-6">
                    <input class="form-control " id="runUsuario" name="runUsuario" type="text" placeholder="112223337" onkeyup="eliminarCaracteres()">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="nombresUsuario">Nombres (*)</label>
                <div class="col-sm-6">
                    <input class="form-control" id="nombresUsuario" name="nombresUsuario" type="text">                                     
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="apellidosUsuario">Apellidos (*)</label>
                <div class="col-sm-6">
                    <input class="form-control" id="apellidosUsuario" name="apellidosUsuario" type="text">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="emailUsuario"><strong>E-Mail (*)</strong></label>
                <div class="col-sm-6">
                    <input class="form-control" id="emailUsuario" name="emailUsuario" type="text" placeholder="ejemplo@celeste.cl">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="emailUsuarioRepetido"><strong>Repetir E-Mail (*)</strong></label>
                <div class="col-sm-6">
                    <input class="form-control" id="emailUsuarioRepetido" name="emailUsuarioRepetido" type="text" placeholder="ejemplo@celeste.cl">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="sexo">Sexo (*)</label>
                <div class="col-sm-6">
                    <div class="col-md-3">
                        <input  type="radio" id="sexoM" name="sexo" value="Masculino" checked="checked" >&nbsp;&nbsp;Masculino
                    </div>
                    <div class="col-md-3">
                        <input  type="radio" id="sexoF" name="sexo" value="Femenino" >&nbsp;&nbsp;Femenino
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="telefonoUsuario">Teléfono (*)</label>
                <div class="col-sm-6">
                    <input class="form-control" id="telefonoUsuario" name="telefonoUsuario" type="text" placeholder="Ej: 988776655">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="direccionUsuario">Dirección (*)</label>
                <div class="col-sm-6">
                    <input class="form-control" id="direccionUsuario" name="direccionUsuario" type="text">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="tipo">Tipo de Cliente (*)</label>
                <div class="col-sm-8">
                    <div class="col-md-4">
                        <input  type="radio" id="sexoM" name="tipo" value="natural" checked="checked" >&nbsp;&nbsp;Soy Persona Natural
                    </div>
                    <div class="col-md-4">
                        <input  type="radio" id="sexoF" name="tipo" value="empresa" >&nbsp;&nbsp;Soy Empresa
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="contrasenaUsuario">Contraseña (*)</label>
                <div class="col-sm-6">
                    <input class="form-control" type="password" id="contrasenaUsuario" name="contrasenaUsuario" placeholder="Ingrese una clave entre 4 y 8 digitos">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="contrasenaRepetidaUsuario"><strong>Repetir Contraseña (*) </strong></label>
                <div class="col-sm-6">
                    <input class="form-control" type="password" id="contrasenaRepetidaUsuario" name="contrasenaRepetidaUsuario" placeholder="Repita su Clave">
                </div>
            </div>
            <div style="text-align: center">
                <input  type="checkbox"  id="TerminosyCondiciones" name="TerminosyCondiciones" value="Femenino" >&nbsp;<i class="TextoFormulario">Al registrar tu información estás aceptando los<a onclick="AbreTerminosyCondiciones()" style="color: green"> términos y condiciones</a></i><br><br>
            </div>           
            <div class="form-group" style="text-align: center">
                <div class="col-sm-offset-2 col-sm-9">
                    <a onclick="guardarCliente()" class="btn btn-success"><i class="icon icon-next"> </i> Solicitar Registrarme</a>
                </div>
            </div>

            <input type="hidden" id="accion" name="accion" value="AGREGAR">
            <input type="hidden" id="idPerfil" name="idPerfil" value="3">
            <input type="hidden" id="estado" name="estado" value="0">
        </form>
    </div>
</div>
<!-- DIALOGO MODAL TERMINOS Y CONDICIONES-->
<div class="modal fade bs-example-modal-md" id="dg-modela" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
    <div class="modal-dialog modal-md">
        <div class="modal-content">
            <section id="panel-modal">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <img id="logo-modal" src="/SistemaOFIS/img/log.png" width="100px" style="float: left;">
                    <label class="titulo-modal" style="width: 350px; padding-top: 50px;"><h3 class="modal-title" id="modalLabel"></h3></label>
                </div>
                <form id="fm" method="POST" >
                    <div class="modal-body">
                        <section class="row">                            
                            <section class="col-md-12">
                                <div id="nombresGroup" class="form-group has-feedback">
                                    Bienvenido al sistema de registro de Ofis. Estos Términos y Condiciones regulan el acceso en Chile a nuestro sitio web y su uso por todo usuario o consumidor. 
                                    En este sitio podrás usar, sin costo alguno, nuestro software, para visitar y adquirir, si lo deseas, los productos y servicios que se exhiben aquí. 
                                    Sin embargo, tu registro esta sujeto a revisión, en cuanto revisemos la veracidad de tus datos, habilitaremos tu acceso al sistema.
                                    Te recomendamos leer atentamente estos términos y condiciones.                                   
                                </div>
                            </section>                           
                        </section><!-- Fin Row-->
                    </div>
                    <div class="modal-footer">
                        <a class="btn btn-default" data-dismiss="modal">Cerrar</a>
                    </div>
                </form>
            </section>
        </div>
    </div>
</div>
<!-- END DIALOGO MODAL-->
<script>
    function AbreTerminosyCondiciones() {
        $('#modalLabel').html("Terminos y Condiciones");
        $('#dg-modela').modal(this)//CALL MODAL MENSAJE
    }
</script>

<%@ include file="footerVisitante.jsp" %>     