<%@page language="java" contentType="text/html;charset=UTF-8" import="tienda.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registro Usuario EliteCars</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../FicherosCSS/Inicio_EliteCars.css"> <!-- Enlaza la hoja de estilos personalizada para la página -->
    <link rel="icon" href="../ImagenesCoches/LogoEliteCars.jpeg" type="image/jpeg"> <!-- Define el icono de la página web -->
</head>
<body>
    <mi-cabecera></mi-cabecera>

	<%-- Utilizamos una variable en la sesión para informar de los mensajes de Error --%>
	<%
	String mensaje = (String)session.getAttribute("mensaje");

	if (mensaje != null) {
	%>
	<%-- Eliminamos el mensaje consumido --%>
	<%
		session.removeAttribute("mensaje");
	%>
	<h1> <%=mensaje%> </h1>
	<%
	}
	%>

	<%-- Si no hay usuario o el usuario no es válido --%>

	<%
	if ((session.getAttribute("usuario") == null) ||
	    ((Integer)session.getAttribute("usuario") <=0 ))
	{
	%>
	<%-- Mostramos el formulario para la introducción del usuario y la clave --%>
    <br>
    <br>
	<form method="post" action="../Registro.html">
        <input type="hidden" name="url" value="FicherosJSP/LoginUsuario.jsp">
        <label for="nombre">Nombre:</label>
            <br>
            <input type="text" id="nombre" name="nombre" required>
            <br>
    
            <label for="apellidos">Apellidos:</label>
            <br>
            <input type="text" id="apellidos" name="apellidos" required>
            <br>
    
            <label for="email">Email:</label>
            <br>
            <input type="text" id="email" name="email" required>
            <br>
    
            <label for="telefono">Telefono:</label>
            <br>
            <input type="tel" id="telefono" name="telefono" required>
            <br>

            <label for="domicilio">Domicilio</label>
            <br>
            <input type="text" id="domicilio" name="domicilio" required>
            <br>

            <label for="poblacion">Poblacion</label>
            <br>
            <input type="text" id="poblacion" name="poblacion" required>
            <br>

            <label for="provincia">Provincia</label>
            <br>
            <input type="text" id="provincia" name="provincia" required>
            <br>

            <label for="cp">C.P</label>
            <br>
            <input type="text" id="cp" name="cp" required>
            <br>
    
            <label for="usuario">Usuario:</label>
            <br>
            <input type="text" id="usuario" name="usuario" required>
            <br>
    
            <label for="clave">Contraseña:</label>
            <br>
            <input type="password" id="clave" name="clave" required>
            <br>
            <br>
            <button type="submit" name="Registrar">Registrarse</button>
            <br>
    </form>
    <br>
    <br>
	<%
	} else {
	%>
		<%-- Si existe un usuario, se muestran las opciones del apartado del usuario --%>

		<p>Mostraríamos las diferentes opciones</p>
	<%
	}
	%>

    <mi-pie></mi-pie>

    <script src="../FicherosJS/Cabecera_EliteCars.js"></script>
    <script src="../FicherosJS/PiePag_EliteCars.js"></script>
    <script src="../FicherosJS/Carrito.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</body>
</html>
