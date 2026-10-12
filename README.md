# Elite Cars 🚗

**Sitio web de venta de coches de alta gama desarrollado con HTML, CSS, JavaScript y Bootstrap 5.** Elite Cars permite a los usuarios explorar un catálogo de vehículos exclusivos, gestionar su cuenta de usuario, añadir productos al carrito y consultar su historial de pedidos, todo en una interfaz responsive con componentes web reutilizables.

***

## Descripción

Elite Cars es una tienda online de automóviles de lujo y alto rendimiento. El catálogo incluye vehículos como el Lamborghini Urus Mansory, Porsche 911 GT3 RS, Mercedes-AMG Clase A, Mercedes G Brabus, MINI John Cooper Works y Volkswagen Golf GTI Clubsport. El proyecto está estructurado como una SPA estática con múltiples páginas HTML y componentes web personalizados.

***

## Capturas de pantalla

### Web pública

| Inicio | Productos |
|:------:|:---------:|
| ![Inicio](screenshots/ParteWeb/InicioEliteCars.png) | ![Productos](screenshots/ParteWeb/ProductosEliteCars.png) |

| Carrito | Contacto |
|:-------:|:--------:|
| ![Carrito](screenshots/ParteWeb/CarritoEliteCars.png) | ![Contacto](screenshots/ParteWeb/ContactoEliteCars.png) |

### Panel de administración

| Administración de pedidos | Administración de usuarios |
|:------------------------:|:---------------------------:|
| ![Administración de pedidos](screenshots/ParteAdministracion/AdministracionPedidosEliteCars.png) | ![Administración de usuarios](screenshots/ParteAdministracion/AdministracionUsuariosEliteCars.png) |

***

## Catálogo de vehículos

| Vehículo | Imagen |
|:--------:|:------:|
| **Lamborghini Urus Mansory** | ![Lamborghini Urus Mansory](Web/ImagenesCoches/LamborghiniUrusMansory.jpg) |
| **Porsche GT3 RS** | ![Porsche GT3 RS](Web/ImagenesCoches/PorscheGT3RS.jpg) |
| **Mercedes G Brabus** | ![Mercedes G Brabus](Web/ImagenesCoches/MercedesGBrabus.jpg) |
| **Mercedes Clase A AMG** | ![Mercedes Clase A AMG](Web/ImagenesCoches/MercedesClaseAAMG.jpg) |
| **MINI John Cooper Works** | ![MINI John Cooper Works](Web/ImagenesCoches/MiniJohnCooperWorks.jpg) |
| **Volkswagen Golf GTI Clubsport** | ![Volkswagen Golf GTI Clubsport](Web/ImagenesCoches/VWGolfGTIClubsport.jpg) |

***

## Características principales

- **Catálogo de vehículos**: listado de coches de alta gama con imágenes, nombre y precio.
- **Carrito de compra**: los usuarios pueden añadir vehículos al carrito antes de finalizar el pedido.
- **Gestión de usuario**: formulario de inicio de sesión con campos de usuario y contraseña y enlace a registro de nueva cuenta.
- **Panel de usuario registrado**: muestra datos personales e historial de pedidos, todo editable inline.
- **Panel de administración**: gestión de usuarios (alta, activación/desactivación) y administración de pedidos con estado Preparado/No Preparado.
- **Componentes web reutilizables**: `<mi-cabecera>` y `<mi-pie>` como Custom Elements de la Web Components API.
- **Diseño responsive**: navbar que colapsa en móvil gracias a Bootstrap 5 (breakpoint `md`).
- **Redes sociales**: footer con iconos de Twitter, Instagram y Facebook (Font Awesome 5).

***

## Estructura del proyecto

