<?php 
    require_once('baseDatos.php'); 
    session_start();

    if(isset($_SESSION['username'])){
?>
<!DOCTYPE html>
<html lang="es">
<head>
   <title>Pagina de productos</title>
   <link rel="stylesheet" type="text/css" href="./Inicio_EliteCars.css">
   <style>
       header {
           background-color: dark;
           color: white; /* Cambia el color del texto a blanco para que sea visible en el fondo negro */
           display: flex; /* Añadido para hacer que los elementos estén en horizontal */
           justify-content: space-around; /* Añadido para distribuir los elementos de manera uniforme en el espacio disponible */
           padding: 10px; /* Añadido para dar un poco de espacio alrededor de los elementos */
       }
       .nav-link {
           color: white;
           text-decoration: none; /* Elimina el subrayado */
           font-size: 1.2em; /* Aumenta el tamaño del texto */
       }
       .nav-link:hover {
           color: #ddd; /* Cambia el color del texto al pasar el ratón por encima */
       }
   </style>
</head>
<body>
<header>
        <div>
            <a class="nav-link active" href="./inicio.php">Inicio</a>
        </div>
        <div>
            <a class="nav-link" href="./productos.php">Productos</a>
        </div>
        <div>
            <a class="nav-link" href="./pedidos.php">Pedidos</a>
        </div>
        <div>
            <a class="nav-link" href="./cerrarsesion.php">Cerrar Sesion</a>
        </div>
</header>
    <br>
    <div style="text-align: center;">
        <form action="pedidos.php" method="get" style="display: inline-block; margin-right: 20px;">
            <label for="usuario">Usuario:</label>
            <input type="text" id="usuario" name="usuario">
            <input type="submit" value="Filtrar por usuario">
        </form>
        <form action="pedidos.php" method="get" style="display: inline-block; margin-right: 20px;">
            <label for="producto">Producto:</label>
            <input type="text" id="producto" name="producto">
            <input type="submit" value="Filtrar por producto">
        </form>
        <form action="pedidos.php" method="get" style="display: inline-block;">
            <label for="fecha">Fecha:</label>
            <input type="date" id="fecha" name="fecha">
            <input type="submit" value="Filtrar por fecha">
        </form>
    </div>
    <br>


    <div>
        <?php
            // Obtén los valores de los filtros de la URL
            $usuario = isset($_GET['usuario']) ? $_GET['usuario'] : null;
            $producto = isset($_GET['producto']) ? $_GET['producto'] : null;
            $fecha = isset($_GET['fecha']) ? $_GET['fecha'] : null;
        ?>
        <table class="tabla-pedidos">
            <tr>
                <th>ID Pedido</th>
                <th>ID Persona</th>
                <th>Fecha</th>
                <th>Importe</th>
                <th>Estado</th>
                <th>Cod.Producto</th>
                <th>Unidades</th>
                <th>Precio Unitario</th>
                <th>Producto</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <?php
                $resultados = obtenerPedidos($usuario, $fecha);
                while ($fila = mysqli_fetch_row($resultados)) {
                    $resultados2 = obtenerDetallesPedido($fila[0],$producto);
                    $resultados3 = obtenerEstadoPedidos($fila[4]);
                    while ($fila2 = mysqli_fetch_row($resultados2)) {
                        $resultado4 = obtenerProductosPedido($fila2[1]);
                        while ($fila4 = mysqli_fetch_row($resultado4)){
            ?>
            <form action="editarEstadoPedido.php" method="post">
                <tr>
                    <input type="hidden" name="id" value="<?php echo $fila[0];?>">
                    <td><?php echo $fila[0];?></td>
                    <td><?php echo $fila[1];?></td>
                    <td><?php echo $fila[2];?></td>
                    <td><?php echo $fila[3];?></td>
                    <td><?php echo $fila[4];?></td>
                    <td><?php echo $fila2[1];?></td>
                    <td><?php echo $fila2[2];?></td>
                    <td><?php echo $fila2[3];?></td>
                    <td><?php echo $fila4[1];?></td>
                    <?php
                        while ($fila3 = mysqli_fetch_row($resultados3)){
                    ?>
                    <td>
                        <select name="opciones">
                            <option value="pendiente" <?php if ($fila3[1] == 'pendiente') echo 'selected'; ?>>Pendiente</option>
                            <option value="cancelado" <?php if ($fila3[1] == 'cancelado') echo 'selected'; ?>>Cancelado</option>
                            <option value="enviado" <?php if ($fila3[1] == 'enviado') echo 'selected'; ?>>Enviado</option>
                            <option value="entregado" <?php if ($fila3[1] == 'entregado') echo 'selected'; ?>>Entregado</option>
                        </select>
                    </td>
                    <td><input type="submit" name="actualizar" value="Actualizar"></td>
                </tr>
            </form>
            <?php
                            }
                        }
                    }
                }
            ?>
        </table>
    </div>

    <br>
    <br>
    <br>
    <br>
    <br>
    <?php
        include('pie.php');
    ?>
</body>
</html>
<?php   
    }else{
        echo "<h1>Sesion no iniciada, pagina privada</h1>";
    }
?>