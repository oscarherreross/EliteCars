<?php
// Iniciar la sesión
session_start(); 
include('basedatos.php');

// Comprobar si el formulario ha sido enviado
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Comprueba si 'username' y 'password' están en $_POST
    if (isset($_POST['username'], $_POST['password'])) {
        $username = $_POST["username"];
        $password = $_POST["password"];

        // Conectar a la base de datos
        $mysqli = mysqli_connect("localhost", "root", "TU_CLAVE_BD", "daw");

        if (mysqli_connect_error()) {
            printf("Error conectando a la base de datos: %s\n",mysqli_connect_error());
            exit();
        }

        if ($resultado = mysqli_query($mysqli, 'SELECT * FROM usuarios;')) {
            while ($fila = mysqli_fetch_row($resultado)) {
                if($fila[3] == $username && $fila[4] == $password && $fila[2] == 1){
                    $_SESSION["username"] = $username;
                    header("Location: inicio.php");
                } else {
                    $error = "Nombre de usuario o contraseña incorrectos";
                }
            }
            
            mysqli_free_result($resultado);
        }
        mysqli_close($mysqli);
    } else {
        // Hacer algo si 'username' o 'password' no están en $_POST
    }
}

?>

<!DOCTYPE html>
<html>
<head>
    <title>Iniciar sesión</title>
    <link rel="stylesheet" type="text/css" href="Inicio_EliteCars.css">
    <style>
form {
    max-width: 500px;
}
   </style>
</head>
<body>
    <?php
    if (!empty($error)) {
        echo "<div class='error'><h1>$error</h1></div>";
    }
    ?>
    <br>
    <br>
    <form action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]); ?>" method="post">
        <label for="username">Nombre de usuario:</label><br>
        <input type="text" id="username" name="username" required><br>
        <label for="password">Contraseña:</label><br>
        <input type="password" id="password" name="password" required><br>
        <input type="submit" value="Iniciar sesión">
    </form>
</body>
</html>
