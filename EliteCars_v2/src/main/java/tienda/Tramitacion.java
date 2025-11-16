package tienda;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Tramitacion extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccesoBD con = AccesoBD.getInstance();

        /*
         * SECCION DE LOS PEDIDOS
         */
        // Recoge los datos del pedido de la solicitud
        int identificador = con.obtenerIdUsuario((String) session.getAttribute("usuario"));//Devuelve -1 si no se encuentra
        //para la fecha hay que meterla directamente en la consulta como Now()
        double importe = (double) ((Integer) session.getAttribute("resultado"));
        int estado = 1;

        // Crea un nuevo objeto Pedido con los datos recogidos
        Pedido pedido = new Pedido();
        pedido.setPersona(identificador);
        pedido.setImporte(importe);
        pedido.setEstado(estado);

        // Usa la función agregarPedido para guardar el pedido en la base de datos
        boolean exito = con.agregarPedido(pedido);

        /*
         * SECCION DE DETALLES
         */
        List<Producto> carrito = (List<Producto>) session.getAttribute("carrito");
        int codigoPedido = con.obtenerIdPedidoActual(identificador, importe, estado);

        for (Producto producto : carrito){
            producto.getCodigo();
            producto.getCantidad();
            producto.getPrecio();

            con.agregarDetalle(codigoPedido, producto.getCodigo(), producto.getCantidad(), (float) producto.getPrecio());
        }

        //-------------------------------
        if (exito) {
            //Todo ha ido bien
            session.removeAttribute("carrito");
            response.sendRedirect("FicherosJSP/PedidoFinalizado.jsp"); // Redirige a una página de éxito
        } else {
            //No se ha podido hacer la compra
            //response.sendRedirect("FicherosHTML/Empresa_EliteCars.html"); // Redirige a una página de error
        }
    }
}
