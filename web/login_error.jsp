<%-- 
    Document   : login_error
    Created on : 13-07-2017, 3:58:08
    Author     : javie
--%>

<%@ include file="headerVisitante.jsp" %>  

<h1>Login</h1>
<b>Usuario o contrase�a incorrecta</b>
<form action="login" method="post">
    <div>Nombre: </div>
    <input type="text" name="run" id="run" placeholder="185887459" />
    <div>Contrase�a</div> 
    <input type="password" name="clave" id="clave" placeholder="????????????" />
    <div><a href="#" ><strong>�Olvid� su contrase�a?</strong></a></div>
    <input type="hidden">
    <input type="submit" value="Entrar" />
</form>

<%@ include file="footerVisitante.jsp" %>     