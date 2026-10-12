<?php
require_once('baseDatos.php');

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Comprueba si todos los parámetros están en $_POST
    if (isset($_POST['id'], $_POST['nombre'], $_POST['categoria'], $_POST['precio'], $_POST['existencias'])) {
        $id = $_POST['id'];
        $nombre = $_POST['nombre'];
        $categoria = $_POST['categoria'];
        $precio = $_POST['precio'];
        $existencias = $_POST['existencias'];

        if(actualizarProducto($id,$nombre,$categoria,$precio,$existencias)){
            header("Location: productos.php?actualizado=true");
        } else {
            // Hacer algo si la actualización del producto falla
        }
    } else {
        // Hacer algo si no todos los parámetros están en $_POST
    }
}

?>