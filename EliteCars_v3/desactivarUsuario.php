<?php
require_once('baseDatos.php');

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Comprueba si 'id' está en $_POST
    if (isset($_POST['id'])) {
        $id = $_POST['id'];

        if(desactivarUsuario($id)){
            header("Location: inicio.php");
        } else {
            // Hacer algo si la desactivación del usuario falla
        }
    } else {
        // Hacer algo si 'id' no está en $_POST
    }
}

?>
