package tienda;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CancelarPedido extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccesoBD con = AccesoBD.getInstance();

        int pedidoId = Integer.parseInt(request.getParameter("pedidoId"));

        con.actualizarEstadoPedido(pedidoId,4);



        // Redirige al usuario de vuelta a la página original (o a donde quieras)
        response.sendRedirect("FicherosJSP/UsuarioIniciado.jsp");
    }
}
