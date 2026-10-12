<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.List,tienda.*" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Productos EliteCars</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../FicherosCSS/Inicio_EliteCars.css"> <!-- Enlaza la hoja de estilos personalizada para la página -->
    <link rel="icon" href="../ImagenesCoches/LogoEliteCars.jpeg" type="image/jpeg"> <!-- Define el icono de la página web -->
  </head>
  <body>
    <mi-cabecera></mi-cabecera>

    <%
	AccesoBD con=AccesoBD.getInstance();
	List<ProductoBD> productos = con.obtenerProductosBD();
    %>

    <div class="image-container">
        <div class="row">
            <% for (ProductoBD producto : productos){
                int codigo=producto.getCodigo();
                String descripcion=producto.getDescripcion();
                String categoria=producto.getCategoria();
                float precio=producto.getPrecio();
                int existencias=producto.getExistencias();
                String imagen=producto.getImagen();
                %>
                <div class="col-lg-3 col-md-4 col-sm-6 col-xs-12" style="text-align: center">
                    <br>
                    <br>
                    <img src="<%=imagen%>" alt="<%=descripcion%>" class="img-fluid">
                    <h6><%=codigo%>-<%=descripcion%></h6>
                    <p><%=precio%>€</p>
                    <% if (existencias > 0) { %>
                        <input type="button" value="Añadir al carrito" onclick="añadirProducto(<%=codigo%>, '<%=descripcion%>', <%=precio%>, 1, <%=existencias%>)" class="btn btn-primary" style="background-color: black;">
                        <br>
                        <br>
                        <br>
                        <br>
                        <% } else { %>
                        <p>Sin existencias</p>
                    <% } %>
                </div>
            <% } %>

        </div>
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