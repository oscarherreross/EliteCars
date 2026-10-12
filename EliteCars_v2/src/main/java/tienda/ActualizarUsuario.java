package tienda;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ActualizarUsuario extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
    ServletException, IOException{
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String usuario = request.getParameter("codigo");
        String clave = request.getParameter("clave");
        String domicilio = request.getParameter("domicilio");
        String poblacion = request.getParameter("poblacion");
        String provincia = request.getParameter("provincia");
        String cp = request.getParameter("cp");

        AccesoBD con = AccesoBD.getInstance();
        boolean actualizado = con.actualizarUsuario(usuario, clave, nombre, apellidos, domicilio, poblacion, provincia, cp, telefono, email);

        HttpSession session = request.getSession(true);
        if (actualizado) {
            session.setAttribute("mensaje","La información del usuario ha sido actualizada correctamente");
        } else {
            session.setAttribute("mensaje","No se ha podido agregar ni actualizar el usuario correctamente");
        }
        response.sendRedirect("FicherosJSP/UsuarioIniciado.jsp");
    }
}
