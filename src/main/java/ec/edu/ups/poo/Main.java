package ec.edu.ups.poo;

import ec.edu.ups.poo.clases.*;
import ec.edu.ups.poo.controllers.Methods;
import ec.edu.ups.poo.enums.*;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DetalleCompra detalle = new DetalleCompra();
        Proveedor proveedor1 = new Proveedor();
        List<Proveedor> proveedor = new ArrayList<>();
        List<Empleado> empleados = new ArrayList<>();
        List<SolicitudCompra> solicitud = new ArrayList<>();
        List<DetalleCompra> detalleCompras = new ArrayList<>();
        ProductoConIva productoConIva = new ProductoConIva();
        ProductoConDescuento productoConDesc = new ProductoConDescuento();
        ProductoSinIva productoSinIva = new ProductoSinIva();
        Methods methods = new Methods();
        Scanner leer = new Scanner(System.in);
        int opcion;
        boolean continuar = true;
        while (continuar) {
            System.out.println("============= SISTEMA DE GESTION DE COMPRAS ERP =============");
            System.out.println("1. Registrar proveedor");
            System.out.println("2. Registrar solicitud de compra");
            System.out.println("3. Listar proveedores");
            System.out.println("4. Listar productos");
            System.out.println("5. Listar solicitudes de compra");
            System.out.println("6. Buscar proveedor por identificador");
            System.out.println("7. Buscar producto por nombre");
            System.out.println("8. Buscar solicitud por numero");
            System.out.println("9. Aprobar / Rechazar solicitud de compra");
            System.out.println("10. Calcular total de una solicitud");
            System.out.println("11. Salir");
            opcion = leer.nextInt();
            switch (opcion) {
                case 1:
                    boolean continua = true;
                    while (continua) {
                        System.out.println("-*-*------Registrar proveedor-*-*---------");
                        System.out.println("1. Registrar proveedor");
                        System.out.println("2. Salir");
                        int opcionprove = leer.nextInt();

                        if (opcionprove == 1) {
                            int optipo;
                            System.out.println("Digite la identificacion del proveedor: ");
                            String identificacion = leer.next();
                            System.out.println("Digite el nombre del proveedor: ");
                            String nombre = leer.next();
                            leer.nextLine();
                            System.out.println("Digite el email del proveedor: ");
                            String email = leer.next();
                            leer.nextLine();
                            System.out.println("Digite el telefono del proveedor: ");
                            String telefono = leer.next();

                            List<Producto> productos = new ArrayList<>();
                            boolean continuarpro = true;

                            while (continuarpro) {
                                System.out.println("------------Registre los productos del proveedor--------------");
                                System.out.println("*********1. PRODUCTO CON IVA");
                                System.out.println("*********2. PRODUCTO SIN IVA");
                                System.out.println("*********3. PRODUCTO CON DESCUENTO");
                                System.out.println("*********4. SALIR DE REGISTRO DE PRODUCTO");
                                optipo = leer.nextInt();

                                if (optipo == 1) {
                                    System.out.println("Ingrese el id del producto: ");
                                    int id = leer.nextInt();
                                    System.out.println("Ingrese el nombre del producto: ");
                                    String nombreProducto = leer.nextLine();
                                    leer.nextLine();
                                    System.out.println("Ingrese el precio del producto: ");
                                    double precio = leer.nextDouble();
                                    System.out.println("Ingrese el tipo de medida del producto");
                                    System.out.println("a. Unidad");
                                    System.out.println("b. Kilogramo");
                                    System.out.println("c. Litro");
                                    String opmedida = leer.next();

                                    if (opmedida.equalsIgnoreCase("a")) {
                                        productos.add(new ProductoConIva(id, nombreProducto, precio, UnidadMedida.UNIDAD, 0.15));
                                    } else if (opmedida.equalsIgnoreCase("b")) {
                                        productos.add(new ProductoConIva(id, nombreProducto, precio, UnidadMedida.KILOGRAMO, 0.15));
                                    } else if (opmedida.equalsIgnoreCase("c")) {
                                        productos.add(new ProductoConIva(id, nombreProducto, precio, UnidadMedida.LITRO, 0.15));
                                    }

                                } else if (optipo == 2) {
                                    System.out.println("Ingrese el id del producto: ");
                                    int id = leer.nextInt();
                                    System.out.println("Ingrese el nombre del producto: ");
                                    String nombreProducto = leer.nextLine();
                                    leer.nextLine();
                                    System.out.println("Ingrese el precio del producto: ");
                                    double precio = leer.nextDouble();
                                    System.out.println("Ingrese la excension: ");
                                    String excension = leer.nextLine();
                                    leer.nextLine();
                                    System.out.println("Ingrese el tipo de medida del producto");
                                    System.out.println("a. Unidad");
                                    System.out.println("b. Kilogramo");
                                    System.out.println("c. Litro");
                                    String opmedida = leer.next();

                                    if (opmedida.equalsIgnoreCase("a")) {
                                        productos.add(new ProductoSinIva(id, nombreProducto, precio, UnidadMedida.UNIDAD, excension));
                                    } else if (opmedida.equalsIgnoreCase("b")) {
                                        productos.add(new ProductoSinIva(id, nombreProducto, precio, UnidadMedida.KILOGRAMO, excension));
                                    } else if (opmedida.equalsIgnoreCase("c")) {
                                        productos.add(new ProductoSinIva(id, nombreProducto, precio, UnidadMedida.LITRO, excension));
                                    }

                                } else if (optipo == 3) {
                                    System.out.println("Ingrese el id del producto: ");
                                    int id = leer.nextInt();
                                    System.out.println("Ingrese el nombre del producto: ");
                                    String nombreProducto = leer.nextLine();
                                    leer.nextLine();
                                    System.out.println("Ingrese el precio del producto: ");
                                    double precio = leer.nextDouble();
                                    System.out.println("Ingrese el porcentaje del descuento: ");
                                    double descuento = leer.nextDouble() / 100;
                                    System.out.println("Ingrese el tipo de medida del producto");
                                    System.out.println("a. Unidad");
                                    System.out.println("b. Kilogramo");
                                    System.out.println("c. Litro");
                                    String opmedida = leer.next();

                                    if (opmedida.equalsIgnoreCase("a")) {
                                        productos.add(new ProductoConDescuento(id, nombreProducto, precio, UnidadMedida.UNIDAD, descuento));
                                    } else if (opmedida.equalsIgnoreCase("b")) {
                                        productos.add(new ProductoConDescuento(id, nombreProducto, precio, UnidadMedida.KILOGRAMO, descuento));
                                    } else if (opmedida.equalsIgnoreCase("c")) {
                                        productos.add(new ProductoConDescuento(id, nombreProducto, precio, UnidadMedida.LITRO, descuento));
                                    }

                                } else if (optipo == 4) {
                                    continuarpro = false;
                                }
                            }

                            proveedor1 = new Proveedor(identificacion, nombre, email, productos, telefono);
                            proveedor.add(proveedor1);
                            System.out.println("✅ Proveedor registrado con éxito.");

                        } else if (opcionprove == 2) {
                            continua = false;
                        }
                    }
                    break;
                case 2:
                    boolean continuaSolicitud = true;
                    while (continuaSolicitud) {
                        System.out.println("-*-*---------- SOLICITUD DE COMPRA -*-*-----------");
                        System.out.println("1. Solicitud de compra");
                        System.out.println("2. Salir");
                        int op = leer.nextInt();

                        if (op == 1) {
                            System.out.println("Ingrese el id de la solicitud: ");
                            int id = leer.nextInt();
                            System.out.println("Ingrese el año de la solicitud: ");
                            int anio = leer.nextInt();
                            System.out.println("Ingrese el mes de la solicitud (1 - 12): ");
                            int mes = leer.nextInt();
                            System.out.println("Ingrese el día de la solicitud (1 - 31): ");
                            int dia = leer.nextInt();

                            GregorianCalendar fecha = new GregorianCalendar(anio, mes - 1, dia);

                            boolean detallesol = true;

                            while (detallesol) {
                                System.out.println("---------DETALLE DE SOLICITUD--------");
                                System.out.println("1. Agregar detalle de compra");
                                System.out.println("2. Finalizar solicitud");
                                int op2 = leer.nextInt();

                                if (op2 == 1) {
                                    System.out.println("Ingrese el código del detalle: ");
                                    int codigo = leer.nextInt();
                                    System.out.println("Ingrese la cantidad del producto: ");
                                    int cantidad = leer.nextInt();
                                    System.out.println("Ingrese la observación del producto: ");
                                    String observacion = leer.nextLine();
                                    leer.nextLine();


                                    System.out.println("Seleccione el proveedor:");
                                    for (int i = 0; i < proveedor.size(); i++) {
                                        System.out.println((i + 1) + ". " + proveedor.get(i).getNombre());
                                    }
                                    int indexProveedor = leer.nextInt() - 1;

                                    if (indexProveedor >= 0 && indexProveedor < proveedor.size()) {
                                        Proveedor proveedorSeleccionado = proveedor.get(indexProveedor);
                                        List<Producto> productosProveedor = proveedorSeleccionado.getProductos();

                                        if (productosProveedor.isEmpty()) {
                                            System.out.println("⚠️ Este proveedor no tiene productos registrados.");
                                        } else {
                                            System.out.println("Seleccione el producto del proveedor:");
                                            for (int i = 0; i < productosProveedor.size(); i++) {
                                                System.out.println((i + 1) + ". " + productosProveedor.get(i).getNombreProducto());
                                            }

                                            int indexProducto = leer.nextInt() - 1;

                                            if (indexProducto >= 0 && indexProducto < productosProveedor.size()) {
                                                Producto productoSeleccionado = productosProveedor.get(indexProducto);


                                                detalle = new DetalleCompra(codigo, cantidad, observacion, productoSeleccionado);
                                                detalleCompras.add(detalle);

                                                System.out.println("✅ Detalle agregado con éxito.");
                                            } else {
                                                System.out.println("❌ Índice de producto no válido.");
                                            }
                                        }
                                    } else {
                                        System.out.println("❌ Índice de proveedor no válido.");
                                    }
                                } else if (op2 == 2) {
                                    detallesol = false;
                                }
                            }
                            solicitud.add(new SolicitudCompra(id, fecha, EstadoDeSolictud.EN_REVISION));
                            System.out.println("✅ Solicitud de compra registrada con éxito.");
                        } else if (op == 2) {
                            continuaSolicitud = false;
                        }
                    }
                    break;

                case 3:
                    System.out.println("-*-*---------- LISTA DE PROVEEDORES -*-*-----------");
                    for (Proveedor p : proveedor) {
                        System.out.println(p);
                    }
                    break;
                case 4:
                    methods.insertionSortIdentificacion(proveedor);
                    System.out.println("-*-*---------- LISTA DE PRODUCTOS -*-*-----------");{
                        for(Proveedor pro : proveedor) {
                            System.out.println(pro);
                        }
                    System.out.println("Elegir el proveedor(Indentificacion) para mostrar sus productos: ");
                        String opcion2 = leer.nextLine();
                    Proveedor buscar =methods.buscarProveedorPorIdentificacion(proveedor,opcion2);
                    if(buscar==null){
                        System.out.println("El proveedor no existe");
                    }else{
                        System.out.println("Productos del proveedor"+ buscar);
                        for(int i=0;i<buscar.getProductos().size();i++){
                            System.out.println(buscar.getProductos().get(i));
                        }
                    }

                    }
                    break;
                case 5:
                    System.out.println("-*-*---------- LISTA DE SOLICITUDES -*-*-----------");
                    for (SolicitudCompra s : solicitud) {
                        System.out.println(s);
                    }
                    break;
                case 6:
                    System.out.println("-*-*---------- BUSQUEDA POR IDENTIFICACION PROOVEDOR -*-*-----------");
                    System.out.println("INGRESAR LA IDENTIFICACION A BUSCAR: ");
                    String identificacion = leer.nextLine();
                    leer.nextLine();
                    methods.insertionSortIdentificacion(proveedor);
                    System.out.println("ORDENANDO IDENTIFICACION PROVEDOR......");
                    Proveedor respuesta = methods.buscarProveedorPorIdentificacion(proveedor, identificacion);
                    if (respuesta == null)
                        System.out.println("PROVEEDOR NO ENCONTRADO");
                    else
                        System.out.println("PROVEEDOR ENCONTRADO: \n " + respuesta);
                    break;
                case 7:
                    System.out.println("-*-*---------- BUSQUEDA POR NOMBRE PRODUCTO -*-*-----------");
                    System.out.println("INGRESAR EL NOMBRE PRODUCTO: ");
                    String nombre = leer.nextLine();
                    leer.nextLine();
                    methods.ordenarProductosNombre(proveedor1.getProductos());
                    System.out.println("ORDENANDO EL NOMBRE PRODUCTO......");
                    Producto producto = methods.buscarProductoPorNombre(proveedor1.getProductos(), nombre);
                    if (producto == null)
                        System.out.println("PRODUCTO NO ENCONTRADO");
                    else
                        System.out.println("PRODUCTO ENCONTRADO: \n " + producto);
                    break;
                case 8:
                    System.out.println("-*-*---------- BUSQUEDA POR NUMERO DE SOLICITUD -*-*-----------");
                    System.out.println("INGRESAR EL NUMERO DE LA SOLICITUD: ");
                    int numero = leer.nextInt();
                    methods.ordenarSolicitudesPorNumero(solicitud);
                    System.out.println("ORDENANDO LAS SOLICITUDES......");
                    SolicitudCompra solicitudCompra = methods.buscarSolicitudesPorNumero(solicitud, numero);
                    if (solicitudCompra == null)
                        System.out.println("SOLICITUD NO ENCONTRADA");
                    else
                        System.out.println("SOLICITUD ENCONTRADA: \n " + solicitudCompra);
                    break;
                case 9:
                    methods.ordenarSolicitudesPorNumero(solicitud);
                    System.out.println("-*-*---------- SELECCIONE LA LISTA QUE SERA APROVADA/DESAPROVADA -*-*-----------");
                    for (int i = 0; i < solicitud.size(); i++) {
                        System.out.println("Solicitud n° " + (i + 1) + "\n" + solicitud.get(i));
                    }
                    int numeroSolicitud = leer.nextInt();
                    SolicitudCompra solcitudAD = methods.buscarSolicitudesPorNumero(solicitud, numeroSolicitud);
                    if (solcitudAD == null)
                        System.out.println("SOLICITUD NO ENCONTRADA");
                    else {
                        System.out.println("SOLICITUD ENCONTRADA: \n " + solcitudAD);
                        boolean aprovadaDesaprovda = true;
                        while (aprovadaDesaprovda) {
                            System.out.println("¿Qué desea hacer con la solicitud?");
                            System.out.println("1. Aprobar solicitud");
                            System.out.println("2. Rechazar solicitud");
                            int decision = leer.nextInt();

                            if (decision == 1) {
                                solcitudAD.setEstado(EstadoDeSolictud.APROVADA);
                                System.out.println("✅ Solicitud aprobada con éxito.");
                                aprovadaDesaprovda = false;
                            } else if (decision == 2) {
                                solcitudAD.setEstado(EstadoDeSolictud.RECHAZADA);
                                System.out.println("❌ Solicitud rechazada.");
                                aprovadaDesaprovda = false;
                            } else {
                                System.out.println("⚠️ Opción inválida. No se realizaron cambios.");
                            }
                        }
                    }
                    break;
                case 10:
                    methods.ordenarSolicitudesPorNumero(solicitud);
                    System.out.println("-*-*---------- SELECCIONE LA LISTA QUE DESEA VER EL TOTAL -*-*-----------");
                    for (int i = 0; i < solicitud.size(); i++) {
                        System.out.println("Solicitud n° " + (i + 1) + "\n" + solicitud.get(i));
                    }
                    int numeroSolicitud2 = leer.nextInt();
                    SolicitudCompra solcitudTotal = methods.buscarSolicitudesPorNumero(solicitud, numeroSolicitud2);
                    if (solcitudTotal == null)
                        System.out.println("⚠️ SOLICITUD NO ENCONTRADA");
                    else {
                        System.out.println("✅ SOLICITUD ENCONTRADA: \n " + solcitudTotal);
                        double totalSolicitud = solcitudTotal.calcularCostoTotal();
                        System.out.println("TOTAL DE LA SOLICITUD: $" + totalSolicitud);
                    }
                    break;
                case 11:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }
}