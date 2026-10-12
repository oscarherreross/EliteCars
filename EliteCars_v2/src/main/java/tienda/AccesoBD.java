package tienda;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class AccesoBD {
	private static AccesoBD instanciaUnica = null;
	private Connection conexionBD = null;

	public static AccesoBD getInstance(){
		if (instanciaUnica == null){
			instanciaUnica = new AccesoBD();
		}
		return instanciaUnica;
	}

	private AccesoBD() {
		abrirConexionBD();
	}

	public void abrirConexionBD() {
		if (conexionBD == null)
		{
			String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
			// daw es el nombre de la base de datos que hemos creado con anterioridad.
			String DB_URL = "jdbc:mariadb://localhost:3306/daw";
			// El usuario root y su clave son los que se puso al instalar MariaDB.
			String USER = "root";
			String PASS = "TU_CLAVE_BD";
			try {
				Class.forName(JDBC_DRIVER);
				conexionBD = DriverManager.getConnection(DB_URL,USER,PASS);
			}
			catch(Exception e) {
				System.err.println("No se ha podido conectar a la base de datos");
				System.err.println(e.getMessage());
                e.printStackTrace();
			}
		}
	}

	public boolean comprobarAcceso() {
		abrirConexionBD();
		return (conexionBD != null);
	}

	public List<ProductoBD> obtenerProductosBD() {
		abrirConexionBD();
	
		ArrayList<ProductoBD> productos = new ArrayList<>();
	
		try {
			// hay que tener en cuenta las columnas de la tabla de productos
			String con = "SELECT codigo,descripcion,categoria,precio,existencias,imagen FROM productos";
			Statement s = conexionBD.createStatement();
			ResultSet resultado = s.executeQuery(con);
			while(resultado.next()){
				ProductoBD producto = new ProductoBD();
				producto.setCodigo(resultado.getInt("codigo"));
				producto.setDescripcion(resultado.getString("descripcion"));
				producto.setCategoria(resultado.getString("categoria"));
				producto.setPrecio(resultado.getFloat("precio"));
				producto.setExistencias(resultado.getInt("existencias"));
				producto.setImagen(resultado.getString("imagen"));
				productos.add(producto);
			}
		}
		catch(Exception e) {
			System.err.println("Error ejecutando la consulta a la base de datos");
			System.err.println(e.getMessage());
		}
	
		return productos;
	}

	public List<EstadoBD> obtenerEstadoBD(){
		abrirConexionBD();
		ArrayList<EstadoBD> estados = new ArrayList<>();

		try {
			// hay que tener en cuenta las columnas de la tabla de estado
			String con = "SELECT codigo,descripcion FROM estados";
			Statement s = conexionBD.createStatement();
			ResultSet resultado = s.executeQuery(con);
			while(resultado.next()){
				EstadoBD estado = new EstadoBD();
				estado.setCodigo(resultado.getInt("codigo"));
				estado.setDescripcion(resultado.getString("descripcion"));
				estados.add(estado);
			}
		}
		catch(Exception e) {
			System.err.println("Error ejecutando la consulta a la base de datos");
			System.err.println(e.getMessage());
		}
	
		return estados;
	}

	public List<Pedido> obtenerPedidosBD(){
		abrirConexionBD();
		ArrayList<Pedido> pedido = new ArrayList<>();

		try{
			String con = "SELECT codigo, persona, fecha, importe, estado FROM pedidos";
			Statement s = conexionBD.createStatement();
			ResultSet resultado = s.executeQuery(con);
			while(resultado.next()){
				Pedido ped = new Pedido();
				ped.setCodigo(resultado.getInt("codigo"));
				ped.setPersona(resultado.getInt("persona"));
				ped.setImporte(resultado.getDouble("importe"));
				ped.setEstado(resultado.getInt("estado"));
				pedido.add(ped);
			}
		}catch (Exception e) {
			System.err.println("Error ejecutando la consulta a la base de datos");
			System.err.println(e.getMessage());
		}
		return pedido;
	}

	public List<DetalleBD> obtenerDetallesBD(){
		abrirConexionBD();
		ArrayList<DetalleBD> detalle = new ArrayList<>();

		try{
			String con = "SELECT codigo_pedido, codigo_producto, unidades, precio_unitario FROM detalle";
			Statement s = conexionBD.createStatement();
			ResultSet resultado = s.executeQuery(con);
			while(resultado.next()){
				DetalleBD det = new DetalleBD();
				det.setCodigoPedido(resultado.getInt("codigo_pedido"));
				det.setCodigoProducto(resultado.getInt("codigo_producto"));
				det.setUnidades(resultado.getInt("unidades"));
				det.setPrecioUnitario(resultado.getInt("precio_unitario"));
				detalle.add(det);
			}
		}catch (Exception e) {
			System.err.println("Error ejecutando la consulta a la base de datos");
			System.err.println(e.getMessage());
		}
		return detalle;
	}

	public List<UsuarioBD> obtenerUsuariosBD(){
		abrirConexionBD();
		ArrayList<UsuarioBD> usuarios = new ArrayList<>();
		try {
			String con = "SELECT codigo,activo,admin,usuario,clave,nombre,apellidos,domicilio,poblacion,provincia,cp,telefono,email FROM usuarios";
			Statement s = conexionBD.createStatement();
			ResultSet resultado = s.executeQuery(con);
			while(resultado.next()){
				UsuarioBD usuario = new UsuarioBD();
				usuario.setCodigo(resultado.getInt("codigo"));
				usuario.setActivo(resultado.getInt("activo"));
				usuario.setAdmin(resultado.getInt("admin"));
				usuario.setUsuario(resultado.getString("usuario"));
				usuario.setClave(resultado.getString("clave"));
				usuario.setNombre(resultado.getString("nombre"));
				usuario.setApellidos(resultado.getString("apellidos"));
				usuario.setDomicilio(resultado.getString("domicilio"));
				usuario.setPoblacion(resultado.getString("poblacion"));
				usuario.setProvincia(resultado.getString("provincia"));
				usuario.setCp(resultado.getString("cp"));
				usuario.setTelefono(resultado.getString("telefono"));
				usuario.setEmail(resultado.getString("email"));
				usuarios.add(usuario);
			}
		} 
		catch (Exception e) {
			System.err.println("Error ejecutando la consulta a la base de datos");
			System.err.println(e.getMessage());
		}
		return usuarios;
	}

	public int comprobarUsuarioBD(String usuario, String clave) {
		abrirConexionBD();
	
		int codigo = -1;
	
		try{
			String con = "SELECT codigo FROM usuarios WHERE usuario=? AND clave=?";
			PreparedStatement s = conexionBD.prepareStatement(con);
			s.setString(1,usuario);
			s.setString(2,clave);
	
			ResultSet resultado = s.executeQuery();
	
			// El usuario/clave se encuentra en la BD
	
			if ( resultado.next() ) {
				codigo =  resultado.getInt("codigo");
			}
		}
		catch(Exception e) {
	
			// Error en la conexión con la BD
			System.err.println("Error verificando usuario/clave");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	
		return codigo;
	}

	public boolean agregarUsuario(String usuario, String clave, String nombre, String apellidos, String domicilio, String poblacion, String provincia, String cp, String telefono, String email) {
		abrirConexionBD();
	
		boolean registroExitoso = false;
	
		try {
			String sql = "INSERT INTO usuarios (activo, admin, usuario, clave, nombre, apellidos, domicilio, poblacion, provincia, cp, telefono, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conexionBD.prepareStatement(sql);
	
			pstmt.setInt(1, 1);  // activo
			pstmt.setInt(2, 0);  // admin
			pstmt.setString(3, usuario);
			pstmt.setString(4, clave); 
			pstmt.setString(5, nombre);
			pstmt.setString(6, apellidos);
			pstmt.setString(7, domicilio);
			pstmt.setString(8, poblacion);
			pstmt.setString(9, provincia);
			pstmt.setString(10, cp);
			pstmt.setString(11, telefono);
			pstmt.setString(12, email);

	
			int filasAfectadas = pstmt.executeUpdate();
	
			registroExitoso = filasAfectadas > 0;
	
		} catch (SQLException ex) {
			System.err.println("Error al agregar usuario");
			System.err.println(ex.getMessage());
			ex.printStackTrace();
		}
	
		return registroExitoso;
	}	

	public boolean actualizarUsuario(String usuario, String clave, String nombre, String apellidos, String domicilio, String poblacion, String provincia, String cp, String telefono, String email) {
		abrirConexionBD();
	
		boolean actualizacionExitosa = false;
	
		try {
			String sql = "UPDATE usuarios SET clave = ?, nombre = ?, apellidos = ?, domicilio = ?, poblacion = ?, provincia = ?, cp = ?, telefono = ?, email = ? WHERE codigo = ?";
			PreparedStatement pstmt = conexionBD.prepareStatement(sql);
	
			pstmt.setString(1, clave); 
			pstmt.setString(2, nombre);
			pstmt.setString(3, apellidos);
			pstmt.setString(4, domicilio);
			pstmt.setString(5, poblacion);
			pstmt.setString(6, provincia);
			pstmt.setString(7, cp);
			pstmt.setString(8, telefono);
			pstmt.setString(9, email);
			pstmt.setString(10, usuario);
	
			int filasAfectadas = pstmt.executeUpdate();
	
			actualizacionExitosa = filasAfectadas > 0;
	
		} catch (SQLException ex) {
			System.err.println("Error al actualizar usuario");
			System.err.println(ex.getMessage());
			ex.printStackTrace();
		}
	
		return actualizacionExitosa;
	}

	public boolean actualizarEstadoPedido(int codigoPedido, int nuevoEstado){
		abrirConexionBD();
	
		boolean actualizacionExitosa = false;

		try {
			String sql = "UPDATE pedidos SET estado = ? WHERE codigo = ?";
			PreparedStatement pstmt = conexionBD.prepareStatement(sql);
	
			pstmt.setInt(1, nuevoEstado); 
			pstmt.setInt(2, codigoPedido);
	
			int filasAfectadas = pstmt.executeUpdate();
	
			actualizacionExitosa = filasAfectadas > 0;
	
		} catch (SQLException ex) {
			System.err.println("Error al actualizar usuario");
			System.err.println(ex.getMessage());
			ex.printStackTrace();
		}
	
		return actualizacionExitosa;
	}

	public int obtenerIdUsuario(String nombreUsuario) {
		abrirConexionBD();
	
		int idUsuario = -1;
	
		try {
			String sql = "SELECT codigo FROM usuarios WHERE usuario = ?";
			PreparedStatement pstmt = conexionBD.prepareStatement(sql);
	
			pstmt.setString(1, nombreUsuario);
	
			ResultSet rs = pstmt.executeQuery();
	
			if (rs.next()) {
				idUsuario = rs.getInt("codigo");
			}
	
		} catch (SQLException ex) {
			System.err.println("Error al obtener id del usuario");
			System.err.println(ex.getMessage());
			ex.printStackTrace();
		}
	
		return idUsuario;
	}

	public int obtenerIdPedidoActual(int idUsuario, double importe, int estado){
		abrirConexionBD();
		int idPedido = 0;

		try {
			String sql = "SELECT codigo FROM pedidos WHERE persona = ? AND importe = ? AND estado = ?";
			PreparedStatement pstmt = conexionBD.prepareStatement(sql);
	
			pstmt.setInt(1, idUsuario);
			pstmt.setDouble(2, importe);
			pstmt.setInt(3, estado);
	
			ResultSet rs = pstmt.executeQuery();
	
			if (rs.next()) {
				idPedido = rs.getInt("codigo");
			}
	
		} catch (SQLException ex) {
			System.err.println("Error al obtener id del pedido");
			System.err.println(ex.getMessage());
			ex.printStackTrace();
		}
		return idPedido;
	}
	

	public boolean agregarPedido(Pedido pedido) {
		abrirConexionBD();

        try {
			String sql = "INSERT INTO pedidos(persona, fecha, importe, estado) VALUES (?, CURRENT_TIMESTAMP, ?, ?)";
            PreparedStatement ps = conexionBD.prepareStatement(sql);
            ps.setInt(1, pedido.getPersona());
        	ps.setDouble(2, pedido.getImporte());
        	ps.setInt(3, pedido.getEstado());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	public boolean agregarDetalle(int codigoPedido, int codigoProducto, int unidades, float precioUnitario){
		abrirConexionBD();

		try {
			String sql = "INSERT INTO detalle(codigo_pedido, codigo_producto, unidades, precio_unitario) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conexionBD.prepareStatement(sql);
            ps.setInt(1, codigoPedido);
        	ps.setInt(2, codigoProducto);
        	ps.setInt(3, unidades);
			ps.setFloat(4, precioUnitario);

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
	}
//--------------------
	public boolean retirarExistencias(Producto p) {
		abrirConexionBD();
	
		boolean resultado = false;
		int codigoProducto = p.getCodigo();
		int cantidad = 1;
	
		try {
			String sql = "UPDATE productos SET existencias = existencias - ? WHERE codigo = ?";
			PreparedStatement pstmt = conexionBD.prepareStatement(sql);
	
			pstmt.setInt(1, cantidad);
			pstmt.setInt(2, codigoProducto);
	
			int filasAfectadas = pstmt.executeUpdate();
	
			resultado = filasAfectadas > 0;
		} catch (SQLException e) {
			System.err.println("Error actualizando existencias");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	
		return resultado;
	}

	public boolean aumentarExistencias(Producto p) {
		abrirConexionBD();
	
		boolean resultado = false;
		int codigoProducto = p.getCodigo();
		int cantidad = 1;
	
		try {
			String sql = "UPDATE productos SET existencias = existencias + ? WHERE codigo = ?";
			PreparedStatement pstmt = conexionBD.prepareStatement(sql);
	
			pstmt.setInt(1, cantidad);
			pstmt.setInt(2, codigoProducto);
	
			int filasAfectadas = pstmt.executeUpdate();
	
			resultado = filasAfectadas > 0;
		} catch (SQLException e) {
			System.err.println("Error actualizando existencias");
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	
		return resultado;
	}
//--------------------


}
