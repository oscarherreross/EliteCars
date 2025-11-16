/*
    class="navbar-toggler": Esta clase de Bootstrap aplica estilos al botón para que se vea como un botón de conmutación de barra de navegación.
    type="button": Este atributo especifica que el elemento <button> debe comportarse como un botón.
    data-bs-toggle="collapse": Este atributo de datos de Bootstrap se utiliza para definir el comportamiento de conmutación. En este caso, “collapse” significa que el botón se utilizará para mostrar u ocultar otro elemento.
    data-bs-target="#navbarExampleDefault": Este atributo de datos de Bootstrap se utiliza para especificar el ID del elemento que este botón mostrará u ocultará.
    <span class="navbar-toggler-icon"></span>: Este elemento <span> con la clase “navbar-toggler-icon” se utiliza para mostrar el icono del botón de conmutación.

    <li> elemento de una lista (list item)
*/
class Cabecera extends HTMLElement {
    constructor() {
        super()
        this.innerHTML = `
        <header>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
        <nav class="navbar navbar-expand-md navbar-dark bg-dark" style="position: fixed; width:100%; top:0;"> <!--La etiqueta md es la que hace que al estrechar la página se colapsen los items -->
        <div class="container-fluid">
            <a class="navbar-brand" href="#"><strong>EliteCars</strong></a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarExampleDefault">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarExampleDefault">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" href="../FicherosHTML/Inicio_EliteCars.html">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="../FicherosHTML/Empresa_EliteCars.html">Empresa</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="../FicherosHTML/Contacto_EliteCars.html">Contacto</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="../FicherosJSP/Productos_EliteCars.jsp">Productos</a>
                    </li>
                </ul>
                <div class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="../FicherosJSP/LoginUsuario.jsp">Usuario</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="../FicherosJSP/Carrito.jsp">Carrito</a>
                    </li>
                </div>
            </div>
        </div>
    </nav>
        </header>      
        `
    }
}
window.customElements.define('mi-cabecera', Cabecera);