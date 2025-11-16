<%@page language="java" contentType="text/html;charset=UTF-8" import="java.util.*,tienda.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Pedido Realizado</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="../FicherosCSS/Inicio_EliteCars.css"> <!-- Enlaza la hoja de estilos personalizada para la página -->
        <link rel="icon" href="../ImagenesCoches/LogoEliteCars.jpeg" type="image/jpeg"> <!-- Define el icono de la página web -->
      </head>
<body onload="vaciarCarrito();">
    <mi-cabecera></mi-cabecera>

    <br>
    <br>
    <div class="text-center">
        <h1>¡Tu pedido ha sido realizado correctamente!</h1>
        <p>Gracias por tu compra. Tu pedido está siendo procesado y pronto estará en camino.</p>
        <button class="btn btn-secondary" onclick="location.href='../FicherosHTML/Inicio_EliteCars.html'">Volver a la página de inicio</button>
        <button class="btn btn-primary" onclick="location.href='../FicherosHTML/Empresa_EliteCars.html'">Seguir comprando</button>
    </div>

<mi-pie></mi-pie>

    <script src="../FicherosJS/Cabecera_EliteCars.js"></script>
    <script src="../FicherosJS/PiePag_EliteCars.js"></script>
    <script src="../FicherosJS/Carrito.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
  </body>
</html>