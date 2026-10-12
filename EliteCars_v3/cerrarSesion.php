<?php
// Iniciar la sesión
session_start();
//Eliminación de variables
session_unset();
// Destruir la sesión.
session_destroy();

header("Location: identificacion.php"); // Redirigir a la página de inicio de sesión
?>
