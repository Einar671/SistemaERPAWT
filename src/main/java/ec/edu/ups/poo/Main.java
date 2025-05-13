package ec.edu.ups.poo;

import ec.edu.ups.poo.modelo.clases.*;
import ec.edu.ups.poo.modelo.controllers.Methods;
import ec.edu.ups.poo.modelo.enums.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Proveedor> proveedores = new ArrayList<>();
        List<Empleado> empleados = new ArrayList<>();
        List<SolicitudCompra> solicitudes = new ArrayList<>();

        Methods methods = new Methods();
        Scanner leer = new Scanner(System.in);
        int opcion;
        boolean continuar = true;

        while (continuar) {
            System.out.println("============= SISTEMA DE GESTION DE COMPRAS ERP =============");
            System.out.println("1. Registrar proveedor");
            System.out.println("2. Registrar solicitud de compra");
            System.out.println("3. Listar proveedores");
            System.out.println("4. Listar productos por proveedor");
            System.out.println("5. Listar solicitudes de compra");
            System.out.println("6. Buscar proveedor por identificador");
            System.out.println("7. Buscar producto por nombre (dentro de un proveedor)");
            System.out.println("8. Buscar solicitud por numero");
            System.out.println("9. Aprobar / Rechazar solicitud de compra");
            System.out.println("10. Calcular total de una solicitud");
            System.out.println("11. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leer.nextInt();
            leer.nextLine();

            switch (opcion) {
                case 1:
                    boolean continuaRegProv = true;
                    while (continuaRegProv) {
                        System.out.println("-*-*------Registrar proveedor-*-*---------");
                        System.out.println("1. Registrar nuevo proveedor");
                        System.out.println("2. Salir del registro de proveedor");
                        System.out.print("Seleccione una opción: ");
                        int opcionprove = leer.nextInt();
                        leer.nextLine();

                        if (opcionprove == 1) {
                            System.out.print("Digite la identificacion del proveedor: ");
                            String identificacion = leer.nextLine();
                            System.out.print("Digite el nombre del proveedor: ");
                            String nombre = leer.nextLine();
                            System.out.print("Digite el email del proveedor: ");
                            String email = leer.nextLine();
                            System.out.print("Digite el telefono del proveedor: ");
                            String telefono = leer.nextLine();

                            List<Producto> productosProveedor = new ArrayList<>();
                            boolean continuarpro = true;

                            while (continuarpro) {
                                System.out.println("------------Registre los productos del proveedor--------------");
                                System.out.println("*********1. PRODUCTO CON IVA");
                                System.out.println("*********2. PRODUCTO SIN IVA");
                                System.out.println("*********3. PRODUCTO CON DESCUENTO");
                                System.out.println("*********4. SALIR DE REGISTRO DE PRODUCTO");
                                System.out.print("Seleccione el tipo de producto a registrar: ");
                                int optipo = leer.nextInt();
                                leer.nextLine();

                                if (optipo == 1) {
                                    System.out.print("Ingrese el id del producto: ");
                                    int id = leer.nextInt();
                                    leer.nextLine();
                                    System.out.print("Ingrese el nombre del producto: ");
                                    String nombreProducto = leer.nextLine();
                                    System.out.print("Ingrese el precio del producto: ");
                                    double precio = leer.nextDouble();
                                    leer.nextLine();
                                    System.out.println("Ingrese el tipo de medida del producto");
                                    System.out.println("a. Unidad");
                                    System.out.println("b. Kilogramo");
                                    System.out.println("c. Litro");
                                    System.out.print("Seleccione la unidad de medida (a/b/c): ");
                                    String opmedida = leer.nextLine();

                                    UnidadMedida unidad = null;
                                    if (opmedida.equalsIgnoreCase("a")) {
                                        unidad = UnidadMedida.UNIDAD;
                                    } else if (opmedida.equalsIgnoreCase("b")) {
                                        unidad = UnidadMedida.KILOGRAMO;
                                    } else if (opmedida.equalsIgnoreCase("c")) {
                                        unidad = UnidadMedida.LITRO;
                                    }

                                    if (unidad != null) {
                                        productosProveedor.add(new ProductoConIva(id, nombreProducto, precio, unidad, 0.15));
                                        System.out.println(" ✅  Producto con IVA registrado con éxito.");
                                    } else {
                                        System.out.println(" ⚠️  Unidad de medida inválida. Producto no registrado.");
                                    }


                                } else if (optipo == 2) {
                                    System.out.print("Ingrese el id del producto: ");
                                    int id = leer.nextInt();
                                    leer.nextLine();
                                    System.out.print("Ingrese el nombre del producto: ");
                                    String nombreProducto = leer.nextLine();
                                    System.out.print("Ingrese el precio del producto: ");
                                    double precio = leer.nextDouble();
                                    leer.nextLine();
                                    System.out.print("Ingrese la categoría de exensión: ");
                                    String excension = leer.nextLine();

                                    System.out.println("Ingrese el tipo de medida del producto");
                                    System.out.println("a. Unidad");
                                    System.out.println("b. Kilogramo");
                                    System.out.println("c. Litro");
                                    System.out.print("Seleccione la unidad de medida (a/b/c): ");
                                    String opmedida = leer.nextLine();

                                    UnidadMedida unidad = null;
                                    if (opmedida.equalsIgnoreCase("a")) {
                                        unidad = UnidadMedida.UNIDAD;
                                    } else if (opmedida.equalsIgnoreCase("b")) {
                                        unidad = UnidadMedida.KILOGRAMO;
                                    } else if (opmedida.equalsIgnoreCase("c")) {
                                        unidad = UnidadMedida.LITRO;
                                    }

                                    if (unidad != null) {
                                        productosProveedor.add(new ProductoSinIva(id, nombreProducto, precio, unidad, excension));
                                        System.out.println(" ✅  Producto sin IVA registrado con éxito.");
                                    } else {
                                        System.out.println(" ⚠️  Unidad de medida inválida. Producto no registrado.");
                                    }


                                } else if (optipo == 3) {
                                    System.out.print("Ingrese el id del producto: ");
                                    int id = leer.nextInt();
                                    leer.nextLine();
                                    System.out.print("Ingrese el nombre del producto: ");
                                    String nombreProducto = leer.nextLine();
                                    System.out.print("Ingrese el precio del producto: ");
                                    double precio = leer.nextDouble();
                                    leer.nextLine();
                                    System.out.print("Ingrese el porcentaje del descuento (ej: 10 para 10%): ");
                                    double descuento = leer.nextDouble() / 100;
                                    leer.nextLine();
                                    System.out.println("Ingrese el tipo de medida del producto");
                                    System.out.println("a. Unidad");
                                    System.out.println("b. Kilogramo");
                                    System.out.println("c. Litro");
                                    System.out.print("Seleccione la unidad de medida (a/b/c): ");
                                    String opmedida = leer.nextLine();

                                    UnidadMedida unidad = null;
                                    if (opmedida.equalsIgnoreCase("a")) {
                                        unidad = UnidadMedida.UNIDAD;
                                    } else if (opmedida.equalsIgnoreCase("b")) {
                                        unidad = UnidadMedida.KILOGRAMO;
                                    } else if (opmedida.equalsIgnoreCase("c")) {
                                        unidad = UnidadMedida.LITRO;
                                    }

                                    if (unidad != null) {
                                        productosProveedor.add(new ProductoConDescuento(id, nombreProducto, precio, unidad, descuento));
                                        System.out.println(" ✅  Producto con descuento registrado con éxito.");
                                    } else {
                                        System.out.println(" ⚠️  Unidad de medida inválida. Producto no registrado.");
                                    }

                                } else if (optipo == 4) {
                                    continuarpro = false;
                                } else {
                                    System.out.println(" ⚠️  Opción de producto inválida.");
                                }
                            }

                            Proveedor nuevoProveedor = new Proveedor(identificacion, nombre, email, productosProveedor, telefono);
                            proveedores.add(nuevoProveedor);
                            System.out.println("✅ Proveedor registrado con éxito.");

                        } else if (opcionprove == 2) {
                            continuaRegProv = false;
                        } else {
                            System.out.println(" ⚠️  Opción inválida.");
                        }
                    }
                    break;
                case 2:
                    boolean continuaSolicitud = true;
                    while (continuaSolicitud) {
                        System.out.println("-*-*---------- SOLICITUD DE COMPRA -*-*-----------");
                        System.out.println("1. Registrar nueva solicitud de compra");
                        System.out.println("2. Salir del registro de solicitudes");
                        System.out.print("Seleccione una opción: ");
                        int op = leer.nextInt();
                        leer.nextLine();

                        if (op == 1) {
                            if (proveedores.isEmpty()) {
                                System.out.println(" ⚠️  No hay proveedores registrados. Registre un proveedor primero.");
                                continue;
                            }

                            System.out.print("Ingrese el id de la solicitud: ");
                            int idSolicitud = leer.nextInt();
                            leer.nextLine();

                            if (methods.buscarSolicitudesPorNumero(solicitudes, idSolicitud) != null) {
                                System.out.println(" ⚠️  Ya existe una solicitud con este ID. Ingrese uno diferente.");
                                continue;
                            }

                            System.out.print("Ingrese el año de la solicitud: ");
                            int anio = leer.nextInt();
                            leer.nextLine();
                            System.out.print("Ingrese el mes de la solicitud (1 - 12): ");
                            int mes = leer.nextInt();
                            leer.nextLine();
                            System.out.print("Ingrese el día de la solicitud (1 - 31): ");
                            int dia = leer.nextInt();
                            leer.nextLine();

                            GregorianCalendar fecha = new GregorianCalendar(anio, mes - 1, dia);

                            SolicitudCompra nuevaSolicitud = new SolicitudCompra(idSolicitud, fecha, EstadoDeSolictud.EN_REVISION);

                            boolean detallesol = true;

                            while (detallesol) {
                                System.out.println("---------DETALLE DE SOLICITUD--------");
                                System.out.println("1. Agregar detalle de compra");
                                System.out.println("2. Finalizar registro de detalles y guardar solicitud");
                                System.out.print("Seleccione una opción: ");
                                int op2 = leer.nextInt();
                                leer.nextLine();

                                if (op2 == 1) {
                                    System.out.print("Ingrese el código del detalle: ");
                                    int codigoDetalle = leer.nextInt();
                                    leer.nextLine();
                                    System.out.print("Ingrese la cantidad del producto: ");
                                    int cantidad = leer.nextInt();
                                    leer.nextLine();
                                    System.out.print("Ingrese la observación del producto: ");
                                    String observacion = leer.nextLine();



                                    System.out.println("Seleccione el proveedor del producto:");
                                    for (int i = 0; i < proveedores.size(); i++) {
                                        System.out.println((i + 1) + ". " + proveedores.get(i).getNombre());
                                    }
                                    System.out.print("Ingrese el número del proveedor: ");
                                    int indexProveedor = leer.nextInt() - 1;
                                    leer.nextLine();

                                    if (indexProveedor >= 0 && indexProveedor < proveedores.size()) {
                                        Proveedor proveedorSeleccionado = proveedores.get(indexProveedor);
                                        List<Producto> productosProveedor = proveedorSeleccionado.getProductos();

                                        if (productosProveedor.isEmpty()) {
                                            System.out.println(" ⚠️  Este proveedor no tiene productos registrados. No se puede agregar el detalle.");
                                        } else {
                                            System.out.println("Seleccione el producto del proveedor:");
                                            for (int i = 0; i < productosProveedor.size(); i++) {
                                                System.out.println((i + 1) + ". " + productosProveedor.get(i).getNombreProducto());
                                            }
                                            System.out.print("Ingrese el número del producto: ");
                                            int indexProducto = leer.nextInt() - 1;
                                            leer.nextLine();


                                            if (indexProducto >= 0 && indexProducto < productosProveedor.size()) {
                                                Producto productoSeleccionado = productosProveedor.get(indexProducto);
                                                nuevaSolicitud.addDetalles(codigoDetalle, cantidad, observacion, productoSeleccionado);
                                                System.out.println(" ✅  Detalle agregado a la solicitud.");
                                            } else {
                                                System.out.println(" ❌   Índice de producto no válido. Detalle no agregado.");
                                            }
                                        }
                                    } else {
                                        System.out.println(" ❌   Índice de proveedor no válido. Detalle no agregado.");
                                    }
                                } else if (op2 == 2) {
                                    detallesol = false;
                                } else {
                                    System.out.println(" ⚠️  Opción inválida.");
                                }
                            }
                            solicitudes.add(nuevaSolicitud);
                            System.out.println(" ✅  Solicitud de compra registrada con éxito.");
                            continuaSolicitud = false;
                        } else if (op == 2) {
                            continuaSolicitud = false;
                        } else {
                            System.out.println(" ⚠️  Opción inválida.");
                        }
                    }
                    break;

                case 3:
                    System.out.println("-*-*---------- LISTA DE PROVEEDORES -*-*-----------");
                    if (proveedores.isEmpty()) {
                        System.out.println("No hay proveedores registrados.");
                    } else {
                        methods.insertionSortIdentificacion(proveedores);
                        for (Proveedor p : proveedores) {
                            System.out.println(p);
                        }
                    }
                    break;

                case 4:
                    System.out.println("-*-*---------- LISTA DE PRODUCTOS POR PROVEEDOR -*-*-----------");
                    if (proveedores.isEmpty()) {
                        System.out.println(" ⚠️  No hay proveedores registrados.");
                    } else {
                        System.out.println("Seleccione el proveedor para mostrar sus productos:");
                        for (int i = 0; i < proveedores.size(); i++) {
                            System.out.println((i + 1) + ". " + proveedores.get(i).getNombre());
                        }
                        System.out.print("Ingrese el número del proveedor: ");
                        int indexProveedor = leer.nextInt() - 1;
                        leer.nextLine();

                        if (indexProveedor >= 0 && indexProveedor < proveedores.size()) {
                            Proveedor proveedorSeleccionado = proveedores.get(indexProveedor);
                            System.out.println("Productos de " + proveedorSeleccionado.getNombre() + ":");
                            List<Producto> productosDelProveedor = proveedorSeleccionado.getProductos();
                            if (productosDelProveedor.isEmpty()) {
                                System.out.println("Este proveedor no tiene productos registrados.");
                            } else {
                                methods.ordenarProductosNombre(productosDelProveedor);
                                for (Producto prod : productosDelProveedor) {
                                    System.out.println(prod);
                                }
                            }
                        } else {
                            System.out.println(" ❌   Índice de proveedor no válido.");
                        }
                    }
                    break;

                case 5:
                    System.out.println("-*-*---------- LISTA DE SOLICITUDES -*-*-----------");
                    if (solicitudes.isEmpty()) {
                        System.out.println("No hay solicitudes de compra registradas.");
                    } else {
                        methods.ordenarSolicitudesPorNumero(solicitudes);
                        for (SolicitudCompra s : solicitudes) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 6: // Buscar proveedor por identificador
                    System.out.println("-*-*---------- BUSQUEDA POR IDENTIFICACION PROVEEDOR -*-*-----------");
                    if (proveedores.isEmpty()) {
                        System.out.println(" ⚠️  No hay proveedores registrados para buscar.");
                    } else {
                        System.out.print("INGRESAR LA IDENTIFICACION A BUSCAR: ");
                        String identificacionBuscar = leer.nextLine();

                        methods.insertionSortIdentificacion(proveedores);
                        System.out.println("ORDENANDO IDENTIFICACION PROVEEDOR......");
                        Proveedor respuesta = methods.buscarProveedorPorIdentificacion(proveedores, identificacionBuscar);

                        if (respuesta == null) {
                            System.out.println("PROVEEDOR NO ENCONTRADO");
                        } else {
                            System.out.println("PROVEEDOR ENCONTRADO: \n " + respuesta);
                        }
                    }
                    break;

                case 7:
                    System.out.println("-*-*---------- BUSQUEDA POR NOMBRE PRODUCTO -*-*-----------");
                    if (proveedores.isEmpty()) {
                        System.out.println(" ⚠️  No hay proveedores registrados para buscar productos.");
                    } else {
                        System.out.println("Seleccione el proveedor donde buscar el producto:");
                        for (int i = 0; i < proveedores.size(); i++) {
                            System.out.println((i + 1) + ". " + proveedores.get(i).getNombre());
                        }
                        System.out.print("Ingrese el número del proveedor: ");
                        int indexProveedor = leer.nextInt() - 1;
                        leer.nextLine();

                        if (indexProveedor >= 0 && indexProveedor < proveedores.size()) {
                            Proveedor proveedorSeleccionado = proveedores.get(indexProveedor);
                            List<Producto> productosDelProveedor = proveedorSeleccionado.getProductos();

                            if (productosDelProveedor.isEmpty()) {
                                System.out.println("Este proveedor no tiene productos registrados.");
                            } else {
                                System.out.print("INGRESAR EL NOMBRE PRODUCTO A BUSCAR: ");
                                String nombreBuscar = leer.nextLine();

                                methods.ordenarProductosNombre(productosDelProveedor);
                                System.out.println("ORDENANDO EL NOMBRE PRODUCTO......");

                                Producto producto = methods.buscarProductoPorNombre(productosDelProveedor, nombreBuscar);

                                if (producto == null) {
                                    System.out.println("PRODUCTO NO ENCONTRADO en este proveedor.");
                                } else {
                                    System.out.println("PRODUCTO ENCONTRADO: \n " + producto);
                                }
                            }
                        } else {
                            System.out.println(" ❌   Índice de proveedor no válido.");
                        }
                    }
                    break;

                case 8:
                    System.out.println("-*-*---------- BUSQUEDA POR NUMERO DE SOLICITUD -*-*-----------");
                    if (solicitudes.isEmpty()) {
                        System.out.println(" ⚠️  No hay solicitudes registradas para buscar.");
                    } else {
                        System.out.print("INGRESAR EL NUMERO DE LA SOLICITUD A BUSCAR: ");
                        int numeroBuscar = leer.nextInt();
                        leer.nextLine();

                        methods.ordenarSolicitudesPorNumero(solicitudes);
                        System.out.println("ORDENANDO LAS SOLICITUDES......");

                        SolicitudCompra solicitudEncontrada = methods.buscarSolicitudesPorNumero(solicitudes, numeroBuscar);

                        if (solicitudEncontrada == null) {
                            System.out.println("SOLICITUD NO ENCONTRADA");
                        } else {
                            System.out.println("SOLICITUD ENCONTRADA: \n " + solicitudEncontrada);
                        }
                    }
                    break;

                case 9:
                    System.out.println("-*-*---------- GESTIONAR ESTADO DE SOLICITUD -*-*-----------");
                    if (solicitudes.isEmpty()) {
                        System.out.println(" ⚠️  No hay solicitudes registradas para gestionar.");
                    } else {
                        methods.ordenarSolicitudesPorNumero(solicitudes);
                        System.out.println("Seleccione la solicitud que desea gestionar:");
                        for (int i = 0; i < solicitudes.size(); i++) {
                            System.out.println("Solicitud n° " + solicitudes.get(i).getId() + "\n" + solicitudes.get(i));
                        }
                        System.out.print("Ingrese el número (ID) de la solicitud: ");
                        int numeroSolicitudGestionar = leer.nextInt();
                        leer.nextLine();

                        SolicitudCompra solicitudAD = methods.buscarSolicitudesPorNumero(solicitudes, numeroSolicitudGestionar);

                        if (solicitudAD == null) {
                            System.out.println("SOLICITUD NO ENCONTRADA.");
                        } else {
                            System.out.println("SOLICITUD ENCONTRADA: \n " + solicitudAD);
                            boolean aprovadaDesaprovda = true;
                            while (aprovadaDesaprovda) {
                                System.out.println("¿Qué desea hacer con la solicitud?");
                                System.out.println("1. Aprobar solicitud");
                                System.out.println("2. Rechazar solicitud");
                                System.out.println("3. Cancelar");
                                System.out.print("Seleccione una opción: ");
                                int decision = leer.nextInt();
                                leer.nextLine();

                                if (decision == 1) {
                                    solicitudAD.gestionarEstado(EstadoDeSolictud.APROVADA);
                                    System.out.println(" ✅  Solicitud aprobada con éxito.");
                                    aprovadaDesaprovda = false;
                                } else if (decision == 2) {
                                    solicitudAD.gestionarEstado(EstadoDeSolictud.RECHAZADA);
                                    System.out.println(" ❌  Solicitud rechazada.");
                                    aprovadaDesaprovda = false;
                                } else if (decision == 3) {
                                    System.out.println("Gestión de solicitud cancelada.");
                                    aprovadaDesaprovda = false;
                                } else {
                                    System.out.println(" ⚠️  Opción inválida. No se realizaron cambios en el estado.");
                                }
                            }
                        }
                    }
                    break;

                case 10:
                    System.out.println("-*-*---------- CALCULAR TOTAL DE SOLICITUD -*-*-----------");
                    if (solicitudes.isEmpty()) {
                        System.out.println(" ⚠️  No hay solicitudes registradas para calcular su total.");
                    } else {
                        methods.ordenarSolicitudesPorNumero(solicitudes);
                        System.out.println("Seleccione la solicitud para calcular su total:");
                        for (int i = 0; i < solicitudes.size(); i++) {
                            System.out.println("Solicitud n° " + solicitudes.get(i).getId() + "\n" + solicitudes.get(i));
                        }
                        System.out.print("Ingrese el número (ID) de la solicitud: ");
                        int numeroSolicitudTotal = leer.nextInt();
                        leer.nextLine();

                        SolicitudCompra solcitudTotal = methods.buscarSolicitudesPorNumero(solicitudes, numeroSolicitudTotal);

                        if (solcitudTotal == null) {
                            System.out.println(" ⚠️  SOLICITUD NO ENCONTRADA.");
                        } else {
                            double totalSolicitud = solcitudTotal.calcularCostoTotal();
                            System.out.println("TOTAL DE LA SOLICITUD (ID: " + solcitudTotal.getId() + "): $" + totalSolicitud);
                        }
                    }
                    break;

                case 11:
                    continuar = false;
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
        leer.close();
    }
}