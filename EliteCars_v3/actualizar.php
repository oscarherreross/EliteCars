<?php
require_once('baseDatos.php');

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Comprueba si todos los parámetros están en $_POST
    if (isset($_POST['id'], $_POST['activo'], $_POST['admin'], $_POST['nombre'], $_POST['apellidos'], $_POST['domicilio'], $_POST['poblacion'], $_POST['provincia'], $_POST['cp'], $_POST['telefono'], $_POST['email'])) {
        $id = $_POST['id'];
        $activo = $_POST['activo'];
        $admin = $_POST['admin'];
        $nombre = $_POST['nombre'];
        $apellidos = $_POST['apellidos'];
        $domicilio = $_POST['domicilio'];
        $poblacion = $_POST['poblacion'];
        $provincia = $_POST['provincia'];
        $cp = $_POST['cp'];
        $telefono = $_POST['telefono'];
        $email = $_POST['email'];

        if(actualizarUsuario($id,$activo,$admin,$nombre,$apellidos,$domicilio,$poblacion,$provincia,$cp,$telefono,$email)){
            header("Location: inicio.php");
        } else {
            // Hacer algo si la actualización del usuario falla
        }
    } else {
        // Hacer algo si no todos los parámetros están en $_POST
    }
}

?>