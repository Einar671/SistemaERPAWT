package ec.edu.ups.poo;

import ec.edu.ups.poo.clases.*;
import ec.edu.ups.poo.enums.*;

import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Proveedor proveedor1 = new Proveedor();
        List<Proveedor> proveedor = new ArrayList<>();
        List<Empleado> empleados = new ArrayList<>();
        ProductoConIva productoConIva = new ProductoConIva();
        ProductoConDescuento productoConDesc = new ProductoConDescuento();
        ProductoSinIva productoSinIva = new ProductoSinIva();
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
            System.out.println("6. Buscar proveedor por id");
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
                                    String nombreProducto = leer.next();
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
                                    String nombreProducto = leer.next();
                                    System.out.println("Ingrese el precio del producto: ");
                                    double precio = leer.nextDouble();
                                    System.out.println("Ingrese la excension: ");
                                    String excension = leer.next();
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
                                    String nombreProducto = leer.next();
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
                            continua = false; // salir del menú de registro de proveedores
                        }
                    }
                    break;

                case 2:

                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
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