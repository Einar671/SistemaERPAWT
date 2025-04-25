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
        List<Producto> productos = new ArrayList<>();
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
                    int opcionprove;
                    System.out.println("-*-*------Registrar proveedor-*-*---------");
                    System.out.println("1. Registrar proveedor");
                    System.out.println("2. Salir");
                    opcionprove = leer.nextInt();
                    boolean continua=true;
                    while (continua){
                        if (opcionprove == 1) {
                            int optipo;
                            System.out.println("Digite la identificacion del proveedor: ");
                            String identificacion = leer.next();
                            System.out.println("Digite el nombre del proveedor: ");
                            String nombre = leer.next();
                            System.out.println("Digite el email del proveedor: ");
                            String email = leer.next();
                            System.out.println("Digite el telefono del proveedor: ");
                            String telefono = leer.next();
                            boolean continuarpro=true;
                            while (continuarpro) {
                                System.out.println("------------Registre los productos del proveedor--------------");
                                System.out.println("*********1. PRODUCTO CON IVA");
                                System.out.println("*********2. PRODUCTO SIN IVA");
                                System.out.println("*********3. PRODUCTO CON DESCUENTO");
                                System.out.println("*********4. SALIR DE REGISTRO DE PRODUCTO0");
                                optipo = leer.nextInt();
                                List<ProductoConIva> listaProductoConIva = new ArrayList<>();
                                List<ProductoSinIva> productoSinIvas = new ArrayList<>();
                                List<ProductoConDescuento> listaProductoConDescuento = new ArrayList<>();
                                if (optipo == 1) {
                                    String opmedida;
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
                                    opmedida = leer.next();
                                    if (opmedida.equalsIgnoreCase("a")) {
                                        productoConIva = new ProductoConIva(id, nombreProducto, precio, UnidadMedida.UNIDAD, 0.15);
                                       productos.add(productoConIva);

                                    } else if (opmedida.equalsIgnoreCase("b")) {
                                        productoConIva = new ProductoConIva(id, nombreProducto, precio, UnidadMedida.KILOGRAMO, 0.15);
                                        productos.add(productoConIva);

                                    } else if (opmedida.equalsIgnoreCase("c")) {
                                        productoConIva = new ProductoConIva(id, nombreProducto, precio, UnidadMedida.LITRO, 0.15);
                                        productos.add(productoConIva);
                                    }
                                }
                                if (optipo == 2) {
                                    String opmedida;
                                    System.out.println("Ingrese el id del producto: ");
                                    int id = leer.nextInt();
                                    System.out.println("Ingrese el nombre del producto: ");
                                    String nombreProducto = leer.next();
                                    System.out.println("Ingrese el precio del producto: ");
                                    double precio = leer.nextDouble();
                                    System.out.println("Ingrese la excension(alimento, servicios basicos, salud, educacion,transporte): ");
                                    String excension = leer.next();
                                    System.out.println("Ingrese el tipo de medida del producto");
                                    System.out.println("a. Unidad");
                                    System.out.println("b. Kilogramo");
                                    System.out.println("c. Litro");
                                    opmedida = leer.next();

                                    if (opmedida.equalsIgnoreCase("a")) {
                                        productoSinIva = new ProductoSinIva(id, nombreProducto, precio, UnidadMedida.UNIDAD, excension);
                                        productos.add(productoSinIva);

                                    } else if (opmedida.equalsIgnoreCase("b")) {
                                        productoSinIva = new ProductoSinIva(id, nombreProducto, precio, UnidadMedida.KILOGRAMO, excension);
                                        productos.add(productoSinIva);
                                    } else if (opmedida.equalsIgnoreCase("c")) {
                                        productoSinIva = new ProductoSinIva(id, nombreProducto, precio, UnidadMedida.LITRO, excension);
                                        productos.add(productoSinIva);
                                    }

                                }
                                if (optipo == 3) {
                                    String opmedida;
                                    System.out.println("Ingrese el id del producto: ");
                                    int id = leer.nextInt();
                                    System.out.println("Ingrese el nombre del producto: ");
                                    String nombreProducto = leer.next();
                                    System.out.println("Ingrese el precio del producto: ");
                                    double precio = leer.nextDouble();
                                    System.out.println("Ingrese el procentaje del descuento: ");
                                    double descuento1 = leer.nextDouble();
                                    double descuentototal = descuento1/100;
                                    System.out.println("Ingrese el tipo de medida del producto");
                                    System.out.println("a. Unidad");
                                    System.out.println("b. Kilogramo");
                                    System.out.println("c. Litro");
                                    opmedida = leer.next();
                                    if (opmedida.equalsIgnoreCase("a")) {
                                        productoConDesc= new ProductoConDescuento(id,nombreProducto,precio,UnidadMedida.UNIDAD,descuentototal);
                                        productos.add(productoConDesc);
                                    } else if (opmedida.equalsIgnoreCase("b")) {
                                        productoConDesc=new ProductoConDescuento(id,nombreProducto,precio,UnidadMedida.KILOGRAMO,descuentototal);
                                        productos.add(productoConDesc);
                                    } else if (opmedida.equalsIgnoreCase("c")) {
                                        productoConDesc=new ProductoConDescuento(id,nombreProducto,precio,UnidadMedida.LITRO,descuentototal);
                                        productos.add(productoConDesc);
                                    }
                                }
                                if (optipo == 4) {
                                    continuarpro=false;
                                }
                            }
                            proveedor1=new Proveedor(identificacion,nombre,email,productos,telefono);
                            proveedor.add(proveedor1);
                        }
                        else if (opcionprove==2){
                            continua=false;
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