package tienda;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Logout extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtén el objeto de sesión
        HttpSession session = request.getSession(false);

        // Comprueba si hay una sesión
        if(session != null){
            // Invalida la sesión
            session.invalidate();
        }

        // Redirige al usuario a la página de inicio de sesión
        response.sendRedirect("FicherosJSP/LoginUsuario.jsp");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtén el objeto de sesión
        HttpSession session = request.getSession(false);

        // Comprueba si hay una sesión
        if(session != null){
            // Invalida la sesión
            session.invalidate();
        }

        // Redirige al usuario a la página de inicio de sesión
        response.sendRedirect("FicherosJSP/LoginUsuario.jsp");
    }
}
