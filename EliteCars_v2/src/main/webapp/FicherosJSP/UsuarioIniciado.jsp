<%@page language="java" contentType="text/html;charset=UTF-8" import="java.util.List,tienda.*" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">    <!--Establece el idioma en ingles-->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Información de Clientes y Pedidos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../FicherosCSS/Inicio_EliteCars.css"> <!-- Enlaza la hoja de estilos personalizada para la página -->
    <link rel="icon" href="../ImagenesCoches/LogoEliteCars.jpeg" type="image/jpeg"> <!-- Define el icono de la página web -->
</head>
<!--<body onload="cargarDatosUsuario()">-->
<body>
    <mi-cabecera></mi-cabecera>

    <%
	    AccesoBD con=AccesoBD.getInstance();
	    List<UsuarioBD> usuarios = con.obtenerUsuariosBD();
        List<Pedido> pedidos = con.obtenerPedidosBD();
        List<DetalleBD> detalles = con.obtenerDetallesBD();
        List<ProductoBD> productos = con.obtenerProductosBD();
        List<EstadoBD> estados = con.obtenerEstadoBD();

        int idUsuario = con.obtenerIdUsuario(session.getAttribute("usuario").toString());

        String nombre;
        String apellidos;
        String domicilio;
        String poblacion;
        String provincia;
        String cp;
        String telefono;
        String email;
        String user;
        String clave;

        for(UsuarioBD usuario : usuarios){
            if(session.getAttribute("usuario").equals(usuario.getUsuario())){
                nombre = usuario.getNombre();
                apellidos = usuario.getApellidos();
                domicilio = usuario.getDomicilio();
                poblacion = usuario.getPoblacion();
                provincia = usuario.getProvincia();
                cp = usuario.getCp();
                telefono = usuario.getTelefono();
                email = usuario.getEmail();
                user = usuario.getUsuario();
                clave = usuario.getClave();
    %>

    <br>
        <h1 style="text-align: center;">Información de Clientes</h1>
        <form id="infoUsuario" method="post"  action="../ActualizarUsuario.html">
            <label for="codigo">ID: </label>
            <input type="text" id="codigo" name="codigo" value="<%= usuario.getCodigo() %>" readonly class="form-control-plaintext" style="text-align: center;"><br>
        
            <label for="nombre">Nombre: </label>
            <input type="text" id="nombre" name="nombre" value="<%= nombre %>"><br>
        
            <label for="apellidos">Apellidos: </label>
            <input type="text" id="apellidos" name="apellidos" value="<%= apellidos %>"><br>
        
            <label for="domicilio">Domicilio: </label>
            <input type="text" id="domicilio" name="domicilio" value="<%= domicilio %>"><br>
        
            <label for="poblacion">Poblacion: </label>
            <input type="text" id="poblacion" name="poblacion" value="<%= poblacion %>"><br>
        
            <label for="provincia">Provincia: </label>
            <input type="text" id="provincia" name="provincia" value="<%= provincia %>"><br>
        
            <label for="cp">C.P: </label>
            <input type="text" id="cp" name="cp" value="<%= cp %>"><br>
        
            <label for="telefono">Teléfono: </label>
            <input type="text" id="telefono" name="telefono" value="<%= telefono %>"><br>
        
            <label for="email">Email: </label>
            <input type="text" id="email" name="email" value="<%= email %>"><br>
        
            <label for="user">Usuario: </label>
            <input type="text" id="user" name="user" value="<%= user %>" readonly class="form-control-plaintext" style="text-align: center;"><br>
        
            <label for="clave">Contraseña: </label>
            <input type="text" id="clave" name="clave" value="<%= clave %>" readonly class="form-control-plaintext" style="text-align: center;"><br>

            <br>
            <!--
                <a href="../ActualizarUsuario.html" class="btn btn-primary">Guardar</a>
                <input type="submit" class="btn btn-secondary" value="Guardar">
            -->
            <button class="btn btn-primary" type="submit" name="Guardar">Guardar</button>
            
            <!-- Botón para cerrar la sesión -->
            <a href="../Logout.html" class="btn btn-primary">Cerrar sesion</a>
            <br>
        
        </form>
        
        <%
                }
            }
        %>
    
        <br>
        <h1 style="text-align: center;">Información de Pedidos</h1>
        <div>
            <table>
                <tr>
                    <th>ID Pedido</th>
                    <th>ID Persona</th>
                    <th>Importe</th>
                    <th>Cantidad</th>
                    <th>Precio</th>
                    <th>Articulo</th>
                    <th>Estado</th>
                </tr>
                <tbody id="infoPedidos">
                    <!-- Aquí se insertarán los pedidos del usuario -->
                    <%
                        for(Pedido pedido : pedidos){ 
                            if(idUsuario == pedido.getPersona()){
                                for(DetalleBD det : detalles){
                                    if(det.getCodigoPedido() == pedido.getCodigo()){
                    %>
                    <tr>
                        <td><%= pedido.getCodigo() %></td>
                        <td><%= pedido.getPersona() %></td>
                        <td><%= pedido.getImporte() %>€</td>
                        <td><%= det.getUnidades() %></td>
                        <td><%= det.getPrecioUnitario() %>€</td>
                        <% 
                                        for (ProductoBD producto : productos){
                                            if(producto.getCodigo() == det.getCodigoProducto()){
                        %>
                        <td><%= producto.getDescripcion() %></td>
                        <% 
                                                for (EstadoBD estado : estados){
                                                    if(estado.getCodigo() == pedido.getEstado() && estado.getCodigo() == 1){
                        %>
                        <td><a class="btn btn-primary" href="../CancelarPedido.html?pedidoId=<%= pedido.getCodigo() %>">Cancelar</a></td>
                        <%
                                                    }else if (estado.getCodigo() == pedido.getEstado()){
                        %>
                        <td><%= estado.getDescripcion() %></td>
                    </tr>
                    <%
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    %>
                </tbody>
                
            </table>
        </div>

        <br>
        <br>
        <br>
    <mi-pie></mi-pie>

    <script src="../FicherosJS/Cabecera_EliteCars.js"></script>
    <script src="../FicherosJS/PiePag_EliteCars.js"></script>
    <script src="../FicherosJS/Carrito.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</body>
</html>