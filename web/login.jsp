<%-- 
    Document   : login
    Created on : 13-07-2017, 3:57:56
    Author     : 
--%>

<%@ include file="headerVisitante.jsp" %>  
<div class="row">
    <div class="col-md-12" style="padding: 5px; border: green 1px solid; border-radius: 15px; text-align: center;  margin-bottom: 20px;">
        <h4 class="TextoTituloFormulario"><strong>INGRESE SUS DATOS PARA INICIAR SESIÓN</strong></h4>
    </div>
    <div class="col-md-12" style="padding: 3%; align-content: center; border: green 1px solid; border-radius: 15px;">      
        <div  class="row" style="text-align: center">
            <div id="alert"></div>    
            <form action="login" method="post" class="form-horizontal">
                <div id="fmlogin" class="form-group">
                    <label for="inputRun" class="col-sm-4 control-label">Run</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="run" id="run" placeholder="112223337" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="col-sm-4 control-label">Contraseña</label>
                    <div class="col-sm-6">
                         <input type="password" class="form-control"  name="clave" id="clave" placeholder="Contraseña" />
                    </div>
                </div>
                <!--<div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
                        <div class="checkbox">
                            <label style="width: 200px;">
                                <a href="restablecerClave.php">Olvido su contraseña</a>                                
                            </label>
                        </div>
                    </div>
                </div>-->
                <div class="form-group" >
                    <div class="col-sm-offset-4 col-sm-4" >
                        <input type="submit" class="btn btn-success" value="Entrar">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="footerVisitante.jsp" %>     