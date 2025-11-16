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
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
   <style>
       .tabla-productos {
           width: 80%; /* Cambia este valor al que prefieras */
           max-height: 500px; /* Cambia este valor al que prefieras */
           overflow-y: auto; /* Permite el desplazamiento si el contenido excede la altura máxima */
           margin: auto; /* Centra la tabla en la página */
       }
       header {
           background-color: dark;
           color: white; /* Cambia el color del texto a blanco para que sea visible en el fondo negro */
           display: flex; /* Añadido para hacer que los elementos estén en horizontal */
           justify-content: space-around; /* Añadido para distribuir los elementos de manera uniforme en el espacio disponible */
           padding: 10px; /* Añadido para dar un poco de espacio alrededor de los elementos */
       }
       /* Añadido CSS para mejorar la apariencia de los enlaces */
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
    <div>
    <table class="tabla-productos">
        <tr>
            <th>Codigo</th>
            <th>Nombre</th>
            <th>Categoria</th>
            <th>Precio</th>
            <th>Existencias</th>
            <th>Accion</th>
        </tr>
        <?php
            if (isset($_GET['actualizado']) && $_GET['actualizado'] == 'true') {
                echo '<h1 class="text-center">El producto se ha actualizado con éxito</h1>';
            }else if (isset($_GET['agregado']) && $_GET['agregado'] == 'true'){
                echo '<h1 class="text-center">El producto se ha agregado con éxito</h1>';
            }
            $resultados = obtenerProductos();
            while ($fila = mysqli_fetch_row($resultados)) {
            ?>
            <form action="editarProducto.php" method="post">
                <tr>
                    <input type="hidden" name="id" value="<?php echo $fila[0];?>">
                    <td><?php echo $fila[0];?></td>
                    <td><input type="text" name="nombre" value="<?php echo $fila[1];?>"></td>
                    <td><input type="text" name="categoria" value="<?php echo $fila[2];?>"></td>
                    <td><input type="text" name="precio" value="<?php echo $fila[3];?>"></td>
                    <td><input type="text" name="existencias" value="<?php echo $fila[4];?>"></td>
                    <td><input type="submit" name="actualizar" value="Actualizar"></td>
                </tr>
            </form>
        <?php
        }
        ?>
    </table>
</form>
<br>
<br>
            <table table class="tabla-productos">
                <tr>
                    <th>Nombre</th>
                    <th>Categoria</th>
                    <th>Precio</th>
                    <th>Existencias</th>
                    <th>Accion</th>
                </tr>
                <form action="agregarProducto.php" method="post">
                    <tr>
                        <td><input type="text" name="nombre" required></td>
                        <td><input type="text" name="categoria" required></td>
                        <td><input type="text" name="precio" required></td>
                        <td><input type="text" name="existencias" required></td>
                        <td><input type="submit" value="Agregar" required></td>
                    </tr>
                
            </table>
            </form>
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