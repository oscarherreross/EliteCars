<?php
require_once('baseDatos.php');

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Comprueba si todos los parámetros están en $_POST
    if (isset($_POST['nombre'], $_POST['categoria'], $_POST['precio'], $_POST['existencias'])) {
        $nombre = $_POST['nombre'];
        $categoria = $_POST['categoria'];
        $precio = $_POST['precio'];
        $existencias = $_POST['existencias'];

        if(agregarProducto($nombre,$categoria,$precio,$existencias)){
            header("Location: productos.php?agregado=true");
        } else {
            // Hacer algo si la adición del producto falla
        }
    } else {
        // Hacer algo si no todos los parámetros están en $_POST
    }
}

?>