```
EliteCars/
├── Web/
│   ├── FicherosHTML/
│   │   ├── Inicio_EliteCars.html
│   │   ├── Empresa_EliteCars.html
│   │   ├── Contacto_EliteCars.html
│   │   ├── Productos_EliteCars.html
│   │   ├── Carrito_EliteCars.html
│   │   ├── Usuario_EliteCars.html
│   │   ├── UsuarioIniciado_EliteCars.html
│   │   └── RegistrarUsuario_EliteCars.html
│   ├── FicherosJS/
│   │   ├── Cabecera_EliteCars.js  # Custom Element <mi-cabecera>
│   │   └── PiePag_EliteCars.js    # Custom Element <mi-pie>
│   ├── FicherosCSS/
│   │   └── Inicio_EliteCars.css
│   └── ImagenesCoches/        # Imágenes de los vehículos y logo
├── Admin/                     # Panel de administración
├── EliteCars_v2/              # Versión Java (JSP + Servlets + MariaDB)
├── EliteCars_v3/              # Versión PHP + MySQL
└── screenshots/               # Capturas de pantalla del README
```

***

## Otras versiones del proyecto

Este repositorio reúne también las versiones de Elite Cars con servidor, cada una en su carpeta y con su propio README:

| Carpeta | Versión | Tecnologías |
|---------|---------|-------------|
| [`EliteCars_v2/`](EliteCars_v2/) | Tienda con backend en Java (proyecto Maven) | JSP, Servlets, MariaDB |
| [`EliteCars_v3/`](EliteCars_v3/) | Tienda y panel de administración en PHP | PHP, MySQL |

> La contraseña de la base de datos no se incluye en el repositorio. Para ejecutar estas versiones, sustituye `TU_CLAVE_BD` por la tuya en `EliteCars_v2/src/main/java/tienda/AccesoBD.java`, `EliteCars_v3/basedatos.php` y `EliteCars_v3/identificacion.php`.

***

## Componentes web personalizados

### `<mi-cabecera>` — `Cabecera_EliteCars.js`
Custom Element que inyecta la barra de navegación fija en todas las páginas. Incluye marca EliteCars, menú de navegación, iconos de Usuario y Carrito, y botón hamburguesa para móvil.

### `<mi-pie>` — `PiePag_EliteCars.js`
Custom Element que inyecta el pie de página fijo con copyright y enlace a redes sociales (Twitter, Instagram, Facebook).

***

## Páginas principales

| Página | Archivo | Descripción |
|--------|---------|-------------|
| Inicio | `Inicio_EliteCars.html` | Portada del sitio |
| Empresa | `Empresa_EliteCars.html` | Información sobre la empresa |
| Contacto | `Contacto_EliteCars.html` | Formulario de contacto con mapa y datos |
| Productos | `Productos_EliteCars.html` | Catálogo de vehículos con precios |
| Carrito | `Carrito_EliteCars.html` | Cesta de la compra con totales |
| Usuario (login) | `Usuario_EliteCars.html` | Inicio de sesión y registro |
| Panel de usuario | `UsuarioIniciado_EliteCars.html` | Datos del cliente y pedidos |

***

## Tecnologías utilizadas

- **HTML5** — estructura semántica.
- **CSS3** — estilos personalizados complementarios a Bootstrap.
- **JavaScript (ES6+)** — lógica del cliente y Custom Elements.
- **Bootstrap 5.3.2** — grid responsive, navbar y componentes UI.
- **Web Components API** — Custom Elements para cabecera y footer reutilizables.
- **Font Awesome 5.15.1** — iconos vectoriales.

***

## Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/oscarherreross/EliteCars.git
   ```
2. Abre `Web/FicherosHTML/Inicio_EliteCars.html` en tu navegador o usa un servidor estático:
   ```bash
   python -m http.server 8080
   ```

***

## Posibles mejoras futuras

- Añadir un **backend** (Node.js + Express) con base de datos real.
- Implementar **autenticación real** con JWT.
- Migrar a **React, Vue o Svelte**.
- Añadir **buscador y filtros** en el catálogo.
- Integrar **pasarela de pago** (Stripe, PayPal).

***

## Autor

Desarrollado por **Óscar** (UV) — maquetación responsive con Bootstrap, Web Components nativos y organización modular HTML/JS/CSS.
