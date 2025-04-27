# Sistema de Gestión de Compras ERP

## 📌 Información General

-   **Título:** Diseño y desarrollo de una aplicación orientada a objetos utilizando Java
-   **Asignatura:** Programación Orientada a Objetos
-   **Carrera:** Computación
-   **Estudiantes:** [Nombre del Estudiante 1], [Nombre del Estudiante 2] (Trabajo en parejas)
-   **Fecha:** [Fecha de Entrega]
-   **Profesor:** [Nombre del Profesor]

---

## 🛠️ Descripción

Este proyecto implementa un módulo básico de gestión de compras para un sistema ERP, desarrollado en Java aplicando los principios de la Programación Orientada a Objetos (POO). El sistema permite automatizar y controlar el proceso de compras, manejando información sobre proveedores, productos y solicitudes de compra a través de un menú interactivo en consola.

Las funcionalidades principales incluyen:

* Registro de proveedores y los productos que ofrecen.
* Creación de solicitudes de compra por parte de diferentes departamentos.
* Control del estado de cada solicitud (EN_REVISIÓN, APROBADA, RECHAZADA).
* Cálculo del costo total de cada solicitud de compra.
* Interacción mediante un menú en consola para registrar, listar y buscar información.

El proyecto aplica obligatoriamente conceptos de POO como Herencia, Clases Abstractas, Interfaces, Enums, y Polimorfismo/Enlaces Dinámicos.

---

## 🚀 Ejecución

Para ejecutar el proyecto:

1.  Asegúrate de tener instalado el Java Development Kit (JDK) en tu sistema.
2.  Clona o descarga este repositorio.
3.  Compila los archivos fuente Java. Si utilizas una IDE (como NetBeans, Eclipse, IntelliJ IDEA), la compilación se manejará automáticamente. Si lo haces desde la terminal, navega hasta el directorio raíz del proyecto y ejecuta:
    ```bash
    javac ec/edu/ups/poo/*.java ec/edu/ups/poo/clases/*.java ec/edu/ups/poo/controllers/*.java ec/edu/ups/poo/enums/*.java ec/edu/ups/poo/interfaces/*.java
    ```
4.  Ejecuta la aplicación principal:
    ```bash
    java ec.edu.ups.poo.Main
    ```
5.  Interactúa con el sistema a través del menú que aparecerá en la consola.

---

## 🧑‍💻 Ejemplo de Interacción (Menú en Consola)

============= SISTEMA DE GESTION DE COMPRAS ERP =============
1. Registrar proveedor
2. Registrar solicitud de compra
3. Listar proveedores
4. Listar productos
5. Listar solicitudes de compra
6. Buscar proveedor por identificador
7. Buscar producto por nombre
8. Buscar solicitud por numero
9. Aprobar / Rechazar solicitud de compra
10. Calcular total de una solicitud
11. Salir
