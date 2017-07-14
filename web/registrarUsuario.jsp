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
                <label class="col-sm-3 control-label" for="run">Rut(*)</label>
                <div class="col-sm-6">
                    <input class="form-control " id="run" name="run" type="text" placeholder="112223337" onkeyup="eliminarCaracteres()">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="nombres">Nombres (*)</label>
                <div class="col-sm-6">
                    <input class="form-control" id="nombres" name="nombres" type="text">                                     
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="apellidos">Apellidos (*)</label>
                <div class="col-sm-6">
                    <input class="form-control" id="apellidos" name="apellidos" type="text">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="correoElectronico"><strong>E-Mail (*)</strong></label>
                <div class="col-sm-6">
                    <input class="form-control" id="correoElectronico" name="correoElectronico" type="text" placeholder="ejemplo@celeste.cl">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="sexo">Sexo (*)</label>
                <div class="col-sm-6">
                    <div class="col-md-3">
                        <input  type="radio" id="sexoM" name="sexo" value="M" checked="checked" >&nbsp;&nbsp;Masculino
                    </div>
                    <div class="col-md-3">
                        <input  type="radio" id="sexoF" name="sexo" value="F" >&nbsp;&nbsp;Femenino
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="telefono">Teléfono (*)</label>
                <div class="col-sm-6">
                    <input class="form-control" id="telefono" name="telefono" type="text" placeholder="Ej: 988776655">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="direccion">Dirección (*)</label>
                <div class="col-sm-6">
                    <input class="form-control" id="direccion" name="direccion" type="text">
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="tipoUsuario">Tipo de Cliente (*)</label>
                <div class="col-sm-8">
                    <div class="col-md-4">
                        <input  type="radio" id="sexoM" name="tipoUsuario" value="Natural" checked="checked" >&nbsp;&nbsp;Soy Persona Natural
                    </div>
                    <div class="col-md-4">
                        <input  type="radio" id="sexoF" name="tipoUsuario" value="Empresa" >&nbsp;&nbsp;Soy Empresa
                    </div>
                </div>  
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="clave">Contraseña (*)</label>
                <div class="col-sm-6">
                    <input class="form-control" type="password" id="clave" name="clave" placeholder="Ingrese una clave entre 4 y 8 digitos">
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
                    <a onclick="registrarUsuario()" class="btn btn-success"><i class="icon icon-next"> </i> Solicitar Registrarme</a>
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
    function registrarUsuario() {
        if (document.getElementById('TerminosyCondiciones').checked) {
            if (validarUsuario()) {
                $.ajax({
                    type: "POST",
                    url: "/SistemaOFIS/administrarUsuario",
                    data: $("#fmusuario").serialize(),
                    success: function (result) {
                        console.log(result);
                        var result = eval('(' + result + ')');
                        if (result.success) {
                            notificacion(result.statusText, 'success', 'alert');
                           
                        } else {
                            notificacion(result.statusText, 'danger', 'alert');
                        }
                    }
                });
            }
        } else {
            notificacion('Primero debe aceptar los términos y condiciones', 'warning', 'alert');
        }
    }
    function validarUsuario() {
        var email = document.getElementById('correoElectronico').value;
        var telefono = document.getElementById('telefono').value;
        var cadenaPass = document.getElementById('clave').value;
        if (Rut(document.getElementById('run').value)) {
            if (document.getElementById('nombres').value != "") {
                if (document.getElementById('apellidos').value != "") {
                    if (email != "") {
                        if (document.getElementById('direccion').value != "") {
                            if (telefono != "" && telefono.length > 5) {
                                if (!isNaN(telefono)) {
                                    if (cadenaPass.length >= 4 && cadenaPass.length <= 8) {
                                        if (cadenaPass == document.getElementById('contrasenaRepetidaUsuario').value) {
                                            return true;
                                        } else {
                                            notificacion("Las contraseñas no coinciden", "warning", "alert");
                                        }
                                    } else {
                                        notificacion("La contraseña debe tener minimo 4 caracteres y Maximo 8", "warning", "alert");
                                    }
                                } else {
                                    notificacion("El teléfono contiene caracteres no válidos", "warning", "alert");
                                }
                            } else {
                                notificacion("Debe ingresar una teléfono de contacto con al menos 6 dígitos", "warning", "alert");
                            }
                        } else {
                            notificacion("Debe ingresar una dirección", "warning", "alert");
                        }
                    } else {
                        notificacion("Debe ingresar un email", "warning", "alert");
                    }
                } else {
                    notificacion("Debe ingresar sus apellidos", "warning", "alert");
                }
            } else {
                notificacion("Debe ingresar sus nombres", "warning", "alert");
            }
        } else {
            notificacion("El run ingresado no es válido", "warning", "alert");
        }
        return false;
    }
    function eliminarCaracteres() {
        var aux = String(document.getElementById("run").value);
        aux = aux.replace('.', '');
        aux = aux.replace('.', '');
        aux = aux.replace('-', '');
        document.getElementById("run").value = aux;
    }
</script>

<%@ include file="footerVisitante.jsp" %>     