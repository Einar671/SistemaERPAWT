package ec.edu.ups.poo.modelo.controllers;

import ec.edu.ups.poo.modelo.clases.Producto;
import ec.edu.ups.poo.modelo.clases.Proveedor;
import ec.edu.ups.poo.modelo.clases.SolicitudCompra;

import java.util.List;

public class Methods {
    public void insertionSortIdentificacion(List<Proveedor> proveedores) {
        for (int i = 0; i < proveedores.size(); i++) {
            int minIdx = i;
            for (int j = i + 1; j < proveedores.size(); j++) {
                if (proveedores.get(j).getIdentificacion().compareTo(proveedores.get(minIdx).getIdentificacion()) < 0) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                Proveedor aux = proveedores.get(minIdx);
                proveedores.set(minIdx, proveedores.get(i));
                proveedores.set(i, aux);
            }
        }
    }

    public Proveedor buscarProveedorPorIdentificacion(List<Proveedor> proveedores, String identificacion) {
        int bajo = 0;
        int alto = proveedores.size() - 1;

        while (bajo <= alto) {
            int centro = (alto + bajo) / 2;
            String valorCentral = proveedores.get(centro).getIdentificacion();
            if (valorCentral.equalsIgnoreCase(identificacion)) {
                return proveedores.get(centro);
            }
            if (valorCentral.compareToIgnoreCase(identificacion) < 0) {
                bajo = centro + 1;
            } else {
                alto = centro - 1;
            }
        }
        return null;
    }

    public void ordenarProductosNombre(List<Producto> productos){
        for (int i = 0; i < productos.size(); i++) {
            int minIdx = i;
            for (int j = i + 1; j < productos.size(); j++) {
                if (productos.get(j).getNombreProducto().compareTo(productos.get(minIdx).getNombreProducto()) < 0) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                Producto aux = productos.get(minIdx);
                productos.set(minIdx, productos.get(i));
                productos.set(i, aux);
            }
        }
    }

    public Producto buscarProductoPorNombre(List<Producto> productos, String nombre){
        int bajo = 0;
        int alto = productos.size() - 1;

        while (bajo <= alto) {
            int centro = (alto + bajo) / 2;
            String valorCentral = productos.get(centro).getNombreProducto();
            if (valorCentral.equalsIgnoreCase(nombre)) {
                return productos.get(centro);
            }
            if (valorCentral.compareToIgnoreCase(nombre) < 0) {
                bajo = centro + 1;
            } else {
                alto = centro - 1;
            }
        }
        return null;
    }

    public void ordenarSolicitudesPorNumero(List<SolicitudCompra> solicitudes) {

        for (int i = 0; i < solicitudes.size(); i++) {
            int minIdx = i;
            for (int j = i + 1; j < solicitudes.size(); j++) {

                if (solicitudes.get(j).getId() < solicitudes.get(minIdx).getId()) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                SolicitudCompra aux = solicitudes.get(minIdx);
                solicitudes.set(minIdx, solicitudes.get(i));
                solicitudes.set(i, aux);
            }
        }
    }

    public SolicitudCompra buscarSolicitudesPorNumero(List<SolicitudCompra> solicitudes, int numero) {
        int bajo = 0;
        int alto = solicitudes.size() - 1;
        while (bajo <= alto) {
            int centro = (alto + bajo) / 2;
            int valorCentral = solicitudes.get(centro).getId();

            if (valorCentral == numero) {
                return solicitudes.get(centro);
            }
            if (valorCentral < numero) {
                bajo = centro + 1;
            } else {
                alto = centro - 1;
            }
        }
        return null;
    }

}
