<%@page language="java" contentType="text/html;charset=UTF-8" import="java.util.*,tienda.*" pageEncoding="UTF-8"%>
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

    <%-- Formulario para modificar los datos de envío y pago --%>
    <br>
    <br>
    <form action="../Tramitacion.html" method="post">
        <%-- Campos para los datos de envío y pago --%>
        <div id="pedidoProcesado">
            <!-- Aquí se insertará la informacion del pedido y del usuario  -->
            <div id="carrito">
              <br>
              <h2>Carrito de Compras</h2>
              <table>
                <thead>
                  <tr>
                    <th>Producto</th>
                    <th>Cantidad</th>
                    <th>Precio (1ud)</th>
                    <th>Total</th>
                  </tr>
                </thead>
                <tbody id="pedidoProcesado">
                  
                  <%List<Producto> carrito = (List<Producto>) session.getAttribute("carrito"); %>
                  <% for (Producto producto : carrito) { %>
                  <tr>
                    <td><%= producto.getNombre() %></td>
                    <td><%= producto.getCantidad() %></td>
                    <td><%= producto.getPrecio() %>€</td>
                    <td><%= producto.getPrecio() * producto.getCantidad() %>€</td>
                  </tr>
                  <% } %>
                </tbody>
              </table>
              <br>
            </div> 
        </div>
        <input type="submit" value="Finalizar compra">
        <input type="button" value="Cancelar compra" onclick="location.href='../FicherosJSP/Carrito.jsp';">
    </form>
    <%
	    AccesoBD con=AccesoBD.getInstance();
	    List<UsuarioBD> usuarios = con.obtenerUsuariosBD();

        int idUsuario = con.obtenerIdUsuario(session.getAttribute("usuario").toString());

        String domicilio;
        String poblacion;
        String provincia;
        String cp;
        String telefono;
        String email;

        for(UsuarioBD usuario : usuarios){
            if(session.getAttribute("usuario").equals(usuario.getUsuario())){
                domicilio = usuario.getDomicilio();
                poblacion = usuario.getPoblacion();
                provincia = usuario.getProvincia();
                cp = usuario.getCp();
                telefono = usuario.getTelefono();
                email = usuario.getEmail();
    %>
    <br>
              <h2>Informacion de envio</h2>
              <table>
                <thead>
                  <tr>
                    <th>Domicilio</th>
                    <th>Poblacion</th>
                    <th>Provincia</th>
                    <th>C.P</th>
                    <th>Telefono</th>
                    <th>Email</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td><%=domicilio%></td>
                    <td><%=poblacion%></td>
                    <td><%=provincia%></td>
                    <td><%=email%></td>
                    <td><%=telefono%></td>
                    <td><%=email%></td>
                  </tr>
                  <%
                    }
                  }
                  %>
                </tbody>
              </table>

    <br>
    <br>

    <mi-pie></mi-pie>

    <script src="../FicherosJS/Cabecera_EliteCars.js"></script>
    <script src="../FicherosJS/PiePag_EliteCars.js"></script>
    <script src="../FicherosJS/Carrito.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
  </body>
</html>
