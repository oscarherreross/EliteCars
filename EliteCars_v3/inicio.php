<?php 
    require_once('baseDatos.php'); 
    session_start();

    if(isset($_SESSION['username'])){
?>
<!DOCTYPE html>
<html lang="es">
<head>
   <title>Pagina de inicio</title>
   <link rel="stylesheet" type="text/css" href="./Inicio_EliteCars.css">
   <style>
       /* Añadido CSS para cambiar el color de la cabecera a negro */
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
    <table class="tabla-inicio">
            <tr>
                <th>ID</th>
                <th>Activo</th>
                <th>Admin</th>
                <th>Usuario</th>
                <th>Contrasena</th>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Domicilio</th>
                <th>Poblacion</th>
                <th>Provincia</th>
                <th>C.P</th>
                <th>Telefono</th>
                <th>Email</th>
                <th></th>
                <th></th>

            </tr>
            <?php
            $resultados = obtenerUsuarios();
            while ($fila = mysqli_fetch_row($resultados)) {
            ?>
            <tr>
                <form method="post" action="actualizar.php">
                    <input type="hidden" name="id" value="<?php echo $fila[0];?>">
                    <td><?php echo $fila[0];?></td>
                    <input type="hidden" name="activo" value="<?php echo $fila[1];?>">
                    <td><?php echo $fila[1];?></td>
                    <td><input type="text" name="admin" value="<?php echo $fila[2];?>" style="width: 30px;"></td>
                    <td><?php echo $fila[3];?></td>
                    <td><?php echo $fila[4];?></td>
                    <td><input type="text" name="nombre" value="<?php echo $fila[5];?>" style="width: 60px;"></td>
                    <td><input type="text" name="apellidos" value="<?php echo $fila[6];?>" style="width: 120px;"></td>
                    <td><input type="text" name="domicilio" value="<?php echo $fila[7];?>" style="width: 80px;"></td>
                    <td><input type="text" name="poblacion" value="<?php echo $fila[8];?>" style="width: 80px;"></td>
                    <td><input type="text" name="provincia" value="<?php echo $fila[9];?>" style="width: 80px;"></td>
                    <td><input type="text" name="cp" value="<?php echo $fila[10];?>" style="width: 50px;"></td>
                    <td><input type="text" name="telefono" value="<?php echo $fila[11];?>" style="width: 80px;"></td>
                    <td><input type="text" name="email" value="<?php echo $fila[12];?>"></td>
                    <td><input type="submit" value="Actualizar"></td>
                </form>
                <?php
                    if($fila[1] == 0){
                        //Sacar boton DESACTIVAR
                ?>
                    <form action="activarUsuario.php" method="post">
                        <input type="hidden" name="id" value="<?php echo $fila[0];?>">
                        <td><input type="submit" value="Activar"></td>
                    </form>
                <?php
                    }else if ($fila[1] == 1){
                        //Sacar boton ACTIVAR
                ?>
                    <form action="desactivarUsuario.php" method="post">
                        <input type="hidden" name="id" value="<?php echo $fila[0];?>">
                        <td><input type="submit" value="Desactivar"></td>
                    </form>
                <?php
                    }
                ?>
            </tr>
            <?php
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