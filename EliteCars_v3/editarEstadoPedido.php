<?php
require_once('baseDatos.php');

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Comprueba si 'opciones' e 'id' están en $_POST
    if (isset($_POST['opciones'], $_POST['id'])) {
        $opcionSeleccionada = $_POST["opciones"];
        $idPedido = $_POST["id"];

        if(actualizarEstadoPedido($idPedido,$opcionSeleccionada)){
            header("Location: pedidos.php");
        } else {
            // Hacer algo si la actualización del estado del pedido falla
        }
    } else {
        // Hacer algo si 'opciones' o 'id' no están en $_POST
    }
}

?>