package tienda;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Registro extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
    ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        String domicilio = request.getParameter("domicilio");
        String poblacion = request.getParameter("poblacion");
        String provincia = request.getParameter("provincia");
        String cp = request.getParameter("cp");

        // URL a la que debemos volver (parametro hidden del formulario de registro)
	    String url = request.getParameter("url");

        // Accedemos al entorno de sesión y si no está creado lo creamos
        HttpSession session = request.getSession(true);

        AccesoBD con = AccesoBD.getInstance();

        int codigo = -1;
        boolean agregado = con.agregarUsuario(usuario, clave, nombre, apellidos, domicilio, 
        poblacion, provincia, cp, telefono, email);

        if (agregado){
            response.sendRedirect(url);
        }else{
            session.setAttribute("mensaje","No se ha podido agregar ni actualizar el usuario correctamente");
        }
        //response.sendRedirect(url);
    }
}