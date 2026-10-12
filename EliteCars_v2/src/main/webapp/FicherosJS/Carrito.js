var carrito = null; // Array que contendrá los objetos que son los productos

/* 
  Función para añadir productos al carrito
*/
function añadirProducto(id, nombre, precio, cantidad, existencias) {
  cargarCarrito();

  var productoExistente = null;

  for (var i = 0; i < carrito.length; i++) {
    if (carrito[i].id === id) {
        productoExistente = carrito[i];
    }
  }

  if (productoExistente) {
    if(productoExistente.existencias > 0){
      productoExistente.cantidad += 1;
      productoExistente.existencias -= 1;

      console.log(productoExistente.nombre + " ya existe en el carrito. Las existencias se han incrementado en 1.");
      alert(productoExistente.nombre + " ya existe en el carrito. Las existencias se han incrementado en 1.");
    }else{
      console.log("No tenemos mas existencias disponibles del producto " + productoExistente.nombre);
      alert("No tenemos mas existencias disponibles del producto " + productoExistente.nombre);
    }
  } else {
    var producto = new Object();
    producto.id = id;
    producto.nombre = nombre;
    producto.precio = precio;
    producto.cantidad = cantidad;
    producto.existencias = existencias;

    producto.existencias -= 1;

    carrito.push(producto);
    console.log(producto.nombre + " ha sido añadido al carrito.");
    alert(producto.nombre + " ha sido añadido al carrito.");
  }

  guardarCarrito();
}

/*
  Funcion para eliminar productos del carrito
*/
function eliminarProducto(producto) {
  cargarCarrito();
  let index = -1;

  for (let i = 0; i < carrito.length; i++) {
    if (carrito[i].nombre === producto.nombre) {
      index = i;
      break;
    }
  }

  if (index > -1) {
    if(carrito[index].cantidad <= 1){
      carrito[index].existencias += 1;
      carrito.splice(index, 1);
      console.log(producto.nombre + " ha sido eliminado del carrito.");
      alert(producto.nombre + " ha sido eliminado del carrito.");
    }
    else{
      carrito[index].cantidad -= 1;
      carrito[index].existencias += 1;
      console.log("Una unidad de " + producto.nombre + " ha sido eliminada del carrito.");
      alert("Una unidad de " + producto.nombre + " ha sido eliminada del carrito.");
    }
    // Actualiza el carrito en tiempo real después de cada operación de eliminación
    guardarCarrito();
  } else {
    console.log("El producto " + producto.nombre + " no se encuentra en el carrito.");
    alert("El producto " + producto.nombre + " no se encuentra en el carrito.");
  }
}

function aumentarCantidad(producto){
  cargarCarrito();

  for (let i = 0; i < carrito.length; i++) {
    if (carrito[i].nombre === producto.nombre) {
      index = i;
      break;
    }
  }

  if (index > -1) {
    if(carrito[index].existencias > 0){//Si ya no hay mas existencias para añadir
      carrito[index].cantidad += 1;
      carrito[index].existencias -= 1;
      console.log("Una unidad de " + producto.nombre + " ha sido añadida del carrito.");
      alert("Una unidad de " + producto.nombre + " ha sido añadida del carrito.");
      guardarCarrito();
    }else{
      console.log("El producto " + producto.nombre + " no tiene mas existencias.");
      alert("El producto " + producto.nombre + " no tiene mas existencias.");
    }

  }else {
      console.log("El producto " + producto.nombre + " no se encuentra en el carrito.");
      alert("El producto " + producto.nombre + " no se encuentra en el carrito.");
    }
}

function eliminarProductoCompleto(producto){
  cargarCarrito();

  for (let i = 0; i < carrito.length; i++) {
    if (carrito[i].nombre === producto.nombre) {
      index = i;
      break;
    }
  }

  if (index > -1) {
    carrito[index].existencias += carrito[index].cantidad;
    carrito.splice(index, 1);
    console.log(producto.nombre + " ha sido eliminado del carrito.");
    alert(producto.nombre + " ha sido eliminado del carrito.");
    guardarCarrito();
  }else {
      console.log("El producto " + producto.nombre + " no se encuentra en el carrito.");
      alert("El producto " + producto.nombre + " no se encuentra en el carrito.");
    }
}



/*
  Funcion para mostrar los productos del carrito
*/
function listarProductos() {

  cargarCarrito();

  console.log("Productos en el carrito:");
  for (let i = 0; i < carrito.length; i++) {
    console.log(carrito[i]);
  }
}

/*
  Función que carga el carrito
*/
function cargarCarrito() {
  if (carrito === null) { // Si no hemos cargado todavía el carrito
    // Cargamos el carrito almacenado
    carrito = JSON.parse(localStorage.getItem("mi-carrito-almacenado"));
    
    if (carrito === null) { // Si no existía carrito almacenado
      carrito = []; // Creamos un vector vacío
    }
  }
}

