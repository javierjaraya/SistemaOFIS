<%-- 
    Document   : login
    Created on : 13-07-2017, 3:57:56
    Author     : 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="login" method="post">
            <div>Nombre: </div>
            <input type="text" name="run" id="run" placeholder="185887459" />
            <div>Contraseña</div> 
            <input type="password" name="clave" id="clave" placeholder="••••••••••••" />
            <div><a href="#" ><strong>¿Olvidó su contraseña?</strong></a></div>
            <input type="hidden">
            <input type="submit" value="Entrar" />
        </form>
    </body>
</html>