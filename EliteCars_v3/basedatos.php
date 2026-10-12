<?php
    function abrirConexionBD(){
        $recurso=mysqli_connect("localhost","root","TU_CLAVE_BD","daw");
        if(mysqli_connect_error()){
            printf("Error conectando con la base de datos: %\n", mysqli_connect_error());
            return false;
        }
        $recurso->set_charset("utf8");
        return $recurso;
    }

    function cerrarConexion($bbdd) {
        mysqli_close($bbdd);
    }

    function obtenerProductos(){
        $recurso = abrirConexionBD();

        if($recurso){
            $consulta = "SELECT * FROM productos;";
            $resultados = mysqli_query($recurso,$consulta);
            cerrarConexion($recurso);

            return $resultados;
        }
    }

    function obtenerProductosPedido($id){
        $recurso = abrirConexionBD();

        if($recurso){
            $consulta = "SELECT * FROM productos WHERE codigo = $id;";
            $resultados = mysqli_query($recurso,$consulta);
            cerrarConexion($recurso);

            return $resultados;
        }
    }

    function obtenerUsuarios(){
        $recurso = abrirConexionBD();

        if($recurso){
            $consulta = "SELECT * FROM usuarios;";
            $resultados = mysqli_query($recurso,$consulta);
            cerrarConexion($recurso);

            return $resultados;
        }
    }

    function obtenerPedidos($usuario = null, $fecha = null){
        $recurso = abrirConexionBD();
    
        if($recurso){
            // Crea la consulta SQL
            $consulta = "SELECT * FROM pedidos";
    
            // Añade la condición del filtro de fecha a la consulta SQL
            if ($fecha) {
                $consulta .= " WHERE fecha = '$fecha'";
            }

            if($usuario){
                $consulta .= " WHERE persona = $usuario";
            }
    
            $consulta .= ";";
            $resultados = mysqli_query($recurso,$consulta);
            cerrarConexion($recurso);
    
            return $resultados;
        }
    }
    

    function obtenerDetallesPedido($idpedido, $producto = null){
        $recurso = abrirConexionBD();
    
        if($recurso){
            // Crea la consulta SQL
            $consulta = "SELECT * FROM detalle WHERE codigo_pedido = $idpedido";
    
            // Añade la condición del filtro de producto a la consulta SQL
            if ($producto) {
                $consulta .= " AND codigo_producto = '$producto'";
            }
    
            $consulta .= ";";
            $resultados = mysqli_query($recurso,$consulta);
            cerrarConexion($recurso);
    
            return $resultados;
        }
    }
    

    function obtenerEstadoPedidos($estado){
        $recurso = abrirConexionBD();

        if($recurso){
            $consulta = "SELECT * FROM estados WHERE codigo = $estado;";
            $resultados = mysqli_query($recurso,$consulta);
            cerrarConexion($recurso);

            return $resultados;
        }
    }

    function actualizarUsuario($id, $activo, $admin, $nombre, $apellidos, $domicilio, $poblacion, $provincia, $cp, $telefono, $email){
        $recurso = abrirConexionBD();
        $actualizado = false;
    
        if($recurso){
            $actualizado = "UPDATE `usuarios` SET `activo` = '$activo', `admin` = '$admin', `nombre` = '$nombre', `apellidos` = '$apellidos', `domicilio` = '$domicilio', `poblacion` = '$poblacion', `provincia` = '$provincia', `cp` = '$cp', `telefono` = '$telefono', `email` = '$email' WHERE `codigo` = '$id';";
            //echo $actualizado;
            if(mysqli_query($recurso, $actualizado)){
                $actualizado = true;
            } else {
                echo "Error: " . mysqli_error($recurso);
            }
            cerrarConexion($recurso);
        }
        return $actualizado;
    }

    function actualizarProducto($id,$nombre,$categoria,$precio,$existencias){
        $recurso = abrirConexionBD();
        $actualizado = false;
    
        if($recurso){
            $actualizado = "UPDATE `productos` SET `descripcion` = '$nombre', `categoria` = '$categoria', `precio` = '$precio', `existencias` = '$existencias' WHERE `codigo` = '$id';";
            //echo $actualizado;
            if(mysqli_query($recurso, $actualizado)){
                $actualizado = true;
            } else {
                echo "Error: " . mysqli_error($recurso);
            }
            cerrarConexion($recurso);
        }
        return $actualizado;
    }

    function actualizarEstadoPedido($idPedido,$opcionSeleccionada){
        $recurso = abrirConexionBD();
        $actualizado = false;

        if($opcionSeleccionada == "pendiente"){
            $opcionActualizada = 1;
        }else if ($opcionSeleccionada == "cancelado"){
            $opcionActualizada = 4;
        }else if ($opcionSeleccionada == "enviado"){
            $opcionActualizada = 2;
        }else if ($opcionSeleccionada == "entregado"){
            $opcionActualizada = 3;
        }
    
        if($recurso){
            $actualizado = "UPDATE `pedidos` SET `estado` = '$opcionActualizada' WHERE `codigo` = '$idPedido';";
            //echo $actualizado;
            if(mysqli_query($recurso, $actualizado)){
                $actualizado = true;
            } else {
                echo "Error: " . mysqli_error($recurso);
            }
            cerrarConexion($recurso);
        }
        return $actualizado;
    }

    function agregarProducto($nombre,$categoria,$precio,$existencias){
        $recurso = abrirConexionBD();
        $actualizado = false;
    
        if($recurso){
            $actualizado = "INSERT INTO `productos` SET `descripcion` = '$nombre', `categoria` = '$categoria', `precio` = '$precio', `existencias` = '$existencias';";
            //echo $actualizado;
            if(mysqli_query($recurso, $actualizado)){
                $actualizado = true;
            } else {
                echo "Error: " . mysqli_error($recurso);
            }
            cerrarConexion($recurso);
        }
        return $actualizado;
    }

    function activarUsuario($id){
        $recurso = abrirConexionBD();
        $actualizado = false;

        if($recurso){
            $actualizado = "UPDATE `usuarios` SET `activo` = 1 WHERE `codigo` = '$id';";
            //echo $actualizado;
            if(mysqli_query($recurso, $actualizado)){
                $actualizado = true;
            } else {
                echo "Error: " . mysqli_error($recurso);
            }
            cerrarConexion($recurso);
        }
        return $actualizado;
    }

    function desactivarUsuario($id){
        $recurso = abrirConexionBD();
        $actualizado = false;

        if($recurso){
            $actualizado = "UPDATE `usuarios` SET `activo` = 0 WHERE `codigo` = '$id';";
            //echo $actualizado;
            if(mysqli_query($recurso, $actualizado)){
                $actualizado = true;
            } else {
                echo "Error: " . mysqli_error($recurso);
            }
            cerrarConexion($recurso);
        }
        return $actualizado;
    }
?>