/*
  Funcion para guardar el carrito
*/
function guardarCarrito() {
  localStorage.setItem("mi-carrito-almacenado",JSON.stringify(carrito));
  generarContenidoCarrito();
}

/*
  Función para generar contenido web del carrito
*/
function generarContenidoCarrito() {
  cargarCarrito();

  var contenido = "";

  for (var i = 0; i < carrito.length; i++) {
    var producto = carrito[i];
    var total = producto.precio * producto.cantidad;

    contenido += "<tr>";
    contenido += "<td>" + producto.nombre + "</td>";
    contenido += "<td>" + producto.cantidad + "</td>";
    contenido += "<td>" + producto.precio + "€</td>";
    contenido += "<td>" + total + "€</td>";
    contenido += "<td><button onclick='eliminarProducto(" + JSON.stringify(producto) + ")'>Eliminar unidad</button>";
    contenido += " ";
    contenido += "<button onclick='aumentarCantidad(" + JSON.stringify(producto) + ")'>Aumentar cantidad</button>";//Hacer la funcion
    contenido += " ";
    contenido += "<button onclick='eliminarProductoCompleto(" + JSON.stringify(producto) + ")'>Eliminar producto</button></td>";//Hacer la funcion
    contenido += "</tr>";
  }
  contenido += "</br>";

  document.querySelector('#carrito tbody').innerHTML = contenido;
}

/*
RECORDATORIO: Añadir <div id="carrito"></div> donde vayamos a usar esta funcion
*/

/*
  Función para generar un ticket de compra
*/
function generarTicketCompra(idUsuario) {
  cargarCarrito();

  var contenido = "<br> <h1>Ticket de Compra:</h1>";
  var total = 0;

  for (var i = 0; i < carrito.length; i++) {
    var producto = carrito[i];
    var subtotal = producto.precio * producto.cantidad;
    total += subtotal;

    contenido += "<div>";
    contenido += "<h2>" + producto.nombre + "</h2>";
    contenido += "<p>Precio unitario: " + producto.precio + "</p>";
    contenido += "<p>Cantidad: " + producto.cantidad + "</p>";
    contenido += "<p>Subtotal: " + subtotal + "</p>";
    contenido += "</div>";
  }

  contenido += "<h2>Total: " + total + "</h2>";
  contenido += "<a id='compraRealizada' onclick='compraRealizada(" + idUsuario + total + ")' class='btn btn-secondary'>Comprar</a>";
  contenido += " ";
  contenido += "<a id='cancelarCompra' onclick='limpiarTicketCompra()' class='btn btn-secondary'>Cancelar</a>";
  contenido += "<br>";
  contenido += "<br>";
  contenido += "<br>";

  document.getElementById('ticket').innerHTML = contenido;
}

function vaciarCarrito() {
  // Vaciamos el carrito
  carrito = [];
  // Guardamos el carrito vacío en el almacenamiento local
  guardarCarrito();
  console.log("El carrito ha sido vaciado.");
  alert("El carrito ha sido vaciado.");
}

function limpiarTicketCompra(){
  var contenido = "";
  document.getElementById('ticket').innerHTML = contenido;
}

function compraRealizada (idUsuario, total){
  // Carga el carrito
  cargarCarrito();

  // Crea una nueva solicitud AJAX
  var solicitud = new XMLHttpRequest();

  // Configura la solicitud
  solicitud.open("POST", "../Tramitacion.html", true);
  solicitud.setRequestHeader("Content-Type", "application/json");

  //datos para la tabla Pedidos
  var datosPedido = {
    comprador: idUsuario,
    //La fecha la generamos en el servlet
    importeFinal: total,
    estado:1
  };

  // Itera sobre los productos en el carrito
  for (var i = 0; i < carrito.length; i++) {
    var producto = carrito[i];

    // Aquí puedes recoger los datos del pedido que quieres enviar al servlet
    var datosDetalle = {
      //el codigo del pedido al que va asociado
      //el codigo del producto al que va asociado
      cantidad: producto.cantidad,
      precio: producto.precio
    };

    // Convierte los datos de la tabla pedido a una cadena JSON
    var datos = {
      pedido: datosPedido,
      detalles: datosDetalle
    };
    var datosJson = JSON.stringify(datos);
    // Envía la solicitud
    solicitud.send(datosJson);
  }

  // Aquí se maneja la respuesta del servlet
  solicitud.onload = function() {
    if (solicitud.status == 200) {
        // Si la respuesta es exitosa...
        alert("Su compra ha sido realizada correctamente.");
        vaciarCarrito();
        document.getElementById("ticket").innerHTML = "";
        //Falta hacer que se actualicen las existencias en la base de datos
        window.location.href = "../FicherosHTML/Inicio_EliteCars.html";
    } else {
        // Si hay un error...
    }
  };



  //alert("Su compra ha sido realizada correctamente.");
  //vaciarCarrito();
  //document.getElementById("ticket").innerHTML = "";
  //Falta hacer que se actualicen las existencias en la base de datos
  //window.location.href = "../FicherosHTML/Inicio_EliteCars.html";
  
  /*
  let sesion = sessionStorage.getItem('carrito');//Array de Producto (ArrayList <Producto>)
  for(let i=0; i<sesion.length;i++){
    if(comprobarExistencias(sesion[i])){
      llamadaRetirarExistencias(sesion[i]);
      alert("Su compra ha sido realizada correctamente.");
      vaciarCarrito();
      document.getElementById("ticket").innerHTML = "";
      //Falta hacer que se actualicen las existencias en la base de datos
      window.location.href = "../FicherosHTML/Inicio_EliteCars.html";
    }else{
      alert("Su producto " + sesion[i].getNombre() + " no puede ser comprado porque no tenemos existencias suficientes.");
      alert("Solo disponemos de " + getExistenciasProducto(sesion[i]) + "existencias.");
    }
  }
  */
}

