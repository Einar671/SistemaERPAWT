package ec.edu.ups.poo.vista;

import ec.edu.ups.poo.modelo.clases.Proveedor;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class IngresoProovedorView {

    private static List<Proveedor> listaProveedores = new ArrayList<>();

    private Frame ventana;
    private Panel panel1;
    private Panel panel2;
    private Label iden;
    private Label nombre;
    private Label email;
    private Label telefono;
    private Button atras;
    private Button ingresarpro;
    private TextField iden1;
    private TextField nombre1;
    private TextField email1;
    private TextField telefono1;

    public void mostrar() {
        ventana = new Frame("Ingresar proveedor");
        panel1 = new Panel(new FlowLayout());
        panel2 = new Panel(new GridLayout(9, 1, 5, 5));

        iden = new Label("INGRESE IDENTIFICACION");
        nombre = new Label("INGRESE NOMBRE");
        email = new Label("INGRESE EMAIL");
        telefono = new Label("INGRESE TELEFONO");

        iden1 = new TextField();
        nombre1 = new TextField();
        email1 = new TextField();
        telefono1 = new TextField();

        ingresarpro = new Button("Ingresar productos");
        atras = new Button("Atras");

        panel2.add(iden);
        panel2.add(iden1);
        panel2.add(nombre);
        panel2.add(nombre1);
        panel2.add(email);
        panel2.add(email1);
        panel2.add(telefono);
        panel2.add(telefono1);
        panel2.add(ingresarpro);

        panel1.add(panel2);
        panel1.add(atras);
        ventana.add(panel1);

        ventana.setSize(500, 500);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        ingresarpro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identificacion = iden1.getText();
                String nombre = nombre1.getText();
                String email = email1.getText();
                String telefono = telefono1.getText();

                Proveedor proveedor = new Proveedor(identificacion, nombre, email, new ArrayList<>(), telefono);
                listaProveedores.add(proveedor);

                System.out.println("Proveedor guardado: " + proveedor);

                // Abre la ventana de productos
                IngresoProductoView ingresoProductoView= new IngresoProductoView(proveedor);
                ingresoProductoView.mostrar();

                // Oculta la ventana actual
                ventana.setVisible(false);
            }
        });

        atras.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ventana.setVisible(false);
                OpcionesView opcionesView = new OpcionesView();
                opcionesView.mostrar();
            }
        });
    }

    public static List<Proveedor> getListaProveedores() {
        return listaProveedores;
    }
}
