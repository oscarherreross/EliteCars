/*
class="navbar navbar-expand-md navbar-dark bg-dark footer-fixed d-flex justify-content-center align-items-center": Estas son varias clases de Bootstrap y CSS personalizadas que se aplican al elemento <footer>. Estas clases controlan la apariencia y el comportamiento del elemento.
style="color: white;": Este atributo se utiliza para aplicar estilos CSS directamente al elemento. En este caso, se está cambiando el color del texto dentro del <footer> a blanco.
*/
class Pie extends HTMLElement {
    constructor() {
        super()
        this.innerHTML = `
        <footer class="navbar navbar-expand-md navbar-dark bg-dark footer-fixed d-flex justify-content-center align-items-center" style="color: white;">
            <p class="mb-0"><strong>&copy; 2024 EliteCars. Todos los derechos reservados.</strong></p>
            <div class="social-icons">
                <a href="https://twitter.com/" target="_blank" style="color: white; margin-left: 10px; margin-right: 10px;"><i class="fab fa-twitter"></i></a>
                <a href="https://instagram.com/" target="_blank" style="color: white; margin-right: 10px;"><i class="fab fa-instagram"></i></a>
                <a href="https://facebook.com/" target="_blank" style="color: white; margin-right: 10px;"><i class="fab fa-facebook"></i></a>
            </div>
        </footer>
        `
    }
}
window.customElements.define('mi-pie', Pie);
