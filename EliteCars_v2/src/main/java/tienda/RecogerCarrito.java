package tienda;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class RecogerCarrito extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList <Producto> carritoJSON = new ArrayList<Producto>();

        //es el código que nos permitiría convertir la cadena JSON que contiene el carrito en un vector de elementos de la clase Producto, 
        //procesando cadenas JSON con el formato del Listado 30.
        JsonReader jsonReader = Json.createReader(new InputStreamReader(request.getInputStream(),"utf-8"));

        JsonArray jobj = jsonReader.readArray();

        for (int i = 0; i < jobj.size(); i++) {
            JsonObject prod = jobj.getJsonObject(i);
            Producto nuevo = new Producto();
            nuevo.setCodigo(prod.getInt("id"));
            nuevo.setNombre(prod.getString("nombre"));
            nuevo.setPrecio((int) Float.parseFloat(prod.get("precio").toString()));
            nuevo.setCantidad(prod.getInt("cantidad"));//cantidad seleccionada
            nuevo.setExistencias(prod.getInt("existencias"));
            //carritoJSON.add(nuevo);
            
            if(comprobarExistencias(nuevo)){//comprueba si se ha seleccionado una cantidad de productos superior a la existente
                carritoJSON.add(nuevo);
            }else{
                //alert("El producto " + nuevo.getNombre() + " no ha podido ser añadido al carrito porque solo disponemos de " + getExistenciasProducto(nuevo) + "existencias.");
            }
        }

        // Procesa el carrito
        int resultado = procesarCarrito(carritoJSON);

        // Almacena el carrito y el resultado en el objeto request
        request.setAttribute("carrito", carritoJSON);
        request.setAttribute("resultado", resultado);

        // Almacena el carrito y el resultado en la sesión
        HttpSession session = request.getSession();
        session.setAttribute("carrito", carritoJSON);
        session.setAttribute("resultado", resultado);

        // Envía el resultado al cliente
        //response.setContentType("text/plain");
        //response.setCharacterEncoding("UTF-8");
        //response.getWriter().write(resultado);



        response.sendRedirect("FicherosJSP/Resguardo.jsp");
    }


    public int procesarCarrito(ArrayList<Producto> carrito) {
        int total = 0;

        // Itera sobre cada producto en el carrito
        for (Producto producto : carrito) {
            // Añade el precio del producto al total
            total += producto.getPrecio() * producto.getCantidad();
        }

        // Devuelve el total del carrito como una cadena
        return total;
    }

//---------------------

    public boolean comprobarExistencias(Producto p){
		boolean compra;

		//si la cantidad seleccionada es mayor a las existencias, NO se realiza la compra
		if(p.getCantidad() > getExistenciasProducto(p)){
			compra=false;
		}else{
			compra=true;
		}

		return compra;
	}

	public int getExistenciasProducto(Producto p){
		int existencias = -1;
        AccesoBD con=AccesoBD.getInstance();
		List<ProductoBD> listaProductos = con.obtenerProductosBD();

		for(ProductoBD producto : listaProductos){
			if(producto.getCodigo() == p.getCodigo()){
				existencias = producto.getExistencias();
			}
		}
		return existencias;
	}
//---------------------

}

