package ec.edu.ups.poo;

import ec.edu.ups.poo.modelo.clases.*;
import ec.edu.ups.poo.modelo.controllers.Methods;
import ec.edu.ups.poo.modelo.enums.*;
import ec.edu.ups.poo.vista.InicioView;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Proveedor> proveedores = new ArrayList<>();
        List<Empleado> empleados = new ArrayList<>();
        List<SolicitudCompra> solicitudes = new ArrayList<>();

        InicioView inicio = new InicioView();
        inicio.mostrar();
    }
}