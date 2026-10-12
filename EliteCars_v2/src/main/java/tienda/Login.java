package tienda;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Login extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Nombre del usuario
		String usuario = request.getParameter("usuario");
		// Clave
		String clave = request.getParameter("clave");
		// URL a la que debemos volver
		String url = request.getParameter("url");
	
		// Accedemos al entorno de sesión y si no está creado lo creamos
		HttpSession session = request.getSession(true);
		AccesoBD con = AccesoBD.getInstance();
	
		if ((usuario != null) && (clave != null)) {
			int codigo = con.comprobarUsuarioBD(usuario,clave);
			//System.out.println("Codigo: " + codigo);
			//System.out.println("Clave: " + clave);
			//System.out.println("Usuario: " + usuario);
			if (codigo>0) {
				//Codigo del usuario que inicia sesion
				session.setAttribute("usuario",usuario);
				//Nombre con el que el usuario inicia sesion
				session.setAttribute("apodo",codigo);
				//Contrasenya del usuario que inicia sesion
				session.setAttribute("clave",codigo);
				//Mensaje sobre la operacion que se realiza
				session.setAttribute("mensaje","Acceso permitido");

				//System.out.println("Usuario en sesion: " + session.getAttribute("usuario"));
    			//System.out.println("Mensaje en sesion: " + session.getAttribute("mensaje"));
				
				response.sendRedirect(url);
			}else {
				session.setAttribute("mensaje","Usuario y/o clave incorrectos");
				response.sendRedirect("FicherosJSP/LoginUsuario.jsp");
			}
		}
	}
}