function comprobarExistencias(p){
  let compra;

  //si la cantidad seleccionada es mayor a las existencias, NO se realiza la compra
  if(p.getCantidad() > getExistenciasProducto(p)){
    compra=false;
  }else{
    compra=true;
  }

  return compra;
}

function getExistenciasProducto(p) {
  let existencias = -1;
  let con = AccesoBD.getInstance();
  let listaProductos = con.obtenerProductosBD();

  for(let i = 0; i < listaProductos.length; i++) {
      let producto = listaProductos[i];
      if(producto.getCodigo() == p.getCodigo()) {
          existencias = producto.getExistencias();
      }
  }
  return existencias;
}

/*
  Llama a la funcion de AccesoBD que se encarga de aumentar las existencias en la BD
*/
function llamadaAumentarExistencias(p){
  let con = AccesoBD.getInstance();

  con.aumentarExistencias(p);
}

/*
  Llama a la funcion de AccesoBD que se encarga de disminuir las existencias en la BD
*/
function llamadaRetirarExistencias(p){
  let con = AccesoBD.getInstance();

  con.retirarExistencias(p);
}
/*
function cargarDatosUsuario() {
  var nombre = sessionStorage.getItem("nombre");
  var apellidos = sessionStorage.getItem("apellidos");
  var email = sessionStorage.getItem("email");
  var telefono = sessionStorage.getItem("telefono");
  var usuario = sessionStorage.getItem("usuario");
  var clave = sessionStorage.getItem("clave");

  var infoUsuario = document.getElementById("infoUsuario");
  infoUsuario.innerHTML = `
      <tr>
          <td contenteditable="true">${nombre}</td>
          <td contenteditable="true">${apellidos}</td>
          <td contenteditable="true">${email}</td>
          <td contenteditable="true">${telefono}</td>
          <td contenteditable="true">${usuario}</td>
          <td contenteditable="true">${clave}</td>
      </tr>
  `;
}
*/

function invokeScript(divid) {
	var scriptObj = divid.getElementsByTagName("SCRIPT");
	var len = scriptObj.length;
	for (var i = 0; i < len; i++) {
		var scriptText = scriptObj[i].text;
		var scriptFile = scriptObj[i].src
		var scriptTag = document.createElement("SCRIPT");
		if ((scriptFile != null) && (scriptFile != "")) {
			scriptTag.src = scriptFile;
		}
		scriptTag.text = scriptText;
		if (!document.getElementsByTagName("HEAD")[0]) {
			document.createElement("HEAD").appendChild(scriptTag)
		}
		else {
			document.getElementsByTagName("HEAD")[0].appendChild(scriptTag);
		}
	}
}

function nuevaConexion() {
	var xmlhttp = false;
	try {
		xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	}
	catch (e) {
		try {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		catch (E) {
			xmlhttp = false;
		}
	}
	if (!xmlhttp && typeof XMLHttpRequest != 'undefined') {
		xmlhttp = new XMLHttpRequest();
	}
	return xmlhttp;
}

function EnviarCarrito(url, capa, valores) {
	var contenido = document.getElementById(capa);
	conexion = nuevaConexion();
	conexion.open("POST", url, true);
	conexion.onreadystatechange = function () {
		if ((conexion.readyState == 4) && (conexion.status == 200)) {
			contenido.innerHTML = conexion.responseText;
			invokeScript(document.getElementById(capa));
		}
	}
    conexion.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
	conexion.send(JSON.stringify(valores));
}

